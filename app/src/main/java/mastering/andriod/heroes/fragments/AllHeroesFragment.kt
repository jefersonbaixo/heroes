package mastering.andriod.heroes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import mastering.andriod.heroes.viewmodels.AllHeroesViewModel
import mastering.andriod.heroes.adapters.HeroClickListener
import mastering.andriod.heroes.adapters.HeroesAdapter
import mastering.andriod.heroes.R
import mastering.andriod.heroes.databinding.FragmentAllHeroesBinding
import mastering.andriod.heroes.models.Hero

class AllHeroesFragment : Fragment(), HeroClickListener {

    private lateinit var binding: FragmentAllHeroesBinding
    private lateinit var viewModel: AllHeroesViewModel
    private lateinit var adapter: HeroesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllHeroesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[AllHeroesViewModel::class.java]

        viewModel.fetchHeroes()
        setupObservers()
        setupList()
    }

    private fun setupList() {
        with(binding) {
            adapter = HeroesAdapter(mutableListOf(), this@AllHeroesFragment)
            list.adapter = adapter

            list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        viewModel.fetchHeroes()
                    }
                }
            })
        }
    }

    private fun setupObservers() {
        viewModel.heroesData.observe(viewLifecycleOwner) {
            adapter.updateHeroes(it)
        }
        viewModel.loadingData.observe(viewLifecycleOwner) {
            binding.loading.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    override fun onHeroClicked(hero: Hero) {
        findNavController().navigate(R.id.action_allHeroesFragment_to_heroDetailsFragment)
    }

}