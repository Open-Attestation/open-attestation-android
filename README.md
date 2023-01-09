# open-attestation-android
The open-attestation-android library allows Android app developers to build apps that can verify and view [OpenAttestation](https://www.openattestation.com/) documents

## Usage
### verifyDocument
`verifyDocument` takes a wrapped document and performs a [verifysignature](https://github.com/Open-Attestation/open-attestation#verify-signature-of-document) on it. A boolean indicating if the document is valid is returned as a parameter to the input completion handler.

```
    import com.openattestation.open_attestation_android.OpenAttestation
    
    ...
    
    val oa = OpenAttestation()
    oa.verifyDocument(context, document) { isValid ->
        if (isValid) {
            // Document is valid
        }
    }
```
### OaRendererActivity
`OaRendererActivity` is an Activity that takes a wrapped document and renders the document template if the provided document is valid.
```
    import com.openattestation.open_attestation_android.OaRendererActivity
    
    ...
    
    val intent = Intent(this, OaRendererActivity::class.java)
    intent.putExtra(OaRendererActivity.OA_DOCUMENT_KEY, document)
    intent.putExtra(OaRendererActivity.OA_DOCUMENT_FILENAME_KEY, filename)
    startActivity(intent)
```

## Example

To run the example project, open the project with Android Studio and run the `open-attestation-example` app

## Installation

open-attestation-android library is available through [JitPack](https://jitpack.io/). To install
it, follow these steps:

### Add the JitPack repository

#### For Gradle versions 6.8 and above
In Settings.gradle of your app:
```ruby
dependencyResolutionManagement {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
#### For Gradle versions below 6.8
In your root build.gradle:
```ruby
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

### Add the open-attestation-android library as a dependency to your app
In your app build.gradle:
```ruby
dependencies {
    ...
    implementation 'com.github.Open-Attestation:open-attestation-android:<VERSION>'
}
```
You can find available versions on https://github.com/Open-Attestation/open-attestation-android/releases
Example: `com.github.Open-Attestation:open-attestation-android:1.0.0-beta`

### Add OaRendererActivity to your app manifest
In AndroidManifest.xml:
```ruby
<application
    ...
    <activity
        android:name="com.openattestation.open_attestation_android.OaRendererActivity"
        android:exported="false">
        <meta-data
            android:name="android.app.lib_name"
            android:value="" />
    </activity>
</application>
```

## Sample App
### OA Wallet Android
A sample app that uses this library to interact with OpenAttestation documents can be found on https://github.com/Open-Attestation/oa-wallet-android

## License

open-attestation-android is available under the Apache-2.0 license. See the LICENSE file for more info.