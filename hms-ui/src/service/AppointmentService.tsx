import { isAxiosError } from "axios";
import { AxiosInstance } from "../interceptor/AxiosInterceptor";

export const getAllAppointmentReasons = async () => {
  try {
    const resp = await AxiosInstance.get(
      `appointment/getAllAppointmentReasons`,
    );
    return resp.data.data;
  } catch (error: unknown) {
    if (isAxiosError(error) && error.response) {
      throw error.response.data;
    }
    throw error;
  }
};


export const scheduledAppointment = async (appointment:any) => {
  return AxiosInstance.post("/appointment/scheduledAppointment", appointment)
    .then((resp: any) => resp.data)
    .catch((error) => {
      if (error.response) {
        throw error.response.data;
      }
      throw error;
    });
};
