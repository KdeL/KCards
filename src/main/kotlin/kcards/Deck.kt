package kcards

class Deck: CardHolder() {
    fun setup() {
        for(suit in Suite.values())
            for (rank in Rank.values())
                this addCard Card(suit, rank)
        shuffle()
    }

    fun deal(recipient: CardHolder, count: Int): Boolean {
        return moveCards(recipient, count)
    }
}