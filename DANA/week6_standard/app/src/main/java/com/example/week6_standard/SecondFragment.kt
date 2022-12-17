package com.example.week6_standard

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.week6_standard.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    var currentPosition=0

    inner class PagerRunnable:Runnable{
        override fun run() {
            while(true){
                Thread.sleep(3000)
                handler.sendEmptyMessage(0)
            }
        }
    }
    val handler= Handler(Looper.getMainLooper()){
        setPage()
        true
    }

    private fun setPage(){
        binding.vpSecond.setCurrentItem(currentPosition,true)
        currentPosition+=1
        if(currentPosition==3) currentPosition=0
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        val picAdapter = PictureAdapter(getImageResources())
        binding.vpSecond.adapter = picAdapter
        binding.vpSecond.currentItem =0

        binding.indicator.setViewPager(binding.vpSecond)
        binding.indicator.createIndicators(3,0)

        val thread=Thread(PagerRunnable())
        thread.start()

        return binding.root
    }
    private fun getImageResources():ArrayList<Int>{
        return arrayListOf<Int>(R.drawable.dessert, R.drawable.brunch, R.drawable.fall)

    }
}