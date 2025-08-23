package com.mousa.thamnyahapp.repository

import androidx.recyclerview.widget.DiffUtil
import com.mousa.thamnyahapp.domain.model.HomeSection

class HomeSectionDiffCallback : DiffUtil.ItemCallback<HomeSection>() {
    override fun areItemsTheSame(oldItem: HomeSection, newItem: HomeSection): Boolean {
        return oldItem.order == newItem.order
    }

    override fun areContentsTheSame(oldItem: HomeSection, newItem: HomeSection): Boolean {
        return oldItem == newItem
    }
}