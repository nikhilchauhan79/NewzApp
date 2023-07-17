package com.example.newzapp.ui.screens.customtab

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent

fun openChromeTab(context: Context, url: String) {
  val package_name = "com.android.chrome"
  val activity = (context as? Activity)
  val builder = CustomTabsIntent.Builder()
    .setShowTitle(true)

  val customBuilder = builder.build()

  try {
//    val packageInfo = activity?.packageManager?.getPackageInfo(package_name, PackageManager.GET_ACTIVITIES)
    customBuilder.run {
      intent.`package` = package_name
      launchUrl(context, Uri.parse(url))
    }
  } catch (exception: PackageManager.NameNotFoundException) {
    Log.d("ChromeCustomTab", "openChromeTab: chrome not installed")
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    activity?.startActivity(intent)
  }

}