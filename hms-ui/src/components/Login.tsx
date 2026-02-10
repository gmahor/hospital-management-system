import { Button, PasswordInput, TextInput } from "@mantine/core";
import { useForm } from "@mantine/form";
import { IconAt, IconLock } from "@tabler/icons-react";
import { Link, useNavigate } from "react-router-dom";
import bg from "../assets/bg.jpg";
import { loginUser } from "../service/UserService";
import {
  ErrorNotification,
  SuccessNotification,
} from "../utils/CustomNotification";

export const Login = () => {

 const navigate =  useNavigate();


  const form = useForm({
    initialValues: {
      email: "",
      password: "",
    },

    validate: {
      email: (value: string) =>
        /^\S+@\S+$/.test(value) ? null : "Invalid email",

      password: (value: string) => {
        if (!value) return "Password is required";
        if (value.length < 8) return "Password must be at least 8 characters";
        return null;
      },
    },
  });

  const handleSubmit = (values: typeof form.values) => {
    const payload: any = {
      email: values.email,
      password: values.password,
    };

    loginUser(payload)
      .then((_data) => {
        SuccessNotification(
          "Login Success!!",
          "Login Successfully",
          2000,
          "top-center",
        );
        navigate("/");
      })
      .catch((error) => {
        ErrorNotification(
          "Login Failed!!",
          error.errorMessage,
          2000,
          "top-center",
        );
      });
  };

  return (
    <div
      style={{
        backgroundImage: `url(${bg})`,
      }}
      className="h-screen w-screen bg-cover bg-center bg-no-repeat flex items-center justify-center"
    >
      <div className="w-112.5 backdrop-blur-md p-10 py-8 text-2xl border rounded-lg shadow-lg">
        <form
          onSubmit={form.onSubmit(handleSubmit)}
          className="flex flex-col gap-5"
        >
          <div className="self-center font-medium font-heading text-primary-400 text-4xl underline">
            Login
          </div>

          <TextInput
            label="Email"
            placeholder="Your email"
            size="md"
            radius="md"
            leftSection={<IconAt size={16} />}
            {...form.getInputProps("email")}
          />

          <PasswordInput
            label="Password"
            placeholder="Your password"
            size="md"
            radius="md"
            leftSection={<IconLock size={16} />}
            {...form.getInputProps("password")}
          />

          <Button
            type="submit"
            variant="gradient"
            gradient={{ from: "primary", to: "cyan" }}
          >
            Submit
          </Button>
          <div className="text-neutral-700 text-sm self-center">
            Don't have an account?{" "}
            <Link to="/register" className="hover:underline text-blue-500">
              Register
            </Link>
          </div>
        </form>
      </div>
    </div>
  );
};
