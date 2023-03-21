package cards

/**
 * [CardHolder] to be extended by all entities holding [Card]s. Examples can be a deck of cards or a player.
 */
abstract class CardHolder {
    /**
     * List of [Card]s held by this holder at any given time. For a player, this may represent their "hand".
     * Members [addCard], [removeCard], [moveCard] and [moveAllCards] are used to add/remove [Card]s on this list.
     * Use [shuffle] to re-arrange the [Card]s into a random order.
     */
    private val cards = ArrayList<Card>()

    /**
     * adds the [card] to the [cards] pile. Return value indicates if the operation was successful.
     * The [card] is appended to the end of [cards] pile.
     */
    protected infix fun addCard(card: Card): Boolean = cards.add(card)

    /**
     * Removes the [card] from the [cards] pile. Return value indicates if the operation was successful.
     */
    protected infix fun removeCard(card: Card): Boolean = cards.remove(card)

    /**
     * Removes the given [Card] from [cards] and adds it to the given [recipient].
     * If there are multiple cards matching the criteria ( [suite] andn [rank] ),only  the first one found in [cards] is moved.
     * Return value indicates if the operation was successful.
     * New objects are not instantiated during this operation.
     */
    fun moveCard(suite: Suite, rank: Rank, recipient: CardHolder): Boolean {
        cards.firstOrNull { card -> card.suite == suite && card.rank == rank }?.let { card ->
            cards.remove(card)
            recipient.cards.add(card)
            return true
        }
        return false
    }

    /**
     * Removes the given [Card] from [cards] and adds it to the given [recipient].
     * If there are multiple cards matching the [card], only the first one found in [cards] is moved.
     * Return value indicates if the operation was successful.
     * New objects are not instantiated during this operation.
     */
    fun moveCard(card: Card, recipient: CardHolder): Boolean {
        cards.firstOrNull { it === card }?.let { card ->
            cards.remove(card)
            recipient.cards.add(card)
            return true
        }
        return false
    }

    /**
     * Moves [count] of [Card]s to the recipient. Returns if operation was successful
     */
    fun moveCards(recipient: CardHolder, count: Int = 1): Boolean{
        if(cards.size < count) return false
        for(i in 1..count) recipient.cards.add(cards.removeFirst())
        return true
    }

    /**
     * Moves all from [cards] to the [recipient]. New objects are not instantiated during this operation.
     */
    fun moveAllCards(recipient: CardHolder) {
        while(cards.isNotEmpty()) { recipient.cards.add(cards.removeFirst()) }
    }

    /**
     * Suffles the list of [Card]s currently held in [cards]
     */
    fun shuffle() = cards.shuffle()

    /**
     * Sorts the list of [Card]s by [Suite] and [Rank]
     */
    fun sort() = cards.sortWith(compareBy( Card::suite, Card::rank))

    /**
     * Returns a complete list of [Card]s in [cards] as an immutable list
     */
    fun listOfCards(): List<Card> { return cards }

    /**
     * Returns a [String] showing the [cards] pile in the current order
     */
    fun printCards(): String { return cards.joinToString{ it -> it.display } }
}
