import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  accessToken: localStorage.getItem("accessToken") || "",
  refreshToken: localStorage.getItem("refreshToken") || "",
};

const jwtSlice = createSlice({
  name: "jwt",
  initialState,
  reducers: {
    setTokens: (state, action) => {
      const { accessToken, refreshToken } = action.payload;
      localStorage.setItem("accessToken", accessToken);
      localStorage.setItem("refreshToken", refreshToken);
      state.accessToken = accessToken;
      state.refreshToken = refreshToken;
    },
    removeTokens: (state) => {
      localStorage.removeItem("accessToken");
      localStorage.removeItem("refreshToken");
      state.accessToken = "";
      state.refreshToken = "";
    },
  },
});

// const jwtSlice = createSlice({
//   name: "jwt",
//   initialState: localStorage.getItem("token") || "",
//   reducers: {
//     setJwt: (state, action) => {
//       localStorage.setItem("token", action.payload);
//       state = action.payload;
//       return state;
//     },
//     removeJwt: (state) => {
//       localStorage.removeItem("token");
//       state = "";
//       return state;
//     },
//   },
// });

export const { setTokens, removeTokens } = jwtSlice.actions;
export default jwtSlice.reducer;
