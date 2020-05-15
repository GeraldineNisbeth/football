package com.example.football.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.football.R
import com.example.football.models.Team
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class TeamAdapter(private val teams: List<Team>, private val context :Context, val itemClickListener : OnItemClickListener)
: RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    class ViewHolder(val view : View): RecyclerView.ViewHolder(view) {
        val ivLogo = view.findViewById(R.id.ivLogo) as ImageView
        val tvName = view.findViewById(R.id.tvName) as TextView
        val cvTeams = view.findViewById(R.id.cvTeams) as CardView
    }
    interface OnItemClickListener{
        fun onItemClicked(team :Team)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.prototype_team, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team = teams[position]

        holder.tvName.text = team.name

        val pic = Picasso.Builder(context)
        pic.downloader(OkHttp3Downloader(context))

        pic.build().load(team.logo)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.ivLogo)

        holder.cvTeams.setOnClickListener{
            itemClickListener.onItemClicked(team)
        }
    }
}