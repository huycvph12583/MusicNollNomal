package com.example.musicnoll

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.musicnoll.NavigationActivityView.Companion.MAX_STEP
import com.example.musicnoll.databinding.ActivityNavigationViewBinding
import com.example.musicnoll.databinding.IntroAppDesignBinding
import com.google.android.material.tabs.TabLayoutMediator

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewPager2.adapter = AppIntroViewPager2Adapter()
            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            }.attach()

            viewPager2.setPageTransformer(ZoomOutPageTransformer())

            viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    // static indicator demp
                    // bottomProgressDots(position) // indicator progress dots color change

                    if (position == MAX_STEP - 1) {
                        btnNext.text = getString(R.string.intro_get_started) // "Get Started"
                        btnNext.contentDescription =
                            getString(R.string.intro_get_started) // "Get Started"
                    } else {
                        btnNext.text = getString(R.string.intro_next) // "Next"
                        btnNext.contentDescription = getString(R.string.intro_next) // "Next"
                    }
                }
            })

            // ............................................................
            binding.btnSkip.setOnClickListener {
                findNavController().navigate(R.id.action_navigationActivityView_to_loginActivity)
            }

            // ............................................................
            btnNext.setOnClickListener {
                if (btnNext.text.toString() == getString(R.string.intro_get_started)) {
                    findNavController().navigate(R.id.action_navigationActivityView_to_loginActivity)
                } else {
                    // to change current page - on click "Next BUTTON"
                    val current = (viewPager2.currentItem) + 1
                    viewPager2.currentItem = current

                    // to update button text
                    if (current >= MAX_STEP - 1) {
                        btnNext.text = getString(R.string.intro_get_started) // "Get Started"
                        btnNext.contentDescription =
                            getString(R.string.intro_get_started) // "Get Started"
                    } else {
                        btnNext.text = getString(R.string.intro_next) // "Next"
                        btnNext.contentDescription = getString(R.string.intro_next) // "Next"
                    }
                }
            }
        }
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
            IntroAppDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        )
    }

    // get the size of color array
    override fun getItemCount(): Int = MAX_STEP // Int.MAX_VALUE

    // binding the screen with view
    override fun onBindViewHolder(holder: PagerVH2, position: Int) = holder.itemView.run {
        with(holder) {
            if (position == 0) {
                bindingDesign.introTitle.text = context.getString(R.string.intro_title_1)
                bindingDesign.introDescription.text =
                    context.getString(R.string.intro_description_1)
                bindingDesign.introImage.setImageResource(R.drawable.intro_img1)
            }
            if (position == 1) {
                bindingDesign.introTitle.text = context.getString(R.string.intro_title_2)
                bindingDesign.introDescription.text =
                    context.getString(R.string.intro_description_2)
                bindingDesign.introImage.setImageResource(R.drawable.intro_img2)
            }
            if (position == 2) {
                bindingDesign.introTitle.text = context.getString(R.string.intro_title_3)
                bindingDesign.introDescription.text =
                    context.getString(R.string.intro_description_3)
                bindingDesign.introImage.setImageResource(R.drawable.intro_img3)
            }
        }
    }
}

class PagerVH2(val bindingDesign: IntroAppDesignBinding) :
    RecyclerView.ViewHolder(bindingDesign.root)
