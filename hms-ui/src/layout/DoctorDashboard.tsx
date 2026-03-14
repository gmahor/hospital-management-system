import { useSelector } from "react-redux";
import { Outlet } from "react-router-dom";
import { Sidebar } from "../components/doctor/Sidebar";
import { Header } from "../components/Header";

export const DoctorDashboard = () => {
  const user = useSelector((state: any) => state.user);
  return (
    <div className="flex">
      <Sidebar data={user} />
      <div className="w-full flex flex-col">
        <Header data={user} />
        <Outlet />
      </div>
    </div>
  );
};
