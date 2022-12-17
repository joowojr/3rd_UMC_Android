package com.example.week6_standard

import android.content.ClipData.Item
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.week6_standard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavi.run{
            setOnItemSelectedListener {
                when (it.itemId){
                    R.id.home -> {
                        setFragment(HomeFragment())
                        return@setOnItemSelectedListener true
                    }
                    R.id.tab1 -> {
                        setFragment(FirstFragment())
                        return@setOnItemSelectedListener true
                    }
                    R.id.tab2 -> {
                        setFragment(SecondFragment())
                        return@setOnItemSelectedListener true
                    }
                    else -> false
                }
            }
        }

    }
    private fun setFragment(fragment: Fragment) {

        supportFragmentManager
            .beginTransaction().replace(binding.frame.id, fragment).commitAllowingStateLoss()
    }
}
