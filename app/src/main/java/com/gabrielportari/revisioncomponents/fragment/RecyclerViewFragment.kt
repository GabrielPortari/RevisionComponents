package com.gabrielportari.revisioncomponents.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabrielportari.revisioncomponents.R
import com.gabrielportari.revisioncomponents.adapter.UserAdapter
import com.gabrielportari.revisioncomponents.databinding.FragmentRecyclerViewBinding
import com.gabrielportari.revisioncomponents.databinding.FragmentSecondBinding
import com.gabrielportari.revisioncomponents.model.User
import kotlin.random.Random

class RecyclerViewFragment : Fragment() {

    private var _binding: FragmentRecyclerViewBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*Inicio da criação da recycler view*/
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        /*Criação dos dados*/
        val users = List(10){generateRandomUser()}
        val adapter = UserAdapter(users)
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun generateRandomUser(): User {
        val names = listOf("Alice", "Bob", "Carol", "David", "Eve", "Frank", "Grace", "Henry", "Isabella", "Jack")
        val domains = listOf("example.com", "gmail.com", "yahoo.com")

        val name = names.random()
        val email = "$name@${domains.random()}"
        val age = Random.nextInt(18, 65) // Idade entre 18 e 65 anos

        return User(name, email, age)
    }
}