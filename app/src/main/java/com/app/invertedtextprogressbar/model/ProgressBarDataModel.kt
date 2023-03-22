package com.app.invertedtextprogressbar.model

data class ProgressBarDataModel(
    var title: String? = null,
    var acceptedValue: String? = null,
    var presentedValue: String? = null,
    var percentage: Int = 0
)