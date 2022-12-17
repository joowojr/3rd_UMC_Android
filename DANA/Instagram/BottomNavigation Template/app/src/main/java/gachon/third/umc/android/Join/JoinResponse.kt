package gachon.third.umc.android.Join

import gachon.third.umc.android.Login.Result

data class JoinResponse(
    val isSuccess : Boolean,
    val code : Int,
    val message : String,
    val result : Result
)

