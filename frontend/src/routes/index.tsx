import { Suspense } from 'react'
import Login from '../components/molecules/LoginZone/Login'
import ResetPassword from '../components/molecules/LoginZone/ResetPassword'
import RegisterUser from '../components/molecules/LoginZone/RegisterUser'
import RegisterCompany from '../components/molecules/LoginZone/RegisterCompany'
import Home from '../components/molecules/Home'
import Dashboard from '../components/molecules/Dashboard'

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
          <RegisterCompany />
        </Suspense>
      ),
    },
    {
      path: '/dashboard',
      element: (
        <Suspense fallback={<div>Loading...</div>}>
          <Dashboard />
        </Suspense>
      ),
    },
  ]
  return routes
}
export default getRoutingPath
