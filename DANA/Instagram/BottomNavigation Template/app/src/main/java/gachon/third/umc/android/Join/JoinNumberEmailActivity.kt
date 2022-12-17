package gachon.third.umc.android.Join

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import gachon.third.umc.android.databinding.JoinNumberEmailBinding

class JoinNumberEmailActivity : AppCompatActivity() {
    private lateinit var binding: JoinNumberEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = JoinNumberEmailBinding.inflate(layoutInflater)

        val userId = intent.getStringExtra("userId")
        val pwd = intent.getStringExtra("pwd")
        val userName = intent.getStringExtra("userName")


        val joinAdapter = JoinAdapter(this)
        binding.vpJoin.adapter = joinAdapter
        val tabArray = arrayOf("전화번호", "이메일")
        TabLayoutMediator(binding.tab,binding.vpJoin){ tab, position ->
            tab.text = tabArray[position]
        }.attach()

        val data = Bundle()
        data.apply {
            putString("userId", userId)
            putString("pwd", pwd)
            putString("userName", userName)
        }
        val fragment = EmailFragment()
        fragment.arguments = data
        joinAdapter.setBundle(fragment)



        setContentView(binding.root)

    }
}