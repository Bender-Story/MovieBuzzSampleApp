package com.android.rahul.samplepulltorefreshonmultiplescroll.viewmodel

import com.android.rahul.samplepulltorefreshonmultiplescroll.model.Result

// view model for the main movie list
class MainRowViewModel(
    val result: Result?,
    val showProgress: Boolean = false,
    private val onSelect: (Result) -> Unit
) {
    fun onClick() {
        result?.let { onSelect.invoke(it) }
    }
}