package pl.org.akai.onboarding_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import pl.org.akai.onboarding_domain.use_case.ValidateUserNameUseCase

@Module
@InstallIn(ViewModelComponent::class)
object OnboardingDomainModule {

    @ViewModelScoped
    @Provides
    fun provideOnboardingNameValidationUseCase(): ValidateUserNameUseCase {
        return ValidateUserNameUseCase()
    }
}