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
  TextInput,
} from "@mantine/core";
import { DatePickerInput } from "@mantine/dates";
import { useDisclosure } from "@mantine/hooks";
import { IconCheck, IconEdit, IconFile } from "@tabler/icons-react";
import { useState } from "react";
import { useSelector } from "react-redux";
import { useLoaderData } from "react-router-dom";
import boy from "../../assets/boy.png";
import { apiDepartments, apiSpecializations } from "../../service/DoctorServce";

export const DoctorProfile = () => {
  const user: any = useSelector((state: any) => state.user);
  const [isEdit, setIsEdit] = useState(false);
  const [opened, { open, close }] = useDisclosure(false);
  const [searchValue, setSearchValue] = useState("");
  const { specializations, departments } = useLoaderData() as {
    specializations: any[];
    departments: any[];
  };

  const handleUpdateProfile = () => {};

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
                    // value={value}
                    // onChange={setValue}
                  />
                ) : (
                  <Table.Td>1996-07-12</Table.Td>
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
                  <Table.Td>+91 8447727798</Table.Td>
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
                  <Table.Td>123, Main Street, Mumbai, India</Table.Td>
                )}
              </Table.Tr>
              <Table.Tr>
                <Table.Th className="bg-neutral-150! font-semibold! text-xl">
                  License No
                </Table.Th>
                {isEdit ? (
                  <TextInput
                    className="my-2 mx-2"
                    placeholder="Enter License No. . ."
                  />
                ) : (
                  <Table.Td>987456321147852</Table.Td>
                )}
              </Table.Tr>
              <Table.Tr>
                <Table.Th className="bg-neutral-150! font-semibold! text-xl">
                  Specialization
                </Table.Th>
                {isEdit ? (
                  <Select
                    className="my-2 mx-2"
                    placeholder="Select Specialization. . ."
                    searchable
                    searchValue={searchValue}
                    onSearchChange={setSearchValue}
                    data={specializations}
                  />
                ) : (
                  <Table.Td>Cardiology</Table.Td>
                )}
              </Table.Tr>
              <Table.Tr>
                <Table.Th className="bg-neutral-150! font-semibold! text-xl">
                  Department
                </Table.Th>
                 {isEdit ? (
                  <Select
                    className="my-2 mx-2"
                    placeholder="Select Department. . ."
                    searchable
                    searchValue={searchValue}
                    onSearchChange={setSearchValue}
                    data={departments}
                  />
                ) : (
                  <Table.Td>Neurologist</Table.Td>
                )}
             
              </Table.Tr>
              <Table.Tr>
                <Table.Th className="bg-neutral-150! font-semibold! text-xl">
                  Total Experience
                </Table.Th>
                {isEdit ? (
                  <NumberInput
                    placeholder="Enter Total Experience. . ."
                    className="my-2 mx-2"
                    min={1}
                    max={2}
                    hideControls
                    clampBehavior="strict"
                  />
                ) : (
                  <Table.Td>5 Year</Table.Td>
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

// export async function Specializations() {
//   const specializations = await apiSpecializations();
//   return specializations;
// }

export async function ProfileLoaderApis() {
  const [specializations, departments] = await Promise.all([
    apiSpecializations(),
    apiDepartments(),
  ]);

  return { specializations, departments };
}

