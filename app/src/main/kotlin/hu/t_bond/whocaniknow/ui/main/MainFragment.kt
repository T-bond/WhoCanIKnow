package hu.t_bond.whocaniknow.ui.main

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import hu.t_bond.whocaniknow.R
import hu.t_bond.whocaniknow.databinding.MainFragmentBinding


@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModels()
    private val adapter = ContactListAdapter()
    private var connectivityManager: ConnectivityManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        showLoadingIndicator()
        viewModel.getContacts().observe(viewLifecycleOwner) {
            adapter.contacts = it

            if (it.isEmpty()) {
                showNoDataIndicator()
            } else {
                showData()
            }
        }

        viewModel.getDataAvailable().observe(viewLifecycleOwner) {
            if (it) {
                if (adapter.contacts.isEmpty()) {
                    showLoadingIndicator()
                } else {
                    showData()
                }
            } else {
                showNoDataIndicator()
            }
        }

        setHasOptionsMenu(true)

        connectivityManager =
            getSystemService(binding.root.context, ConnectivityManager::class.java)
        connectivityManager?.registerDefaultNetworkCallback(viewModel.networkCallback)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()

        connectivityManager?.unregisterNetworkCallback(viewModel.networkCallback)
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.main, menu)
        val searchViewItem = menu.findItem(R.id.app_bar_filter).actionView as SearchView
        searchViewItem.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        viewModel.filter = it
                    }

                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    onQueryTextSubmit(newText)

                    return true
                }
            })
        }

        menu.findItem(R.id.app_bar_about).setOnMenuItemClickListener {
            viewModel.filter = ""
            findNavController().navigate(MainFragmentDirections.openAbout())

            true
        }

        super.onCreateOptionsMenu(menu, menuInflater)
    }

    private fun showLoadingIndicator() {
        binding.recyclerView.visibility = View.GONE
        binding.loadingIndication.visibility = View.VISIBLE
        binding.noDataIndicator.visibility = View.GONE
        binding.loadingProgressIndicator.show()
    }

    private fun showNoDataIndicator() {
        binding.recyclerView.visibility = View.GONE
        binding.loadingIndication.visibility = View.GONE
        binding.noDataIndicator.visibility = View.VISIBLE
        binding.loadingProgressIndicator.hide()
    }

    private fun showData() {
        binding.recyclerView.visibility = View.VISIBLE
        binding.loadingIndication.visibility = View.GONE
        binding.noDataIndicator.visibility = View.GONE
        binding.loadingProgressIndicator.hide()
    }

}