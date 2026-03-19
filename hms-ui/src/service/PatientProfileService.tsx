import { AxiosInstance } from "../interceptor/AxiosInterceptor";

export const apiBloodGroups = async () => {
  try {
    const resp = await AxiosInstance.get("/api/bloodGroups");
    return resp.data;
  } catch (error: any) {
    if (error.response) {
      throw error.response.data;
    }
    throw error;
  }
};

export const getProfile = async (id: any) => {
  try {
    const resp = await AxiosInstance.get(`profile/patient/${id}`);
    return resp.data;
  } catch (error: any) {
    if (error.response) {
      throw error.response.data;
    }
    throw error;
  }
};
