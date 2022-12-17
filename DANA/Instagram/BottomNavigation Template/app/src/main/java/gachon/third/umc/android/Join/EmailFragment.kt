package gachon.third.umc.android.Join

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import gachon.third.umc.android.Login.LoginActivity
import gachon.third.umc.android.R
import gachon.third.umc.android.databinding.FragmentEmailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EmailFragment: Fragment() {

    private lateinit var binding: FragmentEmailBinding
    lateinit var userId: String
    lateinit var pwd: String
    lateinit var userName: String
    lateinit var email: String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmailBinding.inflate(inflater, container, false)

        binding.btnErase.setOnClickListener {
            binding.edtEmail.setText("")
        }

        binding.edtEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnJoin.isEnabled = binding.edtEmail.text.toString() != ""
                if (binding.btnJoin.isEnabled) {
                    binding.btnJoin.setBackgroundResource(R.drawable.btn_enable)
                } else
                    binding.btnJoin.setBackgroundResource(R.drawable.btn_disable)
            }

            override fun afterTextChanged(p0: Editable?) {
                /*checkEmail()*/
            }

        })

        binding.btnJoin.setOnClickListener {
            userName = arguments?.getString("userName").toString()
            userId = arguments?.getString("userId").toString()
            email = binding.edtEmail.text.toString()
            pwd = arguments?.getString("pwd").toString()
            Log.d("test", "$userName$userId $email $pwd")
            join()

        }
        return binding.root
    }

    private fun join() {
        val join = Retrofit.Builder()
            .baseUrl("https://kimmarie.shop/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        join.create(JoinService::class.java).postJoin(JoinData(userName, userId, email, pwd))
            .enqueue(object : Callback<JoinResponse> {
                override fun onResponse(call: Call<JoinResponse>, response: Response<JoinResponse>) {
                    Log.d(
                        "join", "onResponse: \n${response.body()}"
                    )
                    if (response.body()!!.isSuccess) {
                        startActivity(Intent(requireActivity(), LoginActivity::class.java))
                        ActivityCompat.finishAffinity(requireActivity())
                    } else {
                        Toast.makeText(activity, "${response.body()!!.message}", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                    override fun onFailure(call: Call<JoinResponse>, t: Throwable) {
                        Log.e("join", "onFailure: ${t.message}")
                    }

            })
    }
}