package org.netology_exceptions

fun main() {
    commisionCalculate(1000, CardsType.MIR)
    commisionCalculate(1000, CardsType.MC)
    commisionCalculate(1000, CardsType.MC,76000)
    commisionCalculate(100000, CardsType.MC)
    commisionCalculate(1000000, CardsType.VISA)
}

fun commisionCalculate(
    transferAmount: Int,
    cardType: CardsType = CardsType.MIR,
    amountOfPreviousTransactions: Int = 0
) {
    val masterCardCommisionLimit = 75_000
    val mounthLimits = 650_00
    val dayLimit = 150_00
    val baseCommision = 35
    val basePercent = 0.75
    var calcCommisionFromTransfer = (transferAmount * basePercent) / 100
    var amount = if (calcCommisionFromTransfer < baseCommision) baseCommision else calcCommisionFromTransfer

    when (cardType) {
        CardsType.MC -> amount.toDouble() + calculateComissionForMC(
            dayLimit,
            mounthLimits,
            transferAmount,
            amountOfPreviousTransactions,
            masterCardCommisionLimit
        )

        CardsType.VISA -> amount.toDouble() + calculateComissionForVISA(
            dayLimit,
            mounthLimits,
            transferAmount,
            amountOfPreviousTransactions
        )

        CardsType.MIR -> amount.toDouble() + 0
    }

    println(amount)
}

private fun calculateComissionForMC(
    dayLimit: Int, monthLimit: Int, transferAmount: Int, prevTransactionsAmount: Int, mcLimit: Int
): Double {
    return if ((prevTransactionsAmount + transferAmount >= dayLimit) || (prevTransactionsAmount + transferAmount >= monthLimit) || (transferAmount + prevTransactionsAmount <= mcLimit)) {
        0.0
    } else
        ((((transferAmount + prevTransactionsAmount) - mcLimit) * 0.6 / 100) + 20)
}

private fun calculateComissionForVISA(
    dayLimit: Int, monthLimit: Int, transferAmount: Int, prevTransactionsAmount: Int
): Double {
    return if ((prevTransactionsAmount + transferAmount >= dayLimit) || (prevTransactionsAmount + transferAmount >= monthLimit)) {
        0.0
    } else
        if (transferAmount * 0.75 / 100 > 35) ((transferAmount * 0.75 / 100)) else 35.0
}

enum class CardsType {
    MIR, VISA, MC
}