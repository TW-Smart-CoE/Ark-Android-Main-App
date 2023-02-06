package com.thoughtworks.ark.app.deeplink

import com.thoughtworks.ark.app.BuildConfig

object Schemes {
    private const val SCHEME = BuildConfig.APP_SCHEME

    const val MAIN = "$SCHEME://main"

    const val HOME = "$SCHEME://home"
    const val DASHBOARD = "$SCHEME://dashboard"
    const val NOTIFICATIONS = "$SCHEME://notifications"
}