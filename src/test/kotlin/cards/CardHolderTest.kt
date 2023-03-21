package cards

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CardHolderTest {

    //Concrete class of CardHolder for testing
    class Holder: CardHolder() {
        fun addACard(suite: Suite, rank: Rank) = addCard(Card(suite, rank))
        fun removeACard(card: Card) = removeCard(card)
        fun addADeckOfCards() {
            for(suit in Suite.values())
                for(rank in Rank.values())
                    addCard(Card(suit, rank))
        }
    }

    lateinit var deck: Holder
    lateinit var player: Holder

    @BeforeEach
    fun setUp() {
        deck = Holder().apply { addADeckOfCards() }
        player = Holder()
    }

    @Test
    fun `moveCard() removes the card and adds to the recipient`() {
        assert(player.listOfCards().isEmpty())
        val card = deck.listOfCards().first()
        deck.moveCard(card, player)
        assert(player.listOfCards().size == 1)
        assert(player.listOfCards().first() === card)
        assert(deck.listOfCards().firstOrNull{ it == card } == null)
    }

    @Test
    fun `moveAllCards() moves all cards to the recipient`() {
        assert(player.listOfCards().isEmpty())
        assert(deck.listOfCards().size > 10)
        val cards = deck.listOfCards()
        for(i in 0..9) {
            deck.moveCard(cards[i], player)
        }
        assert(player.listOfCards().size == 10)
    }

    @Test
    fun `shuffle with`() {

    }

    @Test
    fun shuffle() {
    }

    @Test
    fun showCards() {
    }
}