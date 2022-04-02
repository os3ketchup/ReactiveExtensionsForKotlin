package com.example.reactiveextensionsforkotlin.models.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reactiveextensionsforkotlin.databinding.ItemRvBinding

class MyAdapter(private var list: List<com.example.reactiveextensionsforkotlin.models.Consumer>): RecyclerView.Adapter<MyAdapter.VH>() {

    inner class VH(private var itemRV: ItemRvBinding):RecyclerView.ViewHolder(itemRV.root){
        fun onBind(consumer: com.example.reactiveextensionsforkotlin.models.Consumer){
                itemRV.name.text = consumer.name
                itemRV.number.text = consumer.number
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
