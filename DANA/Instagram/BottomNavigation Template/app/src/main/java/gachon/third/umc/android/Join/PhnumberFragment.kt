package gachon.third.umc.android.Join

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import gachon.third.umc.android.databinding.FragmentEmailBinding
import gachon.third.umc.android.databinding.FragmentPhnumberBinding

class PhnumberFragment : Fragment() {

    private lateinit var binding: FragmentPhnumberBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhnumberBinding.inflate(inflater, container, false)
        return binding.root
    }
}