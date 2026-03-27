import { configureStore } from "@reduxjs/toolkit";
import jwtReducer from "./slices/JwtSlice";
import userReducer from "./slices/UserSlice";
import loadingReducer from "./slices/LoadingSlice";


export default configureStore({
  reducer: {
   loading: loadingReducer,
    jwt: jwtReducer,
    user: userReducer,
  },
});
