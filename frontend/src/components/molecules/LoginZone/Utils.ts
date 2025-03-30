import { PUBLIC_API_URL } from "../../../constants"

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
    formData: { [key: string]: string },
    helperTextMessage: string
  ): Promise<void> => {
    if (formData.emailId === '') {
      setHelperTextMessage('Please enter your email ID.');
      return;
    } else if (formData.emailId !== '' && helperTextMessage !== `It's time to check your inbox for OTP.` && helperTextMessage !== `Entered OTP is invalid.`) {
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
  } else if (formData.otpValue === '') {
    setHelperTextMessage('Entered OTP is invalid.');
    return;
  } else {
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
  }
}