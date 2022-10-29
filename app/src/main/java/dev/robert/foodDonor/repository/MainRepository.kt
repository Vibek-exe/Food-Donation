package dev.robert.foodDonor.repository

import dev.robert.foodDonor.model.Donation
import dev.robert.foodDonor.utils.Resource

interface MainRepository {
    suspend fun getDonations(result: (Resource<List<Donation>>) -> Unit)
    suspend fun donate(donation: Donation, result: (Resource<List<Donation>>) -> Unit)
    suspend fun fetchHistory(result: (Resource<List<Donation>>) -> Unit)

}