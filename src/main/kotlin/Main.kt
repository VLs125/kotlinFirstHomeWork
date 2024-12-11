package org.netology_exceptions

fun main() {
    commisionCalculate(1000, CardsType.MIR)
    commisionCalculate(1000, CardsType.MC)
    commisionCalculate(1000, CardsType.MC, 76000)
    commisionCalculate(100000, CardsType.MC)
    commisionCalculate(1000000, CardsType.VISA)
}

var LIMIT_TEXT = "Извините Ваши лимиты превышены, перевод не не выполнен(("
var COMMISION_TAXES_TEXT = "Сумма коммисии составила:"
val MASTER_CARD_COMMITSION_LIMIT = 75_000
val MONTH_LIMITS = 600_000
val DAY_LIMITS = 150_000
fun commisionCalculate(
    transferAmount: Int,
    cardType: CardsType = CardsType.MIR,
    amountOfPreviousTransactions: Int = 0
) {

    when (cardType) {
        CardsType.MC -> calculateComissionForMC(
            DAY_LIMITS,
            MONTH_LIMITS,
            transferAmount,
            amountOfPreviousTransactions,
            MASTER_CARD_COMMITSION_LIMIT
        )

        CardsType.VISA -> calculateComissionForVISA(
            DAY_LIMITS,
            MONTH_LIMITS,
            transferAmount,
            amountOfPreviousTransactions
        )

        CardsType.MIR -> println("$COMMISION_TAXES_TEXT 0")
    }

}

private fun calculateComissionForMC(
    dayLimit: Int, monthLimit: Int, transferAmount: Int, prevTransactionsAmount: Int, mcLimit: Int
) {

    if ((transferAmount >= dayLimit) || (prevTransactionsAmount + transferAmount >= monthLimit)) {
        println(LIMIT_TEXT)
    } else if ((transferAmount + prevTransactionsAmount <= mcLimit)) {
        println("$COMMISION_TAXES_TEXT 0")
    } else
        println("$COMMISION_TAXES_TEXT " + ((transferAmount * 0.6 / 100) + 20))
}

private fun calculateComissionForVISA(
    dayLimit: Int, monthLimit: Int, transferAmount: Int, prevTransactionsAmount: Int
) {
    if ((transferAmount >= dayLimit) || (prevTransactionsAmount + transferAmount >= monthLimit)) {
        println(LIMIT_TEXT)
    } else if (transferAmount * 0.75 / 100 > 35) {
        println("$COMMISION_TAXES_TEXT " + ((transferAmount * 0.75 / 100)))
    } else println("$COMMISION_TAXES_TEXT 35.0")

}

enum class CardsType {
    MIR, VISA, MC
}