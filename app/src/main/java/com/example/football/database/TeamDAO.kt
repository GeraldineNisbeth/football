package com.example.football.database

import androidx.room.*
import com.example.football.models.Team

@Dao
interface TeamDAO {
    @Insert
    fun insertTeam(vararg team: Team)

    @Query("SELECT * FROM teams")
    fun getAllTeams():List<Team>

    @Update
    fun updateTeam(vararg  team : Team)

     @Delete
    fun deleteTEam(vararg  team : Team)

}