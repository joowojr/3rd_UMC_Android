package com.example.week3_standard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.week3_standard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        /*super.onCreate(savedInstanceState)*/
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnSend.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)

            intent.putExtra("data", viewBinding.editMain.text.toString())
            startActivity(intent)
        }
    }


}
