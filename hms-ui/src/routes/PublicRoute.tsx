import { jwtDecode } from "jwt-decode";
import type { JSX } from "react";
import { useSelector } from "react-redux";
import { Navigate } from "react-router-dom";

interface PublicRouteProps{
    children: JSX.Element;
}

const PublicRoute:React.FC<PublicRouteProps> = ({ children }) => {
   const accessToken = useSelector((state: any) => state.jwt.accessToken);
   if(accessToken){
    const user: any = jwtDecode(accessToken);
    return <Navigate to={`/${user?.role?.toLowerCase()}/dashboard`} />
   }
   return children
};

export default PublicRoute;