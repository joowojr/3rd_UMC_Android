package gachon.third.umc.android

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import gachon.third.umc.android.databinding.ItemFeedBinding

data class FeedData (
    var userName: String,
    var like : Int,
    var tvContent : String,
    /*var imgContent : Int */
    var commentNum : Int,
    var date : String
)
class FeedAdapter (private val dataList: ArrayList<FeedData>)
    : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemFeedBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: FeedData) {
            binding.tvUsername.text = data.userName
            binding.tvLike.text = "좋아요 ${data.like}개"
            binding.tvContent.text = "${data.userName}  ${data.tvContent}"
            binding.tvCommentNum.text = "댓글 ${data.commentNum}개 모두 보기"
            binding.tvDate.text = data.date

            val content: String = binding.tvContent.text.toString()
            val builder = SpannableStringBuilder(content)
            val boldSpan = StyleSpan(Typeface.BOLD)
            val c = " "
            val index = content.indexOf(c)

            builder.setSpan(boldSpan, 0, index, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            binding.tvContent.text= builder
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])

    }

    override fun getItemCount(): Int = dataList.size
}