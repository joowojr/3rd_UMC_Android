package com.example.week8

import android.R
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.week8.databinding.ItemDataBinding



class DataRVAdapter(private val memoList: MutableList<Memo>,val onClickDeleteIcon:
            (memo: Memo)-> Unit, val context: Context/*, val onClickBMIcon:(memo: Memo) -> Unit*/):
    RecyclerView.Adapter<DataRVAdapter.MemoViewHolder>() {
    private val sharedPrefs = context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
    private val editor = sharedPrefs.edit()
    var memoDb = MemoDatabase.getInstance(context)
    // Viewholder 객체
    inner class MemoViewHolder(val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(memo: Memo) {
            binding.tvTitle.text = memo.title
            binding.tvDesc.text = memo.content
            if(memo.bookmark)
                binding.btnBm.setImageResource(R.drawable.btn_star)
            else
                binding.btnBm.setImageResource(R.drawable.star_off)

        }
    }

    //ViewHoler 만들어질 때 실행할 동작
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val viewBinding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemoViewHolder(viewBinding)
    }

   // ViewHolder가 실제로 데이터를 표시해야 할 때 호출되는 함수
    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
       holder.bind(memoList[position])

       holder.itemView.setOnClickListener {
           val intent = Intent(holder.itemView.context, WriteActivity::class.java)
           intent.putExtra("Title", holder.binding.tvTitle.text.toString())
           intent.putExtra("Content", holder.binding.tvDesc.text.toString())
           intent.putExtra("position", position)
           ContextCompat.startActivity(holder.itemView.context, intent, null)

       }

       holder.binding.btnDel.setOnClickListener {
           onClickDeleteIcon.invoke(memoList[position])
       }
       val memo = memoList[position]
       holder.binding.btnBm.setOnClickListener {
           if(sharedPrefs.getBoolean("${memo.id}", false)) {
               holder.binding.btnBm.setImageResource(R.drawable.star_off)
               memo.bookmark = false
               memoDb?.memoDao()?.updateBmById(memo.id,memo.bookmark)
               editor.putBoolean("${memo.id}", false)
               editor.apply()
               Log.d("bm",memo.bookmark.toString())
           }

           else {
               holder.binding.btnBm.setImageResource(R.drawable.btn_star)
               memo.bookmark = true
               memoDb?.memoDao()?.updateBmById(memo.id,memo.bookmark)
               editor.putBoolean("${memo.id}", true)
               editor.apply()
               Log.d("bm",memo.bookmark.toString())
           }
       }
   }


    // 표현할 Item의 총 갯수
   override fun getItemCount(): Int = memoList.size



}