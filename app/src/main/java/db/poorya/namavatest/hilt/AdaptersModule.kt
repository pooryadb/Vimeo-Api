package db.poorya.namavatest.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import db.poorya.namavatest.presentation.main.adapter.VideoAdapter
import db.poorya.namavatest.utils.StateAdapter

@Module
@InstallIn(FragmentComponent::class)
object AdaptersModule {

    @FragmentScoped
    @Provides
    fun provideNewsAdapter() = VideoAdapter()

    @FragmentScoped
    @Provides
    fun provideStateAdapter() = StateAdapter()

}