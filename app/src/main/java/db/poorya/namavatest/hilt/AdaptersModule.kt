package db.poorya.namavatest.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import db.poorya.namavatest.presentation.main.adapter.VideoAdapter

@Module
@InstallIn(FragmentComponent::class)
object AdaptersModule {

    @FragmentScoped
    @Provides
    fun provideNewsAdapter() = VideoAdapter()

}