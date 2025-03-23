import { useState } from 'react'

const ResetPassword: React.FC = () => {
  const [otpSent, setOtpSent] = useState(false)

  const handleBtnClick = () => {
    setOtpSent(!otpSent)
  }

  return (
    <div className="login-zone-screen">
      <h2>Reset your password:</h2>
      <form>
        <label htmlFor="role">Company:</label>
        <select id="role" name="role" required>
          <option value="">Company name</option>
          <option value="0001">Parent</option>
        </select>
        <p className="helpher-text">
          <span>&#8727; &nbsp;</span>If you don't see your company, please contact admin.
        </p>
        <label htmlFor="email">Email:</label>
        <input type="text" id="email" name="email" required />
        <p className="helpher-text">
          <span>&#8727; &nbsp;</span>If your email is with us, please check inbox.
        </p>
        {otpSent && (
          <>
            <label htmlFor="otp">Enter OTP:</label>

            <input type="text" id="otp" name="otp" required />
            <label htmlFor="password">Enter password:</label>
            <input type="text" id="password" name="password" required />

            <label htmlFor="password">Confirm password:</label>
            <input type="text" id="password" name="password" required />
          </>
        )}
        <button type="submit" onClick={handleBtnClick}>
          {otpSent ? 'Send OTP' : 'Reset password'}
        </button>
      </form>
      <div className="additional-links">
        <p>
          &#8658; Got your password? <a href="/login">Login here</a>
        </p>
        <hr></hr>
        <p>
          &#8658; Don't have an account? <a href="/register-user">Register here</a>
        </p>
        <p>
          &#8658; Are you an admin? <a href="/register-company">Register your company here</a>
        </p>
      </div>
    </div>
  )
}

export default ResetPassword
