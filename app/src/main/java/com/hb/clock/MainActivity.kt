package com.hb.clock

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(TextView(this).apply {
            text = "HB Clock\n\nHome screen e long-press kore Widgets theke HB Clock add koro, tarpor corner drag kore resize koro."
            textSize = 18f
            setPadding(48, 96, 48, 48)
        })
    }
}
