package com.openattestation.open_attestation_android_example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.openattestation.open_attestation_android_example.R
import com.openattestation.open_attestation_android.OaRendererActivity
import com.openattestation.open_attestation_android.OpenAttestation

class MainActivity : AppCompatActivity() {
    private lateinit var testVerifyButton: Button
    private lateinit var testViewDocumentButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val filename = "sample.oa"
        val document = application.assets.open(filename).bufferedReader().use{
            it.readText()
        }

        setContentView(R.layout.activity_main)
        testVerifyButton = findViewById<Button>(R.id.testVerifyButton)
        testVerifyButton.setOnClickListener {

            val oa = OpenAttestation()
            oa.verifyDocument(this, document) { isValid ->
                if (isValid) {
                    val alertDialogBuilder = AlertDialog.Builder(this)
                    alertDialogBuilder.setTitle("Verification successful")
                    alertDialogBuilder.setMessage("This document is valid")
                    alertDialogBuilder.setPositiveButton("Dismiss", null)
                    alertDialogBuilder.show()
                }
                else {
                    val alertDialogBuilder = AlertDialog.Builder(this)
                    alertDialogBuilder.setTitle("Verification failed")
                    alertDialogBuilder.setMessage("This document has been tampered with")
                    alertDialogBuilder.setPositiveButton("Dismiss", null)
                    alertDialogBuilder.show()
                }
            }
        }

        testViewDocumentButton = findViewById<Button>(R.id.testViewDocumentButton)
        testViewDocumentButton.setOnClickListener {
            val intent = Intent(this, OaRendererActivity::class.java)
            intent.putExtra(OaRendererActivity.OA_DOCUMENT_KEY, document)
            intent.putExtra(OaRendererActivity.OA_DOCUMENT_FILENAME_KEY, filename)
            startActivity(intent)
        }
    }
}