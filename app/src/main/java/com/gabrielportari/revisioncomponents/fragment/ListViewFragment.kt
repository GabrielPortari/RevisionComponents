package com.gabrielportari.revisioncomponents.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.gabrielportari.revisioncomponents.R
import com.gabrielportari.revisioncomponents.databinding.FragmentListViewBinding

class ListViewFragment : Fragment(){

    private var _binding: FragmentListViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val games = generateGames()
        val listAdapter : ArrayAdapter<String> = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, games)
        binding.listView.adapter = listAdapter

    }

    private fun generateGames() : Array<String> {
        return arrayOf("The Legend of Zelda", "Super Mario Bros.", "Minecraft", "Fortnite", "Apex Legends", "League of Legends", "Counter-Strike", "Call of Duty", "Assassin's Creed", "Overwatch", "Grand Theft Auto", "Red Dead Redemption", "World of Warcraft", "Final Fantasy", "The Witcher", "Cyberpunk 2077", "Halo", "God of War", "Uncharted", "Doom", "Dark Souls", "Bloodborne", "Sekiro", "Elden Ring", "Resident Evil", "Silent Hill", "Metal Gear Solid", "Street Fighter", "Mortal Kombat", "Tekken", "Splatoon", "Animal Crossing", "Pokémon", "Monster Hunter", "Fall Guys", "Among Us", "PUBG", "Rocket League", "Valorant", "Destiny", "Borderlands", "Far Cry", "Just Cause", "Hitman", "Tomb Raider", "Horizon Zero Dawn", "Ghost of Tsushima", "Persona", "Yakuza")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}