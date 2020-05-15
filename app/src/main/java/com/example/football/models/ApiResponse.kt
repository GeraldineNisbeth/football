package com.example.football.models

import com.example.football.models.Team
import com.google.gson.annotations.SerializedName

data class ApiResponse (
    @SerializedName("results")
    var results : Int,
    @SerializedName("teams")
    var teams : List<Team>
){
}