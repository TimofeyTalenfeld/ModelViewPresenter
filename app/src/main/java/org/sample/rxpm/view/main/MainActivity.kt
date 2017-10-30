package org.sample.rxpm.view.main

import org.sample.rxpm.R
import org.sample.rxpm.view.base.BaseActivity
import org.sample.rxpm.view.promo.PromoFragment

class MainActivity: BaseActivity() {
    override fun getLayoutId() = R.layout.activity_main
    override fun fragment() = PromoFragment::class.java to null
}
