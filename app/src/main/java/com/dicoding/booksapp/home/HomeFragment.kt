package com.dicoding.booksapp.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bangkit.core.data.Resource
import com.bangkit.core.ui.BooksAdapter
import com.dicoding.booksapp.R
import com.dicoding.booksapp.databinding.FragmentHomeBinding
import com.dicoding.booksapp.detail.DetailBookActivity
import com.dicoding.booksapp.utils.Util.setVisible
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val booksAdapter = BooksAdapter()
            booksAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailBookActivity::class.java)
                intent.putExtra(DetailBookActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.book.observe(viewLifecycleOwner, { book ->
                if (book != null) {
                    when (book) {
                        is Resource.Loading -> binding.ltLoading.setVisible(true)
                        is Resource.Success -> {
                            binding.ltLoading.setVisible(false)
                            booksAdapter.setData(book.data)
                        }
                        is Resource.Error -> {
                            binding.ltLoading.setVisible(false)
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                book.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(binding.rvTourism) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = booksAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}