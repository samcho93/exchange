package com.project.exchange.domain.model

data class Currency(
    val code: String,
    val name: String,
    val flag: String
) {
    companion object {
        val SUPPORTED_CURRENCIES = listOf(
            Currency("USD", "US Dollar", "\uD83C\uDDFA\uD83C\uDDF8"),
            Currency("KRW", "Korean Won", "\uD83C\uDDF0\uD83C\uDDF7"),
            Currency("EUR", "Euro", "\uD83C\uDDEA\uD83C\uDDFA"),
            Currency("JPY", "Japanese Yen", "\uD83C\uDDEF\uD83C\uDDF5"),
            Currency("GBP", "British Pound", "\uD83C\uDDEC\uD83C\uDDE7"),
            Currency("CNY", "Chinese Yuan", "\uD83C\uDDE8\uD83C\uDDF3"),
            Currency("AUD", "Australian Dollar", "\uD83C\uDDE6\uD83C\uDDFA"),
            Currency("CAD", "Canadian Dollar", "\uD83C\uDDE8\uD83C\uDDE6"),
            Currency("CHF", "Swiss Franc", "\uD83C\uDDE8\uD83C\uDDED"),
            Currency("HKD", "Hong Kong Dollar", "\uD83C\uDDED\uD83C\uDDF0"),
            Currency("SGD", "Singapore Dollar", "\uD83C\uDDF8\uD83C\uDDEC"),
            Currency("SEK", "Swedish Krona", "\uD83C\uDDF8\uD83C\uDDEA"),
            Currency("NOK", "Norwegian Krone", "\uD83C\uDDF3\uD83C\uDDF4"),
            Currency("MXN", "Mexican Peso", "\uD83C\uDDF2\uD83C\uDDFD"),
            Currency("INR", "Indian Rupee", "\uD83C\uDDEE\uD83C\uDDF3"),
            Currency("BRL", "Brazilian Real", "\uD83C\uDDE7\uD83C\uDDF7"),
            Currency("NZD", "New Zealand Dollar", "\uD83C\uDDF3\uD83C\uDDFF"),
            Currency("ZAR", "South African Rand", "\uD83C\uDDFF\uD83C\uDDE6"),
            Currency("TRY", "Turkish Lira", "\uD83C\uDDF9\uD83C\uDDF7"),
            Currency("THB", "Thai Baht", "\uD83C\uDDF9\uD83C\uDDED"),
            Currency("PLN", "Polish Zloty", "\uD83C\uDDF5\uD83C\uDDF1"),
            Currency("DKK", "Danish Krone", "\uD83C\uDDE9\uD83C\uDDF0"),
            Currency("IDR", "Indonesian Rupiah", "\uD83C\uDDEE\uD83C\uDDE9"),
            Currency("CZK", "Czech Koruna", "\uD83C\uDDE8\uD83C\uDDFF"),
            Currency("HUF", "Hungarian Forint", "\uD83C\uDDED\uD83C\uDDFA"),
            Currency("ILS", "Israeli Shekel", "\uD83C\uDDEE\uD83C\uDDF1"),
            Currency("PHP", "Philippine Peso", "\uD83C\uDDF5\uD83C\uDDED"),
            Currency("MYR", "Malaysian Ringgit", "\uD83C\uDDF2\uD83C\uDDFE"),
            Currency("RON", "Romanian Leu", "\uD83C\uDDF7\uD83C\uDDF4"),
            Currency("BGN", "Bulgarian Lev", "\uD83C\uDDE7\uD83C\uDDEC")
        )

        fun findByCode(code: String): Currency? =
            SUPPORTED_CURRENCIES.find { it.code == code }
    }
}
