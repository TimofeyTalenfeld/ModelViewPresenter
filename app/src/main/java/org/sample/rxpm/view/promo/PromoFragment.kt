package org.sample.rxpm.view.promo

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.view_progress_bar.*
import org.sample.rxpm.R
import org.sample.rxpm.adapter.promo.PromoAdapter
import org.sample.rxpm.di.ComponentManager
import org.sample.rxpm.presentation.promo.IPromoPresentationModel
import org.sample.rxpm.presentation.promo.PromoPresentationModel
import org.sample.rxpm.view.base.RxBindableFragment
import javax.inject.Inject

class PromoFragment : RxBindableFragment<IPromoPresentationModel>() {

    @Inject lateinit override var model: PromoPresentationModel

    private lateinit var listAdapter: PromoAdapter

    override fun getLayoutId() = R.layout.fragment_list

    override fun initializeUI(view: View) {
        setupRecycler()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentManager.promoComponent().inject(this)
    }

    override fun onBound() {
        model.feed.bindToView {
            listAdapter.items = it
            listAdapter.notifyDataSetChanged()
            if (listAdapter.itemCount == 0) showProgress() else hideProgress()
        }

        model.showWeb.bindToView {
            // show WebView
        }
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

            listAdapter = PromoAdapter { model.onPromoClicked.accept(it) }
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
