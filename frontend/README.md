# frontend - full-stack-project-with-spring-and-typescript

## learnings summary

### repo setup

- npm create vite@latest
- npm install
- npm run dev

### styling

- npm i sass
- create css file with .scss extension to use scss benefits

### routing setup

- npm i react-router-dom
- call routes method like below

```
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
```

- create routes constant like below

```
import { Suspense } from 'react';
import Login from '../components/molecules/Login';
import Home from '../components/molecules/Home';

const getRoutingPath = () => {
    const routes = [{
        path: '/',
        element: (
            <Suspense fallback={<div>Loading...</div>}>
                <Home />
            </Suspense>
        )
    },{
        path: '/login',
        element: (
            <Suspense fallback={<div>Loading...</div>}>
                <Login />
            </Suspense>
        )
    }];
    return routes;
}
export default getRoutingPath;
```

### prettier setup

- installed related packages and add format script
- npm run format
