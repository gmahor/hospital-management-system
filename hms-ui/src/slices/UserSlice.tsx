import { createSlice } from "@reduxjs/toolkit";
import { jwtDecode } from "jwt-decode";

const userSlice = createSlice({
  name: "user",
  initialState: (() => {
    const token = localStorage.getItem("token");
    if (!token) return {};
    try {
      return jwtDecode(token);
    } catch (error) {
      return {};
    }
  })(),
  reducers: {
    setUser: (state) => {
      const token = localStorage.getItem("token");
      if (token) {
        state = jwtDecode(token);
        return state;
      }
    },
    removeUser: (state) => {
      localStorage.removeItem("token");
      state = {};
      return state;
    },
  },
});

export const { removeUser, setUser } = userSlice.actions;
export default userSlice.reducer;
