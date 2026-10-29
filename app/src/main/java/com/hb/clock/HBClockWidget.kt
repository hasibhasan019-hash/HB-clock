package com.hb.clock

import android.content.Context
import android.util.TypedValue
import android.widget.RemoteViews
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.LocalSize
import androidx.glance.appwidget.AndroidRemoteViews
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider

class HBClockWidget : GlanceAppWidget() {

    override val sizeMode = SizeMode.Responsive(
        setOf(
            DpSize(110.dp, 110.dp),
            DpSize(180.dp, 110.dp),
            DpSize(250.dp, 180.dp)
        )
    )

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent { ClockContent(context) }
    }

    @Composable
    private fun ClockContent(context: Context) {
        val size = LocalSize.current

        val clockSp = when {
            size.width >= 250.dp -> 80f
            size.width >= 180.dp -> 56f
            else -> 36f
        }

        val views = RemoteViews(context.packageName, R.layout.clock_view).apply {
            setTextViewTextSize(R.id.clock, TypedValue.COMPLEX_UNIT_SP, clockSp)
        }

        Box(
            modifier = GlanceModifier
                .fillMaxSize()
                .background(Color(0xFF1C1C1E))
                .cornerRadius(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AndroidRemoteViews(remoteViews = views)
                Text(
                    text = "HB Clock",
                    style = TextStyle(
                        color = ColorProvider(Color.Gray),
                        fontSize = (clockSp / 5f).sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
    }
}

class HBClockWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = HBClockWidget()
}
