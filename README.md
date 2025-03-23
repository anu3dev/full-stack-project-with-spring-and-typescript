# full-stack-project-with-spring-and-typescript

### application flow:-

- Company registration:-
  - Enter email:-
    - Check if email is already registered with any other company, if yes, return error message.
    - Check if email is alreday registered with any other user in any company, if yes, return error message.
    - If both above is no, send OTP to email.
    - If any OTP value exist in DB with current email then override it, else store it.
    - Once user enters OTP, validate it with DB, if it doesn't match with DB, return error message.
    - If otp validation success, delete OTP value from DB, and enable rest input field in registration form.
    - If user refreshes the page, user has to enter email again and repeat the process.
