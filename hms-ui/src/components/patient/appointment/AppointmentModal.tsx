import { Button, Modal, Select, Textarea } from "@mantine/core";
import { DateTimePicker } from "@mantine/dates";
import { useForm } from "@mantine/form";
import { useSelector } from "react-redux";
import { useLoaderData } from "react-router-dom";

import { getDoctorsDropdown } from "../../../service/DoctorProfileService";
import {
  getAllAppointmentReasons,
  scheduledAppointment,
} from "../../../service/AppointmentService";
import {
  ErrorNotification,
  SuccessNotification,
} from "../../../utils/CustomNotification";

interface DoctorDropDown {
  id: number;
  name: string;
}

export default function AppointmentModal({
  opened,
  close,
}: {
  opened: boolean;
  close: () => void;
}) {
  //   const doctors = useLoaderData();
  const { doctors, appointmentReasons } = useLoaderData() as {
    doctors: { value: string; label: string }[];
    appointmentReasons: string[];
  };

  const user = useSelector((state: any) => state.user);

  const form = useForm({
    mode: "uncontrolled",
    initialValues: {
      patientId: user?.profileId,
      doctorId: "",
      appointmentTime: new Date(),
      reason: "",
      notes: "",
    },

    validate: {
      doctorId: (value) => (value ? null : "Doctor is required"),
      appointmentTime: (value) =>
        value ? null : "Appointment time is required",
      reason: (value) => (value ? null : "Reason is required"),
    },
  });

  const handleSubmit = async (values: typeof form.values) => {
    scheduledAppointment(values)
      .then((data) => {
        close();
        SuccessNotification(
          "Appointment Scheduled !!",
          data.data,
          2000,
          "top-center",
        );
      })
      .catch((errorPayload) => {
        ErrorNotification(
          "Appointment Scheduling Failed!!",
          errorPayload,
          2000,
          "top-center",
        );
      });
  };

  return (
    <>
      <Modal
        opened={opened}
        onClose={close}
        size="lg"
        title={
          <div className="text-xl font-semibold text-primary-500">
            Schedule Appointment
          </div>
        }
        centered
      >
        <form onSubmit={form.onSubmit(handleSubmit)}>
          <div className="grid grid-cols-1 gap-5">
            <Select
              data={doctors}
              label="Doctor"
              placeholder="Select Doctor"
              withAsterisk
              key={form.key("doctorId")}
              {...form.getInputProps("doctorId")}
            />
            <DateTimePicker
              label="Pick date and time"
              placeholder="Pick date and time"
              withAsterisk
              key={form.key("appointmentTime")}
              {...form.getInputProps("appointmentTime")}
            />
            <Select
              data={appointmentReasons}
              label="Reason for appointment"
              placeholder="Enter Reason for appointment"
              withAsterisk
              key={form.key("reason")}
              {...form.getInputProps("reason")}
            />
            <Textarea
              label="Notes"
              placeholder="Enter Any Additional Notes..."
              key={form.key("notes")}
              {...form.getInputProps("notes")}
            />
            <Button
              type="submit"
              className="bg-primary-500 hover:bg-primary-600"
              fullWidth
            >
              Schedule
            </Button>
          </div>
        </form>
      </Modal>
    </>
  );
}

async function getAllDoctors() {
  const doctors = await getDoctorsDropdown();
  return doctors.map((doctor: DoctorDropDown) => ({
    value: String(doctor.id),
    label: doctor.name,
  }));
}

async function allAppointmentReasons() {
  return await getAllAppointmentReasons();
}

export const doctorsAndAppointmentReasons = async () => {
  const [doctors, appointmentReasons] = await Promise.all([
    getAllDoctors(),
    allAppointmentReasons(),
  ]);

  return {
    doctors,
    appointmentReasons,
  };
};
