package com.example.week6_standard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.week6_standard.databinding.FragmentFirstBinding
import com.google.android.material.tabs.TabLayoutMediator

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater,container,false)
        val vpAdapter = ViewPagerAdapter(requireActivity())
        binding.vpFirst.adapter = vpAdapter

        val tabTitleArray = arrayOf(
            "One",
            "Two",
            "Three"
        )
        TabLayoutMediator(binding.tabFrag,binding.vpFirst ){ tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()
        return binding.root


    }
}
