package com.example.findingredients

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import okhttp3.*
import java.io.IOException


/**
 * A simple [Fragment] subclass.
 * Use the [searchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class searchFragment : Fragment() {
    companion object {
        fun newInstance(): searchFragment {
            return searchFragment()
        }
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val rootView: View =
            inflater.inflate(R.layout.fragment_search, container, false)
        val searchBar: SearchView = rootView.findViewById(R.id.searchRec)
        searchBar.queryHint = "Search For Recipes Here"
        searchBar.setOnClickListener(View.OnClickListener { searchBar.onActionViewExpanded(); })

        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // task HERE
                //on submit send entire query

                val client = OkHttpClient()

                fun run() {
                    val request = Request.Builder()
                            .url("https://find-ingredients.herokuapp.com/api/search/")
                            .build()

                    client.newCall(request).enqueue(object : Callback {
                        override fun onFailure(call: Call, e: IOException) {
                            e.printStackTrace()
                        }

                        override fun onResponse(call: Call, response: Response) {
                            response.use {
                                if (!response.isSuccessful) throw IOException("Unexpected code $response")
                                val res = response.body!!.string()

                                //Proof of concept connecting to backend API
                                activity!!.runOnUiThread { Toast.makeText(activity, res, Toast.LENGTH_LONG).show() }
                            }
                        }
                    })
                }

                run()
                return false
            }

        })
        // use activity-specific methods
        getActivity()?.getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return rootView
    }

}