package com.example.qrholder.presentation.menu

import android.content.Intent
import android.net.Uri
import com.example.qrholder.R
import com.example.qrholder.databinding.FragmentMenuBinding
import com.example.qrholder.presentation.core.fragment.AbstractFragment
import com.example.qrholder.presentation.core.fragment.BottomNavViewVisibility


class MenuFragment : AbstractFragment<FragmentMenuBinding, MenuViewModel>(
    FragmentMenuBinding::inflate, MenuViewModel::class.java
), BottomNavViewVisibility.Show {

    override fun setupViews() {
        super.setupViews()
        binding.cvSupport.setOnClickListener {
            startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.support_link)))
            )
        }
    }
}