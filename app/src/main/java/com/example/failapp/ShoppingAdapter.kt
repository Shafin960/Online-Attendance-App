package com.example.failapp

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.failapp.models.Data

class ShoppingAdapter(private val context: Context,private val shopping: List<Data>) :
    RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder>() {


    private lateinit var  mListener: OnItemClickListener
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: OnItemClickListener ){
        mListener=listener
    }

    inner  class ShoppingViewHolder(itemView: View, listener: OnItemClickListener): RecyclerView.ViewHolder(itemView){
        var shopno : TextView=itemView.findViewById(R.id.shopno)
        var shopname: TextView=itemView.findViewById(R.id.shopname)
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view= LayoutInflater.from(context).inflate(R.layout.item_shopping,parent,false)
        return ShoppingViewHolder(view,mListener)
    }

    override fun getItemCount(): Int {
        return  shopping.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val shop=shopping[position]
        holder.shopno.text="Shop No: ${shop.id.toString()}"
        holder.shopname.text="Shop Name: ${shop.name}"
    }
}