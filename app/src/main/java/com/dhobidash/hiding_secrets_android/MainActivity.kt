package com.dhobidash.hiding_secrets_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dhobidash.hiding_secrets_android.ui.theme.Hiding_secrets_androidTheme
import com.dhobidash.secrets.NativeLib

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Hiding_secrets_androidTheme {
                val nativeLib = NativeLib()
                val apiKey = nativeLib.getApiKeyValue("API_KEY")
                val anotherApiKey = nativeLib.getApiKeyValue("ANOTHER_API_KEY")
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Display(apiKey, anotherApiKey)
                }
            }
        }
    }
}

@Composable
fun Display(firstApiKey: String, secondApiKey: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "First key is $firstApiKey!")
        Text(text = "Super Secret API key is $secondApiKey!")
    }

}