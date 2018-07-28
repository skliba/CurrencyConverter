package com.example.stefano.currencyconverter.util.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.stefano.currencyconverter.CurrencyConverterApp
import timber.log.Timber
import java.math.BigDecimal

fun Context.getColorCompat(@ColorRes colorRes: Int) = ContextCompat.getColor(this, colorRes)
fun Context.getDrawableCompat(@DrawableRes drawableRes: Int) = ContextCompat.getDrawable(this, drawableRes)

fun View.visible() {
    this.visibility = VISIBLE
}

fun View.hide() {
    this.visibility = GONE
}

fun View.invisible() {
    this.visibility = INVISIBLE
}

fun View.enable() {
    this.isEnabled = true
}

fun View.disable() {
    this.isEnabled = false
}

fun Double.roundToADecimalPlace() = BigDecimal(this).setScale(1, BigDecimal.ROUND_HALF_UP).toDouble()

fun ViewGroup.inflate(@LayoutRes layout: Int, attachToRoot: Boolean = false): View =
        LayoutInflater.from(context).inflate(layout, this, attachToRoot)

/**
 * For debug purposes
 */
fun toast(text: String) = Toast.makeText(CurrencyConverterApp.appComponent.context(), text, Toast.LENGTH_SHORT).show()

fun View.showKeyboard() = try {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
} catch (e: Exception) {
    Timber.e("Can't change keyboard visibility")
}

fun View.hideKeyboard() = try {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
} catch (e: Exception) {
    Timber.e("Can't change keyboard visibility")
}

inline fun <reified T : Activity> Context.startActivity(b: Bundle? = null, flags: Int = 0) {
    val i = Intent(this, T::class.java).addFlags(flags)
    if (b != null) i.putExtras(b)
    startActivity(i)
}

inline fun <reified T : Activity> Fragment.startActivity(b: Bundle? = null, flags: Int = 0) {
    val i = Intent(activity, T::class.java).addFlags(flags)
    if (b != null) i.putExtras(b)
    startActivity(i)
}

inline fun <reified T : Activity> Context.startActivityClearTop(b: Bundle? = null, flags: Int = Intent.FLAG_ACTIVITY_CLEAR_TOP) {
    val i = Intent(this, T::class.java).addFlags(flags)
    if (b != null) i.putExtras(b)
    startActivity(i)
}

inline fun <reified T : Activity> Activity.startActivityForResult(b: Bundle? = null, requestCode: Int, flags: Int = 0) {
    val i = Intent(this, T::class.java).addFlags(flags)
    if (b != null) i.putExtras(b)
    startActivityForResult(i, requestCode)
}

inline fun <reified T : Activity> Fragment.startActivityForResult(b: Bundle? = null, requestCode: Int, flags: Int = 0) {
    val i = Intent(activity, T::class.java).addFlags(flags)
    if (b != null) i.putExtras(b)
    startActivityForResult(i, requestCode)
}

inline fun <reified T : Fragment> FragmentActivity.replaceFragment(@IdRes layoutId: Int, args: Bundle? = null) {
    val fragment = Fragment.instantiate(this, T::class.java.name, args)
    supportFragmentManager
            .beginTransaction()
            .replace(layoutId, fragment)
            .commitNow()
}

inline val <T> T.exhaustive: T
    get() = this
