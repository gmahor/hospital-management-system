import { Outlet } from "react-router-dom";
import { Sidebar } from "../components/Sidebar";
import { Header } from "../components/Header";
import { useSelector } from "react-redux";

export const AdminDashboard = () => {
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
