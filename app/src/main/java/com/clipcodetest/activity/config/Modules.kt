package com.clipcodetest.activity.config

import com.clipcodetest.data.UsersApiService
import com.clipcodetest.viewmodel.GlobalViewModel
import com.clipcodetest.viewmodel.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

fun injectFeature() = loadFeature

//se inicializan modulos
private val loadFeature by lazy {
    loadKoinModules(
        arrayListOf(
            viewModelModule,
            networkModule
        )
    )
}

val viewModelModule: Module = module {
    viewModel {
        GlobalViewModel()
    }
    viewModel {
        UsersViewModel()
    }
}

val networkModule: Module = module {
    single { get<Retrofit>(named("users_api")).create(UsersApiService::class.java) }
}
