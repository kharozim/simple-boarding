package id.sekdes.simpleboarding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import id.sekdes.simpleboarding.databinding.LayoutSlideBinding

class BoardPagerAdapter(
    context: Context
) : PagerAdapter() {

    private val mContext = context

    private val slideImage = arrayListOf(
        R.drawable.a1,
        R.drawable.a2,
        R.drawable.a3,
    )

    private val slideTitle = arrayListOf(
        R.string.slide_1_title,
        R.string.slide_2_title,
        R.string.slide_3_title,
    )

    private val slideDesc = arrayListOf(
        R.string.slide_1_desc,
        R.string.slide_2_desc,
        R.string.slide_3_desc,
    )


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return slideDesc.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = LayoutSlideBinding.inflate(LayoutInflater.from(mContext))

        binding.imageView.setImageResource(slideImage[position])
        binding.tvTitle.text = mContext.getText(slideTitle[position])
        binding.tvDesc.text = mContext.getText(slideDesc[position])

        container.addView(binding.root)

        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }

}