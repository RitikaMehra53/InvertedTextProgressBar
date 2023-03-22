package com.app.invertedtextprogressbar.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.invertedtextprogressbar.adapter.ProgressBarAdapter
import com.app.invertedtextprogressbar.model.ProgressBarDataModel
import com.app.invertedtextprogressbar.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.lang.reflect.Type


class MainActivity : AppCompatActivity() {

    private val onBackPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
           finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = resources.getColor(R.color.white)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR//  set status text dark
        setContentView(R.layout.activity_main)
        setData()

        iv_back.setOnClickListener {
            onBackPressedCallback.handleOnBackPressed()
        }
    }

    private fun setData() {
        val json = getJsonFromAssets(this, "progressBarData.json")
        val type: Type = object : TypeToken<ArrayList<ProgressBarDataModel?>?>() {}.type
        val progressList: ArrayList<ProgressBarDataModel> = Gson().fromJson(json, type)

        rv_progress_bar.layoutManager = LinearLayoutManager(this)
        rv_progress_bar.adapter = ProgressBarAdapter(progressList)
    }

    private fun getJsonFromAssets(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}