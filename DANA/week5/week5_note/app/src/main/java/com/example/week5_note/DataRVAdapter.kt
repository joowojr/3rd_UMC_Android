package com.example.week5_note

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.week5_note.databinding.ItemDataBinding

data class Data (
    var title: String?,
    var desc :String?
)

class DataRVAdapter(private val dataList: ArrayList<Data>, val onClickDeleteIcon: (item: Data) -> Unit):
    RecyclerView.Adapter<DataRVAdapter.DataViewHolder>() {

    // Viewholder 객체
    inner class DataViewHolder(val viewBinding: ItemDataBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: Data) {
            viewBinding.tvTitle.text = data.title
            viewBinding.tvDesc.text = data.desc

        }
    }

    //ViewHoler 만들어질 때 실행할 동작
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(viewBinding)
    }

   // ViewHolder가 실제로 데이터를 표시해야 할 때 호출되는 함수
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
       holder.bind(dataList[position])
       holder.viewBinding.btnDel.setOnClickListener {
           onClickDeleteIcon.invoke(dataList[position])
       }

       //수정하기
       holder.itemView.setOnClickListener {
           val intent = Intent(holder.itemView?.context, WriteActivity::class.java)
           intent.putExtra("Title", holder.viewBinding.tvTitle.text.toString())
           intent.putExtra("Desc", holder.viewBinding.tvDesc.text.toString())
           intent.putExtra("position", position.toString())
           ContextCompat.startActivity(holder.itemView.context, intent, null)

       }

   }


    // 표현할 Item의 총 갯수
   override fun getItemCount(): Int = dataList.size



}