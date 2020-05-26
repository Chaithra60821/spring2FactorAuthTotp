# spring2FactorAuthTotp

TWO Factor authentication:

Two factor authentication will add a extra layer of security to web application.
Two factor auth more secure, because a criminal cannot access the user’s account unless they have access to both the user’s regular password and one time password. As one time password will get expire it will hard for the hacker to decode the password.

Currently, there are two widely used methods to get that one time password:
1.	SMS-based: In this method, every time the user logs in, they receive a text message to their registered phone number, which contains a One Time Password.
2.	TOTP-based: In this method, while enabling 2-factor authentication, the user is asked to scan a QR image using a specific smartphone application.
That application then continuously generates the One Time Password for the user.

The SMS-based easy, but it has its own problems, like waiting for the SMS on every login attempt, security issues, and so on. The TOTP-based method is becoming popular because of it’s advantages over the SMS-based method. 
 There are various methods of implementing 2-factor authentication, and TOTP (the Time-based One-Time Password algorithm) authentication is one of them.
Ex : google, facebook and git hub etc


How the TOTP-based method works.
	To implement two step verification our password should be changing on time
	Steps to implement
1. Backend server creates a secret key for that particular user.
2. Server then shares that secret key with the user’s phone application.
3. Phone application initializes a counter.
4. Phone application generate a one time password using that secret key and counter.
5. Phone application changes the counter after a certain interval and regenerates the one time password making it dynamic.


HOW TOtp is implemented 
1.	Generate password using secret key and counter
2.	Keeping track of the counter

Generate password using secret key and counter:
HOTP : HOTP defines an algorithm to create a one time password from a secret key and a counter.
1.	Obtain HMAC hash (using SHA-1 hashing algorithm) by secretKey and counter :  hmacHash = HMAC-SHA-1(secretKey, counter);

2.	 In this code, the output would be a 20 byte long string. That long string is not suitable as a one time password. So we need a way to truncate that string.
         a.	string.offset = hmacHash[19] & 0xf;
         b.	truncatedHash = (hmacHash[offset++] & 0x7f) << 24 | (hmacHash[offset++] & 0xff) << 16 | (hmacHash[offset++] & 0xff) << 8 | (hmacHashh[offset++] & 0xff) [Here we are taking we concatenate the bytes from hmacHash[offset] to hmacHash[offset+3]
         c.	finalOTP = (truncatedHash % (10 ^ numberOfDigitsRequiredInOTP));
The disadvanatage of this is we have keep the counter and this otp will be valid until it will be used to over come this they had come up with the TOTP



Keeping track of the counter
A TOTP uses the HOTP algorithm to obtain the one time password. The only difference is that it uses “Time” in the place of “counter,” 
That means that instead of initializing the counter and keeping track of it, we can use time as a counter in the HOTP algorithm to obtain the OTP. As a server and phone both have access to time, neither of them has to keep track of the counter.
counter = currentUnixTime / 30 – every thirty seconds this will return new values. Here we always take a lowerbound
	To resolve the time issue this algo will use a unix time
