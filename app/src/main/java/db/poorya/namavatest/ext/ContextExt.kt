package db.poorya.namavatest.ext

import android.content.*
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.content.res.TypedArray
import android.net.Uri
import android.provider.Settings
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.*
import androidx.appcompat.app.AppCompatDialog
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import java.io.File


fun Context.isDarkTheme(): Boolean {
    return resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
}

fun Context.inflateView(@LayoutRes layoutRes: Int): View {
    return LayoutInflater.from(this)
        .inflate(layoutRes, null)
}

fun Context.makeDialog(
    @LayoutRes layoutRes: Int,
    @StyleRes themRes: Int,
    isBottom: Boolean = false
): AppCompatDialog {
    return AppCompatDialog(this, themRes)
        .apply {
            setContentView(inflateView(layoutRes))
            if (isBottom) {
                val wManager: WindowManager.LayoutParams = this.window?.attributes!!
                wManager.gravity = Gravity.BOTTOM or Gravity.CENTER
            }
        }
}

fun Context.openAppInfoSetting() {
    //redirect user to app Settings
    val i = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    i.addCategory(Intent.CATEGORY_DEFAULT)
    i.data = Uri.parse("package:$packageName")
    ContextCompat.startActivity(this, i, null)
}

fun Context.openURL(url: String, @StringRes notFoundString: Int) {
    try {
        val fullUrl = if (url.startsWith("http")) url else "http://$url"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(fullUrl))
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        toast(getString(notFoundString))
    }
}

fun Context.share(text: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}

fun Context.shareApp(@StringRes titleString: Int? = null) {
    val app: ApplicationInfo = applicationContext.applicationInfo
    val filePath: String = app.sourceDir
    val intent = Intent(Intent.ACTION_SEND)

    // MIME of .apk is "application/vnd.android.package-archive".
    // but Bluetooth does not accept this. Let's use "*/*" instead.
    intent.type = "*/*"


    // Append file and send Intent
    intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(File(filePath)))
    startActivity(
        Intent.createChooser(
            intent,
            titleString?.let { getString(titleString) } ?: "Share App"
        )
    )
}

fun Context.openEmail(
    addresses: Array<String>,
    cc: Array<String> = emptyArray(),
    bcc: Array<String> = emptyArray(),
    subject: String = "",
    message: String = "",
    @StringRes notFoundString: Int
) {
    //https://developer.android.com/guide/components/intents-common#ComposeEmail
    val intentGoogle = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, addresses)
        putExtra(Intent.EXTRA_CC, cc)
        putExtra(Intent.EXTRA_BCC, bcc)
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, message)
    }

    if (intentGoogle.resolveActivity(packageManager) != null)
        startActivity(intentGoogle)
    else {
        toast(getString(notFoundString))
    }

}

@ColorInt
fun Context.getAttrColor(@AttrRes attrID: Int): Int {
    val typedValue = TypedValue()
    val theme = this.theme
    theme.resolveAttribute(attrID, typedValue, true)
    return typedValue.data
}

fun Context.getAttr(attrID: Int): Int {
    val typedValue = TypedValue()
    val theme = this.theme
    theme.resolveAttribute(attrID, typedValue, true)
    return typedValue.data
}

fun Context.getDrawableCompat(res: Int): VectorDrawableCompat? {
    return VectorDrawableCompat.create(resources, res, theme)
}

fun Context.hasPermissions(vararg permission: String): Boolean {
    var result = true

    permission.forEach {
        val a = ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
        result = result && a
    }

    return result
}


fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Context.getStatusBarHeightPixel(): Int = try {
    val res = resources.getIdentifier("status_bar_height", "dimen", "android")

    resources.getDimensionPixelSize(res)
} catch (e: Exception) {
    "Context.getStatusBarHeightPixel: error: $e".logE("getStatusBarHeightPixel")

    80
}

fun Context.getActionBarHeightPixel(): Int {
    val styledAttributes: TypedArray =
        theme.obtainStyledAttributes(intArrayOf(android.R.attr.actionBarSize))
    val mActionBarSize = styledAttributes.getDimension(0, 157f).toInt()
    styledAttributes.recycle()

    return mActionBarSize
}

fun Context.copyToClipboard(text: String) {
    val myClipboard: ClipboardManager? =
        ContextCompat.getSystemService(this, ClipboardManager::class.java)

    val myClip = ClipData.newPlainText("copied:", text)
    myClipboard!!.setPrimaryClip(myClip)
}