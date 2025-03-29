export const handleRegistrationBtnText = (otpSent: boolean, otpVerified: boolean):string => { 
    let TxtVal = 'Send OTP to register';
    if (otpSent) {
        TxtVal = 'Send OTP again';
    }
    if(otpVerified){
        TxtVal = 'Register';
    }
    return TxtVal;
 };

 export const handleRegistrationBtnClick = (setOtpSent: (value: boolean) => void, setOtpVerified: (value: boolean) => void):void => {
    setOtpSent(true)
    setOtpVerified(true)
 };