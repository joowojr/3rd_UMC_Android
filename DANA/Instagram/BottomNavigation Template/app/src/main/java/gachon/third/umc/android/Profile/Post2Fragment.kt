package gachon.third.umc.android.Profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import gachon.third.umc.android.databinding.FragmentPost2Binding


class Post2Fragment : Fragment() {
    lateinit var binding: FragmentPost2Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPost2Binding.inflate(inflater, container, false)
        return binding.root
    }
}