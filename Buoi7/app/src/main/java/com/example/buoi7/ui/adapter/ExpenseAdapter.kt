package com.example.buoi7.ui.adapter

import com.example.buoi7.data.model.ExpenseModel
import com.example.buoi7.databinding.ExpenseItemBinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ExpenseAdapter (private val onDeleteClick: (ExpenseModel) -> Unit) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {
    private var expense = listOf<ExpenseModel>()
    class ExpenseViewHolder (val binding : ExpenseItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpenseViewHolder {
        val binding = ExpenseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpenseViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ExpenseViewHolder,
        position: Int
    ) {
        val expense = expense[position]
        holder.binding.tvTitle.text = expense.title
        holder.binding.tvAmount.text = expense.amount.toString()
        holder.binding.tvCategory.text = expense.category
        holder.binding.tvDate.text = expense.date

        holder.itemView.setOnClickListener {
            onDeleteClick(expense)
        }
    }

    override fun getItemCount(): Int {
        return expense.size
    }

    fun setExpense(list: List<ExpenseModel>) {
        expense = list
        notifyDataSetChanged()
    }
}


