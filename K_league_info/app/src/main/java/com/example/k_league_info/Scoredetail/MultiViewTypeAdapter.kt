package com.example.k_league_info.Scoredetail

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.k_league_info.R
import kotlinx.android.synthetic.main.item_teamdetail.view.*

class MultiViewTypeAdapter(private val list: MutableList<HighlightModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var totalTypes = list.size

    // getItemViewType의 리턴값 Int가 viewType으로 넘어온다.
    // viewType으로 넘어오는 값에 따라 viewHolder를 알맞게 처리해주면 된다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View?
        return when (viewType) {
            HighlightModel.ITEM_TIME -> {
                view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_time, parent, false)
                TimeTypeViewHolder(view)
            }
            HighlightModel.ITEM_GOAL -> {
                view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_goal, parent, false)
                GoalTypeViewHolder(view)
            }
            HighlightModel.ITEM_CARD -> {
                view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
                CardTypeViewHolder(view)
            }
            HighlightModel.ITEM_SWITCH -> {
                view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_switch, parent, false)
                SwitchTypeViewHolder(view)
            }
            else -> throw RuntimeException("알 수 없는 뷰 타입 에러")
        }
    }

    override fun getItemCount(): Int {
        return totalTypes
    }
    

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("MultiViewTypeAdapter", "Hi, onBindViewHolder")
        val obj = list[position]
        when (obj.type) {
            HighlightModel.ITEM_TIME -> {
                (holder as TimeTypeViewHolder).title.text = obj.title
                holder.time.text = obj.time
                Glide.with(holder.itemView).asBitmap().load(obj.img).fitCenter()
                    .into(holder.time_image)
            }
            HighlightModel.ITEM_GOAL -> {
                (holder as GoalTypeViewHolder).title.text = obj.title
                holder.time.text = obj.time
                Glide.with(holder.itemView).asBitmap().load(obj.img).fitCenter()
                    .into(holder.player_face)
                holder.player.text = obj.player
                holder.team_name.text = obj.teamName
                holder.how.text = obj.contentString
            }
            HighlightModel.ITEM_CARD -> {
                (holder as CardTypeViewHolder).title.text = obj.title
                holder.time.text = obj.time
                Glide.with(holder.itemView).asBitmap().load(obj.img).fitCenter()
                    .into(holder.player_face)
                holder.player.text = obj.player
                holder.team_name.text = obj.teamName
            }
            HighlightModel.ITEM_SWITCH -> {
                (holder as SwitchTypeViewHolder).title.text = obj.title
                holder.time.text = obj.time
                Glide.with(holder.itemView).asBitmap().load(obj.img).fitCenter()
                    .into(holder.in_player_face)
                holder.player.text = obj.player
                holder.team_name.text = obj.teamName
                Glide.with(holder.itemView).asBitmap().load(obj.img2).fitCenter()
                    .into(holder.out_player_face)
                holder.player2.text = obj.player2
                holder.team_name2.text = obj.teamName2
            }
        }
    }


    // 여기서 받는 position은 데이터의 index다.
    override fun getItemViewType(position: Int): Int {
        Log.d("MultiViewTypeAdapter", "Hi, getItemViewType")
        return list[position].type
    }

    inner class TimeTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val time: TextView = itemView.findViewById(R.id.time)
        val time_image: ImageView = itemView.findViewById(R.id.time_image)
    }

    inner class GoalTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val time: TextView = itemView.findViewById(R.id.time)
        val player_face: ImageView = itemView.findViewById(R.id.player_face)
        val team_name: TextView = itemView.findViewById(R.id.team_name)
        val player: TextView = itemView.findViewById(R.id.player)
        val how: TextView = itemView.findViewById(R.id.how)
    }

    inner class CardTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val time: TextView = itemView.findViewById(R.id.time)
        val player_face: ImageView = itemView.findViewById(R.id.player_face)
        val team_name: TextView = itemView.findViewById(R.id.team_name)
        val player: TextView = itemView.findViewById(R.id.player)
    }

    inner class SwitchTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val time: TextView = itemView.findViewById(R.id.time)
        val in_player_face: ImageView = itemView.findViewById(R.id.in_player_face)
        val team_name: TextView = itemView.findViewById(R.id.in_team_name)
        val player: TextView = itemView.findViewById(R.id.in_player)
        val out_player_face: ImageView = itemView.findViewById(R.id.out_player_face)
        val team_name2: TextView = itemView.findViewById(R.id.out_team_name)
        val player2: TextView = itemView.findViewById(R.id.out_player)
    }
}