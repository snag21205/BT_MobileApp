package com.example.buoi_4

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.buoi_4.databinding.ActivityLogginResultBinding


class LogginResult : AppCompatActivity() {

    lateinit var binding: ActivityLogginResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityLogginResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_loggin_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var bundle: Bundle? = intent.getBundleExtra("account")
        binding.txtUsername.text = bundle?.getString("username")
        binding.txtPassword.text = bundle?.getString("password")

    }
}