package db.poorya.namavatest.ext

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope

fun EditText?.afterTextChange(afterTextChanged: (String) -> Unit): TextWatcher {
    var beforeText = ""
    val watcher = object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            if (beforeText == editable.toString())
                return

            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            beforeText = s.toString()
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    }

    this?.addTextChangedListener(watcher)

    return watcher
}

fun EditText.textString() =
    this.text.toString()

fun TextInputLayout.textString() =
    this.editText?.editableText.toString()

fun EditText.onChange(
    waitMs: Long = 800L,
    scope: CoroutineScope,
    destinationFunction: (String) -> Unit,
): TextWatcher = afterTextChange(debounce(waitMs, scope, destinationFunction))