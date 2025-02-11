package com.aspis.tech.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.aspis.tech.App
import com.aspis.tech.adapter.UrlListAdapter
import com.aspis.tech.api.ApiState
import com.aspis.tech.databinding.ActivityMainBinding
import com.aspis.tech.di.component.DaggerActivityComponent
import com.aspis.tech.di.module.ActivityModule
import com.aspis.tech.model.Url
import com.aspis.tech.ui.viewModel.UrlViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var urlViewModel: UrlViewModel

    @Inject
    lateinit var urlAdapter: UrlListAdapter

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        dependencyInjection()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(mainBinding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupUrlData()
        setupObserver()

        mainBinding.ivMainBack.setOnClickListener {
            finish()
        }

        mainBinding.btnStartScan.setOnClickListener {
            setupUrlData()
        }
    }

    private fun setupUrlData() {
        val rvUrlList = mainBinding.rvUrlList
        rvUrlList.layoutManager = LinearLayoutManager(this)
        rvUrlList.adapter = urlAdapter
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                urlViewModel.uiState.collect() {
                    when (it) {
                        is ApiState.UiState.Success -> {
                            mainBinding.pbUrlLoader.visibility = View.GONE
                            displayData(it.data.urls)
                            mainBinding.rvUrlList.visibility = View.VISIBLE
                        }

                        is ApiState.UiState.Error -> {
                            mainBinding.pbUrlLoader.visibility = View.GONE
                            mainBinding.rvUrlList.visibility = View.GONE
                            Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_LONG).show()
                        }

                        is ApiState.UiState.Loading -> {
                            mainBinding.pbUrlLoader.visibility = View.VISIBLE
                            mainBinding.rvUrlList.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun displayData(urlList: List<Url>) {
        urlAdapter.updateUrl(urlList)
        urlAdapter.notifyDataSetChanged()
    }

    private fun dependencyInjection() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as App).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }
}