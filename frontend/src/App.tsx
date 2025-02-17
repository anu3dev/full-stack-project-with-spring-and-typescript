import { createBrowserRouter, RouterProvider } from "react-router-dom";
import getRoutingPath from "./route/routes";

const App = () => {
  const getRoute = () => {
    return createBrowserRouter(
        getRoutingPath(),
        {basename: "/"}
    );
}
  return (
    < RouterProvider router={getRoute()}/>
  )
}

export default App
