package gachon.third.umc.android.Profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import gachon.third.umc.android.*
import gachon.third.umc.android.databinding.FragmentProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfileFragment: Fragment() {

    lateinit var binding: FragmentProfileBinding
    private var jwt = MainApplication.prefs.getString("jwt", "")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        setProfile()
        var highlightDataList = arrayListOf<HighlightData>()
        binding.rvHighlight.adapter = HighlightAdapter(highlightDataList)

        highlightDataList.apply{
            highlightDataList.add(HighlightData("신규", multi_type1))
            for(x in 1..4){
                highlightDataList.add(HighlightData("", multi_type2))
            }
        }

    binding.btnEditProfile.setOnClickListener{
        sendProfile()
     }
        var highlightFlag = false
        binding.highlights.setOnClickListener{
            if(highlightFlag) {
                binding.rvHighlight.visibility = View.GONE
                binding.text2.visibility = View.GONE
                highlightFlag = false
                binding.icon.setImageResource(R.drawable.ic_arrow)
            }
            else {
                binding.rvHighlight.visibility = View.VISIBLE
                binding.text2.visibility = View.VISIBLE
                highlightFlag = true
                binding.icon.setImageResource(R.drawable.ic_arrow_top)
            }
        }
        val profileVPAdapter = PostVPAdapter(this)

        binding.vpPost.adapter =  profileVPAdapter
        val tabArray = arrayOf(R.drawable.ic_postgrid, R.drawable.ic_myinfo_tag)
        TabLayoutMediator(binding.tabPost, binding.vpPost) { tab, position ->
            tab.setIcon(tabArray[position])
        }.attach()

     return binding.root

 }

    override fun onResume() {
        super.onResume()
        setProfile()
    }

    fun sendProfile(){
        val server = Retrofit.Builder()
            .baseUrl("https://kimmarie.shop/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        server.create(ProfileService::class.java)
            .getProfile(jwt)
            .enqueue(object : Callback<ProfileResponse> {
                override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                    if( response.body()!=null && response.body()!!.isSuccess) {
                        val intent = Intent(activity, EditProfileActivity::class.java).apply{

                            putExtra("userId", response.body()!!.result.userID)
                            putExtra("userName", response.body()!!.result.userName)
                            putExtra("intro", response.body()!!.result.userIntro)
                            putExtra("jwt", jwt)

                        }
                        startActivity(intent)
                    }

                }

                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                }
            })
    }
    private fun setProfile(){
        val server = Retrofit.Builder()
            .baseUrl("https://kimmarie.shop/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        server.create(ProfileService::class.java)
            .getProfile(jwt)
            .enqueue(object : Callback<ProfileResponse> {
                override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                    if( response.body()!=null && response.body()!!.isSuccess) {
                        binding.tvUserName.text = response.body()!!.result.userName
                        binding.tvUserId.text= response.body()!!.result.userID
                        binding.tvIntro.text = response.body()!!.result.userIntro
                        binding.tvPostNum.text=response.body()!!.result.postNum.toString()
                        binding.tvFollowerNum.text=response.body()!!.result.followerNum.toString()
                        binding.tvFollowingNum.text=response.body()!!.result.followingNum.toString()
                    }
                    if (binding.tvUserName.text==""){
                        binding.tvUserName.visibility = View.GONE}
                    else{
                        binding.tvUserName.visibility = View.VISIBLE}

                    if (binding.tvIntro.text==""){
                        binding.tvIntro.visibility = View.GONE}
                    else{
                        binding.tvIntro.visibility = View.VISIBLE}

            }

                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                }
            })
    }

}