package br.com.bb.oewallet.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.androidstartermvvm.R

abstract class BaseActivity : AppCompatActivity() {
    fun replace(fragment: Fragment, tag: String, addToBackstack: Boolean? = false) {
        val fg = supportFragmentManager.findFragmentByTag(tag) ?: fragment
        supportFragmentManager.beginTransaction()
            .also {
                if (addToBackstack == true) {
                    it.addToBackStack(tag)
                }

            }
            .replace(R.id.container, fg, tag)
            .commit()
    }

}
