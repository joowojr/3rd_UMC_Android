package gachon.third.umc.android.Join

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import gachon.third.umc.android.R
import gachon.third.umc.android.databinding.JoinUsernameBinding

class JoinNameActivity : AppCompatActivity() {
    private lateinit var binding: JoinUsernameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = JoinUsernameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.edtUsername.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnNext.isEnabled  = binding.edtUsername.text.toString() !=""
                if(binding.btnNext.isEnabled ) {
                    binding.btnNext.setBackgroundResource(R.drawable.btn_enable)
                }
                else
                    binding.btnNext.setBackgroundResource(R.drawable.btn_disable)
            }
            override fun afterTextChanged(p0: Editable?) {

            }
        })
        //다음으로
        binding.btnNext.setOnClickListener {
            val emailIntent = Intent(this, JoinNumberEmailActivity::class.java)
            val userId = intent.getStringExtra("userId")
            val pwd = intent.getStringExtra("pwd")
            val userName = binding.edtUsername.text.toString()
            emailIntent.apply {
                putExtra("userId", userId)
                putExtra("pwd", pwd)
                putExtra("userName", userName)
                Log.d("test", "Join $userId $pwd $userName")
            }
            startActivity(emailIntent)
        }
    }
}