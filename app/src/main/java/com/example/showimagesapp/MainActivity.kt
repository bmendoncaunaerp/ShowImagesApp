package com.example.showimagesapp

import android.Manifest.permission.READ_MEDIA_IMAGES
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.showimagesapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            onPermissionResult(isGranted)
        }

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { image ->
            showImage(image)
        }

    private val imageUrls = listOf(
        "https://www.gstatic.com/webp/gallery/1.jpg",
        "https://www.gstatic.com/webp/gallery/2.jpg",
        "https://www.gstatic.com/webp/gallery/3.jpg",
        "https://www.gstatic.com/webp/gallery/4.jpg",
        "https://www.gstatic.com/webp/gallery/5.jpg",
        "https://www.gstatic.com/webp/gallery3/1.png",
        "https://www.gstatic.com/webp/gallery3/2.png",
        "https://www.gstatic.com/webp/gallery3/3.png",
        "https://www.gstatic.com/webp/gallery3/4.png",
        "https://www.gstatic.com/webp/gallery3/5.png",
        "https://www.gstatic.com/webp/gallery3/6.png",
        "https://www.gstatic.com/webp/gallery3/7.png",
        "https://www.gstatic.com/webp/gallery3/8.png",
        "https://www.gstatic.com/webp/gallery3/9.png",
        "https://www.gstatic.com/webp/gallery3/10.png",
        "https://www.gstatic.com/webp/gallery3/11.png",
        "https://www.gstatic.com/webp/gallery3/12.png",
        "https://www.gstatic.com/webp/gallery/1.sm.jpg",
        "https://www.gstatic.com/webp/gallery/2.sm.jpg",
        "https://www.gstatic.com/webp/gallery/3.sm.jpg",
        "https://www.gstatic.com/webp/gallery/4.sm.jpg",
        "https://www.gstatic.com/webp/gallery/5.sm.jpg",
        "https://www.gstatic.com/webp/gallery3/1.sm.png",
        "https://www.gstatic.com/webp/gallery3/2.sm.png",
        "https://www.gstatic.com/webp/gallery3/3.sm.png",
        "https://www.gstatic.com/webp/gallery3/4.sm.png",
        "https://www.gstatic.com/webp/gallery3/5.sm.png",
        "https://www.gstatic.com/webp/gallery3/6.sm.png",
        "https://www.gstatic.com/webp/gallery3/7.sm.png",
        "https://www.gstatic.com/webp/gallery3/8.sm.png",
        "https://www.gstatic.com/webp/gallery3/9.sm.png",
        "https://www.gstatic.com/webp/gallery3/10.sm.png",
        "https://www.gstatic.com/webp/gallery3/11.sm.png",
        "https://www.gstatic.com/webp/gallery3/12.sm.png",
        "https://www.gstatic.com/webp/gallery4/1.png",
        "https://www.gstatic.com/webp/gallery4/2.png",
        "https://www.gstatic.com/webp/gallery4/3.png",
        "https://www.gstatic.com/webp/gallery4/4.png",
        "https://www.gstatic.com/webp/gallery4/5.png",
        "https://www.gstatic.com/webp/gallery4/6.png",
        "https://www.gstatic.com/webp/gallery4/7.png",
        "https://www.gstatic.com/webp/gallery4/8.png",
        "https://www.gstatic.com/webp/gallery4/9.png",
        "https://www.gstatic.com/webp/gallery4/10.png",
        "https://www.gstatic.com/webp/gallery4/11.png",
        "https://www.gstatic.com/webp/gallery4/12.png",
        "https://www.gstatic.com/webp/gallery4/13.png"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        showImage()

        binding.btnUpdateImage.setOnClickListener {
            showImage()
        }

        binding.btnSelectImage.setOnClickListener {
            selectImage()
        }
    }

    private fun selectImage() {
        when {
            // Caso permissão já tenha sido concedida, executa ação
            ContextCompat.checkSelfPermission(
                this,
                READ_MEDIA_IMAGES
            ) == PackageManager.PERMISSION_GRANTED -> {
                getContent.launch("image/*")
            }

            // Caso permissão tenha sido negada uma vez, exibe explicação e possibilita autorizar
            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                READ_MEDIA_IMAGES
            ) -> {
                showPermissionExplanation(requestAgain = true)
            }

            // Caso contrário solicita permissão
            else -> {
                requestPermissionLauncher.launch(READ_MEDIA_IMAGES)
            }
        }
    }

    private fun onPermissionResult(isGranted: Boolean) {
        if (isGranted) {
            // Caso permissão tenha sido concedida acessa recurso
            getContent.launch("image/*")
        } else {
            // Caso contrário exibe mensagem explicando necessidade da permissão
            showPermissionExplanation(requestAgain = false)
        }
    }

    private fun showPermissionExplanation(requestAgain: Boolean) {
        val snackbar = Snackbar.make(
            findViewById(android.R.id.content),
            "Precisamos da sua permissão para selecionar uma imagem da galeria",
            Snackbar.LENGTH_LONG
        )
        if (requestAgain) {
            snackbar.setAction("Permitir") {
                requestPermissionLauncher.launch(READ_MEDIA_IMAGES)
            }
        }
        snackbar.show()
    }

    private fun showImage(image: Any? = null) {
        Glide.with(this)
            .load(image ?: imageUrls.random())
            .centerCrop()
            .error(R.drawable.ic_error)
            .placeholder(R.drawable.progress_animation)
            .into(binding.imgImages)
    }
}