package com.gabrielportari.revisioncomponents.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.gabrielportari.revisioncomponents.R
import com.gabrielportari.revisioncomponents.databinding.ActivityDrawerRecyclerAndListBinding
import com.gabrielportari.revisioncomponents.fragment.ListViewFragment
import com.gabrielportari.revisioncomponents.fragment.RecyclerViewFragment

class DrawerRecyclerAndListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDrawerRecyclerAndListBinding;
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_drawer_recycler_and_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityDrawerRecyclerAndListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* Criação do navigation drawer */
        actionBarDrawerToggle = ActionBarDrawerToggle(this, binding.main, R.string.nav_open, R.string.nav_close)
        binding.main.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        /* Criação da toolbar */
        setSupportActionBar(binding.materialToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        replaceFragment(RecyclerViewFragment()) // seleciona o fragment inicial
        /* click listener do navigation drawer */

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.nav_show_list -> {
                    replaceFragment(ListViewFragment())
                    binding.main.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_show_recycler -> {
                    replaceFragment(RecyclerViewFragment())
                    binding.main.closeDrawer(GravityCompat.START)
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .addToBackStack(null)
            .commit()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            true
        }else super.onOptionsItemSelected(item)
    }
}