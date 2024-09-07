package home.howework.currencyconverter.features

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.MenuRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import home.howework.currencyconverter.utils.CurrenciesWorld
import home.howework.currencyconverter.utils.format
import home.howework.currencyconverter.utils.hideKeyBoard
import home.howework.currencyconverter.utils.toDouble
import home.howework.currensyconverter.R
import home.howework.currensyconverter.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import pl.droidsonroids.gif.BuildConfig


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    var wallets: Double? = null
    var item: String? = null
    val virtualWalletsClon = home.howework.presentation.utils.Wallets.VirtualWallets()
    private val mainViewModel: home.howework.presentation.vm.MainViewModel by viewModels<home.howework.presentation.vm.MainViewModel>()
    var scope = CoroutineScope(Job() + Dispatchers.Default)

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        BuildConfig.DEBUG
        makeVibrate()
        val filter = IntentFilter().apply { addAction(ConnectivityManager.CONNECTIVITY_ACTION) }
        val receiverWifi = WifiReceiver()
        registerReceiver(
            receiverWifi,
            filter
        )

        lifecycleScope.launchWhenCreated {
            showLastCurrency()
        }

        if (binding.button != null) {
            binding.button.setOnClickListener { v: View ->
                showMenu(v, R.menu.popup_menu)
            }
        }
        binding.button2.isEnabled = false
        binding.button3.isEnabled = false
        binding.button2.setOnClickListener {
            hideKeyBoard()
            lifecycleScope.launch {

                when (item) {
                    "Дол. в суммы" -> {
                        when (CurrencyConverter.countOnButtonClickWhenDollarRequest) {
                            0 -> {
                                val job = scope.launch {
                                    launch(Dispatchers.Main) {
                                        delay(1000)
                                        if (mainViewModel._errorInfo.value.isNotEmpty()) {
                                            mainViewModel.errorInfo.collect {
                                                Toast.makeText(
                                                    this@MainActivity,
                                                    "$it!. Некоторые функции могут не работать!",
                                                    Toast.LENGTH_SHORT
                                                ).show();
                                            }
                                        }
                                    }
                                    CurrencyConverter.countOnButtonClickWhenDollarRequest++
                                    mainViewModel.reloadCurrency3("USD", "UZS")

                                    mainViewModel.response3
                                        .onEach {
                                            if (it.payload.rates.size != 0 && CurrencyConverter.sumToDollar == 0.0) {
                                                CurrencyConverter.sumToDollar =
                                                    it.payload.rates[12].sell!!
                                                delay(200)
                                                launch(Dispatchers.Main) {
                                                    makeVibrate()
                                                    Toast.makeText(
                                                        this@MainActivity,
                                                        "Курс доллара в сумах :${it.payload.rates[12].sell!!}!",
                                                        Toast.LENGTH_LONG
                                                    ).show();
                                                    binding.button2.isEnabled = false
                                                }
                                                validate()
                                            } else {
                                                return@onEach
                                            }
                                        }.launchIn(this)

                                }
                                delay(1300)
                                job.cancel()
                                job.join()
                            }

                            1 -> {

                                validate()
                            }
                        }
                    }

                    "Рубль" -> {
                        validate()
                    }

                    "Сум" -> {
                        when (CurrencyConverter.countOnButtonClickWhenSumRequest) {
                            0 -> {
                                val job = scope.launch {
                                    launch(Dispatchers.Main) {
                                        delay(1000)
                                        if (mainViewModel._errorInfo.value.isNotEmpty()) {
                                            mainViewModel.errorInfo.collect {
                                                Toast.makeText(
                                                    this@MainActivity,
                                                    "$it! Некоторые функции могут не работать!",
                                                    Toast.LENGTH_SHORT
                                                ).show();
                                            }
                                        }
                                    }
                                    CurrencyConverter.countOnButtonClickWhenSumRequest++
                                    mainViewModel.reloadCurrency3("USD", "UZS")
                                    mainViewModel.response3
                                        .onEach {
                                            if (it.payload.rates.size != 0 && CurrencyConverter.sumToDollar == 0.0) {
                                                CurrencyConverter.sumToDollar =
                                                    it.payload.rates[12].sell!!
                                                delay(200)
                                                launch(Dispatchers.Main) {
                                                    makeVibrate()
                                                    Toast.makeText(
                                                        this@MainActivity,
                                                        "Курс продажи доллара за сумы :${it.payload.rates[12].sell!!}!",
                                                        Toast.LENGTH_LONG
                                                    ).show();
                                                    binding.button2.isEnabled = false
                                                }
                                                validate()
                                            } else {
                                                return@onEach
                                            }
                                        }.launchIn(this)
                                }
                                delay(1300)
                                job.cancel()
                                job.join()
                            }

                            1 -> {

                                validate()
                            }
                        }
                    }

                    "Евро" -> {
                        when (CurrencyConverter.countOnButtonClickWhenEuroRequest) {
                            0 -> {
                                val job = scope.launch {
                                    launch(Dispatchers.Main) {
                                        delay(1000)
                                        if (mainViewModel._errorInfo.value.isNotEmpty()) {
                                            mainViewModel.errorInfo.collect {
                                                Toast.makeText(
                                                    this@MainActivity,
                                                    "$it! Некоторые функции могут не работать!",
                                                    Toast.LENGTH_SHORT
                                                ).show();
                                            }
                                        }
                                    }
                                    CurrencyConverter.countOnButtonClickWhenEuroRequest++
                                    mainViewModel.reloadCurrency2("EUR", "RUB")
                                    mainViewModel.response2
                                        .onEach {
                                            if (it.payload.rates.size != 0 && CurrencyConverter.euroToRuble == 0.0 && item == "Евро") {
                                                CurrencyConverter.euroToRuble =
                                                    it.payload.rates[12].sell!!
                                                delay(200)
                                                launch(Dispatchers.Main) {
                                                    makeVibrate()
                                                    Toast.makeText(
                                                        this@MainActivity,
                                                        "Курс продажи евро :${it.payload.rates[12].sell!!}!",
                                                        Toast.LENGTH_SHORT
                                                    ).show();
                                                    binding.button2.isEnabled = false
                                                }
                                                validate()
                                            } else {
                                                return@onEach
                                            }
                                        }.launchIn(this)
                                }
                                delay(1300)
                                job.cancel()
                                job.join()
                            }

                            1 -> {
                                validate()
                            }
                        }
                    }
                }
            }
        }
        binding.button3.setOnClickListener { v: View ->
            item = ""
            binding.button2.isEnabled = true
            binding.button.text = "Валюта"
            binding.edit1.text.clear()
            binding.textView4.text = "0.0"
            virtualWalletsClon.clearTotalAmount()
            binding.img.visibility = View.INVISIBLE
        }
    }

    override fun onStart() {
        super.onStart()
    }

    private suspend fun showLastCurrency() {
        val job = lifecycleScope.launch {
            scope.launch {
                launch(Dispatchers.Main) {
                    delay(4000)
                    if (mainViewModel._errorInfo.value.isNotEmpty()) {
                        mainViewModel.errorInfo.collect {
                            mainViewModel._errorInfo.value = ""
                            Snackbar.make(
                                binding.root,
                                "Сеть недоступна",
                                Snackbar.LENGTH_INDEFINITE
                            )
                                .setActionTextColor(Color.WHITE)
                                .setBackgroundTint((Color.BLUE))
                                .setAction("Перезапустить") {
                                    lifecycleScope.launch {
                                        showLastCurrency()
                                    }
                                }
                                .show()
                        }
                    }
                }

                mainViewModel.reloadCurrency("USD", "RUB")

                mainViewModel.response
                    .onEach { response ->
                        val inv = response
                        delay(20)
                        launch(Dispatchers.Main) {
                            if (response != null) {
                                if (response.payload.rates.size != 0) {
                                    CurrencyConverter.curValue = response.payload.rates[12].sell!!
                                    binding.price.text =
                                        " ${CurrencyConverter.curValue.toString()} руб "
                                    CurrencyConverter.dollarToRuble = CurrencyConverter.curValue
                                    makeVibrate()
                                }
                            }
                            cancel()
                        }
                    }.launchIn(this)
            }
        }
        delay(1300)
        job.cancel()
        job.join()
    }


    private fun validate() {
        lifecycleScope.launch(Dispatchers.Main) {
            if (binding.edit1.text.isNotBlank()) {
                wallets = binding.edit1.text.toDouble()
                binding.img.visibility = View.VISIBLE

                var common = 0.0
                when (item) {

                    "Рубль" -> {
                        virtualWalletsClon.addMoney(CurrenciesWorld.RUBLE, wallets!!)
                        if (virtualWalletsClon.rub != 0.0) {
                            common =
                                (virtualWalletsClon.moneyInUSD(CurrenciesWorld.RUBLE) * 10) / 10.0
                            if (common >= 1) {
                                //  val digitsf=common.toBigDecimal().toPlainString()
                                val digitsf = common
                                val stringDig = digitsf.format(2)
                                binding.textView4.text = "${stringDig} дол."

                            } else {
                                val digitsf = common
                                val stringDig = digitsf.format(2)
                                binding.textView4.text = "${stringDig} цент."
                            }
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "В кошелке нет денег",
                                Toast.LENGTH_SHORT
                            )
                                .show(); }
                    }

                    "Евро" -> {
                        virtualWalletsClon.addMoney(CurrenciesWorld.EURO, wallets!!)
                        if (virtualWalletsClon.eur != 0.0) {
                            common =
                                (virtualWalletsClon.moneyInUSD(CurrenciesWorld.EURO) * 10) / 10.0
                            if (common >= 1) {
                                val digitsf = common
                                val stringDig = digitsf.format(2)
                                binding.textView4.text = "${stringDig} дол."
                            } else {

                                val digitsf = common
                                val stringDig = digitsf.format(2)
                                binding.textView4.text = "${stringDig} цент."
                            }
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "В кошелке нет денег",
                                Toast.LENGTH_SHORT
                            )
                                .show(); }
                    }

                    "Дол. в суммы" -> {
                        virtualWalletsClon.addMoney(CurrenciesWorld.DOLLAR, wallets!!)
                        if (virtualWalletsClon.usd != 0.0) {
                            common =
                                (virtualWalletsClon.moneyInUSD(CurrenciesWorld.DOLLAR) * 10) / 10.0
                            if (common >= 1) {
                                val digitsf = common
                                val stringDig = digitsf.format(2)
                                binding.textView4.text = "${stringDig} сум."
                            } else {
                                val digitsf = common
                                val stringDig = digitsf.format(2)
                                binding.textView4.text = "${stringDig} тийин."
                            }
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "В кошелке нет денег",
                                Toast.LENGTH_SHORT
                            )
                                .show(); }
                    }

                    "Сум" -> {
                        virtualWalletsClon.addMoney(CurrenciesWorld.SUMUZ, wallets!!)
                        if (virtualWalletsClon.uzs != 0.0) {
                            common =
                                (virtualWalletsClon.moneyInUSD(CurrenciesWorld.SUMUZ) * 10) / 10.0
                            if (common >= 1) {
                                val digitsf = common
                                val stringDig = digitsf.format(2)
                                binding.textView4.text = "${stringDig} дол."
                            } else {
                                val digitsf = common
                                val stringDig = digitsf.format(2)
                                binding.textView4.text = "${stringDig} цент."
                            }
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "В кошелке нет денег",
                                Toast.LENGTH_SHORT
                            )
                                .show(); }
                    }
                }
            } else {
                makeVibrate()
                Toast.makeText(this@MainActivity, "Заполните поле!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private fun makeVibrate() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else
            vibrator.vibrate(400)
    }

    fun showMenu(v: View, @MenuRes menuRes: Int) {
        val popup = PopupMenu(this, v)
        popup.menuInflater.inflate(menuRes, popup.menu)
        popup.setOnMenuItemClickListener { menuItem: MenuItem ->
            item = menuItem.title.toString()
            binding.button.text = item
            binding.button2.isEnabled = true
            binding.button3.isEnabled = true
            return@setOnMenuItemClickListener true
        }
        popup.setOnDismissListener {
            // Respond to popup being dismissed.
        }
        // Show the popup menu.
        popup.show()
    }


    inner class WifiReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == ConnectivityManager.CONNECTIVITY_ACTION) {
                val connectivity =
                    intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)
                if (connectivity) {
                    Toast.makeText(
                        this@MainActivity,
                        "Соединение с wifi или с сетью отсутствует( Некоторые функции могут не работать",
                        Toast.LENGTH_LONG
                    )
                        .show();
                }

            }
        }

    }

    object CurrencyConverter {
        var curValue: Double = 0.0
        var dollarToRuble: Double = 0.0
        var euroToRuble = 0.0
        var sumToDollar = 0.0
        var countOnButtonClickWhenDollarRequest = 0
        var countOnButtonClickWhenEuroRequest = 0
        var countOnButtonClickWhenSumRequest = 0
    }
}