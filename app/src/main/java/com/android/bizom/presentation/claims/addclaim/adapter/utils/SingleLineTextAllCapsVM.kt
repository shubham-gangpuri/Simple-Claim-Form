package com.android.bizom.presentation.claims.addclaim.adapter.utils

import androidx.databinding.ObservableField
import com.android.bizom.data.file.container.Claimfield

class SingleLineTextAllCapsVM(val claimfield: Claimfield) {

    val required: Boolean = claimfield.required == 1
    val label: String by lazy { if(required) "${claimfield.label} *" else claimfield.label }

    val input: ObservableField<String> = ObservableField()
}