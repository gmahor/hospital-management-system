import { AxiosInstance } from "../interceptor/AxiosInterceptor";

export const apiSpecializations = async () => {
  try {
    const resp = await AxiosInstance.get("/api/specializations", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
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
    const resp = await AxiosInstance.get("/api/departments", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    });
    return resp.data;
  } catch (error: any) {
    if (error.response) {
      throw error.response.data;
    }
    throw error;
  }
};
