package org.sample.rxpm.adapter.promo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.sample.rxpm.R
import org.sample.rxpm.utils.setupSpan
import org.sample.rxpm.adapter.promo.PromoAdapter.ViewTypes.EVEN_VIEW_TYPE
import org.sample.rxpm.adapter.promo.PromoAdapter.ViewTypes.LAST_VIEW_TYPE
import org.sample.rxpm.adapter.promo.PromoAdapter.ViewTypes.ODD_VIEW_TYPE
import org.sample.rxpm.model.promo.Promo

class PromoAdapter(var items: List<Promo> = listOf(), private val onItemClicked: (Promo) -> Unit)
    : RecyclerView.Adapter<PromoAdapter.PromoViewHolder>() {

    init {
        setHasStableIds(true)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: PromoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(getLayoutForType(viewType), parent, false)
        checkLastViewSpan(view, viewType)
        return PromoViewHolder(view, onItemClicked)
    }

    private fun checkLastViewSpan(view: View, viewType: Int) {
        if (viewType != LAST_VIEW_TYPE) return
        setupSpan(view)
    }

    private fun getLayoutForType(type: Int): Int {
        return when(type) {
            EVEN_VIEW_TYPE -> R.layout.view_promo_even
            ODD_VIEW_TYPE -> R.layout.view_promo_odd
            else -> R.layout.view_promo_last
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            position + 1 == items.size -> LAST_VIEW_TYPE
            position % 2 == 0 -> EVEN_VIEW_TYPE
            else -> ODD_VIEW_TYPE
        }
    }

    object ViewTypes {
        const val EVEN_VIEW_TYPE = 1
        const val ODD_VIEW_TYPE = 2
        const val LAST_VIEW_TYPE = 3
    }

    class PromoViewHolder(view: View, onItemClicked: (Promo) -> Unit): RecyclerView.ViewHolder(view) {

        private var promo: Promo? = null

        init {
            itemView.findViewById(R.id.clickableArea).setOnClickListener { promo?.apply { onItemClicked.invoke(this) } }
        }

        fun bind(item: Promo) {
            promo = item
            (itemView.findViewById(R.id.label) as TextView).text = item.title
        }
    }
}
