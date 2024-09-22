package com.androidarmour.security_library

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.Request

class NetworkPacketSniffingExample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_network_packet_sniffing_example)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // TLS Encryption
        val client = OkHttpClient.Builder()
            .build()

        val request = Request.Builder()
            .url("https://yourserver.com/api")
            .build()

        val response = client.newCall(request).execute()

        //  Certificate Pinning
        val client1 = OkHttpClient.Builder()
            .certificatePinner(
                CertificatePinner.Builder()
                    .add("yourdomain.com", "sha256/your_certificate_fingerprint")
                    .build()
            )
            .build()

        val request1 = Request.Builder()
            .url("https://yourdomain.com/api")
            .build()

        val response1 = client1.newCall(request1).execute()
    }
}