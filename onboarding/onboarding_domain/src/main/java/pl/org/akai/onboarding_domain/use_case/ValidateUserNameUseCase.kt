package pl.org.akai.onboarding_domain.use_case

class ValidateUserNameUseCase {

    operator fun invoke(value: String) : Boolean {
        return value.length > 1 && value.matches("^[a-zA-Z1-9]*$".toRegex())
    }
}