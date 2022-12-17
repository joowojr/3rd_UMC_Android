package com.example.week7_challenge

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.week7_challenge.databinding.ItemMusicBinding

data class Music(
    var artist : String,
    var title : String?,
    var file : Int,
    var album : Int

)
class ListAdapter (var musicList :ArrayList<Music>): RecyclerView.Adapter<ListAdapter.MusicViewHolder>() {

    inner class MusicViewHolder(val binding: ItemMusicBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(music: Music){
                    binding.tvArtist.text = music.artist
                    binding.tvTitle.text = music.title
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val binding = ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MusicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bind(musicList[position])
        holder.itemView.setOnClickListener{
            val intent= Intent(holder.itemView?.context, PlayActivity::class.java)
            intent.putExtra("title", holder.binding.tvTitle.text.toString())
            intent.putExtra("artist", holder.binding.tvArtist.text.toString())
            intent.putExtra("album", musicList[position].album)
            intent.putExtra("file", musicList[position].file)
            intent.putExtra("position",position)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

    override fun getItemCount(): Int = musicList.size

}