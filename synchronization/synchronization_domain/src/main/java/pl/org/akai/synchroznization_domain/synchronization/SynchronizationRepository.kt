package pl.org.akai.synchroznization_domain.synchronization

import data.remote.data.GameItemResponseDto
import kotlinx.coroutines.flow.Flow
import utils.Resource

interface SynchronizationRepository {

    suspend fun synchronize(): Flow<Resource<List<GameItemResponseDto>>>
}