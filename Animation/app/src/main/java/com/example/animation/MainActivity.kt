package com.example.animation

import android.animation.AnimatorInflater
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.animation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnProperty.setOnClickListener {
            val animator = AnimatorInflater.loadAnimator(this, R.animator.property_animator)
            animator.setTarget(binding.rocket)
            animator.start()
        }

        binding.btnView.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.shake)
            binding.rocket.startAnimation(animation)
        }

        binding.btnDrawable.setOnClickListener {
            binding.flame.setImageResource(R.drawable.drawable_animation)
            val frameAnimation = binding.flame.drawable as AnimationDrawable
            frameAnimation.start()
        }
    }
}