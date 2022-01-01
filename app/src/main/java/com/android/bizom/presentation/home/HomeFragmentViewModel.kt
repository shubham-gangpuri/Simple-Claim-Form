package com.android.bizom.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.bizom.domain.SampleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(private val sampleUseCase: SampleUseCase) : ViewModel(){

    fun getOldClaimsList() {
        //TODO
    }

}