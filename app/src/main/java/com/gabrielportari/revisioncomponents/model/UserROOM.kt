package com.gabrielportari.revisioncomponents.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UserROOM(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(last_name = "last_name") val lastName: String?,
    @ColumnInfo(email = "email") val email: String?
) {
}