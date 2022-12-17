package com.example.week4_standard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.week4_standard.databinding.ActivitySecondBinding

class SecondActivity: AppCompatActivity()  {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvMemo.text= intent.getStringExtra("data")

        /*binding.btnEdit.setOnClickListener {
            val intent_2 = Intent(this, MainActivity::class.java)
            val memo_sec = binding.tvMemo.text.toString()
            intent_2.putExtra("data", memo_sec)
            startActivity(intent_2)
        }*/

    }
}


