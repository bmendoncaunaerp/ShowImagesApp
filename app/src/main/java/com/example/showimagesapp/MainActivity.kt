package com.example.showimagesapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.showimagesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
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
    }

    private fun showImage() {
        Glide.with(this)
            .load(imageUrls.random())
            .centerCrop()
            .error(R.drawable.ic_error)
            .placeholder(R.drawable.progress_animation)
            .into(binding.imgImages)
    }
}