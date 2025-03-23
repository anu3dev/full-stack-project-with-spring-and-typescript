const Login: React.FC = () => {
  const handleBtnClick = () => {}

  return (
    <div className="login-zone-screen">
      <h2>Login here:</h2>
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

        <label htmlFor="password">Password:</label>
        <input type="password" id="password" name="password" required />

        <button type="submit" onClick={handleBtnClick}>
          Login
        </button>
      </form>
      <div className="additional-links">
        <p>
          &#8658; Not able to login? <a href="/reset-password">Reset here</a>
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

export default Login
