package com.example.week3_challenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.EditText
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.week3_challenge.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        /*viewBinding.btnSec.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("str","back")
            }
            setResult(RESULT_OK,intent)
            if (!isFinishing) finish()
        }*/
        viewBinding.btnThird.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onBackPressed() {
        val intent_2 = Intent(this, MainActivity::class.java).apply {
            putExtra("str","back")
        }
        setResult(RESULT_OK, intent_2)
        super.onBackPressed()
    }

}
