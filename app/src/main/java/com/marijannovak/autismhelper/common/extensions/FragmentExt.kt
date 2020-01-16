package com.marijannovak.autismhelper.common.extensions

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.marijannovak.autismhelper.common.base.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


fun Fragment.snackbar(@StringRes stringRes: Int) {
    view?.let {
        Snackbar.make(it, stringRes, Snackbar.LENGTH_SHORT).show()
    }
}

fun Fragment.snackbar(text: String) {
    view?.let {
        Snackbar.make(it, text, Snackbar.LENGTH_SHORT).show()
    }
}

val Fragment.navController: NavController? get() = view?.findNavController()

val Fragment.navGraph: NavGraph? get() = navController?.graph

val Fragment.navGraphViewModelStoreOwner: ViewModelStoreOwner
    get() {
        if (navController == null || navGraph?.id == null) {
            throw Exception("invalid nav controller or nav graph id")
        } else {
            return navController!!.getViewModelStoreOwner(navGraph!!.id)
        }
    }

inline fun <reified V : BaseViewModel> Fragment.parentActivityViewModel(): Lazy<V> =
    sharedViewModel(from = { requireActivity() })

inline fun <reified V : BaseViewModel> Fragment.navGraphViewModel(): Lazy<V> =
    sharedViewModel(from = { navGraphViewModelStoreOwner })
