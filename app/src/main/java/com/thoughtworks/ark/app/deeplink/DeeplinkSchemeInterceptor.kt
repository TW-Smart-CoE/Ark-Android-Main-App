package com.thoughtworks.ark.app.deeplink

import com.thoughtworks.ark.app.MainActivity
import com.thoughtworks.ark.app.R
import com.thoughtworks.ark.router.Router
import com.thoughtworks.ark.router.RouterInterceptor
import com.thoughtworks.ark.router.SchemeRequest

class DeeplinkSchemeInterceptor : RouterInterceptor {
    override suspend fun shouldIntercept(request: SchemeRequest): Boolean {
        return request.scheme == Schemes.HOME ||
            request.scheme == Schemes.DASHBOARD ||
            request.scheme == Schemes.NOTIFICATIONS
    }

    override suspend fun intercept(request: SchemeRequest): SchemeRequest {
        val selectedItemId = when (request.scheme) {
            Schemes.HOME -> R.id.navigation_home
            Schemes.DASHBOARD -> R.id.navigation_dashboard
            Schemes.NOTIFICATIONS -> R.id.navigation_notifications
            else -> R.id.navigation_home
        }

        return Router.scheme(Schemes.MAIN)
            .params(MainActivity.KEY_SELECTED_ITEM_ID to selectedItemId)
            .request
    }
}