package db.poorya.namavatest.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import db.poorya.namavatest.domain.provider.remote.VimeoService
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object ProviderModule {

    @ViewModelScoped
    @Provides
    fun provideVimeoService(retrofit: Retrofit): VimeoService =
        retrofit.create(VimeoService::class.java)

}