package gachon.third.umc.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gachon.third.umc.android.databinding.HlAddBinding
import gachon.third.umc.android.databinding.HlNoneBinding

data class HighlightData (
    val text : String,
    val type : Int
)

class HighlightAdapter (private val dataList: ArrayList<HighlightData>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class AddViewHolder(val binding: HlAddBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: HighlightData) {
            binding.tvNew.text = data.text
        }
    }
    inner class NoneViewHolder(val binding: HlNoneBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: HighlightData) {
            binding.tvNone.text= data.text
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            multi_type1 ->{
            val binding = HlAddBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            AddViewHolder(binding)
        }
            multi_type2 ->{
            val binding = HlNoneBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            NoneViewHolder(binding)
        }
            else -> {
                val binding = HlNoneBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                NoneViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AddViewHolder) {
                holder.bind(dataList[position])
            }
        else if (holder is NoneViewHolder){
                holder.bind(dataList[position])
            }
        }
    override fun getItemViewType(position: Int): Int {
        return dataList[position].type
    }

    override fun getItemCount(): Int = dataList.size
}