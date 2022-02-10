package com.example.catfacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : FragmentActivity() {
    private lateinit var adapter: SliderAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private val tabNames: Array<String> = arrayOf("Факты из интернета", "Избранное")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = SliderAdapter(this)
        viewPager = findViewById(R.id.pager)
        viewPager.adapter = adapter
        tabLayout = findViewById(R.id.tabLayoutTabs)
        TabLayoutMediator(tabLayout,viewPager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()
    }
}