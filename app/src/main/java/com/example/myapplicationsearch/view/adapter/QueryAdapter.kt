package com.example.myapplicationsearch.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationsearch.data.model.QuerySearch
import com.example.myapplicationsearch.databinding.ItemSearchBinding
import java.util.*

class QueryAdapter(private val queries: List<QuerySearch>) : RecyclerView.Adapter<QueryAdapter.QueryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueryViewHolder {
        var binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QueryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return queries.size
    }

    override fun onBindViewHolder(holder: QueryViewHolder, position: Int) {
        holder.bind(queries[position])
    }

    inner class QueryViewHolder(view: ItemSearchBinding) : RecyclerView.ViewHolder(view.root) {
        var mBinding = view
        fun bind(query: QuerySearch) {
            mBinding.query = query
        }
    }
}