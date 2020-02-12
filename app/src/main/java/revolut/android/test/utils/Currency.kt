package revolut.android.test.utils

import androidx.annotation.DrawableRes
import revolut.android.test.R

enum class Currency(val desc: String, @DrawableRes val flag: Int) {
    AUD("Australian Dollar",
        R.drawable.aud
    ),
    BGN("Bulgarian Lev", R.drawable.bgn),
    BRL("Brazilian Real", R.drawable.brl),
    CAD("Canadian Dollar", R.drawable.cad),
    CHF("Swiss Franc", R.drawable.chf),
    CNY("Chinese Yuan", R.drawable.cny),
    CZK("Czech Koruna", R.drawable.czk),
    DKK("Danish Krone", R.drawable.dkk),
    EUR("Euro", R.drawable.eur),
    GBP("Pound Sterling", R.drawable.gbp),
    HKD("Hong Kong Dollar", R.drawable.hkd),
    HRK("Croatian Kuna", R.drawable.hrk),
    HUF("Hungarian Forint", R.drawable.huf),
    IDR("Indonesian Rupiah",
        R.drawable.idr
    ),
    ILS("New Israeli Sheqel", R.drawable.ils),
    INR("Indian Rupee", R.drawable.inr),
    ISK("Icelandic Krona", R.drawable.isk),
    JPY("Japanese Yen", R.drawable.jpy),
    KRW("South Korean Won",
        R.drawable.isk
    ),
    MXN("Mexican Peso", R.drawable.mxn),
    MYR("Malaysian Ringgit", R.drawable.myr),
    NOK("Norwegian Krone", R.drawable.nok),
    NZD("New Zealand Dollar",
        R.drawable.nzd
    ),
    PHP("Philippine Peso", R.drawable.php),
    PLN("Polish Zloty", R.drawable.pln),
    RON("Romanian Leu", R.drawable.ron),
    RUB("Russian Ruble", R.drawable.rub),
    SEK("Swedish Krona", R.drawable.sek),
    SGD("Singapore Dollar", R.drawable.sgd),
    THB("Thai Baht", R.drawable.thb),
    TRY("Turkish Lira", R.drawable.try_),
    USD("US Dollar", R.drawable.usd),
    ZAR("South African Rand",
        R.drawable.zar
    )
}