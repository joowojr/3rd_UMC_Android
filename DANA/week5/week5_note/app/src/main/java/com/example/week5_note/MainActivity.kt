package com.example.week5_note

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week5_note.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var getResultText: ActivityResultLauncher<Intent>
    private lateinit var viewBinding: ActivityMainBinding
    var dataList = arrayListOf<Data>()

    override fun onCreate(savedInstanceState: Bundle?) {
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        viewBinding.rvData.layoutManager = LinearLayoutManager(this)
        viewBinding.rvData.adapter = DataRVAdapter(dataList, onClickDeleteIcon = {deleteItem(it)})

        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            result ->
            val saved_title = result.data?.getStringExtra("Title").toString()
            val saved_desc = result.data?.getStringExtra("Desc").toString()
            if (result.resultCode== RESULT_OK) {
               dataList.add(Data(saved_title,saved_desc))
               viewBinding.rvData.adapter?.notifyItemInserted(dataList.size)
            }
            if (result.resultCode==1){
                val position = Integer.parseInt(result.data?.getStringExtra("position"))
                dataList[position].title = saved_title
                dataList[position].desc = saved_desc
                viewBinding.rvData.adapter?.notifyItemChanged(position)
            }
            }


        //메모 추가
        viewBinding.btnWrite.setOnClickListener {
            val intent = Intent(this, WriteActivity::class.java)
            getResultText.launch(intent)
        }


    }

    fun deleteItem(data: Data){
        dataList.remove(data)
        viewBinding.rvData.adapter?.notifyDataSetChanged()
    }
}