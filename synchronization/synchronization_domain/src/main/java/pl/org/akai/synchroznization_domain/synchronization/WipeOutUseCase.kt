package pl.org.akai.synchroznization_domain.synchronization

class WipeOutUseCase(
    private val repository: SynchronizationRepository
) {
    suspend operator fun invoke(){
        return repository.wipeOut()
    }
}