import { Avatar, Button, Divider, FileInput, Modal } from "@mantine/core";
import { hasLength, useForm } from "@mantine/form";
import { useDisclosure } from "@mantine/hooks";
import { IconEdit, IconFile } from "@tabler/icons-react";
import { useEffect, useState } from "react";
import { useQuery } from "react-query";
import { useSelector } from "react-redux";
import boy from "../../assets/boy.png";
import {
  apiUpdateProfile,
  getProfile,
} from "../../service/PatientProfileService";
import {
  ErrorNotification,
  SuccessNotification,
} from "../../utils/CustomNotification";
import { ProfilePersonalInfo } from "./ProfilePersonalInfo";

export interface PatientProfile {
  id: number;
  name: string;
  email: string;
  dob: string | null;
  phone: string | null;
  address: string | null;
  aadhaarNo: string | null;
  bloodGroup: string | null;
  allergies: string | null;
  chronicDisease: string | null;
}

type FormValues = {
  dob: Date | null;
  phone: string;
  address: string;
  aadhaarNo: string;
  bloodGroup: string;
  allergies: string;
  chronicDisease: string;
};

export const Profile = () => {
  const user: any = useSelector((state: any) => state.user);
  const [isEdit, setIsEdit] = useState(false);
  const [opened, { open, close }] = useDisclosure(false);

  const {
    data: profileDetails,
    isLoading,
    error,
    refetch,
  } = useQuery({
    queryKey: ["profile", user?.profileId],
    queryFn: async () => {
      const resp = await getProfile(user?.profileId);
      return {
        ...resp,
        dob: resp.dob ? new Date(resp.dob) : null,
      };
    },
    enabled: !!user?.profileId,
  });

  const form = useForm<FormValues>({
    mode: "uncontrolled",
    initialValues: {
      dob: null,
      phone: "",
      address: "",
      aadhaarNo: "",
      bloodGroup: "",
      allergies: "",
      chronicDisease: "",
    },
    validate: {
      dob: (value) => (value ? null : "Please enter your date of birth"),
      phone: hasLength({ min: 10, max: 10 }, "Phone number must be 10 digits"),
      aadhaarNo: hasLength(
        { min: 12, max: 12 },
        "Aadhar number must be 12 digits",
      ),
      bloodGroup: (value) => (value ? null : "Please select a blood group"),
      allergies: (value) =>
        value.trim().length > 0 ? null : "Please enter at least one allergy",
      chronicDisease: (value) =>
        value.trim().length > 0
          ? null
          : "Please enter at least one chronic disease",
    },
  });

  useEffect(() => {
    if (profileDetails) {
      form.setValues({
        dob: profileDetails.dob ? new Date(profileDetails.dob) : null,
        phone: profileDetails.phone || "",
        address: profileDetails.address || "",
        aadhaarNo: profileDetails.aadhaarNo || "",
        bloodGroup: profileDetails.bloodGroup || "",
        allergies: profileDetails.allergies || "",
        chronicDisease: profileDetails.chronicDisease || "",
      });
    }
  }, [profileDetails]);

  const handleUpdateProfile = async (values: typeof form.values) => {
    const payload: any = {
      id: profileDetails?.id || 0,
      dob: values.dob,
      phone: values.phone,
      address: values.address,
      aadhaarNo: values.aadhaarNo,
      bloodGroup: values.bloodGroup,
      allergies: values.allergies,
      chronicDisease: values.chronicDisease,
    };

    apiUpdateProfile(payload)
      .then((data) => {
        if (data?.statusCode === 200 && data?.isSuccess) {
          SuccessNotification(
            "Update Profile",
            data?.message,
            2000,
            "top-center",
          );
        }
        setIsEdit(false);
        refetch();
      })
      .catch((error) => {
        ErrorNotification(
          "Update Profile",
          error.errorMessage,
          2000,
          "top-center",
        );
      });
  };

  if (isLoading) return <div>Loading profile...</div>;
  if (error) return <div>Failed to load profile</div>;

  return (
    <div className="p-10">
      <div className="flex justify-between items-start">
        <div className="flex gap-2 items-center">
          <div className="flex flex-col items-center gap-3">
            <Avatar variant="filled" src={boy} size={150} alt="it's me" />
            {isEdit && (
              <Button size="sm" variant="filled" onClick={open}>
                Upload
              </Button>
            )}
          </div>
          <div className="flex flex-col gap-3">
            <div className="text-3xl font-medium text-neutral-900">
              {user.username}
            </div>
            <div className="text-xl text-neutral-700">{user.email}</div>
          </div>
        </div>
        {!isEdit ? (
          <Button
            size="xs"
            variant="filled"
            color="red"
            leftSection={<IconEdit stroke={1.5} />}
            onClick={() => setIsEdit(true)}
          >
            Edit
          </Button>
        ) : (
          <Button
            size="sm"
            variant="filled"
            color="red"
            onClick={() => setIsEdit(false)}
          >
            x
          </Button>
        )}
      </div>

      <Divider my="xl" />

      <ProfilePersonalInfo
        isEdit={isEdit}
        form={form}
        profileDetails={profileDetails}
        handleUpdateProfile={handleUpdateProfile}
      />

      <Modal
        opened={opened}
        onClose={close}
        title={
          <span className="text-xl font-medium">Upload Profile Picture</span>
        }
      >
        <FileInput
          leftSection={<IconFile size={18} stroke={1.5} />}
          placeholder="Select file"
          leftSectionPointerEvents="none"
        />
        <div className="flex my-2 justify-center">
          <Button>Submit</Button>
        </div>
      </Modal>
    </div>
  );
};
