package com.zano.myfinanceapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.zano.myfinanceapp.ui.theme.MyFinanceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://zinwaiyan.website/finance-app/")))
            finish()
        } catch (_: ActivityNotFoundException) {
            setContent {
                MyFinanceAppTheme {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Enable a web browser to open your budget.")
                    }
                }
            }
        }
    }
}
