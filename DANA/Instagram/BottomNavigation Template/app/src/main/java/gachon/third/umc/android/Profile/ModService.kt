package gachon.third.umc.android.Profile

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PATCH

interface ModService {
    @PATCH("user/mod")
    fun setProfile(
        @Header("X-ACCESS-TOKEN") jwt: String,
        @Body modData: ModData
    ): Call<ModResponse>
}