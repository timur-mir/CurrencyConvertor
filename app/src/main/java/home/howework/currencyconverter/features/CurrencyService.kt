package home.howework.currencyconverter.features

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.widget.RemoteViews
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import home.howework.currensyconverter.R
import home.howework.data.CurrencyConverterRepo
import home.howework.domain.CurrencyConverterRepoInteface.CurrencyConverterRepoInterface
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.notify

class CurrencyService:Service() {
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
   val CHANNEL_ID = "Notify_channel"
   val NOTIFICATION_ID = 101
    private val description = "Currency notification"

    override fun onBind(intent: Intent?): IBinder? {
      return Binder()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(DelicateCoroutinesApi::class)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var context: Context? = this
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            GlobalScope.launch {
                while (true) {
                    var remoteViews: RemoteViews =
                        RemoteViews(packageName, R.layout.currency_notify)
                        notificationChannel = NotificationChannel(
                         CHANNEL_ID,
                            description,
                            NotificationManager.IMPORTANCE_HIGH
                        )
                        notificationChannel.enableLights(false)
                        notificationChannel.enableVibration(false)
                        notificationManager.createNotificationChannel(notificationChannel)

                        val notification = NotificationCompat.Builder(context!!, CHANNEL_ID)
                            .setSmallIcon(R.drawable.m2)
                            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.cur2))
                            .setContentTitle("Курс доллара")
                            .setOngoing(true)
                            .setOnlyAlertOnce(false)
                            .setAutoCancel(false)
                            .setCustomContentView(remoteViews)
                            .build()

                        val currencyConverterRepo = CurrencyConverterRepo()
                        val responseNetwork = currencyConverterRepo.getCurrency("USD", "RUB")

                       if (responseNetwork != null) {
                            if (responseNetwork.payload.rates.isNotEmpty()) {
                                remoteViews.setTextViewText(
                                    R.id.notify_message,
                                    " Сегодня доллар стоит: ${responseNetwork.payload.rates[12].sell!!} руб "
                                )
                            }
                           startForeground(1,notification )
                   //  notificationManager.notify(NOTIFICATION_ID,notification)
                    delay(60000*60)
                        }

                    }
            }

        return Service.START_STICKY
    }
    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Служба остановлена",
            Toast.LENGTH_SHORT).show();
    }
}