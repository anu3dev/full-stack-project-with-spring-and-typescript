const Dashboard: React.FC<{ firstName: string }> = (props) => {
  const { firstName } = props

  return (
    <div>
      <h1>Hi {firstName}</h1>
    </div>
  )
}

export default Dashboard
