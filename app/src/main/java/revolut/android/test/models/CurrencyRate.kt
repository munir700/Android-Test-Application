package revolut.android.test.models

import com.google.gson.annotations.SerializedName


data class CurrencyRate(
    @SerializedName("base") val base: String,
    @SerializedName("date") val date: String,
    @SerializedName("rates") val rates: Map<String, Double>
)
