package org.sample.mvp.view.promo

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.view_progress_bar.*
import org.sample.mvp.R
import org.sample.mvp.adapter.promo.PromoAdapter
import org.sample.mvp.di.ComponentManager
import org.sample.mvp.model.promo.Promo
import org.sample.mvp.presentation.base.IPresenter
import org.sample.mvp.presentation.promo.PromoPresenter
import org.sample.mvp.view.base.RxBindableFragment
import javax.inject.Inject

class PromoFragment : RxBindableFragment<PromoView, PromoPresenter>(), PromoView {

    @Inject lateinit override var presenter: PromoPresenter

    private lateinit var listAdapter: PromoAdapter

    override fun getLayoutId() = R.layout.fragment_list

    override fun presenter(): IPresenter<PromoView> = presenter

    override fun initializeUI(view: View) {
        setupRecycler()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentManager.promoComponent().inject(this)
    }

    override fun showFeed(items: List<Promo>) {
        listAdapter.items = items
        listAdapter.notifyDataSetChanged()
        if (listAdapter.itemCount == 0) showProgress() else hideProgress()
    }

    override fun showWeb(title: String, url: String) {
        // show WebView
    }

    private fun hideProgress() {
        progress.visibility = View.GONE
    }

    private fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    private fun setupRecycler() {
        list.apply {

            layoutManager = StaggeredGridLayoutManager(SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL) as RecyclerView.LayoutManager

            listAdapter = PromoAdapter { presenter.onPromoClicked(it) }
            adapter = listAdapter

            addItemDecoration(
                HorizontalDividerItemDecoration.Builder(context)
                    .sizeResId(R.dimen.default_divider)
                    .colorResId(R.color.app_background)
                    .build()
            )

            addItemDecoration(
                VerticalDividerItemDecoration.Builder(context)
                    .sizeResId(R.dimen.default_divider)
                    .colorResId(R.color.app_background)
                    .showLastDivider()
                    .build()
            )

            setPadding(
                paddingLeft,
                paddingTop,
                paddingRight - resources.getDimensionPixelSize(R.dimen.default_divider),
                paddingBottom
            )
        }
    }

    companion object {
        const val SPAN_COUNT = 2
    }
}
