import { Select, Table, TagsInput, TextInput } from "@mantine/core";
import { DatePickerInput } from "@mantine/dates";
import {
  IconCalendar,
  IconDroplet,
  IconHome,
  IconId,
  IconMedicalCrossOff,
  IconPhone,
  IconVirus,
} from "@tabler/icons-react";
import { useState } from "react";
import { useLoaderData } from "react-router-dom";
import { apiBloodGroups } from "../../service/PatientProfileService";
import { formatDate } from "../../utils/DateUtil";
import type { PatientProfile } from "./Profile";

export const ProfileTable = ({
  isEdit,
  form,
  profileDetails,
}: {
  isEdit: boolean;
  form: any;
  profileDetails: PatientProfile | null;
}) => {
  const [searchValue, setSearchValue] = useState("");
  const bloodGroupsData = useLoaderData();
  return (
    <Table
      className="text-center"
      variant="vertical"
      layout="fixed"
      withTableBorder
    >
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
            ) : profileDetails?.dob ? (
              formatDate(profileDetails.dob)
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
            ) : profileDetails?.phone ? (
              profileDetails?.phone
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
            ) : profileDetails?.address ? (
              profileDetails?.address
            ) : (
              "--"
            )}
          </Table.Td>
        </Table.Tr>
        <Table.Tr>
          <Table.Th className="bg-neutral-150! font-semibold! text-xl">
            Aadhar No
          </Table.Th>
          <Table.Td>
            {isEdit ? (
              <TextInput
                leftSection={<IconId size={18} stroke={1.5} />}
                className="my-2 mx-2"
                placeholder="Enter Aadhar No. . ."
                {...form.getInputProps("aadhaarNo")}
                key={form.key("aadhaarNo")}
              />
            ) : profileDetails?.aadhaarNo ? (
              profileDetails?.aadhaarNo
            ) : (
              "--"
            )}
          </Table.Td>
        </Table.Tr>
        <Table.Tr>
          <Table.Th className="bg-neutral-150! font-semibold! text-xl">
            Blood Group
          </Table.Th>
          <Table.Td>
            {isEdit ? (
              <Select
                leftSection={<IconDroplet size={18} stroke={1.5} />}
                className="my-2 mx-2"
                placeholder="Select Blood Group..."
                searchable
                searchValue={searchValue}
                onSearchChange={setSearchValue}
                data={bloodGroupsData}
                value={form.values.bloodGroup}
                onChange={(val) => form.setFieldValue("bloodGroup", val || "")}
                key={form.key("bloodGroup")}
              />
            ) : profileDetails?.bloodGroup ? (
              profileDetails.bloodGroup
            ) : (
              "--"
            )}
          </Table.Td>
        </Table.Tr>
        <Table.Tr>
          <Table.Th className="bg-neutral-150! font-semibold! text-xl">
            Allergies
          </Table.Th>
          <Table.Td>
            {isEdit ? (
              <TagsInput
                leftSection={<IconVirus size={18} stroke={1.5} />}
                className="my-2 mx-2"
                placeholder="Enter Allergies..."
                value={form.values.allergies.split(",").filter(Boolean)}
                onChange={(tags) =>
                  form.setFieldValue("allergies", tags.join(","))
                }
              />
            ) : profileDetails?.allergies ? (
              profileDetails.allergies
            ) : (
              "--"
            )}
          </Table.Td>
        </Table.Tr>
        <Table.Tr>
          <Table.Th className="bg-neutral-150! font-semibold! text-xl">
            Chronic Disease
          </Table.Th>
          <Table.Td>
            {isEdit ? (
              <TagsInput
                leftSection={<IconMedicalCrossOff size={18} stroke={1.5} />}
                className="my-2 mx-2"
                placeholder="Enter Chronic Disease..."
                value={form.values.chronicDisease.split(",").filter(Boolean)}
                onChange={(tags) =>
                  form.setFieldValue("chronicDisease", tags.join(","))
                }
              />
            ) : profileDetails?.chronicDisease ? (
              profileDetails.chronicDisease
            ) : (
              "--"
            )}
          </Table.Td>
        </Table.Tr>
      </Table.Tbody>
    </Table>
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
