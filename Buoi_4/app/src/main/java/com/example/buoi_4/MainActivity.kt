package com.example.buoi_4

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.buoi_4.databinding.ActivityMainBinding
import android.content.Intent




class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

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

        binding.btnLoggin.setOnClickListener {
            val intent = Intent(this, LogginResult::class.java)

            val bundle: Bundle = Bundle()
            bundle.putString("username", binding.edtUsername.text.toString())
            bundle.putString("password", binding.edtPassword.text.toString())

            intent.putExtra("account", bundle)

            startActivity(intent)

        }
    }
}