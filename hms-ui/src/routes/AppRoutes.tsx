import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import { Login } from "../components/Login";
import Random from "../components/Random";
import { Register } from "../components/Register";
import { AdminDashboard } from "../layout/AdminDashboard";
import PublicRoute from "./PublicRoute";
import ProtectedRoute from "./ProtectedRoute";

const AppRoutes = () => {
  return (
    <Router>
      <Routes>
        <Route
          path="/login"
          element={
            <PublicRoute>
              <Login />
            </PublicRoute>
          }
        />
        <Route
          path="/register"
          element={
            <PublicRoute>
              <Register />
            </PublicRoute>
          }
        />
        <Route
          path="/"
          element={
            <ProtectedRoute>
              <AdminDashboard />
            </ProtectedRoute>
          }
        >
          <Route path="/dashboard" element={<Random />} />
          <Route path="/doctors" element={<Random />} />
          <Route path="/patients" element={<Random />} />
          <Route path="/appointments" element={<Random />} />
          <Route path="/pharmacy" element={<Random />} />
        </Route>
      </Routes>
    </Router>
  );
};

export default AppRoutes;
