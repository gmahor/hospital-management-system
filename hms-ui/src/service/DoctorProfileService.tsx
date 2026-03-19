import { AxiosInstance } from "../interceptor/AxiosInterceptor";

export const apiSpecializations = async () => {
  try {
    const resp = await AxiosInstance.get("/api/specializations");
    return resp.data;
  } catch (error: any) {
    if (error.response) {
      throw error.response.data;
    }
    throw error;
  }
};

export const apiDepartments = async () => {
  try {
    const resp = await AxiosInstance.get("/api/departments");
    return resp.data;
  } catch (error: any) {
    if (error.response) {
      throw error.response.data;
    }
    throw error;
  }
};
