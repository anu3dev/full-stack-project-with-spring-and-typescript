import { useState } from 'react'

const RegisterUser: React.FC = () => {
  const [otpSent, setOtpSent] = useState(false)

  const handleBtnClick = () => {
    setOtpSent(!otpSent)
  }

  return (
    <div className="login-zone-screen">
      <h2>User registration:</h2>
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

        {otpSent && (
          <>
            <p className="helpher-text">
              <span>&#8727; &nbsp;</span>If your email is valid, please check inbox.
            </p>
            <label htmlFor="otp">Enter OTP:</label>
            <input type="text" id="otp" name="otp" required />

            <label htmlFor="mobile">Enter mobile no.:</label>
            <input type="text" id="mobile" name="mobile" required />

            <label htmlFor="name">Enter full name:</label>
            <input type="text" id="name" name="name" required />

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
          &#8658; Got your password? <a href="/login">Login here</a>
        </p>
        <hr></hr>
        <p>
          &#8658; Are you an admin? <a href="/register-company">Register your company here</a>
        </p>
      </div>
    </div>
  )
}

export default RegisterUser
