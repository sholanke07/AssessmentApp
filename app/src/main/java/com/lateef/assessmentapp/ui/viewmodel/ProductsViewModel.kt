package com.lateef.assessmentapp.ui.viewmodel



import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lateef.assessmentapp.common.Response
import com.lateef.assessmentapp.domain.usecase.GetProductUseCase
import com.lateef.assessmentapp.ui.state.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber


@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase
): ViewModel() {

    //viewmodel to keep the ui state
    private val state = MutableStateFlow(ProductState())
    var _state: StateFlow<ProductState> = state

    init {
        getProductList()
    }

    //the viewmodelscope run on background thread
    fun getProductList() = viewModelScope.launch(Dispatchers.IO) {

       getProductUseCase().collect {
            when(it){
                is Response.Success ->{
                    state.value = ProductState(product = it.data?: emptyList())
                }
                is Response.Loading ->{
                    state.value = ProductState(isLoading = true)
                }
                is Response.Error ->{
                    state.value = ProductState(error = it.message?: "An Unexpected Error")
                }
            }
        }
    }
}








