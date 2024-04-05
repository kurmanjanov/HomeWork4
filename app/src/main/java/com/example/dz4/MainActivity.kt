package com.example.Dz4

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.dz4.R

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var image: ImageView
    private lateinit var editText: EditText

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            image.setImageURI(uri)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = this.findViewById(R.id.button_whatsaap)
        image = this.findViewById(R.id.image)
        editText = this.findViewById(R.id.number)
        goToWhatsapp()
    }

    private fun goToWhatsapp() {
        button.setOnClickListener {
            val phoneNumber = editText.text.toString().trim()
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://api.whatsapp.com/send?phone=$phoneNumber")
            startActivity(intent)
        }
        image.setOnClickListener {
            openGallery()
        }
    }

    private fun openGallery() {
        getContent.launch("image/*")
    }
}