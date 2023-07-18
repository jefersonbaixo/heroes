package mastering.andriod.heroes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import mastering.andriod.heroes.R
import mastering.andriod.heroes.databinding.FragmentHeroDetailsBinding

class HeroDetailsFragment : Fragment() {

    private lateinit var binding: FragmentHeroDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupToolbar()

    }

    private fun setupView() {
        val args: HeroDetailsFragmentArgs by navArgs()
        val name = args.hero.name
        val imageUrl = args.hero.thumbnail.getFullImageUrl()
        val description = args.hero.description.ifEmpty { "No description available" }

        with(binding) {
            heroName.text = name
            heroDescription.text = description
            Glide.with(this@HeroDetailsFragment).load(imageUrl).centerCrop()
                .placeholder(R.drawable.loading_bg_color)
                .error(R.drawable.baseline_no_photography_24)
                .into(heroImage)
        }
    }

    private fun setupToolbar() {
        val toolbar = binding.toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.arrow_back)
        }
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

}