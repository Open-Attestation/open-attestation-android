package com.example.open_attestation_android_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import com.example.open_attestation_android.OpenAttestation

class MainActivity : AppCompatActivity() {
    private lateinit var testVerifyButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testVerifyButton = findViewById<Button>(R.id.testVerifyButton)
        testVerifyButton.setOnClickListener {
            val file_name = "national-youth-council_obs-moc.oa"
            val oadoc = application.assets.open(file_name).bufferedReader().use{
                it.readText()
            }

            val oa = OpenAttestation()
            oa.verifyDocument(this, oadoc) { isValid ->
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
    }
}