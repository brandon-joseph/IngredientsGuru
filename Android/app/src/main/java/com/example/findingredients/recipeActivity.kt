package com.example.findingredients

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


private lateinit var recyclerView : RecyclerView
private lateinit var mAdapter : RecyclerView.Adapter<*>
private lateinit var layoutManager : RecyclerView.LayoutManager
class recipeActivity : AppCompatActivity() {


    private lateinit var recipeName: String
    private lateinit var urlString: String
    private lateinit var imgUrl: String
    private lateinit var ingredients: MutableList<Ingredient>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view)


        val title: TextView = findViewById(R.id.recipeTitle)
        val url: TextView = findViewById(R.id.recipeUrl)
        val img: ImageView = findViewById(R.id.img)

        recipeName = ""
        urlString = ""
        imgUrl = ""
        ingredients = mutableListOf()
        intent.extras?.let { bundle ->
            recipeName = bundle.get("title") as String
            urlString = bundle.get("url") as String
            imgUrl = bundle.get("imgUrl") as String
            ingredients = bundle.getParcelableArrayList("ingredients")!!
        }
        title.text = recipeName
        url.text = urlString

        url.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        url.setOnClickListener{
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(urlString))
            startActivity(browserIntent)
        }

        Glide.with(this).load(imgUrl).into(img)

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)

        // use a linear layout manager
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        // specify an adapter (see step 3)
        mAdapter = recPageAdapter(ingredients)
        recyclerView.adapter = mAdapter
}
}