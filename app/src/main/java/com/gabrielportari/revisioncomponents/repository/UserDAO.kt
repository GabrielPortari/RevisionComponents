package com.gabrielportari.revisioncomponents.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gabrielportari.revisioncomponents.model.UserROOM

@Dao
interface UserDAO {
    @Query("SELECT * FROM userROOM")
    fun getAll(): List<UserROOM>

    @Query("SELECT * FROM userROOM WHERE uid IN (:userIds)")
    fun getAllByIds(userIds: IntArray): List<UserROOM>

    @Query("SELECT * FROM userROOM WHERE first_name LIKE :first")
    fun findByName(first: String, last: String): UserROOM

    @Insert
    fun insertAll(vararg users: UserROOM)

    @Delete
    fun delete(user: UserROOM)


}