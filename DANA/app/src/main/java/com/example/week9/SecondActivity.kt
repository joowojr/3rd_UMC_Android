package com.example.week9

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.week9.databinding.ActivitiySecondBinding
import com.kakao.sdk.user.UserApiClient

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitiySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitiySecondBinding.inflate(layoutInflater)

        UserApiClient.instance.me { user, error ->
            binding.tvEmail.text = " 이메일 : ${user?.kakaoAccount?.email}"
        }
    }

}
