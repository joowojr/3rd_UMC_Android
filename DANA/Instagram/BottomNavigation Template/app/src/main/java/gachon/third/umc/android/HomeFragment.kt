package gachon.third.umc.android

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import gachon.third.umc.android.databinding.FragmentHomeBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HomeFragment: Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        var storyDataList = arrayListOf<StoryData>()
        var feedDataList = arrayListOf<FeedData>()

        binding.rvStory.adapter = StoryAdapter(storyDataList)
        binding.rvStory.layoutManager =LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        storyDataList.apply {
            val range_story = (1..24)
            storyDataList.add(StoryData("내 스토리",R.drawable.ic_profile_default,R.drawable.image, "${range_story.random()}시간전",multi_type1))
            for(x in 1..10){
                storyDataList.add(StoryData("name$x",R.drawable.ic_profile_default,R.drawable.image,"${range_story.random()}시간전", multi_type2))
            }
        }

        binding.rvFeed.adapter = FeedAdapter(feedDataList)
        binding.rvFeed.layoutManager =LinearLayoutManager(activity)

        val date = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("MM월 dd일")
        val feedDate = date.format(formatter)
        val range_like = (1..1000)

        feedDataList.apply {
            for (x in 1.. 10){
                feedDataList.add(FeedData("name$x",range_like.random(),"게시물$x", range_like.random(),feedDate))
            }

        }

        class RecyclerViewHeight(private val divHeight: Int) : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.top = divHeight
            }
        }
            class RecyclerViewWidth(private val divWidth: Int) : ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.right = divWidth
            }
        }
        binding.rvStory.addItemDecoration(RecyclerViewWidth(5))

        binding.rvFeed.addItemDecoration(RecyclerViewHeight(10))

        return binding.root


    }
}