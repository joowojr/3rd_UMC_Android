package gachon.third.umc.android

import android.os.*
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import gachon.third.umc.android.databinding.StoryViewBinding
import kotlin.concurrent.thread

class StoryViewActivity: AppCompatActivity() {
    private lateinit var binding: StoryViewBinding
    var time = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StoryViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sbStory.progress = 0
        binding.sbStory.max = 5000
        binding.sbStory.setOnTouchListener{ _ : View?, _ : MotionEvent ->
            true
        }
        timer()

        binding.imgProfile.setImageResource(intent.getIntExtra("profileImg", 0))
        binding.tvUsername.text = intent.getStringExtra("userName")
        binding.imgStory.setImageResource(intent.getIntExtra("storyImg", 0))
        binding.imgStory.clipToOutline = true
        binding.tvTime.text = intent.getStringExtra("time")

    }


    private fun timer() {
        thread(start = true) {
            while (time <5000 ) {
                Thread.sleep(1)
                handler.sendEmptyMessage(0)
            }

        }
    }
    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {

                binding.sbStory.progress = time
                time += 5
            if (time == 5000) finish()

        }
    }

}