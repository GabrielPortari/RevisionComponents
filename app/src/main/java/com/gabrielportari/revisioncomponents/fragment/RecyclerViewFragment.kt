package com.gabrielportari.revisioncomponents.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabrielportari.revisioncomponents.adapter.UserAdapter
import com.gabrielportari.revisioncomponents.databinding.FragmentRecyclerViewBinding
import com.gabrielportari.revisioncomponents.model.UserData
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
        val users = List(30){generateRandomUser()}
        val adapter = UserAdapter(users)
        binding.recyclerView.adapter = adapter
    }

    fun generateRandomUser(): UserData {
        val names = listOf("Alice", "Bob", "Carol", "David", "Eve", "Frank", "Grace", "Henry", "Isabella", "Jack")
        val domains = listOf("example.com", "gmail.com", "yahoo.com")

        val name = names.random()
        val email = "$name@${domains.random()}"
        val phone = "+55 (11) 9${Random.nextInt(10000000, 99999999)}"

        return UserData(name, email, phone)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
