package com.dhobidash.secrets

class NativeLib {

    /**
     * A native method that is implemented by the 'secrets' native library,
     * which is packaged with this application.
     */
    external fun getApiKeyValue(keyName: String): String

    companion object {
        // Used to load the 'secrets' library on application startup.
        init {
            System.loadLibrary("secrets")
        }
    }
}