import { Center, Loader } from "@mantine/core";
const Loading = () => {
  return (
    <Center
      style={{
        position: "fixed",
        top: 0,
        left: 0,
        width: "100vw",
        height: "100vh",
        backdropFilter: "blur(6px)",
        backgroundColor: "rgba(255, 255, 255, 0.3)",
        zIndex: 9999,
      }}
    >
      <Loader type="dots" size="lg" color="primary" />
    </Center>
  );
};

export default Loading;
