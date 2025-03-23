import { Suspense } from 'react'
import Login from '../components/molecules/LoginZone/Login'
import ResetPassword from '../components/molecules/LoginZone/ResetPassword'
import RegisterUser from '../components/molecules/LoginZone/RegisterUser'
import RegisterAdmin from '../components/molecules/LoginZone/RegisterAdmin'
import Home from '../components/molecules/Home'

const getRoutingPath = () => {
  const routes = [
    {
      path: '/',
      element: (
        <Suspense fallback={<div>Loading...</div>}>
          <Home />
        </Suspense>
      ),
    },
    {
      path: '/login',
      element: (
        <Suspense fallback={<div>Loading...</div>}>
          <Login />
        </Suspense>
      ),
    },
    {
      path: '/reset-password',
      element: (
        <Suspense fallback={<div>Loading...</div>}>
          <ResetPassword />
        </Suspense>
      ),
    },
    {
      path: '/register-user',
      element: (
        <Suspense fallback={<div>Loading...</div>}>
          <RegisterUser />
        </Suspense>
      ),
    },
    {
      path: '/register-company',
      element: (
        <Suspense fallback={<div>Loading...</div>}>
          <RegisterAdmin />
        </Suspense>
      ),
    },
  ]
  return routes
}
export default getRoutingPath
