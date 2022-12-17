package gachon.third.umc.android.Join

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import gachon.third.umc.android.R
import gachon.third.umc.android.databinding.JoinPasswordBinding

class JoinPasswordActivity : AppCompatActivity() {
    private lateinit var binding: JoinPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = JoinPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.edtPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnNext.isEnabled = binding.edtPassword.text.toString().length >= 6
                if (binding.btnNext.isEnabled ) {
                    binding.btnNext.setBackgroundResource(R.drawable.btn_enable)
                }
                else{
                binding.btnNext.setBackgroundResource(R.drawable.btn_disable)}
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
        //다음으로
        binding.btnNext.setOnClickListener {
            val nameIntent = Intent(this, JoinNameActivity::class.java)
            val userId = intent.getStringExtra("userId")
            val pwd = binding.edtPassword.text.toString()
            nameIntent.apply {
                putExtra("userId", userId)
                putExtra("pwd", pwd)
                Log.d("test", "Join $userId $pwd")
            }
            startActivity(nameIntent)
        }

    }
}