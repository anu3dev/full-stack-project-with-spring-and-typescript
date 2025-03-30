# full-stack-project-with-spring-and-typescript

### Application flow:-

- Company registration flow:-
  - Enter email:-

      ![alt text](https://github.com/anu3dev/full-stack-project-with-spring-and-typescript/blob/main/images/comp-reg-01.png)

  - Check if user enters the email, if no then display error message
 
      ![alt text](https://github.com/anu3dev/full-stack-project-with-spring-and-typescript/blob/main/images/comp-reg-02.png)
    
  - Check if email is already registered with any other company, if yes, return error message.
 
      ![alt text](https://github.com/anu3dev/full-stack-project-with-spring-and-typescript/blob/main/images/comp-reg-03.png)
    
  - Check if email is alreday registered with any other user in any company, if yes, return error message.
 
      ![alt text](https://github.com/anu3dev/full-stack-project-with-spring-and-typescript/blob/main/images/comp-reg-04.png)
    
  - If both above is no, send OTP to email, If any OTP value exist in DB with current email then override it, else store it.
 
      ![alt text](https://github.com/anu3dev/full-stack-project-with-spring-and-typescript/blob/main/images/comp-reg-05.png)

  - Check if user enters OTP, if no then display error message
 
      ![alt text](https://github.com/anu3dev/full-stack-project-with-spring-and-typescript/blob/main/images/comp-reg-06.png)

  - If OTP entered by user doesn't match with DB value, display error message
 
      ![alt text](https://github.com/anu3dev/full-stack-project-with-spring-and-typescript/blob/main/images/comp-reg-07.png)
      ![alt text](https://github.com/anu3dev/full-stack-project-with-spring-and-typescript/blob/main/images/comp-reg-08.png)
    
  - Once user enters valid OTP, display success message. If otp validation success, delete OTP value from DB, and enable rest input field in registration form. If user refreshes the page, user has to enter email again and repeat the process.

      ![alt text](https://github.com/anu3dev/full-stack-project-with-spring-and-typescript/blob/main/images/comp-reg-09.png)

  - Validate if user doesn't enter required field.
 
       ![alt text](https://github.com/anu3dev/full-stack-project-with-spring-and-typescript/blob/main/images/comp-reg-10.png) 
    
  - Below values will be stored in respective DB's as per user interaction during company registration.
 
      ![alt text](https://github.com/anu3dev/full-stack-project-with-spring-and-typescript/blob/main/images/comp-reg-11.png)
    ![alt text](https://github.com/anu3dev/full-stack-project-with-spring-and-typescript/blob/main/images/comp-reg-12.png)
    
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
    |||uniqueId -> 8 digit numeric unique employee ID start with 10000000|
    - `*` -> values will be collected from user.

- General login flow:-
  - Login will be done using user and password, validate if user entered the crediantial or not, if not then display error message.
 
      ![alt text](https://github.com/anu3dev/full-stack-project-with-spring-and-typescript/blob/main/images/login-01.png)
      ![alt text](https://github.com/anu3dev/full-stack-project-with-spring-and-typescript/blob/main/images/login-02.png)
      ![alt text](https://github.com/anu3dev/full-stack-project-with-spring-and-typescript/blob/main/images/login-03.png)

  - Validate if user entered the crediantial or not, if not then display error message, if yes, set AccessToken to session storage and redirect to dashboard.
 
      ![alt text](https://github.com/anu3dev/full-stack-project-with-spring-and-typescript/blob/main/images/login-04.png)
      ![alt text](https://github.com/anu3dev/full-stack-project-with-spring-and-typescript/blob/main/images/login-05.png)
    
  - If credentials matches, AccessToken will be stored to session storage, this token will be used for authorization and authenication. If token is not present or unauthorized/expired, user will be redirected to login page.
 
      ![alt text](https://github.com/anu3dev/full-stack-project-with-spring-and-typescript/blob/main/images/login-06.png)

- Parent's admin login flow:-
  - Can see full list of employees from parent company, will have option to view, update, disable and delete.
  - Parent company employees will only able to login to portal, if parent admin approves once.
  - If parent admin disables a employee, parent company employee will not able to login until and unless it will be enabled again and action email will be sent.
  - Can see full list of vendors, will have option to view, update, disable and delete.
  - If parent admin disables a vendor, vendor company list will be hidden from user registration page and action email will be sent to company admin, it will also disbale vendor's employee login other than it's admin.

- Parent's employee registration flow:-
  - Enter email:-
  - Check if email is already registered with any other company, if yes, return error message.
  - Check if email is alreday registered with any other user in any company, if yes, return error message.
  - If both above is no, send OTP to email.
  - If any OTP value exist in DB with current email then override it, else store it.
  - Once user enters OTP, validate it with DB, if it doesn't match with DB, return error message.
  - If otp validation success, delete OTP value from DB, and enable rest input field in registration form.
  - If user refreshes the page, user has to enter email again and repeat the process.
  - Below values will be stored in user's DB as per user interaction during user registration.
    |User DB|
    |-|
    |- id -> default auto increment value|
    |- emailId`*` -> user input|
    |- phoneNo`*` -> user input|
    |companyId -> 6 digit company ID will be displayed to user in dropdown during registration and will be mapped against user|
    |name`*` -> user has to enter full name|
    |approvalStatus -> will be stored as true once respective vendor will approve it's employee|
    |approvedBy -> will be stored approval person unique employee ID|
    |password -> alpha numeric 8 digit temp password will be send to email with successful registration kit once approval will be done|
    |isAdmin -> stored as false|
    |uniqueId -> 8 digit numeric unique employee ID start with 10000000|
    - `*` -> values will be collected from user. 

- Parent's employee login flow:-
  - Parent employee can only see list of unapproved vendors with view, approve and contact.
  - Parent employee will not able to see details of its peers plus any contact info of vendors.

- Vendor's admin login flow:-
  - Once parent admin/employees approves vendor, it will be auto populated to user registration page.
  - Vendor admin can only see list of users registered to his/her company through registration page.
  - Vendor admin can view, update, disable and delete to it's employee.
  - Once employee login disables, user will not able to login unless and untill it will be enable again.
  - Once vendor employee account will be approved from respective vendor admin, welcome email will be sent to vendor employee with temp password and success kit.

- Vendor's employee registration flow:-
  - Enter email:-
  - Check if email is already registered with any other company, if yes, return error message.
  - Check if email is alreday registered with any other user in any company, if yes, return error message.
  - If both above is no, send OTP to email.
  - If any OTP value exist in DB with current email then override it, else store it.
  - Once user enters OTP, validate it with DB, if it doesn't match with DB, return error message.
  - If otp validation success, delete OTP value from DB, and enable rest input field in registration form.
  - If user refreshes the page, user has to enter email again and repeat the process.
  - Below values will be stored in user's DB as per user interaction during user registration.
    |User DB|
    |-|
    |- id -> default auto increment value|
    |- emailId`*` -> user input|
    |- phoneNo`*` -> user input|
    |companyId -> 6 digit company ID will be displayed to user in dropdown during registration and will be mapped against user|
    |name`*` -> user has to enter full name|
    |approvalStatus -> will be stored as true once respective vendor will approve it's employee|
    |approvedBy -> will be stored approval person unique employee ID|
    |password -> alpha numeric 8 digit temp password will be send to email with successful registration kit once approval will be done|
    |isAdmin -> stored as false|
    |uniqueId -> 8 digit numeric unique employee ID start with 10000000|
    - `*` -> values will be collected from user. 

- Vendor's employee login flow:-
  - Once vendor employee account will be approved from respective vendor admin, user will able to login.
  - other actions will be decided later.

