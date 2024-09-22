package com.androidarmour.security_library

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.safetynet.SafetyNet
import org.json.JSONObject

class RootingDetectionExample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rooting_detection_example)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

fun checkSafetyNet(context: Context) {
    val client = SafetyNet.getClient(context)
    val API_KEY ="your_api_key"
    client.attest(nonce(), API_KEY)
        .addOnSuccessListener { response ->
            val jwsResult = response.jwsResult
            // Parse and verify the JWS response for device integrity
            verifySafetyNetResponse(jwsResult)
        }
        .addOnFailureListener { e ->
            // Handle error
        }
}

fun nonce(): ByteArray {
    // Generate a nonce (random value for each request)
    return "random_string".toByteArray()
}

fun verifySafetyNetResponse(jwsResult: String) {
    // Parse and verify the response to check for device integrity
    val parts = jwsResult.split(".")
    val json = JSONObject("decoded")
    val isBasicIntegrity = json.getBoolean("basicIntegrity")
    val isCTSProfileMatch = json.getBoolean("ctsProfileMatch")

    if (isBasicIntegrity && isCTSProfileMatch) {
        // Device passes SafetyNet checks (not rooted)
    } else {
        // Device is rooted or compromised
    }
}