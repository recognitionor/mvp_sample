package com.example.search_user_test.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.example.search_user_test.model.UserItem
import io.reactivex.Maybe

@Dao
interface UserDao {
    /*WHERE login LIKE :query*/
    @Query("SELECT * FROM users")
    fun getUser(): Maybe<List<UserItem>>

    @Insert(onConflict = IGNORE)
    fun insertUser(user: UserItem?): Maybe<Void>

    @Delete
    fun deleteUser(user: UserItem?): Maybe<Void>
}