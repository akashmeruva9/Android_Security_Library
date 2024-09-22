package com.androidarmour.security_library

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.security.MessageDigest

class CheatToolDetectionExample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cheat_tool_detection_example)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

fun detectCheatTools(context: Context) {
    val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val runningProcesses = activityManager.runningAppProcesses
    runningProcesses?.forEach { processInfo ->
        val processName = processInfo.processName
        // List of known cheat tools like GameGuardian
        if (processName.contains("gameguardian")) {
            // Handle cheat tool detection
            stopAppFunctionality()
        }
    }
}

fun verifyAPKIntegrity(context: Context): Boolean {
    val apkFile = File(context.applicationInfo.sourceDir)
    val digest = MessageDigest.getInstance("SHA-256")
    val apkInputStream = apkFile.inputStream()
    val buffer = ByteArray(1024)
    var bytesRead: Int
    while (apkInputStream.read(buffer).also { bytesRead = it } != -1) {
        digest.update(buffer, 0, bytesRead)
    }
    val currentChecksum = digest.digest().joinToString("") { "%02x".format(it) }

    // Replace with your APK's original checksum
    val originalChecksum = "your_original_apk_checksum"
    return currentChecksum == originalChecksum
}

fun stopAppFunctionality() {
    // Logic to stop the app or block its functionality
}
