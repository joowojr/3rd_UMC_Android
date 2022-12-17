package gachon.third.umc.android.Join

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import gachon.third.umc.android.R
import gachon.third.umc.android.databinding.JoinUseridBinding

class JoinUserIdActivity : AppCompatActivity() {
    private lateinit var binding: JoinUseridBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = JoinUseridBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.edtUserid.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnNext.isEnabled  = binding.edtUserid.text.toString() !=""
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
            val intent = Intent(this, JoinPasswordActivity::class.java)
            val userId = binding.edtUserid.text.toString()
            intent.putExtra("userId", userId)
            Log.d("test", "Join $userId")
            startActivity(intent)
    }
    }
}