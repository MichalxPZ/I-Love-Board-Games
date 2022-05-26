package pl.org.akai.synchroznization_domain.synchronization

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import data.local.GamesDao
import data.preferences.Preferences
import data.remote.Api

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideSynchRepository(
        api: Api,
        dao: GamesDao,
        preferences: Preferences
    ) : SynchronizationRepository {
        return SynchronizationRepositoryImpl(
            api = api,
            dao = dao,
            preferences = preferences
        )
    }
}