package dev.robert.foodDonor.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import dev.robert.foodDonor.adapter.DonationsAdapter
import dev.robert.foodDonor.databinding.FragmentDonationsBinding
import dev.robert.foodDonor.utils.Resource
import dev.robert.foodDonor.viewmodel.DonationsViewModel


@AndroidEntryPoint
class DonationsFragment : Fragment() {
    private lateinit var binding: FragmentDonationsBinding
    private lateinit var auth: FirebaseAuth
    private val viewModel: DonationsViewModel by viewModels()
    private val adapter by lazy { DonationsAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDonationsBinding.inflate(inflater, container, false)
        val view = binding.root
        auth = FirebaseAuth.getInstance()

        binding.recyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.getDonations()
        }

        viewModel.donations.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Resource.Loading -> {
                    binding.progressCircular.isVisible = true
                }
                is Resource.Success -> {
                    binding.progressCircular.isVisible = false
                    adapter.submitList(state.data)
                }
                is Resource.Error -> {
                    binding.progressCircular.isVisible = false
                    Toast.makeText(requireContext(), "An error occurred", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return view
    }

}