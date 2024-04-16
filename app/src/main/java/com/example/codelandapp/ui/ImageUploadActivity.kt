package com.example.codelandapp.ui

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.codelandapp.R
import com.example.codelandapp.databinding.ActivityImageUploadBinding

class ImageUploadActivity : AppCompatActivity() {
    private lateinit var binding : ActivityImageUploadBinding
    private var uploadedImage: ByteArray? = null
    private val PICK_IMAGE_REQUEST = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityImageUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnUpload.setOnClickListener {
            openImagePicker()
        }

        binding.btnView.setOnClickListener {
            uploadedImage?.let { image->
                displayImage(image)
            }
        }
    }

    private fun openImagePicker() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {

            val imageUri = data.data

            uploadedImage = contentResolver.openInputStream(imageUri!!)?.readBytes()

        }
    }

    private fun displayImage(image: ByteArray) {

        val bitmap = BitmapFactory.decodeByteArray(image, 0, image.size)

        val imageView = ImageView(this)
        imageView.setImageBitmap(bitmap)

        binding.flImageUpload.removeAllViews()

        binding.flImageUpload.addView(imageView)
    }
}