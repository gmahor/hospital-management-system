import { Button, Card } from "@mantine/core";
import { IconCheck } from "@tabler/icons-react";
import type { PatientProfile } from "./Profile";
import { ProfileTable } from "./ProfileTable";

export const ProfilePersonalInfo = ({
  isEdit,
  form,
  profileDetails,
  handleUpdateProfile,
}: {
  isEdit: boolean;
  form: any;
  profileDetails: PatientProfile | null;
  handleUpdateProfile: any;
}) => {
  return (
    <Card shadow="sm" padding="lg" radius="md" withBorder>
      <form onSubmit={form.onSubmit(handleUpdateProfile)}>
        <div>
          <div className="my-4 text-2xl font-medium text-neutral-900 underline">
            Personal Information
          </div>
          <ProfileTable
            isEdit={isEdit}
            form={form}
            profileDetails={profileDetails}
          />
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
        </div>
      </form>
    </Card>
  );
};
