package com.example.myapplicationsearch.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationsearch.data.model.Item
import com.example.myapplicationsearch.data.model.ItemProduct
import com.example.myapplicationsearch.data.model.ProductDetail
import com.example.myapplicationsearch.databinding.ItemProductBinding
import com.squareup.picasso.Picasso
import java.util.ArrayList

class ProductAdapter (private val products: ArrayList<ProductDetail>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        var binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    inner class ProductViewHolder(view: ItemProductBinding) : RecyclerView.ViewHolder(view.root) {
        var mBinding = view
        fun bind(product: ProductDetail) {
            mBinding.product = product
            mBinding.textView2.text = "$" + product.primaryOffer.maxPrice
            Picasso.get().load(product.imageUrl).into(mBinding.imageView);
        }
    }

}