package com.example.finalproject.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Model.Barang
import com.example.finalproject.databinding.ListitemBinding

class MainAdapter (val barang: MutableList<Barang>,val listener: MainAdapter.OnClick):RecyclerView.Adapter<MainAdapter.BarangViewHolder>(){

    inner class BarangViewHolder(val Binding: ListitemBinding):RecyclerView.ViewHolder(Binding.root)
    interface OnClick{
        fun OnRead(barang: Barang)
        fun OnUpdate(barang: Barang)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {
        return BarangViewHolder(ListitemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        holder.Binding.apply {
            tvTittle.text = barang[position].nama
            setSekotak.setOnClickListener {
                listener.OnRead(barang[position])   //setSekotak
            }

            btnUpdatePen.setOnClickListener{
                listener.OnUpdate(barang[position])
            }

        }

    }

    override fun getItemCount(): Int {
        return barang.size
    }
}