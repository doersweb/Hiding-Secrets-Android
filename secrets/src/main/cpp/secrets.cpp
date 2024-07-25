#include <jni.h>
#include <string>
#include <fstream>
#include <sstream>
#include <iostream>
#include <cstdlib>
#include <unistd.h>


const std::string localPropertiesPath = "/data/local/tmp/local.properties";

std::string readLocalProperties() {

    std::ifstream file(localPropertiesPath);
    if (!file.is_open()) {
        return "";
    }

    std::stringstream buffer;
    buffer << file.rdbuf();
    std::string content = buffer.str();
    return content;
}

std::string getEnvironmentVariable(const std::string &variable) {
    const char *value = std::getenv(variable.c_str());
    return value ? std::string(value) : "";
}

std::string getSecretValue(const std::string &key) {
    std::string propertiesContent = readLocalProperties();

    if (propertiesContent.empty()) {
        // Fallback to environment variables
        return getEnvironmentVariable(key);
    }

    // Parse the propertiesContent to find the key-value pair
    std::string secretValue;
    std::istringstream stream(propertiesContent);
    std::string line;

    while (std::getline(stream, line)) {
        if (line.find(key) != std::string::npos) {
            secretValue = line.substr(line.find('=') + 1);
            break;
        }
    }

    return secretValue;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_dhobidash_secrets_NativeLib_getApiKeyValue(JNIEnv *env, jobject thiz, jstring jKey) {
    const char *key = env->GetStringUTFChars(jKey, nullptr);
    std::string secretValue = getSecretValue(key);
    env->ReleaseStringUTFChars(jKey, key);
    return env->NewStringUTF(secretValue.c_str());
}