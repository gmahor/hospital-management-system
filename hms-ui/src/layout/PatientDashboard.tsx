import { Outlet } from "react-router-dom";
import { Header } from "../components/Header";
import { useSelector } from "react-redux";
import { Sidebar } from "../components/patient/Sidebar";

export const PatientDashboard = () => {
   const user = useSelector((state: any) => state.user);
  return (
    <div className="flex">
      <Sidebar data={user}/>
      <div className="w-full flex flex-col">
        <Header data={user}/>
        <Outlet />
      </div>
    </div>
  );
};
