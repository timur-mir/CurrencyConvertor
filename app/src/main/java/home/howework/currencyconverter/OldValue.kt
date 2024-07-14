package home.howework.currencyconverter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.provider.Telephony
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi

//var receiver = makeBroadcastReceiver()
//registerReceiver(
//receiver,
//IntentFilter("android.provider.Telephony.SMS_RECEIVED"),
//RECEIVER_EXPORTED
//)
//val filter = IntentFilter().apply { addAction(ConnectivityManager.CONNECTIVITY_ACTION) }
//
////        filter.addAction("android.net.wifi.supplicant.CONNECTION_CHANGE")
////        filter.addAction("android.net.wifi.STATE_CHANGE")
//val receiverWifi = WifiReceiver()
//registerReceiver(
//receiverWifi,
//filter
//)

//private fun makeBroadcastReceiver(): BroadcastReceiver {
//    return object : BroadcastReceiver() {
//        @RequiresApi(Build.VERSION_CODES.O)
//        override fun onReceive(context: Context?, intent: Intent?) {
//            for (sms in Telephony.Sms.Intents.getMessagesFromIntent(
//                intent
//            )) {
//                val smsSender = sms.originatingAddress
//                val smsMessageBody = sms.displayMessageBody
//                //   if (smsSender == "the_number_that_you_expect_the_SMS_to_come_FROM") {
//                binding.textSms.visibility = View.VISIBLE
//                binding.button4.visibility = View.VISIBLE
//                binding.editNumber.visibility = View.VISIBLE
//                binding.editSms.visibility = View.VISIBLE
//                binding.textSms.text = "$smsSender $smsMessageBody"
//                binding.editNumber.setText(smsSender)
//                break
//                // }
//            }
//        }
//
//    }
//}
//  fun sendSms(phoneNumber: String, message: String) {
//        try {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//                var smsManager: SmsManager = application.getSystemService(SmsManager::class.java)
//                smsManager.sendTextMessage(phoneNumber, null, message, null, null)
//                Toast.makeText(applicationContext, "Отправка..", Toast.LENGTH_LONG).show()
//            } else {
//                var smsManager: SmsManager = SmsManager.getDefault()
//                smsManager.sendTextMessage(phoneNumber, null, message, null, null)
//                Toast.makeText(applicationContext, "Отправка..", Toast.LENGTH_LONG).show()
//            }
//
//        } catch (e: Exception) {
//
//            Toast.makeText(
//                applicationContext,
//                "Заполните поля.." + e.message.toString(),
//                Toast.LENGTH_LONG
//            )
//                .show()
//        }
//    }
//

//   fun sendSms3(phoneNumber: String, message: String) {
//        val sms = "smsto:$phoneNumber"
//        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse(sms))
//        intent.putExtra("sms_body", message)
//        startActivity(intent)
//    }

//    fun sendSMS2(phoneNumber: String, message: String) {
//        val sentPI: PendingIntent = PendingIntent.getBroadcast(
//            this,
//            0,
//            Intent("SMS_SENT"),
//            PendingIntent.FLAG_IMMUTABLE
//        )
//        SmsManager.getDefault().sendTextMessage(phoneNumber, null, message, sentPI, null)
//    }
//    fun checkPermission() {
//        if (checkSelfPermission(android.Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED
//            || checkSelfPermission(android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
//            || checkSelfPermission(android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED
//
//        ) {
//            requestPermissions(
//                arrayOf(
//                    android.Manifest.permission.RECEIVE_SMS,
//                    android.Manifest.permission.SEND_SMS,
//                    android.Manifest.permission.READ_SMS,
//
//                    ), PackageManager.PERMISSION_GRANTED
//            )
//        }
//    }

////        private val checkTextWatcher = object : TextWatcher {
////            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
////                TODO("Not yet implemented")
////            }
////
////            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
////                TODO("Not yet implemented")
////            }
////
////            override fun afterTextChanged(s: Editable?) {
////                validate()
////            }
////        }


//   object TotalM {
////        var totalAmount: Double = 0.0
////        var rub: Double = 0.0
////        var eur: Double = 0.0
////        var usd: Double = 0.0
////        var uzs: Double = 0.0
//
//    }

//
//binding.button4.setOnClickListener {
//    val textBody = binding.editSms.text.toString()
//    val numberPhone = binding.editNumber.text.toString()
//    Toast.makeText(
//        applicationContext,
//        "Отправка смс на номер:$numberPhone",
//        Toast.LENGTH_LONG
//    ).show()
//    sendSms(numberPhone, textBody)
//}
//        item = ""
//            binding.button2.isEnabled = true
//            binding.button.text = "Валюта"
//            binding.edit1.text.clear()
//            binding.textView4.text = "0.0"
//            virtualWalletsClon.clearTotalAmount()
//            binding.button3.isEnabled = false
//            binding.textSms.text = ""
//            binding.textSms.visibility = View.INVISIBLE
//            binding.button4.visibility = View.INVISIBLE
//            binding.editNumber.visibility = View.INVISIBLE
//            binding.editSms.visibility = View.INVISIBLE
//            binding.img.visibility = View.INVISIBLE
//        }
