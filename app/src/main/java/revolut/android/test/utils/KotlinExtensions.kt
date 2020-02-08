package revolut.android.test.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG)
        .show()
}

fun Activity.hideKeyBoard() {
    val view: View? = this.currentFocus
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view?.windowToken, 0)
}

fun Activity.showKeyBoard() {
    val view: View? = this.currentFocus
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    //imm.hideSoftInputFromWindow(view?.windowToken, InputMethodManager.SHOW_IMPLICIT)
    imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}