import { AxiosInstance } from "../interceptor/AxiosInterceptor";

export const apiSpecializations = async () => {
  try {
    const resp = await AxiosInstance.get("/api/specializations");
    return resp.data.data;
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
    return resp.data.data;
  } catch (error: any) {
    if (error.response) {
      throw error.response.data;
    }
    throw error;
  }
};

export const getDoctorProfile = async (id: any) => {
  try {
    const resp = await AxiosInstance.get(`profile/doctor/${id}`);
    return resp.data.data;
  } catch (error: any) {
    if (error.response) {
      throw error.response.data;
    }
    throw error;
  }
};

export const apiUpdateDoctorDetails = async (payload: any) => {
  return await AxiosInstance.put("/profile/doctor/updateDoctorDetails", payload)
    .then((resp: any) => resp.data)
    .catch((error) => {
      if (error.response) {
        throw error.response.data;
      }
      throw error;
    });
};
