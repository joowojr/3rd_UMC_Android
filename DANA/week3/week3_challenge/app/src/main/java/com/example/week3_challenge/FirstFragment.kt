package com.example.week3_challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.week3_challenge.databinding.FragmentFirstBinding


class FirstFragment: Fragment() {
    private  lateinit var viewBinding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentFirstBinding.inflate(layoutInflater)

        viewBinding.btnSend.setOnClickListener{
            val result = viewBinding.editFrag.text.toString()
            setFragmentResult("requestKey", bundleOf("bundleKey" to result))
        }
        return viewBinding.root
        }


    }
