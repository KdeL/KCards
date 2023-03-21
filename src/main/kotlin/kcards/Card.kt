package kcards

/**
 * Represents a playing card. Holds a [Rank] and a [Suite] that can't be changed after creation.
 * [display] is the [String] representation of the [Card] object
 */
data class Card(val suite: Suite, val rank: Rank) {
    val display = "${rank.symbol}${suite.symbol}"
}
