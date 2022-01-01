package com.android.bizom.presentation.claims.addclaim

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.bizom.data.file.container.Claimtypedetail
import com.android.bizom.domain.SampleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClaimsFragmentViewModel @Inject constructor(private val sampleUseCase: SampleUseCase) : ViewModel(){

    private var _claimTypeDetailMap = MutableLiveData<HashMap<String, List<Claimtypedetail>>>()
    val claimTypeDetailMap: LiveData<HashMap<String, List<Claimtypedetail>>> = _claimTypeDetailMap

    init {
        getSampleResponse()
    }

    private fun getSampleResponse() {
        viewModelScope.launch {
            val tempMap = HashMap<String, List<Claimtypedetail>>()
            sampleUseCase()?.apply {
                this.Claims.forEach {
                    tempMap[it.Claimtype.name] = it.Claimtypedetail
                }
                _claimTypeDetailMap.postValue(tempMap)
            }
        }
    }

}