package com.dicoding.booksapp.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bangkit.core.data.Resource
import com.bangkit.core.domain.model.Book
import com.bumptech.glide.request.RequestOptions
import com.dicoding.booksapp.R
import com.dicoding.booksapp.databinding.ActivityDetailBookBinding
import com.dicoding.booksapp.utils.Util.addChip
import com.dicoding.booksapp.utils.Util.setVisible
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.viewModel

class DetailBookActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
        const val BASE_URL_IMAGE = "http://covers.openlibrary.org/b/id/"
    }

    private val detailBookViewModel: DetailBookViewModel by viewModel()
    private lateinit var binding: ActivityDetailBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val detailBook = intent.getParcelableExtra<Book>(EXTRA_DATA)

        showDetailBook(detailBook)
    }

    private fun showDetailBook(detailBook: Book?) {
        if (detailBook != null) {
            var statusFavorite = detailBook.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailBookViewModel.setFavoriteBook(detailBook, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
        val detailBookID = detailBook?.bookId
        if (detailBookID != null) {
            detailBookViewModel.setSelectedDetailBook(detailBookID)
            detailBookViewModel.detailbook.observe(this, { it ->
                when (it) {
                    is Resource.Loading -> showLoading(true)
                    is Resource.Success -> {
                        showLoading(false)
                        val detail = it.data
                        if (detail != null) {
                            binding.collapsingAppbar.title = detail.title

                            binding.content.tvWebsite.text = resources.getString(R.string.website)
                            binding.content.tvWebsite.movementMethod =
                                LinkMovementMethod.getInstance()
                            binding.content.tvWebsite.setOnClickListener {
                                val browserIntent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://openlibrary.org" + detail.bookdetailId)
                                )
                                startActivity(browserIntent)
                            }

                            if (detail.first_publish_date == "null") {
                                binding.content.tvFirstPublish.visibility = View.GONE
                            } else {
                                binding.content.tvFirstPublish.text = resources.getString(
                                    R.string.publishdate,
                                    detail.first_publish_date
                                )
                            }


                            if (detail.first_sentence == "null") {
                                binding.content.tvFirstSentence.visibility = View.GONE
                            } else {
                                binding.content.tvFirstSentence.text = resources.getString(
                                    R.string.fsentence,
                                    "\n\n" + detail.first_sentence
                                )

                            }

                            binding.content.detailSubject.apply {
                                removeAllViews()
                                visibility = View.VISIBLE
                                detail.subjects.split(",")
                                    .map { it.trim() }
                                    .map { addChip(it) }
                            }


                            Picasso
                                .get()
                                .load(BASE_URL_IMAGE + detail.cover.toString() + "-L.jpg")
                                .apply {
                                    RequestOptions.placeholderOf(com.bangkit.core.R.drawable.ic_loading)
                                        .error(com.bangkit.core.R.drawable.ic_error)
                                }
                                .into(binding.ivDetailImage)
                            Log.d("test", detail.toString())

                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(this@DetailBookActivity, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_white
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_not_favorite_white
                )
            )
        }
    }

    private fun showLoading(state: Boolean) {
        with(binding) {
            if (state) {
                content.ltLoading.setVisible(true)
                content.detailSubject.visibility = View.GONE
                content.tvSubject.visibility = View.GONE
                content.tvWebsite.visibility = View.GONE
                content.tvFirstSentence.visibility = View.GONE
                content.tvFirstPublish.visibility = View.GONE
            } else {
                content.ltLoading.setVisible(false)
                content.detailSubject.visibility = View.VISIBLE
                content.tvSubject.visibility = View.VISIBLE
                content.tvWebsite.visibility = View.VISIBLE
                content.tvFirstSentence.visibility = View.VISIBLE
                content.tvFirstPublish.visibility = View.VISIBLE
            }
        }

    }
}