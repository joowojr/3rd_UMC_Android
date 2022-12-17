package gachon.third.umc.android.Profile

data class ProfileData(
    val userIdx: Int,
    val userID: String,
    val userName: String,
    val userIntro: String,
    val userWebsite: String,
    val userProfileImg: String,
    val postNum: Int,
    val followerNum: Int,
    val followingNum: Int
)
