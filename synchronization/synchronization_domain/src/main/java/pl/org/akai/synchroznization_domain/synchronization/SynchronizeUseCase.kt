package pl.org.akai.synchroznization_domain.synchronization

import data.remote.data.GameItemResponseDto
import kotlinx.coroutines.flow.Flow
import utils.Resource

class SynchronizeUseCase(
    private val repository: SynchronizationRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<GameItemResponseDto>>> {
        return repository.synchronize()
    }
}