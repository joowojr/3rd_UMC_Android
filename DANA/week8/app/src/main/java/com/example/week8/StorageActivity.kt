package com.example.week8

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week8.databinding.ActivityStorageBinding

class StorageActivity : AppCompatActivity() {
    private lateinit var getResultText: ActivityResultLauncher<Intent>
    private lateinit var binding: ActivityStorageBinding
    private lateinit var memoDb: MemoDatabase
    var memoList = ArrayList<Memo>()

    private lateinit var memoAdapter: DataRVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStorageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        memoList.clear()
        memoDb = MemoDatabase.getInstance(this)!!
        var bookmarkedMemo = memoDb.memoDao().selectByBookmark(true)
        Log.d("list", bookmarkedMemo.toString())

        if (bookmarkedMemo.isNotEmpty()) {
            memoList.addAll(bookmarkedMemo!!)

        }

        memoAdapter = DataRVAdapter(memoList, onClickDeleteIcon
        = { deleteMemo(it) }, this/*, onClickBMIcon = {bookmarkMemo(it)}*/
        )
        binding.rvData.layoutManager = LinearLayoutManager(this)
        binding.rvData.adapter = memoAdapter

        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                var savedTitle = result.data?.getStringExtra("Title").toString()
                var savedContent = result.data?.getStringExtra("Content").toString()
                var id = result.data!!.getIntExtra("position", 0)
                var bookmark = result.data!!.getBooleanExtra("bookmark", false)
                //데이터 생성
                var memo = Memo(id, savedTitle, savedContent, bookmark, false)
                //리스트에 저장
                memoList.add(memo)
                binding.rvData.adapter?.notifyItemInserted(memoList.size)
                //데이터 베이스에 저장
                memoDb.memoDao().insert(memo)
                memoAdapter.notifyItemChanged(id)

            }



            binding.btnHome.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                getResultText.launch(intent)
            }


        }

        }
        fun deleteMemo(memo: Memo) {

            memoDb.memoDao().delete(memo)

            memoList.remove(memo)
            binding.rvData.adapter?.notifyItemRemoved(memo.id)
            binding.rvData.adapter?.notifyDataSetChanged()
            binding.rvData.adapter?.notifyItemRangeChanged(memo.id, memoList.size);
    }
}