import { AxiosInstance } from "../interceptor/AxiosInterceptor";

export const registerUser = async (user: any) => {
  return AxiosInstance.post("/user/register", user)
    .then((resp: any) => resp.data)
    .catch((error) => {
      if (error.response) {
        throw error.response.data;
      }
      throw error;
    });
};

export const loginUser = async (user: any) => {
  return AxiosInstance.post("/user/login", user)
    .then((resp: any) => resp.data)
    .catch((error) => {
      if (error.response) {
        throw error.response.data;
      }
      throw error;
    });
};
