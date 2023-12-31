package mastering.andriod.heroes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mastering.andriod.heroes.databinding.ItemNameBinding
import mastering.andriod.heroes.models.Hero

class HeroesAdapter(private var heroes: List<Hero>, private val listener: HeroClickListener): RecyclerView.Adapter<HeroesAdapter.HeroVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroVH {
        val binding = ItemNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeroVH(binding)
    }

    override fun getItemCount(): Int = heroes.size

    override fun onBindViewHolder(holder: HeroVH, position: Int) {
        holder.bind(heroes[position])
    }

    fun updateHeroes(newHeroes: List<Hero>) {
        val oldSize = heroes.size
        heroes = newHeroes
        notifyItemRangeInserted(oldSize, newHeroes.size)
    }

    inner class HeroVH(private val binding: ItemNameBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: Hero) {
            binding.name.text = hero.name
            binding.name.setOnClickListener {
                listener.onHeroClicked(hero)
            }
        }
    }
}

interface HeroClickListener {
    fun onHeroClicked(hero: Hero)
}