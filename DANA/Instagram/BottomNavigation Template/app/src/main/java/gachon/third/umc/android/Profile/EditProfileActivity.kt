package gachon.third.umc.android.Profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import gachon.third.umc.android.MainApplication
import gachon.third.umc.android.databinding.ActivityEditProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EditProfileActivity: AppCompatActivity() {
    private  lateinit var binding:ActivityEditProfileBinding
    private var jwt = MainApplication.prefs.getString("jwt", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userName = intent.getStringExtra("userName")
        val userId = intent.getStringExtra("userId")
        val intro = intent.getStringExtra("intro")
        binding.edtName.setText(userName)
        binding.edtUserid.setText(userId)
        binding.edtIntro.setText(intro)

        binding.btnClose.setOnClickListener {
            finish()
        } // x버튼 누르면 profileFragment로 돌아감

        binding.edtUserid.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnCheck.isEnabled = binding.edtUserid.text.toString() != ""

            }
            override fun afterTextChanged(p0: Editable?) {
                binding.btnCheck.isEnabled = binding.edtUserid.text.toString() != ""
                }


        })
        binding.btnCheck.setOnClickListener {
            editProfile()
        }

    }
    fun editProfile(){
        val server = Retrofit.Builder()
            .baseUrl("https://kimmarie.shop/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val data = ModData(binding.edtName.text.toString(), binding.edtUserid.text.toString(), binding.edtIntro.text.toString(),"")
        server.create(ModService::class.java)
            .setProfile(jwt!!, data)
            .enqueue(object : Callback<ModResponse> {
                override fun onResponse(call: Call<ModResponse>, response: Response<ModResponse>) {
                    if(response.body()!!.isSuccess) {
                        if(response.body()!=null) {
                            Toast.makeText(
                                this@EditProfileActivity,
                                "사용자 정보를 수정하였습니다",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
                override fun onFailure(call: Call<ModResponse>, t: Throwable) {
                }
            })

        if(!isFinishing) finish()
        // profileFragment에 편집한 프로필 정보 전달, profileFragment로 돌아감
    }

}