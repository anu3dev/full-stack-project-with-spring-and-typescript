import React, { useState } from 'react'
import { handleRegistrationBtnText, handleRegistrationBtnClick } from './Utils'

const RegisterCompany: React.FC = () => {
  const [helperTextMessage, setHelperTextMessage] = useState('')
  const [otpVerified, setOtpVerified] = useState(false)
  const [formData, setFormData] = useState<{
    emailId: string
    otpValue: string
    name: string
    phoneNo: string
    registeredBy?: string
  }>({
    emailId: '',
    otpValue: '',
    name: '',
    phoneNo: '',
    registeredBy: '',
  })

  const handleFormValueChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }))
  }

  const handleBtnClick = () => {
    handleRegistrationBtnClick(
      setOtpVerified,
      setHelperTextMessage,
      formData,
      helperTextMessage,
      'company',
      setFormData,
    )
  }

  return (
    <div className="login-zone-screen">
      {helperTextMessage.includes('registered successfully with unique') ? (
        <h2>{helperTextMessage}</h2>
      ) : (
        <>
          <h2>Company registration:</h2>
          <form>
            <label htmlFor="email">Your email:</label>
            <input
              type="text"
              name="emailId"
              value={formData.emailId}
              placeholder="Enter your email ID please..."
              onChange={handleFormValueChange}
            />

            {(helperTextMessage === `It's time to check your inbox for OTP.` ||
              helperTextMessage === `Entered OTP is invalid.`) &&
              !otpVerified && (
                <>
                  <label htmlFor="otp">Enter OTP:</label>
                  <input
                    type="text"
                    name="otpValue"
                    value={formData.otpValue}
                    placeholder="Enter OTP please..."
                    onChange={handleFormValueChange}
                  />
                </>
              )}

            {helperTextMessage && (
              <p className="helpher-text">
                <span>&#8727; &nbsp;</span>
                {helperTextMessage}
              </p>
            )}

            {otpVerified && (
              <>
                <label htmlFor="company">Company name:</label>
                <input
                  type="text"
                  name="name"
                  value={formData.name}
                  placeholder="Enter your company name please..."
                  onChange={handleFormValueChange}
                />

                <label htmlFor="mobile">Enter phone number:</label>
                <input
                  type="text"
                  name="phoneNo"
                  value={formData.phoneNo}
                  placeholder="Enter your phone number please..."
                  onChange={handleFormValueChange}
                />

                <label htmlFor="mobile">Enter your name:</label>
                <input
                  type="text"
                  name="registeredBy"
                  value={formData.registeredBy}
                  placeholder="Enter your name please..."
                  onChange={handleFormValueChange}
                />

                {/* <label htmlFor="password">Enter password:</label>
            <input type="text" id="password" name="password" required />
            <label htmlFor="password">Confirm password:</label>
            <input type="text" id="password" name="password" required /> */}
              </>
            )}
            <button type="button" onClick={handleBtnClick}>
              {handleRegistrationBtnText(helperTextMessage, otpVerified)}
            </button>
          </form>
        </>
      )}
      <div className="additional-links">
        <p>
          &#8658; Already registered? <a href="/login">Login here</a>
        </p>
        <p>
          &#8658; Not able to login? <a href="/reset-password">Password reset</a>
        </p>
        <hr></hr>
        <p>
          &#8658; Are you an user? <a href="/register-user">Register here</a>
        </p>
      </div>
    </div>
  )
}

export default RegisterCompany
