package gachon.third.umc.android.Profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import gachon.third.umc.android.databinding.FragmentPost1Binding

class Post1Fragment : Fragment () {
    lateinit var binding: FragmentPost1Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPost1Binding.inflate(inflater, container, false)
        return binding.root
    }
}