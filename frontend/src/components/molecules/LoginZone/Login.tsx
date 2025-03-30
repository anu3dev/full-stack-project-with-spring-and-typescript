import React, { useState, useEffect } from 'react'
import {getCompanyListName} from './Utils'

const Login: React.FC = () => {
  const [getCompanyName, setCompanyName] = useState<string[]>([])

  useEffect(() => {
    getCompanyListName(setCompanyName)
  },[])

  const handleBtnClick = () => {}

  return (
    <div className="login-zone-screen">
      <h2>Login here:</h2>
      <form>
        <label htmlFor="role">Company:</label>
        <select id="role" name="role" required>
          <option value="">Company name</option>
          {getCompanyName.map((company: string) => (
            <option key={company} value={company}>
              {company}
            </option>
          ))}
        </select>
        <p className="helpher-text">
          <span>&#8727; &nbsp;</span>If you don't see your company, please contact admin.
        </p>
        <label htmlFor="email">Email:</label>
        <input type="text" id="email" name="email" required />

        <label htmlFor="password">Password:</label>
        <input type="password" id="password" name="password" required />

        <button type="submit" onClick={handleBtnClick}>
          Login
        </button>
      </form>
      <div className="additional-links">
        <p>
          &#8658; Not able to login? <a href="/reset-password">Password reset</a>
        </p>
        <hr></hr>
        <p>
          &#8658; Are you an user? <a href="/register-user">Register here</a>
        </p>
        <p>
          &#8658; Are you an admin? <a href="/register-company">Register here</a>
        </p>
      </div>
    </div>
  )
}

export default Login
