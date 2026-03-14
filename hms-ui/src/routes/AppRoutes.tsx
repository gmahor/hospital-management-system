import { createBrowserRouter, RouterProvider } from "react-router-dom";
import {
  DoctorProfile,
  ProfileLoaderApis
} from "../components/doctor/DoctorProfile";
import { Login } from "../components/Login";
import Appointment from "../components/patient/Appointment";
import { bloodGroups, Profile } from "../components/patient/Profile";
import Random from "../components/Random";
import { Register } from "../components/Register";
import { AdminDashboard } from "../layout/AdminDashboard";
import { DoctorDashboard } from "../layout/DoctorDashboard";
import { PatientDashboard } from "../layout/PatientDashboard";
import ProtectedRoute from "./ProtectedRoute";
import PublicRoute from "./PublicRoute";

const router = createBrowserRouter([
  {
    path: "/login",
    element: (
      <PublicRoute>
        <Login />
      </PublicRoute>
    ),
  },
  {
    path: "/register",
    element: (
      <PublicRoute>
        <Register />
      </PublicRoute>
    ),
  },
  {
    path: "/",
    element: (
      <ProtectedRoute>
        <AdminDashboard />
      </ProtectedRoute>
    ),
    children: [
      { path: "dashboard", element: <Random /> },
      { path: "doctors", element: <DoctorDashboard /> },
      { path: "patients", element: <PatientDashboard /> },
      { path: "pharmacy", element: <Random /> },
    ],
  },
  {
    path: "/patient",
    element: (
      <ProtectedRoute>
        <PatientDashboard />
      </ProtectedRoute>
    ),
    children: [
      { path: "dashboard", element: <Random /> },
      {
        path: "profile",
        element: <Profile />,
        loader: bloodGroups,
      },
      { path: "appointment", element: <Appointment /> },
      { path: "booking", element: <Random /> },
    ],
  },
  {
    path: "/doctor",
    element: (
      <ProtectedRoute>
        <DoctorDashboard />
      </ProtectedRoute>
    ),
    children: [
      { path: "dashboard", element: <Random /> },
      {
        path: "profile",
        element: <DoctorProfile />,
        loader: ProfileLoaderApis,
      },
    ],
  },
]);

const AppRoutes = () => {
  return <RouterProvider router={router} />;
};

export default AppRoutes;
