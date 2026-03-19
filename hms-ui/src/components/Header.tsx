import { ActionIcon, Button } from "@mantine/core";
import {
  IconBellRinging,
  IconLayoutSidebarLeftCollapseFilled,
} from "@tabler/icons-react";
import { useDispatch } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { removeTokens } from "../slices/JwtSlice";
import { removeUser } from "../slices/UserSlice";
import { ProfileMenu } from "./ProfileMenu";

export const Header = ({ data }: { data: any }) => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const handleLogout = () => {
    dispatch(removeTokens());
    dispatch(removeUser());
    navigate("/login");
  };

  return (
    <div className="bg-cyan-100 w-full h-16 flex justify-between px-5 items-center">
      <ActionIcon variant="transparent" aria-label="Settings" size="lg">
        <IconLayoutSidebarLeftCollapseFilled
          style={{ width: "90%", height: "90%" }}
          stroke={1.5}
        />
      </ActionIcon>
      <div className="flex gap-2 items-center">
        {!data ? (
          <Link to="login">
            <Button>Login</Button>
          </Link>
        ) : (
          <>
            <Button color="red" onClick={handleLogout}>
              Logout
            </Button>
            <ActionIcon variant="transparent" aria-label="Settings" size="md">
              <IconBellRinging
                style={{ width: "90%", height: "90%" }}
                stroke={2}
              />
            </ActionIcon>
            <ProfileMenu data={data} />
          </>
        )}
      </div>
    </div>
  );
};
