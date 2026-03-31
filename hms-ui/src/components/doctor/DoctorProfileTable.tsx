import { Button, Select, Table, TextInput } from "@mantine/core";
import { DatePickerInput } from "@mantine/dates";
import { hasLength, useForm } from "@mantine/form";
import {
  IconCalendar,
  IconCheck,
  IconFileDescription,
  IconHome,
  IconLicense,
  IconPhone,
  IconUserCog,
} from "@tabler/icons-react";
import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { useLoaderData } from "react-router-dom";
import {
  apiDepartments,
  apiSpecializations,
  apiUpdateDoctorDetails,
} from "../../service/DoctorProfileService";
import { setLoading } from "../../slices/LoadingSlice";
import {
  ErrorNotification,
  SuccessNotification,
} from "../../utils/CustomNotification";
import { formatDate } from "../../utils/DateUtil";
import type { DoctorProfile } from "./DoctorPersonalInfo";

type FormValues = {
  dob: Date | null;
  phone: string;
  address: string;
  licenseNo: string;
  specialization: string;
  department: string;
  totalExperience: string;
};

export const DoctorProfileTable = ({
  isEdit,
  setIsEdit,
  doctorDetails,
  refetch,
}: {
  isEdit: boolean;
  setIsEdit: React.Dispatch<React.SetStateAction<boolean>>;
  doctorDetails: DoctorProfile | null;
  refetch: any;
}) => {
  const dispatch = useDispatch();
  const [searchSpecializationValue, setSearchSpecializationValue] =
    useState("");
  const [searchDepartmentValue, setSearchDepartmentValue] = useState("");
  const { specializations, departments } = useLoaderData() as {
    specializations: any[];
    departments: any[];
  };

  const form = useForm<FormValues>({
    mode: "uncontrolled",
    initialValues: {
      dob: null,
      phone: "",
      address: "",
      licenseNo: "",
      specialization: "",
      department: "",
      totalExperience: "",
    },
    validate: {
      dob: (value) => (value ? null : "Please enter your date of birth"),
      phone: hasLength({ min: 10, max: 10 }, "Phone number must be 10 digits"),
      address: (value) => (value ? null : "Please enter you address"),
      licenseNo: (value) => (value ? null : "Please enter your license no"),
      specialization: (value) =>
        value ? null : "Please enter your specialization",
      department: (value) => (value ? null : "Please enter your department"),
      totalExperience: (value) =>
        value ? null : "Please enter your total experience",
    },
  });

  useEffect(() => {
    if (doctorDetails) {
      form.setValues({
        dob: doctorDetails.dob ? new Date(doctorDetails.dob) : null,
        phone: doctorDetails.phone || "",
        address: doctorDetails.address || "",
        licenseNo: doctorDetails.licenseNo || "",
        specialization: doctorDetails.specialization || "",
        department: doctorDetails.department || "",
        totalExperience: doctorDetails.totalExperience || "",
      });
    }
  }, [doctorDetails]);

  const handleUpdateProfile = async (values: typeof form.values) => {
    dispatch(setLoading(true));
    const payload: any = {
      id: doctorDetails?.id || 0,
      dob: new Date(values.dob).toISOString().split("T")[0],
      phone: values.phone,
      address: values.address,
      licenseNo: values.licenseNo,
      specialization: values.specialization,
      department: values.department,
      totalExperience: values.totalExperience,
    };

    apiUpdateDoctorDetails(payload)
      .then((resp) => {
        if (resp?.statusCode === 200 && resp?.isSuccess) {
          SuccessNotification(
            "Update Profile",
            resp?.message,
            2000,
            "top-center",
          );
        }
        setIsEdit(false);
        refetch();
      })
      .catch((err) => {
        ErrorNotification(
          "Update Profile",
          err.errorMessage,
          2000,
          "top-center",
        );
      })
      .finally(() => {
        dispatch(setLoading(false));
      });
  };

  return (
    <>
      <form onSubmit={form.onSubmit(handleUpdateProfile)}>
        <Table variant="vertical" layout="fixed" withTableBorder>
          <Table.Tbody>
            <Table.Tr>
              <Table.Th className="bg-neutral-150! font-semibold! text-xl">
                Date of Birth
              </Table.Th>
              <Table.Td>
                {isEdit ? (
                  <DatePickerInput
                    leftSection={<IconCalendar size={18} stroke={1.5} />}
                    className="my-2 mx-2"
                    placeholder="Enter Dob..."
                    {...form.getInputProps("dob")}
                    key={form.key("dob")}
                  />
                ) : doctorDetails?.dob ? (
                  formatDate(doctorDetails.dob)
                ) : (
                  "--"
                )}
              </Table.Td>
            </Table.Tr>
            <Table.Tr>
              <Table.Th className="bg-neutral-150! font-semibold! text-xl">
                Phone
              </Table.Th>
              <Table.Td>
                {isEdit ? (
                  <TextInput
                    leftSection={<IconPhone size={18} stroke={1.5} />}
                    placeholder="Enter Phone Number..."
                    className="my-2 mx-2"
                    {...form.getInputProps("phone")}
                    key={form.key("phone")}
                  />
                ) : doctorDetails?.phone ? (
                  doctorDetails?.phone
                ) : (
                  "--"
                )}
              </Table.Td>
            </Table.Tr>
            <Table.Tr>
              <Table.Th className="bg-neutral-150! font-semibold! text-xl">
                Address
              </Table.Th>
              <Table.Td>
                {isEdit ? (
                  <TextInput
                    leftSection={<IconHome size={18} stroke={1.5} />}
                    className="my-2 mx-2"
                    placeholder="Enter Address. . ."
                    {...form.getInputProps("address")}
                    key={form.key("address")}
                  />
                ) : doctorDetails?.address ? (
                  doctorDetails?.address
                ) : (
                  "--"
                )}
              </Table.Td>
            </Table.Tr>
            <Table.Tr>
              <Table.Th className="bg-neutral-150! font-semibold! text-xl">
                License No
              </Table.Th>
              <Table.Td>
                {isEdit ? (
                  <TextInput
                    leftSection={<IconLicense size={18} stroke={1.5} />}
                    className="my-2 mx-2"
                    placeholder="Enter License No. . ."
                    {...form.getInputProps("licenseNo")}
                    key={form.key("licenseNo")}
                  />
                ) : doctorDetails?.licenseNo ? (
                  doctorDetails?.licenseNo
                ) : (
                  "--"
                )}
              </Table.Td>
            </Table.Tr>
            <Table.Tr>
              <Table.Th className="bg-neutral-150! font-semibold! text-xl">
                Specialization
              </Table.Th>
              <Table.Td>
                {isEdit ? (
                  <Select
                    leftSection={<IconFileDescription size={18} stroke={1.5} />}
                    className="my-2 mx-2"
                    placeholder="Select Specialization. . ."
                    searchable
                    searchValue={searchSpecializationValue}
                    onSearchChange={setSearchSpecializationValue}
                    data={specializations}
                    value={form.values.specialization}
                    onChange={(val) =>
                      form.setFieldValue("specialization", val || "")
                    }
                    key={form.key("specialization")}
                  />
                ) : doctorDetails?.specialization ? (
                  doctorDetails.specialization
                ) : (
                  "--"
                )}
              </Table.Td>
            </Table.Tr>
            <Table.Tr>
              <Table.Th className="bg-neutral-150! font-semibold! text-xl">
                Department
              </Table.Th>
              <Table.Td>
                {isEdit ? (
                  <Select
                    leftSection={<IconHome size={18} stroke={1.5} />}
                    className="my-2 mx-2"
                    placeholder="Select Department..."
                    searchable
                    searchValue={searchDepartmentValue}
                    onSearchChange={setSearchDepartmentValue}
                    data={departments}
                    value={form.values.department}
                    onChange={(val) =>
                      form.setFieldValue("department", val || "")
                    }
                    key={form.key("department")}
                  />
                ) : doctorDetails?.department ? (
                  doctorDetails.department
                ) : (
                  "--"
                )}
              </Table.Td>
            </Table.Tr>
            <Table.Tr>
              <Table.Th className="bg-neutral-150! font-semibold! text-xl">
                Total Experience
              </Table.Th>
              <Table.Td>
                {isEdit ? (
                  <TextInput
                    leftSection={<IconUserCog size={18} stroke={1.5} />}
                    className="my-2 mx-2"
                    placeholder="Enter Total Experience. . ."
                    {...form.getInputProps("totalExperience")}
                    key={form.key("totalExperience")}
                  />
                ) : doctorDetails?.totalExperience ? (
                  doctorDetails?.totalExperience
                ) : (
                  "--"
                )}
              </Table.Td>
            </Table.Tr>
          </Table.Tbody>
        </Table>
        {isEdit && (
          <div className="flex justify-center my-2">
            <Button
              size="xs"
              variant="filled"
              color="primary"
              leftSection={<IconCheck stroke={1.5} />}
              type="submit"
            >
              Submit
            </Button>
          </div>
        )}
      </form>
    </>
  );
};

export async function ProfileLoaderApis() {
  const [specializations, departments] = await Promise.all([
    apiSpecializations(),
    apiDepartments(),
  ]);

  return { specializations, departments };
}
