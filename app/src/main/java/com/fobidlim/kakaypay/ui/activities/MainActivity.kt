package com.fobidlim.kakaypay.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.fobidlim.kakaypay.R
import com.fobidlim.kakaypay.ViewModelFactory
import com.fobidlim.kakaypay.applicationComponent
import com.fobidlim.kakaypay.getViewModel
import com.fobidlim.kakaypay.libs.BaseActivity
import com.fobidlim.kakaypay.libs.IntentKey
import com.fobidlim.kakaypay.models.Media
import com.fobidlim.kakaypay.ui.adapters.MediaAdapter
import com.fobidlim.kakaypay.viewmodels.MainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val adapter: MediaAdapter by lazy {
        MediaAdapter(viewModel)
    }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        applicationComponent.inject(this)
        viewModel = getViewModel(viewModelFactory)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        recycler_view.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = this@MainActivity.adapter
        }

        viewModel.updateData()
            .compose(bindToLifecycle())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { adapter.updateData(it) }

        viewModel.showErrorToast()
            .compose(bindToLifecycle())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }

        viewModel.showMediaDetails()
            .compose(bindToLifecycle())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::showMediaDetailsActivity)
    }

    private fun showMediaDetailsActivity(media: Media) =
        Intent(this, MediaDetailsActivity::class.java).let {
            it.putExtra(IntentKey.MEDIA, media)
            startActivity(it)
        }
}