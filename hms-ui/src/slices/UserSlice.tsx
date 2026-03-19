import { createSlice } from "@reduxjs/toolkit";
import { jwtDecode } from "jwt-decode";

const userSlice = createSlice({
  name: "user",
  initialState: (() => {
    const accessToken = localStorage.getItem("accessToken");
    if (!accessToken) return {};
    try {
      return jwtDecode(accessToken);
    } catch (error) {
      return {};
    }
  })(),
  reducers: {
    setUser: (state) => {
      const accessToken = localStorage.getItem("accessToken");
      if (accessToken) {
        state = jwtDecode(accessToken);
        return state;
      }
    },
    removeUser: (state) => {
      localStorage.removeItem("accessToken");
      state = {};
      return state;
    },
  },
});

export const { removeUser, setUser } = userSlice.actions;
export default userSlice.reducer;
