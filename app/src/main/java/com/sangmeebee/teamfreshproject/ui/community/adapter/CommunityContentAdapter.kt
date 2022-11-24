package com.sangmeebee.teamfreshproject.ui.community.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sangmeebee.teamfreshproject.databinding.ItemCommunityBinding
import com.sangmeebee.teamfreshproject.model.BoardModel

class CommunityContentAdapter : PagingDataAdapter<BoardModel, CommunityContentAdapter.CommunityViewHolder>(BoardDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityViewHolder {
        val binding = ItemCommunityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommunityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommunityViewHolder, position: Int) =
        holder.bind(getItem(position)!!)

    class CommunityViewHolder(private val binding: ItemCommunityBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(board: BoardModel) {
            binding.board = board
        }
    }

    class BoardDiffCallback : DiffUtil.ItemCallback<BoardModel>() {
        override fun areItemsTheSame(
            oldItem: BoardModel,
            newItem: BoardModel,
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: BoardModel,
            newItem: BoardModel,
        ): Boolean = oldItem == newItem
    }
}
