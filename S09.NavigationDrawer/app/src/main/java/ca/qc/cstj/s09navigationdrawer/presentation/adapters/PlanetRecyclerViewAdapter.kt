package ca.qc.cstj.s09navigationdrawer.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.qc.cstj.s09navigationdrawer.databinding.ItemPlanetBinding
import ca.qc.cstj.s09navigationdrawer.domain.models.Planet
import com.bumptech.glide.Glide

class PlanetRecyclerViewAdapter(
    var planets: List<Planet> = listOf(),
    private val onPlanetClick: (Planet) -> Unit) : RecyclerView.Adapter<PlanetRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetRecyclerViewAdapter.ViewHolder {
        return ViewHolder(ItemPlanetBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PlanetRecyclerViewAdapter.ViewHolder, position: Int) {
        val planet = planets[position]
        holder.bind(planet)

        holder.itemView.setOnClickListener {
            onPlanetClick(planet)
        }
    }

    override fun getItemCount() = planets.size


    inner class ViewHolder(private val binding: ItemPlanetBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(planet: Planet)  {
            binding.txvPlanetName.text = planet.name

            Glide.with(binding.root.context)
                .load(planet.image)
                .into(binding.imgImagePlanet)
        }
    }


}