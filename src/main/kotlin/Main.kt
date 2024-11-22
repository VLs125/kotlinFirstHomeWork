package org.netology_exceptions

fun main() {
    commisionCalculate(1000)
    commisionCalculate(100000)
    commisionCalculate(100)
}

fun commisionCalculate(transferAmount: Int) {
    val baseCommision = 35
    val basePercent = 0.75
    var calcCommisionFromTransfer = (transferAmount * basePercent) / 100
    var amount = if (calcCommisionFromTransfer < baseCommision) baseCommision else calcCommisionFromTransfer

    println(amount)
}