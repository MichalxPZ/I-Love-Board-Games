package pl.org.akai.synchroznization_domain.synchronization

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideSynchUseCase(
        repository: SynchronizationRepository
    ): SynchronizeUseCase {
        return SynchronizeUseCase(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideWipeOutUseCase(
        repository: SynchronizationRepository
    ): WipeOutUseCase {
        return WipeOutUseCase(repository)
    }
}