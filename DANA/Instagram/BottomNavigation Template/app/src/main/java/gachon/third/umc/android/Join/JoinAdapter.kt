package gachon.third.umc.android.Join

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class JoinAdapter(fragment: JoinNumberEmailActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> PhnumberFragment()
            1 -> EmailFragment()
            else -> EmailFragment()
        }

    }
    lateinit var emailFragment: EmailFragment

    fun setBundle(fragment: EmailFragment) {
        emailFragment = fragment
    }

}