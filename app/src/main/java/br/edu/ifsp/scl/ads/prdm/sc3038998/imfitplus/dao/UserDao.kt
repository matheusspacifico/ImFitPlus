package br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus.dao

import androidx.room.Insert
import androidx.room.Query
import br.edu.ifsp.scl.ads.prdm.sc3038998.imfitplus.entity.User

interface UserDao {

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: Long): User?

    @Insert
    suspend fun insertUser(user: User): Long
}