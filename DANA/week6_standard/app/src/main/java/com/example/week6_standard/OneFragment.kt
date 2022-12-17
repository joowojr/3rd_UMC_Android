package com.example.week6_standard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.week6_standard.databinding.FragmentFirstBinding
import com.example.week6_standard.databinding.FragmentOneBinding

class OneFragment : Fragment()  {
    lateinit var binding: FragmentOneBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneBinding.inflate(inflater,container,false)

        return binding.root
    }
}