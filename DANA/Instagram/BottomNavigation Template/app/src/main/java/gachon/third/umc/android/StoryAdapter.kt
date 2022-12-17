package gachon.third.umc.android

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import gachon.third.umc.android.databinding.StoryAddBinding
import gachon.third.umc.android.databinding.StoryNewBinding
import kotlinx.coroutines.NonDisposableHandle.parent

data class StoryData (
    val userName: String,
    val profileImg : Int,
    val storyImg : Int,
    val time : String,
    val type : Int
)

class StoryAdapter (private val storyList: ArrayList<StoryData>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class AddViewHolder(val binding: StoryAddBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: StoryData) {
            binding.tvUsername.text = data.userName
            binding.imgProfile.setImageResource(data.profileImg)
        }
    }
    inner class NewViewHolder(val binding: StoryNewBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: StoryData) {
            binding.tvUsername.text = data.userName
            binding.imgProfile.setImageResource(data.profileImg)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("viewType", "$viewType");
        return when (viewType){
            multi_type1-> {
                val binding = StoryAddBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                AddViewHolder(binding)
            }
            multi_type2->{
                val binding = StoryNewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                NewViewHolder(binding)
            }

            else->{
                val binding = StoryNewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                NewViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       if (holder is AddViewHolder) {
               holder.bind(storyList[position])
           holder.itemView.setOnClickListener{
               val intent= Intent(holder.itemView?.context, StoryViewActivity::class.java)
               intent.putExtra("userName", storyList[position].userName)
               intent.putExtra("profileImg",storyList[position].profileImg)
               intent.putExtra("storyImg",storyList[position].profileImg)
               ContextCompat.startActivity(holder.itemView.context, intent, null)
           }
           }
        else if (holder is NewViewHolder){
               holder.bind(storyList[position])
           holder.itemView.setOnClickListener{
               val intent= Intent(holder.itemView?.context, StoryViewActivity::class.java)
               intent.putExtra("userName", storyList[position].userName)
               intent.putExtra("profileImg",storyList[position].profileImg)
               intent.putExtra("storyImg",storyList[position].storyImg)
               intent.putExtra("time",storyList[position].time)
               ContextCompat.startActivity(holder.itemView.context, intent, null)
           }}

       }
    override fun getItemViewType(position: Int): Int {
        return storyList[position].type
    }
    override fun getItemCount(): Int = storyList.size

}