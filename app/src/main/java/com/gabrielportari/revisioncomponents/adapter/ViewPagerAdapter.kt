package com.gabrielportari.revisioncomponents.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle){
    private var fragmentList = ArrayList<Fragment>()
    private var fragmentTitles = ArrayList<String>()

    // retorna a quantidade de fragments
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    // cria o fragment na posição indicada
    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    // adiciona um fragment e titulo nas respectivas listas
    fun addFragment(fragment: Fragment, title: String){
        fragmentList.add(fragment)
        fragmentTitles.add(title)
    }

    //recupera o titulo do fragment da respectiva posicao
    fun getPageTitle(position: Int): String {
        return fragmentTitles[position]
    }

}