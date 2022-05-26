package pl.org.akai.game_list_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import pl.org.akai.game_list_domain.repository.GameRepository
import pl.org.akai.game_list_domain.use_case.GetGames

@Module
@InstallIn(ViewModelComponent::class)
object GameListUseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGameListUseCase(
        repository: GameRepository
    ): GetGames {
        return GetGames(
            gameRepository = repository
        )
    }
}