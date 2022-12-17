package com.example.week8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var getResultText: ActivityResultLauncher<Intent>
    private lateinit var binding:ActivityMainBinding
    var memoList = mutableListOf<Memo>()
    private lateinit var memoDb: MemoDatabase
    private lateinit var memoAdapter : DataRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //초기화
        memoDb = MemoDatabase.getInstance(this)!!
        val savedMemo = memoDb.memoDao().selectAll()
        //리스트에, 저장되어있던 데이터 세팅
        if (savedMemo.isNotEmpty()) {
            memoList.addAll(savedMemo)
        }

        var sharedPrefs = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        var editor = sharedPrefs.edit()

        memoAdapter = DataRVAdapter(memoList,onClickDeleteIcon
        ={deleteMemo(it)},this)/*, onClickBMIcon = {bookmarkMemo(it)}*/
        binding.rvData.layoutManager = LinearLayoutManager(this)
        binding.rvData.adapter = memoAdapter

        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
                result ->
            if (result.resultCode== RESULT_OK) {
                var savedTitle = result.data?.getStringExtra("Title").toString()
                var savedContent = result.data?.getStringExtra("Content").toString()
                var id = result.data!!.getIntExtra("position",0)
                var bookmark = result.data!!.getBooleanExtra("bookmark",false)
                //데이터 생성
                var memo = Memo(id, savedTitle, savedContent, bookmark, false)
                //리스트에 저장
                editor.putBoolean("$id", false)
                editor.apply()

                memoList.add(memo)
                binding.rvData.adapter?.notifyItemInserted(memoList.size)
                //데이터 베이스에 저장
                memoDb.memoDao().insert(memo)
            }

        }

        //메모 추가
        binding.btnWrite.setOnClickListener {
            val intent = Intent(this, WriteActivity::class.java)
            getResultText.launch(intent)

        }
        binding.btnStorage.setOnClickListener {
            val intent = Intent(this, StorageActivity::class.java)
            startActivity(intent)
        }

    }
    fun deleteMemo(memo:Memo){

        memoDb?.memoDao()?.delete(memo)

        memoList.remove(memo)
        binding.rvData.adapter?.notifyItemRemoved(memo.id)
        binding.rvData.adapter?.notifyDataSetChanged()
        binding.rvData.adapter?.notifyItemRangeChanged(memo.id, memoList.size);
    }


}