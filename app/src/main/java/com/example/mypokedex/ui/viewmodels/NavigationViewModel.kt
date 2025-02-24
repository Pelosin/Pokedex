package com.example.mypokedex.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypokedex.domain.repository.IIsFirstOpenRepository
import com.example.mypokedex.ui.composables.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val firstOpenRepository: IIsFirstOpenRepository,
) : ViewModel() {

    private val _navigationState = MutableStateFlow(Screen.Main.route)
    val navigationState = _navigationState.asStateFlow()

    fun checkForRoute() {
        viewModelScope.launch {
            if(firstOpenRepository.getIsFirstOpen()) {
                _navigationState.value = Screen.OnBoarding.route
            } else {
                _navigationState.value = Screen.Main.route
            }
        }
    }

}