import {
  Box,
  Button,
  Center,
  PasswordInput,
  SegmentedControl,
  TextInput,
} from "@mantine/core";
import { useForm } from "@mantine/form";
import { useDisclosure } from "@mantine/hooks";
import {
  IconAt,
  IconLock,
  IconStethoscope,
  IconUser,
  IconUserHexagon,
} from "@tabler/icons-react";
import { Link, useNavigate } from "react-router-dom";
import bg from "../assets/bg.jpg";
import { registerUser } from "../service/UserService";
import {
  ErrorNotification,
  SuccessNotification,
} from "../utils/CustomNotification";

export const Register = () => {
  const [visible, { toggle }] = useDisclosure(false);
 const navigate =  useNavigate();

  const handleSubmit = (values: typeof form.values) => {
    const payload: any = {
      username: values.username,
      email: values.email,
      password: values.password,
      role: values.type,
    };

    registerUser(payload)
      .then((data) => {
        SuccessNotification(
          "Registeration Success!!",
          data,
          2000,
          "top-center",
        );
        navigate('/login');
      })
      .catch((errorPayload) => {
        ErrorNotification(
          "Registration Failed!!",
          errorPayload.errorMessage,
          2000,
          "top-center",
        );
      });
  };

  const form = useForm({
    initialValues: {
      type: "PATIENT",
      username: "",
      email: "",
      password: "",
      confirmPassword: "",
    },

    validate: {
      email: (value: string) =>
        /^\S+@\S+$/.test(value) ? null : "Invalid email",

      password: (value: string) => {
        if (!value) return "Password is required";
        if (value.length < 8) return "Password must be at least 8 characters";
        return null;
      },
      confirmPassword: (value: string, values: any) => {
        if (!value) return "Confirm password is required";
        if (value !== values.password) return "Passwords do not match";
        return null;
      },
    },
  });

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
            Register
          </div>

          <SegmentedControl
            className="bg-neutral-300"
            size="md"
            radius="md"
            fullWidth
            color="primary"
            data={[
              {
                value: "PATIENT",
                label: (
                  <Center>
                    <IconUser size={16} />
                    <Box ml={10}>Patient</Box>
                  </Center>
                ),
              },
              {
                value: "DOCTOR",
                label: (
                  <Center>
                    <IconStethoscope size={16} />
                    <Box ml={10}>Doctor</Box>
                  </Center>
                ),
              },
              {
                value: "ADMIN",
                label: (
                  <Center>
                    <IconUserHexagon size={16} />
                    <Box ml={10}>Admin</Box>
                  </Center>
                ),
              },
            ]}
            {...form.getInputProps("type")}
          />

          <div className="grid grid-cols-2 gap-4">
            <TextInput
              label="Username"
              placeholder="Your username"
              size="md"
              radius="md"
              leftSection={<IconUser size={16} />}
              {...form.getInputProps("username")}
              classNames={{
                input: "placeholder:text-sm",
              }}
            />

            <TextInput
              label="Email"
              placeholder="Your email"
              size="md"
              radius="md"
              leftSection={<IconAt size={16} />}
              {...form.getInputProps("email")}
              classNames={{
                input: "placeholder:text-sm",
              }}
            />
          </div>
          <div className="grid grid-cols-2 gap-4">
            <PasswordInput
              label="Password"
              visible={visible}
              placeholder="Your password"
              onVisibilityChange={toggle}
              leftSection={<IconLock size={16} />}
              {...form.getInputProps("password")}
              classNames={{
                input: "placeholder:text-xs",
              }}
            />
            <PasswordInput
              label="Confirm password"
              visible={visible}
              placeholder="Confirm your password"
              onVisibilityChange={toggle}
              leftSection={<IconLock size={16} />}
              {...form.getInputProps("confirmPassword")}
              classNames={{
                input: "placeholder:text-xs",
              }}
            />
          </div>
          <Button
            type="submit"
            variant="gradient"
            gradient={{ from: "primary", to: "cyan" }}
          >
            Submit
          </Button>
          <div className="text-neutral-700 text-sm self-center">
            Already have an account?{" "}
            <Link to="/login" className="hover:underline text-blue-500">
              Login
            </Link>
          </div>
        </form>
      </div>
    </div>
  );
};
