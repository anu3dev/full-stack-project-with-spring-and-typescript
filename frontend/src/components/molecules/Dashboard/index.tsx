import { getSessionStorageValue } from '../../../utils/commonUtils'
import Details from './Details'

const Dashboard: React.FC = () => {
  const userNameFromSession = getSessionStorageValue('FirstName')
  const userName = userNameFromSession ? `${userNameFromSession}!` : 'Guest!'

  const adminType = getSessionStorageValue('Role')

  return (
    <div className="dashboard-screen">
      <h3 className="dashboard-screen__name">Hi {userName}</h3>
      {(adminType === 'superAdmin' || adminType === 'superUser') && <Details type="Company" />}
      {(adminType === 'superAdmin' || adminType === 'admin') && <Details type="User" />}
    </div>
  )
}

export default Dashboard
