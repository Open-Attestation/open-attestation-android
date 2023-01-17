package com.openattestation.open_attestation_android_example

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.openattestation.open_attestation_android.OaRendererActivity
import com.openattestation.open_attestation_android.OpenAttestation

class MainActivity : AppCompatActivity() {
    private lateinit var testVerifyButton: Button
    private lateinit var testViewDocumentButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        testVerifyButton = findViewById<Button>(R.id.testVerifyButton)
        testVerifyButton.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Select document")
            alertDialogBuilder.setItems(arrayOf("sample.oa", "sample-card.oa", "sample-a4.oa","tampered.oa")
            ) { _, which ->
                when (which) {
                    0 -> {
                        verifySelectedDocument("sample.oa")
                    }
                    1 -> {
                        verifySelectedDocument("sample-card.oa")
                    }
                    2 -> {
                        verifySelectedDocument("sample-a4.oa")
                    }
                    3 -> {
                        verifySelectedDocument("tampered.oa")
                    }
                }
            }
            alertDialogBuilder.setPositiveButton("Dismiss") { _, _ ->

            }
            alertDialogBuilder.show()
        }

        testViewDocumentButton = findViewById<Button>(R.id.testViewDocumentButton)
        testViewDocumentButton.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Select document")
            alertDialogBuilder.setItems(arrayOf("sample.oa", "sample-card.oa", "sample-a4.oa","tampered.oa")
            ) { _, which ->
                when (which) {
                    0 -> {
                        viewSelectedDocument("sample.oa")
                    }
                    1 -> {
                        viewSelectedDocument("sample-card.oa")
                    }
                    2 -> {
                        viewSelectedDocument("sample-a4.oa")
                    }
                    3 -> {
                        viewSelectedDocument("tampered.oa")
                    }
                }
            }
            alertDialogBuilder.setPositiveButton("Dismiss") { _, _ ->

            }
            alertDialogBuilder.show()
        }
    }

    fun verifySelectedDocument(filename: String) {
        val document = application.assets.open(filename).bufferedReader().use{
            it.readText()
        }

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

    fun viewSelectedDocument(filename: String) {
        val document = application.assets.open(filename).bufferedReader().use{
            it.readText()
        }

        val intent = Intent(this, OaRendererActivity::class.java)
        intent.putExtra(OaRendererActivity.OA_DOCUMENT_KEY, document)
        intent.putExtra(OaRendererActivity.OA_DOCUMENT_FILENAME_KEY, filename)
        startActivity(intent)
    }
}