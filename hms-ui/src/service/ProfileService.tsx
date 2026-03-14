import { AxiosInstance } from "../interceptor/AxiosInterceptor";

export const apiBloodGroups = async () => {
  try {
    const resp = await AxiosInstance.get("/api/bloodGroups", {
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
