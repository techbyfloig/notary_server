package com.techbyfloig.cubannotary.notary.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.techbyfloig.cubannotary.notary.model.AppType
import com.techbyfloig.cubannotary.notary.repo.PassportRepo
import com.techbyfloig.cubannotary.notary.model.passport.entities.Passport
import org.springframework.stereotype.Service
import javax.persistence.OptimisticLockException

@Service
class ApplicationService(
    private val passportRepo: PassportRepo
) {

    fun getApplications(id: Long): Result<String> {
        return try {
            //get passport applications
            val dataResult = passportRepo.findByUserId(id).map {
                it.toDto()
            }

            if (dataResult.isNotEmpty()) {
                var passports = ""

                dataResult.forEach {
                    passports += (Gson().toJson(it) + ",\n")
                }

                // get others applications


                //put everything together
                val finalJsonPassports = "{\"Passport\": $passports}"
                Result.success(finalJsonPassports)
            } else {
                Result.failure(Exception("Not application found"))
            }

        } catch (e: IllegalArgumentException) {
            Result.failure(e)
        }
    }

    fun getApplication(id: Long, appType: Int): Result<Any> {
        return try {
            when (AppType.from(appType)) {
                AppType.PASSPORT -> {
                    val dataResult = passportRepo.findById(id).map { it.toDto() }
                    if (!dataResult.isPresent) Result.failure(Exception("Not app found")) else
                        Result.success(dataResult.get())
                }

                AppType.RESIDENCE -> Result.success("residence")
                null -> Result.failure(Exception("AppType incorrect"))
            }

        } catch (e: IllegalArgumentException) {
            Result.failure(e)
        }
    }

    fun saveApplication(application: String, appType: Int): Result<String> {
        val objectMapper = ObjectMapper()

        return try {
            when (AppType.from(appType)) {
                AppType.PASSPORT -> {
                    val passport = objectMapper.readValue(application, Passport::class.java)
                    val dataResult = passportRepo.save(passport)

                    if (dataResult.userId.toString().isEmpty()) Result.failure(Exception("Not saved"))
                    else Result.success("Success")
                }

                AppType.RESIDENCE -> Result.success("residence")
                null -> Result.failure(Exception("AppType incorrect"))
            }
        } catch (e: IllegalArgumentException) {
            Result.failure(e)
        } catch (e: OptimisticLockException) {
            Result.failure(e)
        }

    }

    fun updateApplication(passport: Passport): Result<Passport> {
        return try {
            passportRepo.deleteById(passport.appId)
            val result = passportRepo.save(passport)
            Result.success(result)
        } catch (e: IllegalArgumentException) {
            Result.failure(e)
        } catch (e: OptimisticLockException) {
            Result.failure(e)
        }

    }

    fun deleteApplication(applicationId: Long, appType: Int): Result<String> {
        return try {
            when (AppType.from(appType)) {
                AppType.PASSPORT -> {
                    passportRepo.deleteById(applicationId)
                    Result.success("Success")
                }

                AppType.RESIDENCE -> Result.success("residence")
                null -> Result.failure(Exception("AppType incorrect"))
            }
        } catch (e: IllegalArgumentException) {
            Result.failure(e)
        }

    }


}