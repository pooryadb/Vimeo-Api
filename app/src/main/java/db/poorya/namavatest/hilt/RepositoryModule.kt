package db.poorya.namavatest.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import db.poorya.namavatest.domain.provider.remote.VimeoService
import db.poorya.namavatest.domain.repository.AppRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideAppRepository(
        vimeoService: VimeoService
    ) = AppRepository(vimeoService)

}