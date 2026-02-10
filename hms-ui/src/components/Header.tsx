import { ActionIcon, Button } from "@mantine/core";
import { IconBellRinging, IconLayoutSidebarLeftCollapseFilled } from "@tabler/icons-react";
import { ProfileMenu } from "./ProfileMenu";
import { Link } from "react-router-dom";

export const Header = () => {
  return (
    <div className="bg-cyan-100 w-full h-16 flex justify-between px-5 items-center">
      <ActionIcon variant="transparent" aria-label="Settings" size="lg">
        <IconLayoutSidebarLeftCollapseFilled
          style={{ width: "90%", height: "90%" }}
          stroke={1.5}
        />
      </ActionIcon>
        <div className="flex gap-2 items-center">
        <Link to="login">
          <Button>Login</Button>
        </Link>
        <ActionIcon variant="transparent" aria-label="Settings" size="md">
          <IconBellRinging style={{ width: "90%", height: "90%" }} stroke={2} />
        </ActionIcon>
        <ProfileMenu />
      </div>
    </div>
  );
};
