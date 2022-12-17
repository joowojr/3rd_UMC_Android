package gachon.third.umc.android.Join

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface JoinService {
    @POST("user/join")
    fun postJoin(
        @Body joindata : JoinData
    ) : Call<JoinResponse>
}