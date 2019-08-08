package com.fobidlim.kakaypay

import androidx.lifecycle.ViewModel
import com.fobidlim.kakaypay.viewmodels.MainViewModel
import com.fobidlim.kakaypay.viewmodels.MediaDetailsViewModel
import com.fobidlim.kakaypay.viewmodels.SignInViewModel
import com.fobidlim.kakaypay.viewmodels.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ActivityViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun splashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    internal abstract fun signInViewModel(viewModel: SignInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun mainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MediaDetailsViewModel::class)
    internal abstract fun mediaDetailsViewModel(viewModel: MediaDetailsViewModel): ViewModel
}