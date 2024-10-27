package com.gabrielportari.revisioncomponents.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.gabrielportari.revisioncomponents.R
import com.gabrielportari.revisioncomponents.adapter.ViewPagerAdapter
import com.gabrielportari.revisioncomponents.databinding.ActivityTabLayoutAndBottomNavBinding
import com.gabrielportari.revisioncomponents.fragment.FirstBottomNavFragment
import com.gabrielportari.revisioncomponents.fragment.FirstTabLayoutFragment
import com.gabrielportari.revisioncomponents.fragment.SecondBottomNavFragment
import com.gabrielportari.revisioncomponents.fragment.SecondTabLayoutFragment
import com.google.android.material.tabs.TabLayoutMediator

class TabLayoutAndBottomNavActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTabLayoutAndBottomNavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tab_layout_and_bottom_nav)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityTabLayoutAndBottomNavBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* Configuração do tab layout */
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPagerAdapter.addFragment(FirstTabLayoutFragment(), "Fragment 1")
        viewPagerAdapter.addFragment(SecondTabLayoutFragment(), "Fragment 2")
        viewPagerAdapter.notifyDataSetChanged()

        binding.viewPager2.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager2){ tab, position ->
            tab.text = viewPagerAdapter.getPageTitle(position)
            binding.viewPager2.setCurrentItem(tab.position, true)
        }.attach()

        /* Configuração da bottom navigation view */
        val bottomNavFragment1 = FirstBottomNavFragment()
        val bottomNavFragment2 = SecondBottomNavFragment()

        //define o fragment inicial
        changeBottomNavFragment(bottomNavFragment1)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_nav_menu1 -> changeBottomNavFragment(bottomNavFragment1)
                R.id.bottom_nav_menu2 -> changeBottomNavFragment(bottomNavFragment2)
            }
            true
        }
    }

    /* Função para mudar o fragment da bottom navigation view */
    private fun changeBottomNavFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.bottom_nav_frame_layout, fragment)
            commit()
        }
}