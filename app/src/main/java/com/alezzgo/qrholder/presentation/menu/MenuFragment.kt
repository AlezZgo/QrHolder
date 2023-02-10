package com.alezzgo.qrholder.presentation.menu

import android.content.Intent
import android.net.Uri
import com.alezzgo.qrholder.BuildConfig
import com.alezzgo.qrholder.R
import com.alezzgo.qrholder.databinding.FragmentMenuBinding
import com.alezzgo.qrholder.presentation.core.fragment.AbstractFragment
import com.alezzgo.qrholder.presentation.core.fragment.BottomNavViewVisibility


class MenuFragment : AbstractFragment<FragmentMenuBinding, MenuViewModel>(
    FragmentMenuBinding::inflate, MenuViewModel::class.java
), BottomNavViewVisibility.Show {

    override fun setupViews() {
        super.setupViews()
        binding.run {
            tvAppVersion.text = BuildConfig.VERSION_NAME
            cvSupport.setOnClickListener {
                startActivity(
                    Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.support_link)))
                )
            }
            cvRateApp.setOnClickListener {
                startActivity(
                    Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.app_link)))
                )
            }
        }
    }
}