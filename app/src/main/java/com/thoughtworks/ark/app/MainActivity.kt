package com.thoughtworks.ark.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.thoughtworks.ark.feature.dashboard.api.DashboardSchemes
import com.thoughtworks.ark.feature.home.api.HomeSchemes
import com.thoughtworks.ark.feature.notifications.api.NotificationsSchemes
import com.thoughtworks.ark.router.Router
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navView by lazy { findViewById<BottomNavigationView>(R.id.nav_view) }

    private val selectedItemId by lazy {
        intent.getIntExtra(KEY_SELECTED_ITEM_ID, R.id.navigation_home)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    Router.scheme(HomeSchemes.HOME)
                        .container(R.id.container)
                        .group()
                        .tag("home")
                        .route(this)
                }
                R.id.navigation_dashboard -> {
                    Router.scheme(DashboardSchemes.DASHBOARD)
                        .container(R.id.container)
                        .group()
                        .tag("dashboard")
                        .route(this)
                }
                R.id.navigation_notifications -> {
                    Router.scheme(NotificationsSchemes.NOTIFICATIONS)
                        .container(R.id.container)
                        .group()
                        .tag("notifications")
                        .route(this)
                }
            }
            true
        }
        navView.selectedItemId = selectedItemId
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val newSelectedItemId = intent.getIntExtra(KEY_SELECTED_ITEM_ID, R.id.navigation_home)
        navView.selectedItemId = newSelectedItemId
    }

    companion object {
        const val KEY_SELECTED_ITEM_ID = "selected_item_id"
    }
}