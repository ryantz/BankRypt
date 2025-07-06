t# JWT token

Json Web Token

## Why is it used?

- Efficient and Simple
- Compact URL-safe method of representing claims bewtween two parties
- Can significantly enhance project's security and user experience

## What is a JWT?

- Open standard used to create compact, self-contained tokens
- Used for securely transmitting information between different applications or services
- Tokens typically used for authentication and authorization
- Contains information that verifies the identity of a user + their permissions

## Structure of a JWT

Three main components (Base64Url encode strings concatenated with periods):

1. **Header**

   - JSON object that typically contains two properties:

     1. Type of token (JWT)
     2. Encryption algorithm used (HMAC, SHA256, RSA ..etc)

   ```json
   {
     "alg": "HS256",
     "type": "JWT"
   }
   ```

2. **Payload**

   - JSON object where all transmitted data lives
   - Aka claim
   - Typically contains:

     1. User information: username, email address
     2. Session data: IP address, time, last login
     3. Authorization permissions: roles, groups user belong to

   - Four types of claims:

     - Commonly used:

       1. Registered
       2. Public

     - Not commonly used:

       1. Private
       2. Custom

   ```json
   {
     "drn": "DS",
     "exp": 1680902696,
     "rexp": "2023-05-05T21:13:56Z"
   }
   ```

3. **Signature**

   - Created by signing the BAse64Url encoded header and payload with a scret key and algorithm
   - Secret key and Algorithm specified by dev
   - Used to verify that the sender of the JWT is who they calim to be
   - Ensures token's integrity
   - Prevents tampering with the message after issuing

   ```text
   HMACSHA256(
        base64UrlEncode(header) + "." +
        base64UrlEncode(payload),
        secret
   )
   ```

In the format of:
HHHH.PPPP.SSSS

These three components help devs build:

- A stateless authentication/authorization flow
- Easily scalable and eliminates the need for servers to maintain session information

## How does a JWT work?
