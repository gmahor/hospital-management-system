import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import { Login } from "../components/Login";
import { Register } from "../components/Register";
import { AdminDashboard } from "../layout/AdminDashboard";

const AppRoutes = () => {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/" element={<AdminDashboard />} />
      </Routes>
    </Router>
  );
};

export default AppRoutes;
