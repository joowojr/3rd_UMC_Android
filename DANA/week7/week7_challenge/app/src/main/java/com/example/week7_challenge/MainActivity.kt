package com.example.week7_challenge

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week7_challenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var musicList = arrayListOf<Music>()    //mp3파일을 저장할 리스트
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 구분선 넣기
        val dividerItemDecoration= DividerItemDecoration(binding.rvMusic.context, LinearLayoutManager(this).orientation)
        binding.rvMusic.addItemDecoration(dividerItemDecoration)

        // 음악 목록
        musicList.apply{
            add(Music("2NE1","I Don't Care",R.raw.music1, R.drawable.music1))
            add(Music("아이유" , "좋은 날",R.raw.music2, R.drawable.img))
        }
        val listAdapter = ListAdapter(musicList)
        binding.rvMusic.layoutManager = LinearLayoutManager(this)
        binding.rvMusic.adapter = listAdapter



    }

}