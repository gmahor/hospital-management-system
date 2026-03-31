import { Avatar, Button, Divider, FileInput, Modal } from "@mantine/core";
import { useDisclosure } from "@mantine/hooks";
import { IconEdit, IconFile } from "@tabler/icons-react";
import { useState } from "react";
import { useSelector } from "react-redux";
import boy from "../../assets/boy.png";
import { DoctorPersonalInfo } from "./DoctorPersonalInfo";

export const DoctorProfile = () => {
  const user: any = useSelector((state: any) => state.user);
  const [isEdit, setIsEdit] = useState(false);
  const [opened, { open, close }] = useDisclosure(false);

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
      <DoctorPersonalInfo isEdit={isEdit} setIsEdit={setIsEdit} user={user} />
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
