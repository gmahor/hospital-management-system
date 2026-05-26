import { isAxiosError } from "axios";
import { AxiosInstance } from "../interceptor/AxiosInterceptor";

export const apiSpecializations = async () => {
  try {
    const resp = await AxiosInstance.get("/api/specializations");
    return resp.data.data;
  } catch (error: unknown) {
    if (isAxiosError(error) && error.response) {
      throw error.response.data;
    }
    throw error;
  }
};

export const apiDepartments = async () => {
  try {
    const resp = await AxiosInstance.get("/api/departments");
    return resp.data.data;
  } catch (error: unknown) {
    if (isAxiosError(error) && error.response) {
      throw error.response.data;
    }
    throw error;
  }
};

export const getDoctorProfile = async (id: string | number) => {
  try {
    const resp = await AxiosInstance.get(`profile/doctor/${id}`);
    return resp.data.data;
  } catch (error: unknown) {
    if (isAxiosError(error) && error.response) {
      throw error.response.data;
    }
    throw error;
  }
};

export const apiUpdateDoctorDetails = async (payload: unknown) => {
  return await AxiosInstance.put("/profile/doctor/updateDoctorDetails", payload)
    .then((resp) => resp.data)
    .catch((error: unknown) => {
      if (isAxiosError(error) && error.response) {
        throw error.response.data;
      }
      throw error;
    });
};

export const getDoctorsDropdown = async () => {
  try {
    const resp = await AxiosInstance.get(`profile/doctor/getDoctorsDropdown`);
    return resp.data.data;
  } catch (error: unknown) {
    if (isAxiosError(error) && error.response) {
      throw error.response.data;
    }
    throw error;
  }
};
