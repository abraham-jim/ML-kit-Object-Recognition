package com.example.mlkitobjectrecognition

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<takeImageButton>(R.id.imageView)
        val button = findViewById<Button>(R.id.button).setOnClickListener {
            dispathTakePictureIntent()
        }

    }
        val REQUEST_IMAGE_CAPTURE = 1

        private fun dispatchTakePictureIntent() {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            } catch (e: ActivityNotFoundException) {
                // display error state to the user
            }
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                val imageBitmap = data?.extras?.get("data") as Bitmap
                val matrix = Matrix()

                matrix.postRotate(270)
                val rotatedBitmap = Bitmap.createBitmap(
                    imageBitmap,
                    0,
                    0,
                    imageBitmap.width,
                    imageBitmap.height,
                    matrix,
                    false
                )
                val imageView = findViewById<ImageView>(R.id.imageView)
                imageView.setImageBitmap(rotatedBitmap)

            }
        }
    }