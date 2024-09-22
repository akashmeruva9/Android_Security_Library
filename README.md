# 1. Code Protection (DEX, SO, DLL Secured) :

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
