package com.example.rpodmp_lab_2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.add_source.*


class CreateNewSource : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_source)
    }

    fun onSubmitClicked(view: View) {
        Toast.makeText(this, url_field.text, Toast.LENGTH_SHORT).show()
        val intent = Intent(baseContext, MainActivity::class.java)

        intent.putExtra("URL_NEWS", url_field.text.toString())
        startActivity(intent)
    }

}