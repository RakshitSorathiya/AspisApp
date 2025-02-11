package com.aspis.tech.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aspis.tech.R
import com.aspis.tech.model.Url

class UrlListAdapter(private var url: ArrayList<Url>) :
    RecyclerView.Adapter<UrlListAdapter.UrlViewHolder>() {


    @SuppressLint("NotifyDataSetChanged")
    fun updateUrl(urlList: List<Url>) {
        url.clear()
        url.addAll(urlList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = UrlViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.card_url_item, parent, false
        )
    )

    override fun getItemCount() = url.size

    override fun onBindViewHolder(holder: UrlViewHolder, position: Int) {
        holder.bind(holder.itemView.context, url[position])
    }


    class UrlViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val ivUrlStatus = view.findViewById<ImageView>(R.id.card_iv_url_status)
        private val tvThreatHeader = view.findViewById<TextView>(R.id.card_tv_threat_header)
        private val tvUrlReference = view.findViewById<TextView>(R.id.card_tv_url_reference)

        fun bind(context: Context, url: Url) {
            if (ivUrlStatus != null && tvThreatHeader != null && tvUrlReference != null) {
                if (url.urlStatus == "online") {
                    ivUrlStatus.setImageResource(R.drawable.ic_threat_level_high)
                }else{
                    ivUrlStatus.setImageResource(R.drawable.ic_threat_level_low)
                }
                tvThreatHeader.text = url.threat
                tvUrlReference.text = url.urlhausReference
            }
        }
    }

}