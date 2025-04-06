import React, { useState } from 'react'
import { handleLoginBtnClick } from './Utils'

const Login: React.FC = () => {
  const [helperTextMessage, setHelperTextMessage] = useState('')
  const [formData, setFormData] = useState({
    emailId: '',
    password: '',
  })

  const handleFormValueChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }))
  }

  const handleBtnClick = () => {
    handleLoginBtnClick(setHelperTextMessage, formData)
  }

  return (
    <div className="login-zone-screen">
      <h2>Login here:</h2>
      <form>
        <label htmlFor="email">Your email:</label>
        <input
          type="text"
          name="emailId"
          value={formData.emailId}
          placeholder="Enter your email ID please..."
          onChange={handleFormValueChange}
        />

        <label htmlFor="password">Your password:</label>
        <input
          type="password"
          name="password"
          value={formData.password}
          placeholder="Enter your password please..."
          onChange={handleFormValueChange}
        />

        {helperTextMessage && (
          <p className="helpher-text">
            <span>&#8727; &nbsp;</span>
            {helperTextMessage}
          </p>
        )}

        <button type="button" onClick={handleBtnClick}>
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
