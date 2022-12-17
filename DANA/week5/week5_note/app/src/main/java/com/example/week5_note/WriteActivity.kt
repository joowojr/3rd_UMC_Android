package com.example.week5_note

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.example.week5_note.databinding.ActivityWriteBinding

class WriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWriteBinding
    private lateinit var getResultState: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("Title")
        val desc = intent.getStringExtra("Desc")
        val position = intent.getStringExtra("position")
        binding.edtTitle.setText(title)
        binding.edtDesc.setText(desc)


        // 저장하기
        binding.btnSave.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply{
                val edtTitle = binding.edtTitle.text.toString()
                val edtDesc = binding.edtDesc.text.toString()
                putExtra("Title", edtTitle)
                putExtra("Desc", edtDesc)
                putExtra("position", position)
                if (title != edtTitle || desc != edtDesc ){
                    setResult(1, intent) }
                else
                    setResult(RESULT_OK,intent)
            }
            if(!isFinishing) finish()



        }

        //메인으로 돌아가기

    }

/*    override fun onStop() {
        super.onStop()
        string = binding.edtDesc.text.toString()
        binding.edtDesc.setText("");
    }*/

/*    override fun onRestart() {
        super.onRestart()
        val builder = AlertDialog.Builder(this)
        builder.setMessage("계속해서 작성하시겠습니까?")
        builder.setPositiveButton("예"){ dialog, id -> binding.edtDesc.setText(string) }
        builder.setNegativeButton("아니요"){dialog, id -> string = ""; binding.edtDesc.setText(string)  }
        builder.show()

    }*/


}