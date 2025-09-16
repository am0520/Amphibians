package com.am0520.amphibians.data

import com.am0520.amphibians.model.Amphibian
import com.am0520.amphibians.network.AmphibiansApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService,
) {

    suspend fun getAmphibians(): List<Amphibian> {
        return withContext(Dispatchers.IO) {
            amphibiansApiService.getAmphibians()
        }
    }
}
