import { notifications } from "@mantine/notifications";
import { IconCheck, IconX } from "@tabler/icons-react";

export const SuccessNotification = (
  title: string,
  message: string,
  timeout: any,
  text_position: any,
) => {
  return notifications.show({
    title: title,
    message: message,
    color: "green",
    position: text_position,
    autoClose: timeout,
    icon: <IconCheck />,
  });
};

export const ErrorNotification = (
  title: string,
  message: string,
  timeout: any,
  text_position: any,
) => {
  return notifications.show({
    title: title,
    message: message,
    color: "red",
    position: text_position,
    autoClose: timeout,
    icon: <IconX />,
  });
};
