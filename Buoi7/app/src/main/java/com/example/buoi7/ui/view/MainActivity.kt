package com.example.buoi7.ui.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buoi7.databinding.ActivityMainBinding
import com.example.buoi7.databinding.DialogAddExpenseBinding
import com.example.buoi7.ui.adapter.ExpenseAdapter
import com.example.buoi7.ui.viewmodel.ExpenseViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewmodel: ExpenseViewModel
    private lateinit var adapter: ExpenseAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ExpenseAdapter { expense ->
            viewmodel.deleteExpense(expense)
        }

        binding.recyclerView.adapter = adapter

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        viewmodel = ViewModelProvider(this)[ExpenseViewModel::class.java]

        viewmodel.allExpenses.observe(this) {
            adapter.setExpense(it)
        }

        viewmodel.totalExpense.observe(this) {
            binding.tvTotal.text = "Tổng bill: $${it ?: 0.0}"
        }

        binding.fabAdd.setOnClickListener {
            showAddExpenseDialog()
        }


//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }

    private fun MainActivity.showAddExpenseDialog() {
        val dialogBinding = DialogAddExpenseBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this).setTitle("Add Expense").setView(dialogBinding.root)
            .setPositiveButton("Add") { _, _ ->
                val title = dialogBinding.etTitle.text.toString()
                val amount = dialogBinding.etAmount.text.toString().toDoubleOrNull() ?: 0.0
                val category = dialogBinding.etCategory.text.toString()
                val date = SimpleDateFormat("dd MMM YYYY", Locale.getDefault()).format(Date())

                val expense = com.example.buoi7.data.model.ExpenseModel(
                    title = title, amount = amount, category = category, date = date
                )

                viewmodel.insertExpense(expense)
            }.setNegativeButton("Cancel", null).create()
        dialog.show()

    }

}