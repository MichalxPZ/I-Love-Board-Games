package pl.org.akai.synchroznization_domain.synchronization

class SynchronizeUseCase(
    private val repository: SynchronizationRepository
) {
    suspend operator fun invoke() {
        repository.synchronize()
    }
}