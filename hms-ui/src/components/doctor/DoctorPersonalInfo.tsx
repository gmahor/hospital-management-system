import { Card } from "@mantine/core";
import { useQuery } from "react-query";
import { getDoctorProfile } from "../../service/DoctorProfileService";
import { DoctorProfileTable } from "./DoctorProfileTable";

export interface DoctorProfile {
  id: number;
  name: string;
  email: string;
  dob: string | null;
  phone: string | null;
  address: string | null;
  licenseNo: string | null;
  specialization: string | null;
  department: string | null;
  totalExperience: string | null;
}

export const DoctorPersonalInfo = ({
  isEdit,
  setIsEdit,
  user,
}: {
  isEdit: boolean;
  setIsEdit: React.Dispatch<React.SetStateAction<boolean>>;
  user: any;
}) => {
  const {
    data: doctorDetails,
    isLoading,
    error,
    refetch,
  } = useQuery({
    queryKey: ["profile", user?.profileId],
    queryFn: async () => {
      const resp = await getDoctorProfile(user?.profileId);
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
    <Card shadow="sm" padding="lg" radius="md" withBorder>
      <div>
        <div className="my-4 text-2xl font-medium text-neutral-900 underline">
          Personal Information
        </div>
        <DoctorProfileTable
          isEdit={isEdit}
          setIsEdit={setIsEdit}
          doctorDetails={doctorDetails}
          refetch={refetch}
        />
      </div>
    </Card>
  );
};
