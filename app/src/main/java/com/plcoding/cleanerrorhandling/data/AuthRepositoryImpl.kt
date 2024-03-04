package com.plcoding.cleanerrorhandling.data

import com.plcoding.cleanerrorhandling.domain.AuthRepository
import com.plcoding.cleanerrorhandling.domain.DataError
import com.plcoding.cleanerrorhandling.domain.Result
import com.plcoding.cleanerrorhandling.domain.User
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl: AuthRepository {

    override suspend fun register(password: String): Result<User, DataError.Network> {
        // API call logic
        return try {
            val user = User("dummy", "dummy", "dummy")
            Result.Success(user)
        } catch(e: HttpException) {
            when(e.code()) {
                408 -> Result.Error(DataError.Network.REQUEST_TIMEOUT)
                413 -> Result.Error(DataError.Network.PAYLOAD_TOO_LARGE)
                else -> Result.Error(DataError.Network.UNKNOWN)
            }
        }
    }
}