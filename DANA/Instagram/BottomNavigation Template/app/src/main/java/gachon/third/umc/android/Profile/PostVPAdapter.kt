package gachon.third.umc.android.Profile

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import gachon.third.umc.android.Profile.Post1Fragment
import gachon.third.umc.android.Profile.Post2Fragment

class PostVPAdapter (fragment: Fragment) : FragmentStateAdapter(fragment){
    // 1. ViewPager2에 연결할 Fragment 들을 생성
    val fragmentList =listOf(Post1Fragment(), Post2Fragment())

    // 2. ViesPager2에서 노출시킬 Fragment 의 갯수 설정
    override fun getItemCount()= fragmentList.size

    // 3. ViewPager2의 각 페이지에서 노출할 Fragment 설정
    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}