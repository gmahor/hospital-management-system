import { Avatar, Text } from "@mantine/core";
import {
  IconCalendarCheck,
  IconHeartbeat,
  IconLayoutGrid,
  IconUser,
  IconVaccine,
} from "@tabler/icons-react";
import { NavLink } from "react-router-dom";
import boy from "../../assets/boy.png";

const links = [
  {
    name: "Dashboard",
    url: "/patient/dashboard",
    icon: <IconLayoutGrid stroke={1.5} />,
  },
  {
    name: "Profile",
    url: "/patient/profile",
    icon: <IconUser stroke={1.5} />,
  },
  {
    name: "Appointment",
    url: "/patient/appointment",
    icon: <IconCalendarCheck stroke={1.5} />,
  },
  {
    name: "Booking",
    url: "/patient/booking",
    icon: <IconVaccine stroke={1.5} />,
  },
];

export const Sidebar = ({ data }: { data: any }) => {
  return (
    <div className="flex">
      <div className="w-64"></div>

      <div className="w-64 fixed h-screen overflow-y-auto hide-scrollbar bg-dark flex flex-col gap-8 items-center">
        <div className="fixed z-500 py-3 bg-dark text-primary-400 flex gap-1 items-center">
          <IconHeartbeat stroke={2.5} />
          <span className="font-heading text-xl">Pulse</span>
        </div>

        <div className="flex flex-col gap-5 mt-20">
          <div className="flex flex-col gap-1 items-center">
            <div className="p-1 bg-white rounded-full shadow-lg">
              <Avatar variant="filled" src={boy} size="lg" alt="it's me" />
            </div>
            <span className="font-medium text-light">
              {data != null ? data.username : "Guest User"}
            </span>
            <Text c="dimmed" size="xs" className="text-light">
              {data != null ? data.role : "Visitor"}
            </Text>
          </div>
          <div className="flex flex-col gap-1">
            {links.map((link, key) => {
              return (
                <NavLink
                  to={link.url}
                  key={key}
                  className={({ isActive }) =>
                    `flex items-center gap-3 w-full font-medium text-light px-4 py-5 rounded-lg ${isActive ? "bg-primary-400 text-dark" : "hover:bg-gray-100 hover:text-dark text-light"}`
                  }
                >
                  {link.icon}
                  <span>{link.name}</span>
                </NavLink>
              );
            })}
          </div>
        </div>
      </div>
    </div>
  );
};
