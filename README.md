## 1. Code Protection (DEX, SO, DLL Secured) :

- To Configure Proguard and R8 in your android project, go to app level build.gradle file and change the build type in to the following :

```Kotlin
    buildTypes {
        release {
            isDebuggable = true
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isDebuggable = true
            isShrinkResources = true
            isMinifyEnabled = true
        }
    }
```

## 2. Integrity Protection (Hash Validation) :

- We can use Encrypted Shared Prefrences to encrypt sensitive data, the encryption key is stored in the Android KeyStore for additional security.
- Encrypted Shared Preferences provides a way to store key-value pairs in a SharedPreferences object that is encrypted and decrypted transparently.
- The data is secured with the Android KeyStore system, which uses a hardware-backed keystore to protect the keys used for encryption and decryption.
- To use encrpted shared prefrences add this dependency in your build.gradle file.
```Kotlin
    dependencies {
        implementation "androidx.security:security-crypto:1.1.0-alpha03"
    }
```
- To create an EncryptedSharedPreferences object, you need to provide a context and a name for the SharedPreferences file. You also need to specify the encryption algorithm to use and the keyset alias that will be used to encrypt and decrypt the data.
```Kotlin
    val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        "my_secure_prefs",
        "my_keyset_alias",
        this,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
    )
```
- Once you have created the EncryptedSharedPreferences object, you can use it just like a regular SharedPreferences object. For example, to write a string value to the preferences, you can use the putString() method:
```Kotlin
    sharedPreferences.edit().putString("my_key", "my_value").apply()
```
- To read a string value from the preferences, you can use the getString() method:
```Kotlin
    val myValue = sharedPreferences.getString("my_key", "")
```
