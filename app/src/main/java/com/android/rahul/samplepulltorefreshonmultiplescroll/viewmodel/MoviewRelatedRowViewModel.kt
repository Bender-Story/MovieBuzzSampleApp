package com.android.rahul.samplepulltorefreshonmultiplescroll.viewmodel

import com.android.rahul.samplepulltorefreshonmultiplescroll.model.Result

// View model for the related list(row view)
class MoviewRelatedRowViewModel(
    val result: Result,
    private val onSelect: (Result) -> Unit
) {
    fun onClick() {
        onSelect.invoke(result)
    }
}