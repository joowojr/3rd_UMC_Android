package gachon.third.umc.android.Login

data class LoginResponse(
    val isSuccess : Boolean,
    val code : Int,
    val message : String,
    val result : Result
)

