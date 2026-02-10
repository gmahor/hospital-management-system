import axios, { type InternalAxiosRequestConfig } from "axios";

export const AxiosInstance = axios.create({
  baseURL: "http://localhost:9000",
});

AxiosInstance.interceptors.request.use((config: InternalAxiosRequestConfig) => {
  return config;
});
