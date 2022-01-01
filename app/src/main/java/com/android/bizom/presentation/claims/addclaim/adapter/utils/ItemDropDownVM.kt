package com.android.bizom.presentation.claims.addclaim.adapter.utils

import com.android.bizom.data.file.container.Claimfield

class ItemDropDownVM(val claimfield: Claimfield) {

    val required: Boolean = claimfield.required == 1

    val label: String by lazy { if(required) "${claimfield.label} *" else claimfield.label }

    val input = claimfield.input

}