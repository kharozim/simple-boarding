package id.sekdes.simpleboarding

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import id.sekdes.simpleboarding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val TOTAL_SLIDE = 3
        var CURRENT_SLIDE = 0
    }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sliderAdapter = BoardPagerAdapter(this)
        binding.viewPager.adapter = sliderAdapter

        addDotIndicator(0)

        setViewPager()

    }

    private fun setViewPager() {
        val viewPagerPageChangeListener = object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                addDotIndicator(position)
                CURRENT_SLIDE = position

                when (position){
                    0 -> { binding.btnPrev.visibility = View.INVISIBLE}
                    TOTAL_SLIDE - 1 -> binding.btnNext.text = "FINISH"
                    else -> {
                        binding.btnNext.text = "NEXT"
                        binding.btnPrev.visibility = View.VISIBLE
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        }
        binding.viewPager.addOnPageChangeListener(viewPagerPageChangeListener)
        binding.btnPrev.setOnClickListener {
            binding.viewPager.currentItem = CURRENT_SLIDE - 1
        }
        binding.btnNext.setOnClickListener {
            if (CURRENT_SLIDE == TOTAL_SLIDE - 1) {
                finish()
            } else {
                binding.viewPager.currentItem = CURRENT_SLIDE + 1
            }
        }
    }

    private fun addDotIndicator(currentPage: Int) {

        val dots = arrayListOf<TextView>()
        val colorsActive = resources.getIntArray(R.array.array_dot_active)
        val colorsInactive = resources.getIntArray(R.array.array_dot_inactive)

        binding.layoutDots.removeAllViews()
        for (i in 0 until TOTAL_SLIDE) {
            dots.add(TextView(this))
            dots[i].text = Html.fromHtml("&#8226;")
            dots[i].textSize = 35f
            dots[i].setTextColor(colorsInactive[currentPage])
            binding.layoutDots.addView(dots[i])
        }

        if (dots.size > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage])
    }
}