import { useSelector } from "react-redux";
import { Outlet } from "react-router-dom";
import { Header } from "../components/Header";
import { Sidebar } from "../components/patient/Sidebar";

type RootState = {
  user: Record<string, unknown>;
};

export const PatientDashboard = () => {
  const user = useSelector((state: RootState) => state.user);

  return (
    // <div className="grid grid-cols-[16rem_1fr] h-screen">
    //   {/* {!isCollapsed && <Sidebar data={user} />} */}
    //   <Sidebar data={user} />
    //   <div className="flex flex-col overflow-x-auto">
    //     <Header
    //       data={user}
    //       isCollapsed={isCollapsed}
    //       setIsCollapsed={setIsCollapsed}
    //     />
    //     <Outlet />
    //   </div>
    // </div>

    <div className="flex">
      <div className="w-64">
        <Sidebar data={user} />
      </div>
      <div className="flex-1 flex flex-col overflow-x-auto">
        <Header data={user} />
        <Outlet />
      </div>
    </div>
  );
};
