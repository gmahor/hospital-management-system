// import axios, { type InternalAxiosRequestConfig } from "axios";

// export const AxiosInstance = axios.create({
//   baseURL: "http://localhost:9000",
// });

// AxiosInstance.interceptors.request.use((config: InternalAxiosRequestConfig) => {
//   const accessToken = localStorage.getItem("accessToken");
//   if (accessToken && config.headers) {
//     config.headers.Authorization = `Bearer ${accessToken}`;
//   }
//   return config;
// });
import axios from "axios";
import Store from "../store";
import { removeTokens, setTokens } from "../slices/JwtSlice";

export const AxiosInstance = axios.create({
  baseURL: "http://localhost:9000",
});

// Separate axios client for refresh (no interceptors)
const RefreshClient = axios.create({
  baseURL: "http://localhost:9000",
});

// Decode JWT payload
function parseJwt(token: string) {
  try {
    const base64Url = token.split(".")[1];
    const base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
    return JSON.parse(window.atob(base64));
  } catch {
    return null;
  }
}

let isRefreshing = false;
let failedQueue: any[] = [];

const processQueue = (error: any, token: string | null = null) => {
  failedQueue.forEach((prom) => {
    if (error) {
      prom.reject(error);
    } else {
      prom.resolve(token);
    }
  });
  failedQueue = [];
};

async function refreshAccessToken() {
  const refreshToken = localStorage.getItem("refreshToken");
  if (!refreshToken) return null;
  try {
    const res = await RefreshClient.post("/user/refreshToken", null, {
      headers: { Authorization: `Bearer ${refreshToken}` },
    });
    const newAccessToken = res.data.data;
    Store.dispatch(setTokens({ accessToken: newAccessToken, refreshToken }));
    return newAccessToken;
  } catch {
    Store.dispatch(removeTokens());
    return null;
  }
}

// Request interceptor: proactive refresh
AxiosInstance.interceptors.request.use(async (config) => {
  let accessToken = localStorage.getItem("accessToken");

  if (accessToken) {
    const payload = parseJwt(accessToken);
    const expiry = payload?.exp * 1000;
    const now = Date.now();

    // Refresh if less than 2 min left
    if (expiry && expiry - now < 120 * 1000 && !isRefreshing) {
      console.log("token is about to expire in <2 minutes, refreshing...");

      isRefreshing = true;
      accessToken = await refreshAccessToken();
      isRefreshing = false;
    }

    if (accessToken && config.headers) {
      config.headers.Authorization = `Bearer ${accessToken}`;
    }
  }

  return config;
});

// Response interceptor: reactive refresh when refresh token is expired in that case this method is working but there is some issue will fix later
AxiosInstance.interceptors.response.use(
  (res) => res,
  async (error) => {
    const originalRequest = error.config;
    if (error.response?.status === 401 && !originalRequest._retry) {
      if (isRefreshing) {
        return new Promise(function (resolve, reject) {
          failedQueue.push({ resolve, reject });
        })
          .then((token) => {
            originalRequest.headers.Authorization = `Bearer ${token}`;
            return AxiosInstance(originalRequest);
          })
          .catch((err) => Promise.reject(err));
      }

      originalRequest._retry = true;
      isRefreshing = true;

      const newToken = await refreshAccessToken();
      isRefreshing = false;

      if (newToken) {
        processQueue(null, newToken);
        originalRequest.headers.Authorization = `Bearer ${newToken}`;
        return AxiosInstance(originalRequest);
      } else {
        processQueue(new Error("Refresh failed"), null);
      }
    }

    return Promise.reject(error);
  },
);
