# Android Project with Secure API Key Handling

This Android project demonstrates how to securely manage API keys and secrets by reading them from a `local.properties` file or environment variables using JNI with C++.

### Table of Contents

* [Prerequisites](#prerequisites)
* [Setup](#setup)
  * [Step 1: Clone the Repository](#step-1-clone-the-repository)
  * [Step 2: Configure local.properties](#step-2-configure-localproperties)
  * [Step 3: Build and Run the Project](#step-3-build-and-run-the-project)
* [Using Environment Variables](#using-environment-variables)
* [JNI Integration](#jni-integration)
* [Troubleshooting](#troubleshooting)
* [Usage](#usage)


### Prerequisites
* Android Studio installed on your machine
* Android NDK installed


### Setup
#### Step 1: Clone the Repository
```bash
git clone https://github.com/doersweb/Hiding-Secrets-Android.git
```

#### Step 2: Configure local.properties
Create a local.properties file in the root of your project (if it doesn’t exist) and add your API keys and secrets:
Add your API keys and secrets to the local.properties file in the following format:
```bash
API_KEY="123456AB"
ANOTHER_KEY="SUPERSECREKEY"
```

#### Step 3: Build and Run the Project
- ##### Push local.properties to the device : 
  Use the following command to push the local.properties file to the device:
  ```bash
  adb push local.properties /data/local/tmp/
  ```
- ##### Build and run the app.


### Using environment variable
If local.properties is not present, the project will fallback to using environment variables:
- Environment variables can from your pipeline env set up or you can set it up locally too.
  Set the environment variables in your shell before building and running the project:
  ```bash
  export API_KEY="123456AB"
  export ANOTHER_KEY="SUPERSECREKEY"
  ```
  Then, build and run the project as usual


### JNI Integration
The project uses JNI to securely handle API keys and secrets. The JNI code is written in C++ and is located in the cpp directory. The main JNI functions include:

- #### Reading local.properties:
  The function `getSecretValue` inside `secrets.cpp` reads the local.properties file from the `/data/local/tmp/ directory`.
- #### Fallback to environment variables:
  If the key is not found in local.properties, the code fetches it from environment variables.


### Troubleshooting
- #### Blank values:
  Ensure that the `local.properties` file is correctly pushed to the device and located at `/data/local/tmp/`.


### Usage
In your `MainActivity.kt`
  ```bash
  val nativeLib = NativeLib()
  val secretValue = nativeLib.getApiKeyValue(<Your_Api_Key_Name>)
  ```
