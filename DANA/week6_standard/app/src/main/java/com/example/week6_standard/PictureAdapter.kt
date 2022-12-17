package com.example.week6_standard

import android.media.Image
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.week6_standard.databinding.ImageListItemBinding

class PictureAdapter(var pictures : ArrayList<Int>): RecyclerView.Adapter<PictureAdapter.PictureViewHolder>() {

            inner class PictureViewHolder(val binding: ImageListItemBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(pic : Int){
                    binding.image.setImageResource(pic)
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder{
        val binding = ImageListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PictureViewHolder(binding)
    }
    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.bind(pictures[position])
    }
    override fun getItemCount(): Int = pictures.size

}

