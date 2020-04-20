package com.example.rpodmp_lab_2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rpodmp_lab_2.Adapter.FeedAdapter
import com.example.rpodmp_lab_2.Common.RetrofitServiceGenerator
import com.example.rpodmp_lab_2.Model.RSSObject
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private var RSS_link = "https://tech.onliner.by/feed"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = "NEWS"
        setSupportActionBar(toolbar)
        val linearLayoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager

        if (intent.hasExtra("URL_NEWS")) {
            Toast.makeText(this, intent.getStringExtra("URL_NEWS"), Toast.LENGTH_SHORT).show()
            RSS_link = intent.getStringExtra("URL_NEWS") as String
        } else RSS_link = "https://tech.onliner.by/feed"
        loadRSS()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.menu_refresh) {
            loadRSS()
        } else
        if(item?.itemId == R.id.menu_add){
            val intent = Intent(baseContext,CreateNewSource::class.java)
            startActivity(intent)
        }
        return true
    }

    private fun loadRSS() {
        val call = RetrofitServiceGenerator.createService().getFeed(RSS_link)
        call.enqueue(object : Callback<RSSObject> {

            override fun onFailure(call: Call<RSSObject>?, t: Throwable?) {
                Log.d("ResponseError", "failed")
            }

            override fun onResponse(call: Call<RSSObject>?, response: Response<RSSObject>?) {

                if (response?.isSuccessful == true) {
                    response.body()?.let { rssObject ->
                        Log.d("Response", "${rssObject.toString()}")
                        val adapter = FeedAdapter(rssObject, baseContext)
                        recyclerView.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }


}