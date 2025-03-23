import { useState } from 'react'

const RegisterAdmin: React.FC = () => {
  const [otpSent, setOtpSent] = useState(false)

  const handleBtnClick = () => {
    setOtpSent(!otpSent)
  }

  return (
    <div className="login-zone-screen">
      <h2>Company registration:</h2>
      <form>
        <label htmlFor="email">Email:</label>
        <input type="text" id="email" name="email" placeholder='Enter your email ID please...' required />

        {otpSent && (
          <>
            <p className="helpher-text">
              <span>&#8727; &nbsp;</span>If your email is with us, please check inbox.
            </p>
            <label htmlFor="otp">Enter OTP:</label>
            <input type="text" id="otp" name="otp" required />

            <label htmlFor="company">Company name:</label>
            <input type="text" id="company" name="company" required />

            <label htmlFor="mobile">Enter mobile no.:</label>
            <input type="text" id="mobile" name="mobile" required />

            <label htmlFor="password">Enter password:</label>
            <input type="text" id="password" name="password" required />

            <label htmlFor="password">Confirm password:</label>
            <input type="text" id="password" name="password" required />
          </>
        )}
        <button type="submit" onClick={handleBtnClick}>
          Register
        </button>
      </form>
      <div className="additional-links">
        <p>
          &#8658; Already registered? <a href="/login">Login here</a>
        </p>
        <hr></hr>
        <p>
          &#8658; Are you an user? <a href="/register-user">Register here</a>
        </p>
      </div>
    </div>
  )
}

export default RegisterAdmin
