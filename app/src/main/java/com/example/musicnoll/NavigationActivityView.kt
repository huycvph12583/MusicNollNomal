package com.example.musicnoll

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.musicnoll.NavigationActivityView.Companion.MAX_STEP
import com.example.musicnoll.databinding.ActivityNavigationViewBinding
import com.example.musicnoll.databinding.IntroAppDesignBinding

class NavigationActivityView : Fragment() {


    private var mBinding: ActivityNavigationViewBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mBinding = ActivityNavigationViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

    companion object {
        const val MAX_STEP = 3
    }

}

class AppIntroViewPager2Adapter : RecyclerView.Adapter<PagerVH2>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH2 {
        return PagerVH2(
            IntroAppDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    //get the size of color array
    override fun getItemCount(): Int = MAX_STEP // Int.MAX_VALUE

    //binding the screen with view
    override fun onBindViewHolder(holder: PagerVH2, position: Int) = holder.itemView.run {

        with(holder) {
            if (position == 0) {
                bindingDesign.introTitle.text = context.getString(R.string.intro_title_1)
                bindingDesign.introDescription.text = context.getString(R.string.intro_description_1)
                bindingDesign.introImage.setImageResource(R.drawable.intro1)
            }
            if (position == 1) {
                bindingDesign.introTitle.text = context.getString(R.string.intro_title_2)
                bindingDesign.introDescription.text = context.getString(R.string.intro_description_2)
                bindingDesign.introImage.setImageResource(R.drawable.intro2)
            }
            if (position == 2) {
                bindingDesign.introTitle.text = context.getString(R.string.intro_title_3)
                bindingDesign.introDescription.text = context.getString(R.string.intro_description_3)
                bindingDesign.introImage.setImageResource(R.drawable.intro3)
            }
        }
    }
}
class PagerVH2(val bindingDesign: IntroAppDesignBinding) : RecyclerView.ViewHolder(bindingDesign.root)
