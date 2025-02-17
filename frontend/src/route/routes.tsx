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