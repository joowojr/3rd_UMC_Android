package com.example.week8

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.week8.databinding.ActivityWriteBinding

class WriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWriteBinding
    private lateinit var memoDb: MemoDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("Title")
        val content = intent.getStringExtra("Content")
        val id = intent.getIntExtra("position", 0)
        binding.edtTitle.setText(title)
        binding.edtContent.setText(content)

        memoDb = MemoDatabase.getInstance(this)!!

        var sharedPrefs = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        var editor = sharedPrefs.edit()

        // 저장하기
        binding.btnSave.setOnClickListener {
            val edtTitle = binding.edtTitle.text.toString()
            val edtContent = binding.edtContent.text.toString()
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("Title", edtTitle)
                putExtra("Content", edtContent)
                putExtra("position", id)
                putExtra("bookmark", sharedPrefs.getBoolean("$id", false))
            }
            setResult(RESULT_OK, intent)
            if (!isFinishing) finish()
        }


    }
}


