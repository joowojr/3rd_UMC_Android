package gachon.third.umc.android.Profile

import gachon.third.umc.android.ProfileResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header


interface ProfileService {
    @GET("mypage/profile")
    fun getProfile(
        @Header("X-ACCESS-TOKEN") jwt: String
    ): Call<ProfileResponse>

}