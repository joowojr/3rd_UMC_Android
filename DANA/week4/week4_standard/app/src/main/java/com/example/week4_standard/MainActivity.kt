package com.example.week4_standard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.week4_standard.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var  string = ""

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSave.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            val memo_main = binding.edtMemo.text.toString()
            intent.putExtra("data", memo_main)
            startActivity(intent)

        }

    }

    override fun onStop() {
        super.onStop()
        string = binding.edtMemo.text.toString()
        binding.edtMemo.setText("");
    }

    override fun onRestart() {
        super.onRestart()
        val builder = AlertDialog.Builder(this)
        builder.setMessage("계속해서 작성하시겠습니까?")
        builder.setPositiveButton("예"){ dialog, id -> binding.edtMemo.setText(string) }
        builder.setNegativeButton("아니요"){dialog, id -> string = ""; binding.edtMemo.setText(string)  }
        builder.show()

    }


}