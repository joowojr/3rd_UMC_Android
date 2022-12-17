package com.example.week6_standard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.week6_standard.databinding.FragmentOneBinding
import com.example.week6_standard.databinding.FragmentTwoBinding

class TwoFragment: Fragment() {
    lateinit var binding: FragmentTwoBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTwoBinding.inflate(inflater,container,false)

        return binding.root
    }
}