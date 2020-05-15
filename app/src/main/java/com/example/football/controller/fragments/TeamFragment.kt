package com.example.football.controller.fragments

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.football.R
import com.example.football.adapter.TeamAdapter
import com.example.football.models.ApiResponseHeader
import com.example.football.models.Team
import com.example.football.network.TeamService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TeamFragment : Fragment(), TeamAdapter.OnItemClickListener {

    //var team : List<Team> = ArrayList()
    lateinit var recyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvTeams)
        loadTeams(view.context)
    }

    private fun loadTeams(context: Context) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-football-v1.p.rapidapi.com/v2/teams/league/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val teamService : TeamService
        teamService = retrofit.create(TeamService::class.java)
        val request = teamService.getTeams("api-football-v1.p.rapidapi.com", "d229813befmsh4c1646ad132a0b5p1313fcjsn9afecaefc97e")

        request.enqueue(object  : Callback<ApiResponseHeader> {
            override fun onFailure(call: Call<ApiResponseHeader>, t: Throwable) {
                Log.d("fragmentFail", t.toString())
            }

            override fun onResponse(call: Call<ApiResponseHeader>,response: Response<ApiResponseHeader>) {
                if(response.isSuccessful){
                    val teams : List<Team> = response.body()!!.api.teams ?: ArrayList()
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    recyclerView.adapter = TeamAdapter(teams,context, this@TeamFragment)
                }
                else{
                    Log.d("Error", "Error"+ response.code())
                }
            }

        })
    }

    override fun onItemClicked(team: Team) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
