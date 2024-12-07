package com.oneasad.amphibians.data

import com.oneasad.amphibians.model.Amphibian
import com.oneasad.amphibians.network.AmphibiansApiService

interface AmphibiansRepository {
    suspend fun getAmphibians(): List<Amphibian>
}

class NetworkAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
): AmphibiansRepository {
    override suspend fun getAmphibians(): List<Amphibian> = amphibiansApiService.getAmphibians()
}