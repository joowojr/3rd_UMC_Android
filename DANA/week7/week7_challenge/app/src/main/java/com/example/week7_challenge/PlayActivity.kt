package com.example.week7_challenge

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.SystemClock
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.week7_challenge.databinding.ActivityPlayBinding

import java.text.SimpleDateFormat

class PlayActivity: AppCompatActivity() {
    lateinit var binding: ActivityPlayBinding
    private lateinit var mPlayer: MediaPlayer //mp3 player 객체 생성
    var playing = false
    var timeFormat = SimpleDateFormat("mm:ss")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val title = intent.getStringExtra("title")
        val artist = intent.getStringExtra("artist")
        val album = intent.getIntExtra("album", 0)
        val file = intent.getIntExtra("file", 0)


        mPlayer = MediaPlayer.create(this, file)
        playing = true
        mPlayer.start()
        startTread()

        binding.tvTitle.setText(title)
        binding.tvArtist.setText(artist)
        binding.imgAlbum.setImageResource(album)
        binding.sbPlaybar.max = mPlayer.duration
        // 재생/ 일시정지 버튼
        binding.btnPlay.setOnClickListener {
            //재생
            if (playing == false) {
                mPlayer.start()
                startTread()
                playing = true
                binding.icPlay.setImageResource(R.drawable.ic_baseline_play_circle_filled_24)
            }
            //일시 정지
            else {
                mPlayer.pause()

                playing = false
                binding.icPlay.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24)
            }
        }

        // 정지 버튼 - 처음으로
        binding.btnStart.setOnClickListener {
            mPlayer.stop()
            binding.sbPlaybar.progress = 0
            binding.btnPlay.isEnabled = true
            binding.tvCurtime.text ="00:00"
            mPlayer = MediaPlayer.create(this, file)
            playing = false
            binding.icPlay.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24)
        }

            // 정지 버튼 - 끝으로
        binding.btnEnd.setOnClickListener {
            mPlayer.stop()
            binding.sbPlaybar.progress = binding.sbPlaybar.max
            binding.tvCurtime.text = timeFormat.format(
                mPlayer.duration)
            mPlayer = MediaPlayer.create(this, file)
            playing = false
            binding.icPlay.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24)
        }

        binding.sbPlaybar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mPlayer.seekTo(progress)
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }
        })


        }
    override fun onBackPressed(){
        val intent = Intent(this, MainActivity::class.java).apply{
            mPlayer.stop()
        }
        setResult(RESULT_OK, intent)
        super.onBackPressed()
    }
    fun startTread() {
        object : Thread() {
            override fun run() {
                while (playing == true) { //미디어가 실행 중 이라면
                    /*Thread.sleep(1000)*/
                    runOnUiThread {
                        binding.sbPlaybar.progress = mPlayer.currentPosition
                        // 미디어의 재생 포지션을 시크바에 반영
                        binding.tvCurtime.text = timeFormat.format(mPlayer.currentPosition)
                        binding.tvDuration.text = timeFormat.format(
                            mPlayer.duration
                        )

                    }
                    SystemClock.sleep(1000)
                }
            }
        }.start()
    }
    }



