import {
  Avatar,
  Button,
  Card,
  Divider,
  FileInput,
  Modal,
  NumberInput,
  Select,
  Table,
  TagsInput,
  TextInput,
} from "@mantine/core";
import { DatePickerInput } from "@mantine/dates";
import { useDisclosure } from "@mantine/hooks";
import { IconCheck, IconEdit, IconFile } from "@tabler/icons-react";
import { useState } from "react";
import { useQuery } from "react-query";
import { useSelector } from "react-redux";
import { useLoaderData } from "react-router-dom";
import boy from "../../assets/boy.png";
import {
  apiBloodGroups,
  getProfile,
} from "../../service/PatientProfileService";

export interface PatientProfile {
  id: number;
  name: string;
  email: string;
  dob: string | null;
  phone: string | null;
  address: string | null;
  aadhaarNo: string | null;
  bloodGroup: string | null;
}

export const Profile = () => {
  const user: any = useSelector((state: any) => state.user);
  const [isEdit, setIsEdit] = useState(false);
  const [searchValue, setSearchValue] = useState("");
  // const [value, setValue] = useState<string | null>(null);
  const [opened, { open, close }] = useDisclosure(false);
  const bloodGroupsData = useLoaderData();

  const handleUpdateProfile = () => {};

  const {
    data: profileDetails,
    isLoading,
    error,
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
            onClick={() => (isEdit ? setIsEdit(false) : setIsEdit(true))}
          >
            Edit
          </Button>
        ) : (
          <Button
            size="s"
            variant="filled"
            color="red"
            onClick={() => (isEdit ? setIsEdit(false) : setIsEdit(true))}
          >
            x
          </Button>
        )}
      </div>
      <Divider my="xl" />
      <Card shadow="sm" padding="lg" radius="md" withBorder>
        <div>
          <div className="my-4 text-2xl font-medium text-neutral-900 underline">
            Personal Information
          </div>
          <Table variant="vertical" layout="fixed" withTableBorder>
            <Table.Tbody>
              <Table.Tr>
                <Table.Th className="bg-neutral-150! font-semibold! text-xl">
                  Date of Birth
                </Table.Th>
                {isEdit ? (
                  <DatePickerInput
                    className="my-2 mx-2"
                    placeholder="Enter Dob. . ."
                    value={profileDetails?.dob}
                    // onChange={setValue}
                  />
                ) : (
                  <Table.Td>
                    {profileDetails?.dob
                      ? profileDetails.dob.toLocaleDateString() // or use date-fns/moment for custom format
                      : ""}
                  </Table.Td>
                )}
              </Table.Tr>
              <Table.Tr>
                <Table.Th className="bg-neutral-150! font-semibold! text-xl">
                  Phone
                </Table.Th>
                {isEdit ? (
                  <NumberInput
                    placeholder="Enter Phone Number. . ."
                    className="my-2 mx-2"
                    min={1}
                    max={10}
                    hideControls
                    clampBehavior="strict"
                  />
                ) : (
                  <Table.Td>{profileDetails?.phone}</Table.Td>
                )}
              </Table.Tr>
              <Table.Tr>
                <Table.Th className="bg-neutral-150! font-semibold! text-xl">
                  Address
                </Table.Th>
                {isEdit ? (
                  <TextInput
                    className="my-2 mx-2"
                    placeholder="Enter Address. . ."
                  />
                ) : (
                  <Table.Td>{profileDetails?.address}</Table.Td>
                )}
              </Table.Tr>
              <Table.Tr>
                <Table.Th className="bg-neutral-150! font-semibold! text-xl">
                  Aadhar No
                </Table.Th>
                {isEdit ? (
                  <NumberInput
                    className="my-2 mx-2"
                    placeholder="Enter Aadhar No. . ."
                    hideControls
                    clampBehavior="strict"
                  />
                ) : (
                  <Table.Td>{profileDetails?.aadhaarNo}</Table.Td>
                )}
              </Table.Tr>
              <Table.Tr>
                <Table.Th className="bg-neutral-150! font-semibold! text-xl">
                  Blood Group
                </Table.Th>
                {isEdit ? (
                  <Select
                    className="my-2 mx-2"
                    placeholder="Select Blood Group. . ."
                    searchable
                    searchValue={searchValue}
                    onSearchChange={setSearchValue}
                    data={bloodGroupsData}
                  />
                ) : (
                  <Table.Td>O+</Table.Td>
                )}
              </Table.Tr>
              <Table.Tr>
                <Table.Th className="bg-neutral-150! font-semibold! text-xl">
                  Allergies
                </Table.Th>
                {isEdit ? (
                  <TagsInput
                    className="my-2 mx-2"
                    placeholder="Enter Allergies. . ."
                  />
                ) : (
                  <Table.Td>{profileDetails?.allergies}</Table.Td>
                )}
              </Table.Tr>
              <Table.Tr>
                <Table.Th className="bg-neutral-150! font-semibold! text-xl">
                  Chronic Disease
                </Table.Th>
                {isEdit ? (
                  <TagsInput
                    className="my-2 mx-2"
                    placeholder="Enter Chronic Disease. . ."
                  />
                ) : (
                  <Table.Td>{profileDetails?.chronicDisease}</Table.Td>
                )}
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
                onClick={handleUpdateProfile}
              >
                Submit
              </Button>
            </div>
          )}
        </div>
      </Card>
      <Modal
        opened={opened}
        onClose={close}
        title={
          <span className="text-xl font-medium">Upload Profile Picture</span>
        }
      >
        <FileInput
          leftSection={<IconFile size={18} stroke={1.5} />}
          // label="Attach your CV"
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

export async function bloodGroups() {
  const bloodGroups = await apiBloodGroups();
  return bloodGroups;
}

// export async function PateintProfileLoader() {
//   const [bloodGroups, profileDetail] = await Promise.all([
//     apiBloodGroups(),
//     getProfile(),
//   ]);
//   return { bloodGroups, profileDetail };
// }
