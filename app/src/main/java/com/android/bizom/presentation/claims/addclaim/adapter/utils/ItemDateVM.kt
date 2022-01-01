package com.android.bizom.presentation.claims.addclaim.adapter.utils

import android.text.format.DateFormat
import java.util.*

class ItemDateVM {

    val claimDate = DateFormat.format("MMMM d, yyyy ", Date().time) as String
    val label: String = "Claim Date :"

}