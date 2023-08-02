@file:Suppress("unused")

package com.vcdevelopmentworld.interview

import android.animation.ObjectAnimator
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.nfc.NfcAdapter.EXTRA_DATA
import android.os.Build
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.AbsoluteSizeSpan
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import java.math.BigDecimal
import java.util.regex.Matcher
import java.util.regex.Pattern

/*
* Execute block into try...catch
* */
inline fun <T> justTry(tryBlock: () -> T) = try {
    tryBlock()
} catch (e: Throwable) {
    e.printStackTrace()
}



/**Show Toast message*/
fun Any.showToast(context: Context, message: String) =
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

fun Context.showToast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Any.showLongToast(context: Context, message: String) =
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()


fun Context.bannerAdsLoad(context: Context,v : AdView)
{
    val adRequest: AdRequest = AdRequest.Builder().build()

    v.loadAd(adRequest)
}


/**Get class name*/
fun Any.getClassName(): String {
    return this::class.java.simpleName
}

fun Any?.isNull() = this == null

fun Any?.notNull() = this != null

/**Null value check*/
fun String?.nullSafe(defaultValue: String = ""): String {
    return this ?: defaultValue
}

fun Int?.nullSafe(defaultValue: Int = 0): Int {
    return this ?: defaultValue
}

fun Float?.nullSafe(defaultValue: Float = 0.0f): Float {
    return this ?: defaultValue
}

fun Long?.nullSafe(defaultValue: Long = 0L): Long {
    return this ?: defaultValue
}

fun Double?.nullSafe(defaultValue: Double = 0.0): Double {
    return this ?: defaultValue
}

fun BigDecimal?.nullSafe(defaultValue: BigDecimal = BigDecimal(0)): BigDecimal {
    return this ?: defaultValue
}

fun Boolean?.nullSafe(defaultValue: Boolean = false): Boolean {
    return this ?: defaultValue
}

fun <T> List<T>?.nullSafe(defaultValue: List<T> = ArrayList()): List<T> {
    return this ?: defaultValue
}

/**
 *   Convert string to model class
 *
 */




/**Validate web url*/
fun String.isWebUrlIsValid(): Boolean {
    val webUrlPattern = Pattern.compile(
        StringBuilder().append("((?:(http|https|Http|Https|rtsp|Rtsp):")
            .append("\\/\\/(?:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)")
            .append("\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,64}(?:\\:(?:[a-zA-Z0-9\\$\\-\\_")
            .append("\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})?\\@)?)?")
            .append("((?:(?:[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}\\.)+")   // named host
            .append("(?:")   // plus top level domain
            .append("(?:aero|arpa|asia|a[cdefgilmnoqrstuwxz])")
            .append("|(?:biz|b[abdefghijmnorstvwyz])")
            .append("|(?:cat|com|coop|c[acdfghiklmnoruvxyz])").append("|d[ejkmoz]")
            .append("|(?:edu|e[cegrstu])").append("|f[ijkmor]")
            .append("|(?:gov|g[abdefghilmnpqrstuwy])").append("|h[kmnrtu]")
            .append("|(?:info|int|i[delmnoqrst])").append("|(?:jobs|j[emop])")
            .append("|k[eghimnrwyz]").append("|l[abcikrstuvy]")
            .append("|(?:mil|mobi|museum|m[acdghklmnopqrstuvwxyz])")
            .append("|(?:name|net|n[acefgilopruz])").append("|(?:org|om)")
            .append("|(?:pro|p[aefghklmnrstwy])").append("|qa").append("|r[eouw]")
            .append("|s[abcdeghijklmnortuvyz]").append("|(?:tel|travel|t[cdfghjklmnoprtvwz])")
            .append("|u[agkmsyz]").append("|v[aceginu]").append("|w[fs]").append("|y[etu]")
            .append("|z[amw]))").append("|(?:(?:25[0-5]|2[0-4]") // or ip addressDataItems
            .append("[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(?:25[0-5]|2[0-4][0-9]")
            .append("|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1]")
            .append("[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}")
            .append("|[1-9][0-9]|[0-9])))").append("(?:\\:\\d{1,5})?)") // plus option port number
            .append("(\\/(?:(?:[a-zA-Z0-9\\;\\/\\?\\:\\@\\&\\=\\#\\~")  // plus option query params
            .append("\\-\\.\\+\\!\\*\\'\\(\\)\\,\\_])|(?:\\%[a-fA-F0-9]{2}))*)?")
            .append("(?:\\b|$)").toString()
    )

    val m = webUrlPattern.matcher(this) as Matcher
    return m.find()
}

/**Get screen height*/
fun Activity.getScreenHeight(): Int {
    val displayMetrics = DisplayMetrics()
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
        display?.getRealMetrics(displayMetrics)
        displayMetrics.heightPixels
    } else {
        @Suppress("DEPRECATION")
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        displayMetrics.heightPixels
    }
}

/**Get screen width*/
fun Activity.getScreenWidth(): Int {
    val displayMetrics = DisplayMetrics()
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
        display?.getRealMetrics(displayMetrics)
        displayMetrics.widthPixels
    } else {
        @Suppress("DEPRECATION")
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        displayMetrics.widthPixels
    }
}

/**LiveData observe single time*/
fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)
            removeObserver(this)
        }
    })
}

/**Update progress bar with animation*/
fun ProgressBar.updateProgressWithAnimation(updateProgressValue: Int) {
    val animation: ObjectAnimator =
        ObjectAnimator.ofInt(this, "progress", progress, updateProgressValue)
    animation.duration = 400
    animation.interpolator = DecelerateInterpolator()
    animation.start()
}

/**Remove HTML tag from string*/
fun String.removeHtmlTags(): String {
    val pattern: Pattern = Pattern.compile("<.+?>")
    val m: Matcher = pattern.matcher(this)
    return m.replaceAll("")
}

/**Apply text color*/
fun AppCompatTextView.applyTextColor(color: Int) {
    this.setTextColor(ContextCompat.getColor(this.context, color))
}

/**Apply background color*/
fun View.applyBackgroundColor(color: Int) {
    this.setBackgroundColor(ContextCompat.getColor(this.context, color))
}

/**Apply card background color*/
fun CardView.applyCardBackgroundColor(color: Int) {
    this.setCardBackgroundColor(ContextCompat.getColor(this.context, color))
}

/**Apply background drawable*/
fun ViewGroup.applyBackgroundDrawable(id: Int) {
    this.setBackgroundResource(id)
}



/**Set background resource*/
fun AppCompatImageView.setBGResource(resourceId: Int) {
    setImageDrawable(ContextCompat.getDrawable(context, resourceId))
}

/**Set background resource with active/inActive state*/
fun View.setBGResource(isActivate: Boolean, activeResourceId: Int, inActiveResourceId: Int) {
    setBackgroundResource(if (isActivate) activeResourceId else inActiveResourceId)
}

/**Make clickable text*/
fun AppCompatTextView.setClickableText(
    normalText: String,
    clickableText: String,
    normalTextSize: Int? = null,
    clickableTextSize: Int? = null,
    normalTextColor: Int? = null,
    clickableTextColor: Int? = null,

    onClick: () -> Unit
) {
    val normalSpanText = SpannableString("$normalText ")
    val clickableSpanText = SpannableString(clickableText)


    if (normalTextSize != null) {
        normalSpanText.setSpan(
            AbsoluteSizeSpan(normalTextSize),
            0,
            normalSpanText.length,
            SPAN_INCLUSIVE_INCLUSIVE
        ) // set size
    }

    if (normalTextColor != null) {
        normalSpanText.setSpan(
            ForegroundColorSpan(normalTextColor),
            0,
            normalSpanText.length,
            0
        )// set color
    }

    val builder = SpannableStringBuilder()
    builder.append(normalSpanText)


    val clickableSpan = object : ClickableSpan() {
        override fun onClick(textView: View) {
            onClick()
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.isUnderlineText = false
        }
    }
    clickableSpanText.setSpan(
        clickableSpan,
        0,
        clickableSpanText.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    if (clickableTextSize != null) {
        clickableSpanText.setSpan(
            AbsoluteSizeSpan(clickableTextSize),
            0,
            clickableSpanText.length,
            SPAN_INCLUSIVE_INCLUSIVE
        ) // set size
    }

    if (clickableTextColor != null) {
        clickableSpanText.setSpan(
            ForegroundColorSpan(clickableTextColor),
            0,
            clickableSpanText.length,
            0
        ) // set size
    }

    builder.append(clickableSpanText)

    movementMethod = LinkMovementMethod.getInstance()

    setText(builder, TextView.BufferType.SPANNABLE)
    highlightColor = Color.TRANSPARENT
}



/**Hide keyboard*/
fun Activity.hideSoftKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}

/**Show keyboard*/
fun Activity.showSoftKeyboard(mView: View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInputFromWindow(
        mView.applicationWindowToken,
        InputMethodManager.SHOW_FORCED,
        0
    )
}

inline fun <reified T : Any> Activity.extra(key: String = EXTRA_DATA, default: T? = null) = lazy {
    val value = intent?.extras?.get(key)
    if (value is T) value else default
}

inline fun <reified T : Any> Activity.extraNotNull(key: String = EXTRA_DATA, default: T? = null) =
    lazy {
        val value = intent?.extras?.get(key)
        requireNotNull(if (value is T) value else default) { key }
    }

inline fun <reified T : Any> Fragment.extra(key: String = EXTRA_DATA, default: T? = null) = lazy {
    val value = arguments?.get(key)
    if (value is T) value else default
}

inline fun <reified T : Any> Fragment.extraNotNull(key: String = EXTRA_DATA, default: T? = null) =
    lazy {
        val value = arguments?.get(key)
        requireNotNull(if (value is T) value else default) { key }
    }


fun Context.dpToPx(dp: Int): Int {
    return (dp * resources.displayMetrics.density).toInt()
}

fun Context.pxToDp(px: Int): Int {
    return (px / resources.displayMetrics.density).toInt()
}

fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

    if (connectivityManager != null) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            return networkCapabilities != null &&
                    (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }
    return false
}


fun showNoInternetDialog(context: Context) {
    val builder = AlertDialog.Builder(context)
    builder.setTitle("No Internet Connection")
        .setMessage("Please check your internet connection and try again.")
        .setPositiveButton("OK") { dialog: DialogInterface, _: Int ->
            dialog.dismiss()
        }
    val dialog = builder.create()
    dialog.show()
}




