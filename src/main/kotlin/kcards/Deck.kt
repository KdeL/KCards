package kcards

import kotlin.math.min

/**
 * [Deck] Represents a deck of cards. Use the nested Builder to construct a deck
 */
class Deck private constructor(): CardHolder() {
    /**
     * Deals requested number of cards to the recipient.
     * If deck has less cards than requested number, deals all available cards
     * Number of cards actually dealt
     */
    fun deal(recipient: CardHolder, count: Int): Int {
        val dealCount = min(count, listOfCards().size)
        return if(moveCards(recipient, dealCount)) dealCount else 0
    }

    /**
     * Deck Builder. Only way to build a Deck object is to use this
     */
    class Builder() {
        private var suites = ArrayList<Suite>()
        private var ranks = ArrayList<Rank>()

        /**
         * Builds a deck with requests [suites] and [ranks]
         */
        fun build(): Deck {
            val deck = Deck()
            //Add all suites
            for (suit in suites)
                for (rank in Rank.values())
                    deck.addCard(Card(suit, rank))
            //Add all ranks
            for (rank in ranks)
                for (suite in Suite.values())
                    deck.addCard(Card(suite, rank))
            return deck;
        }

        /**
         * Call this before [build] to include a complete deck(52 cards) in the resulting deck
         */
        fun addCompleteDeck() = apply {
            for (suite in Suite.values())
                suites.add(suite)
        }

        /**
         * Call this before [build] to include a complete suite (13 cards) in the resulting deck
         */
        fun addSuite(suite: Suite) = apply { suites.add(suite) }

        /**
         * Call this before [build] to include a complete rank (4 cards) in the resulting deck
         */
        fun addRank(rank: Rank) = apply { ranks.add(rank) }
    }
}