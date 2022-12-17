package com.example.week7

import android.graphics.Color
import android.media.SoundPool
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.example.week7.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    //입력받을 시간 변수
    private var hour = 0
    private var min = 0
    private var sec = 0
    //타이머 실행할 총 시간
    private var time= 0

    //타이머 시작 상태
    var startState = false

    lateinit var binding: ActivityMainBinding
    val soundPool = SoundPool.Builder().build()
    var soundId : Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        soundId = soundPool.load(this, R.raw.alarm, 1)

        binding.btnStart.setOnClickListener {
            startState = true
            binding.btnStart.isEnabled = false
            startTimer()
            binding.btnStart.isEnabled = true
        }
        binding.btnStop.setOnClickListener {
            stopTimer()
            binding.btnStart.isEnabled = true
        }

        binding.btnReset.setOnClickListener {
            resetTimer()
            binding.btnStart.isEnabled = true
        }
        setContentView(binding.root)
    }

    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            if(startState){
                        if(time == 5){
                            changeColor()
                        }
                        if(time == 0){
                            startState = false
                            alarmTimer()
                    }

                updateTimer()
                time -= 1
            }
        }
    }
    private fun startTimer() {
        hour = binding.edtHour.text.toString().toInt()
        min = binding.edtMin.text.toString().toInt()
        sec = binding.edtSec.text.toString().toInt()
        time = hour * 3600 + min * 60 + sec

        startState = true

        thread(start = true) {
            while (time >= 0) {
                handler.sendEmptyMessage(0)
                Thread.sleep(1000)
                if(startState==false)break
            }

        }
    }

    private fun stopTimer() {
        startState = false
        handler.removeMessages(0)
    }

    private fun updateTimer(){
        var hour = String.format("%02d",time / 3600)
        var min = String.format("%02d",time % 3600 / 60)
        var sec = String.format("%02d",time % 3600 % 60)

        binding.edtHour.setText("$hour")
        binding.edtMin.setText("$min")
        binding.edtSec.setText("$sec")

    }

    private fun resetTimer(){
        startState = false
        time=0
        binding.edtHour.setText("00")
        binding.edtMin.setText("00")
        binding.edtSec.setText("00")
        binding.layout.setBackgroundColor(Color.parseColor("#FFFFFF"))
        binding.edtHour.setTextColor(Color.parseColor("#000000"))
        binding.edtMin.setTextColor(Color.parseColor("#000000"))
        binding.edtSec.setTextColor(Color.parseColor("#000000"))
        binding.tv1.setTextColor(Color.parseColor("#000000"))
        binding.tv2.setTextColor(Color.parseColor("#000000"))
    }

    private fun alarmTimer(){

        binding.layout.setBackgroundColor(Color.parseColor("#FFFFFF"))
        binding.edtHour.setTextColor(Color.parseColor("#000000"))
        binding.edtMin.setTextColor(Color.parseColor("#000000"))
        binding.edtSec.setTextColor(Color.parseColor("#000000"))
        binding.tv1.setTextColor(Color.parseColor("#000000"))
        binding.tv2.setTextColor(Color.parseColor("#000000"))
        soundPool.play(soundId,1.0f,1.0f,0,0,1.0f)

    }

    private fun changeColor(){
        binding.layout.setBackgroundColor(Color.parseColor("#C4302B"))
        binding.edtHour.setTextColor(Color.parseColor("#FFFFFF"))
        binding.edtMin.setTextColor(Color.parseColor("#FFFFFF"))
        binding.edtSec.setTextColor(Color.parseColor("#FFFFFF"))
        binding.tv1.setTextColor(Color.parseColor("#FFFFFF"))
        binding.tv2.setTextColor(Color.parseColor("#FFFFFF"))

    }

}
