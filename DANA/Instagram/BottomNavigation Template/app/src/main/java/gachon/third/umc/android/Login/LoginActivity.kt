package gachon.third.umc.android.Login

import android.content.Intent
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import gachon.third.umc.android.Join.JoinUserIdActivity
import gachon.third.umc.android.MainActivity
import gachon.third.umc.android.MainApplication
import gachon.third.umc.android.R
import gachon.third.umc.android.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.edtUserid.inputType = InputType. TYPE_TEXT_FLAG_NO_SUGGESTIONS

        if(MainApplication.prefs.getBoolean("auto",false))
        {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }

        // 비밀번호 보이게, 안 보이게
        var flag = false
        binding.btnSeek.setOnClickListener {
            if (!flag) {
                binding.edtPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                flag = true
                binding.btnSeek.setImageResource(R.drawable.ic_pwd_on)
            } else {
                binding.edtPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
                flag = false
                binding.btnSeek.setImageResource(R.drawable.ic_pwd_off)
            }
        }

        // 아아디 1자리 이상일 때 버튼 클릭 가능
        binding.edtUserid.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun afterTextChanged(p0: Editable?) {
                binding.btnLogin.isEnabled =
                    binding.edtUserid.text.toString() != "" && binding.edtPassword.text.toString() != ""
                if (binding.btnLogin.isEnabled) {
                    binding.btnLogin.setBackgroundResource(R.drawable.btn_enable)
                }
                else
                    binding.btnLogin.setBackgroundResource(R.drawable.btn_disable)
            }

        })

        // 비밀번호가 1자리 이상일 때 버튼 클릭 가능
        binding.edtPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                binding.btnLogin.isEnabled =  binding.edtUserid.text.toString() != "" &&
                        binding.edtPassword.text.toString() != ""
                if (binding.btnLogin.isEnabled) {
                    binding.btnLogin.setBackgroundResource(R.drawable.btn_enable)
                }
                else
                    binding.btnLogin.setBackgroundResource(R.drawable.btn_disable)
            }

        })

        // 아이디, 비밀번호 맞을 때 로그인
        binding.btnLogin.setOnClickListener {
                login()
            }
        binding.btnJoin.setOnClickListener {
            val intent = Intent(this, JoinUserIdActivity::class.java)
            startActivity(intent)
        }
        }

    private fun login() {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://kimmarie.shop/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()//레트로핏 구현체 완성!

        val server = retrofit.create(LoginService::class.java)
        val data = LoginData(binding.edtUserid.text.toString(), binding.edtPassword.text.toString())

        server.postLogin(data)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful){
                        if (response.body()!= null){
                            if(response.body()!!.isSuccess){
                                MainApplication.prefs.setBoolean("auto", true)
                                MainApplication.prefs.setString("jwt", response.body()!!.result.jwt)
                                startActivity(Intent(this@LoginActivity, MainActivity::class.java))

                            } else {
                                Toast.makeText(this@LoginActivity, "${response.body()!!.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }


                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("test", "onFailure: ${t.message}")
                }
            })
    }
    }

