# full-stack-project-with-spring-and-typescript

### Application flow:-

- Vendor registration flow:-
  - Enter email:-
  - Check if email is already registered with any other company, if yes, return error message.
  - Check if email is alreday registered with any other user in any company, if yes, return error message.
  - If both above is no, send OTP to email.
  - If any OTP value exist in DB with current email then override it, else store it.
  - Once user enters OTP, validate it with DB, if it doesn't match with DB, return error message.
  - If otp validation success, delete OTP value from DB, and enable rest input field in registration form.
  - If user refreshes the page, user has to enter email again and repeat the process.
  - Below values will be stored in respective DB's as per user interaction during company registration.
    |OTP DB|Company DB|User DB|
    |-|-|-|
    |- id -> default auto increment value|- id -> default auto increment value|- id -> default auto increment value|
    |- emailId`*` -> user input|- emailId`*` -> user input|- emailId`*` -> user input|
    |- otpValue -> 6 digit numeric value, which will be used to compare with user OTP input|- phoneNo`*` -> user input|- phoneNo`*` -> user input|
    ||uniqueId -> 6 digit numeric unique company ID start with 100000|companyId -> 6 digit company ID will be mapped to user|
    ||registeredBy`*` -> will be mapped as admin name|name`*` -> registeredBy as name will be mapped as user name for admin account|
    ||approvalStatus -> will be true once company will be approved to onboard|approvalStatus -> stored as true as it is a vendor admin account|
    ||approvedBy -> will be user ID from parent company who will verify and approve|approvedBy -> stored as self as it is a vendor admin account|
    ||name`*` -> company name will be taken for future refrence|password -> alpha numeric 8 digit temp password will be send to email with successful registration kit|
    |||isAdmin -> stored as true as it is an admin account for vendor|
    |||uniqueId -> 8 digit numeric unique company ID start with 10000000|
    - `*` -> values will be collected from user.
- General login flow:-
  - login will done using user and password.
  - If credentials matches, AccessToken will be stored to session storage, this token will be used for authorization and authenication.
  - If token is not present or unauthorized/expired, user will be redirected to login page.
- Parent admin login flow:-
  - Can see full list of employees from parent company, will have option to view, update, disable and delete.
  - Parent company employees will only able to login to portal, if parent admin approves.
  - If parent admin disables a employee, parent company employee will not able to login.
  - Can see full list of vendors, will have option to view, update, disable and delete.
  - If parent admin disables a vendor, vendor company list will be hidden from user registration page and action email will be sent to company admin.
- User registration flow:-
- Parent employee login flow:-
  - Parent employee can only see list of unapproved vendors with view, approve and contact.
  - Parent employee will not able to details of its peers plus any contact info of vendors.
- Vendor's employee login flow:-
- Vendor admin login flow:-
