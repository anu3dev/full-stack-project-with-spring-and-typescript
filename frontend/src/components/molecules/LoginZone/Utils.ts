import { PRIVATE_API_URL, PUBLIC_API_URL } from "../../../constants"

/**
 * 
 * @param helperTextMessage 
 * @param otpVerified 
 * @returns CTA button text based on the state of the registration process.
 * 
 */
export const handleRegistrationBtnText = (helperTextMessage: string, otpVerified: boolean): string => {
  let TxtVal = 'Send OTP to register'
  if (helperTextMessage === `It's time to check your inbox for OTP.` || helperTextMessage === `Entered OTP is invalid.`) {
    TxtVal = 'Verify OTP'
  }
  if (otpVerified) {
    TxtVal = 'Register'
  }
  return TxtVal
}

export const handleRegistrationBtnClick = async (
    setOtpVerified: (value: boolean) => void,
    setHelperTextMessage: (value: string) => void,
    formData: {
      emailId: string;
      otpValue: string;
      name: string;
      phoneNo: string;
      registeredBy?: string;
      companyId?: string;
    },
    helperTextMessage: string,
    registrationType: string,
    setFormData: React.Dispatch<React.SetStateAction<{
      emailId: string;
      otpValue: string;
      name: string;
      phoneNo: string;
      registeredBy?: string;
      companyId?: string;
    }>>
  ): Promise<void> => {
    if (formData.emailId === '') {
      setHelperTextMessage('Please enter your email ID.');
      return;
    } else if (formData.emailId !== '' && (helperTextMessage === '' || helperTextMessage === 'Please enter your email ID.')) {
      const url = `${PUBLIC_API_URL}send-otp`;
    fetch(url, {
      method: 'POST',
      body: JSON.stringify({
        emailId: formData.emailId,
      }),
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
      },
    })
    .then((response) => response.text())
    .then ((resp) => {
      setHelperTextMessage(resp)
    })
    .catch(() => {
      setHelperTextMessage('An error occurred while sending OTP.');
    });
    return;
  } else if (formData.otpValue === '' && (helperTextMessage === `It's time to check your inbox for OTP.` || helperTextMessage === `Entered OTP is invalid.`)) {
    setHelperTextMessage('Entered OTP is invalid.');
    return;
  } else if (formData.otpValue !== '' && (helperTextMessage === `It's time to check your inbox for OTP.` || helperTextMessage === `Entered OTP is invalid.`)){
    const url = `${PUBLIC_API_URL}verify-otp`;
    fetch(url, {
      method: 'POST',
      body: JSON.stringify({
        emailId: formData.emailId,
        otpValue: formData.otpValue,
      }),
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
      },
    })
    .then((response) => response.text())
    .then ((resp) => {
      setHelperTextMessage(resp);
      if (resp === 'OTP verified successfully.') {
        setOtpVerified(true);
      }
    })
    .catch(() => {
      setHelperTextMessage('An error occurred while verifying OTP.');
    });
    return;
  } else if ((helperTextMessage === 'OTP verified successfully.' || helperTextMessage === 'Please enter below missing details.') && (formData.name === '' || formData.phoneNo === '' || formData.registeredBy === '')) {
    setHelperTextMessage('Please enter below missing details.');
    return;
  } else {
    const url = `${PUBLIC_API_URL}register-${registrationType}`;
    const bodyPayload: {
      emailId: string;
      name: string;
      phoneNo: string;
      companyId?: string;
      registeredBy?: string;
    } = {
      emailId: formData.emailId,
      name: formData.name,
      phoneNo: formData.phoneNo,
    };
    if (registrationType === 'user') {
      bodyPayload['companyId'] = formData.companyId;
    } else {
      bodyPayload['registeredBy'] = formData.registeredBy;
    }
    fetch(url, {
      method: 'POST',
      body: JSON.stringify(bodyPayload),
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
      },
    })
    .then((response) => response.text())
    .then ((resp) => {
      setHelperTextMessage(resp);
      setFormData({
        emailId: '',
        otpValue: '',
        name: '',
        phoneNo: '',
        registeredBy: '',
      })
    })
    .catch(() => {
      setHelperTextMessage('An error occurred while registering the company.');
    });
  }
}

export const handleLoginBtnClick = async (
  setHelperTextMessage: (value: string) => void,
  formData: { [key: string]: string }
): Promise<void> => {
  if (formData?.emailId === '') {
    setHelperTextMessage('Please enter your email ID.');
    return;
  } else if (formData?.password === '') {
    setHelperTextMessage('Please enter your password.');
    return;
  } else {
    const url = `${PRIVATE_API_URL}login-user`;
    fetch(url, {
      method: 'POST',
      body: JSON.stringify({
      emailId: formData?.emailId,
      password: formData?.password,
      }),
      headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      },
    })
    .then((response) => {
      if (response.status === 200) {
        return response.text();
      } else {
        setHelperTextMessage('Invalid email ID or password.');
        throw new Error('Invalid email ID or password.');
      }
    })
    .then((resp) => {
      if (resp) {
        window.sessionStorage.setItem('AccessToken', resp);
        window.location.assign('/dashboard'); 
      } else {
        setHelperTextMessage('Invalid email ID or password.');
      }
    })
    .catch((e) => {
      setHelperTextMessage(e.message);
    });
  }
}

export const getCompanyListName = (setCompanyName: (companyNames: string[]) => void): Promise<void> => {
  const url = `${PUBLIC_API_URL}company-list`;
  return fetch(url, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Accept': 'application/json',
    },
  })
  .then((response) => response.json())
  .then ((resp) => {
    setCompanyName(resp);
  })
  .catch(() => {
    setCompanyName([]);
  });
}