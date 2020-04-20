package com.example.rpodmp_lab_2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_one_news.*
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter
import org.sufficientlysecure.htmltextview.HtmlTextView

class OneNewsActivity : AppCompatActivity() {

    var title = ""
    var date = ""
    var link = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_news)
        val author = intent.getStringExtra("AUTHOR")
        this.toolbar_one.title = "Author - \n$author"
        setSupportActionBar(toolbar_one)
        title = intent.getStringExtra("TITLE")
        date = intent.getStringExtra("DATE")
        link = intent.getStringExtra("LINK")

        val content =
            "<img src=\"" + intent.getStringExtra("IMAGE") + "\" style=\"width: 100%\"><br>" + intent.getStringExtra("CONTENT") + "<br><a href=\"" +  link + "\">Читать полностью...</a>"
        this.oneTextTitle.text = title
        this.oneTextPubdate.text = date
        val textView = findViewById<View>(R.id.html_text) as HtmlTextView
        textView.setHtml(content, HtmlHttpImageGetter(textView))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.one_page_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.shareBtn) {
            shareData()
        }
        return true
    }

    private fun shareData() {

        val s = title + "\n" + link
        //share intent
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, s)
        startActivity(Intent.createChooser(shareIntent, s))
    }

}
