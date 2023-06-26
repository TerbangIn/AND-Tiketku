package com.ketarketir.tiketkuioflight.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.ketarketir.tiketkuioflight.databinding.ItemDestinationBinding
import com.ketarketir.tiketkuioflight.model.destination.ListDataDestination
import androidx.navigation.findNavController
import com.ketarketir.tiketkuioflight.R

class DestinationAdapter(
    private val onItemClick: (ListDataDestination) -> Unit
) : RecyclerView.Adapter<DestinationAdapter.ViewHolder>() {

    private val destinations: MutableList<ListDataDestination> = mutableListOf()

    fun setData(destinations: List<ListDataDestination>) {
        this.destinations.clear()
        this.destinations.addAll(destinations)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDestinationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val destination = destinations[position]
        holder.bind(destination)
        holder.itemView.setOnClickListener {
            onItemClick(destination)
            navigateToDetailFragment(it, destination)
        }
    }

    override fun getItemCount(): Int {
        return destinations.size
    }

    inner class ViewHolder(private val binding: ItemDestinationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(destination: ListDataDestination) {
            binding.ivPlace.setImageResource(destination.imageResId)
            binding.tvDeparture.text = destination.departure
            binding.tvArrival.text = destination.arrival
            binding.tvPlaneName.text = destination.planeName
            binding.tvDate.text = destination.date
            binding.tvPriceRange.text = destination.priceRange
        }
    }

    private fun navigateToDetailFragment(view: View) {
        view.findNavController().navigate(R.id.action_homeFragment_to_detailDestinationFragment)
    }

    private fun navigateToDetailFragment(view: View, destination: ListDataDestination) {
        val bundle = Bundle()
        bundle.putParcelable("destination", destination)
        view.findNavController().navigate(R.id.detailDestinationFragment, bundle)
    }
}
