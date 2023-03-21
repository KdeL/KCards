package kcards

import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class DeckTest {

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun `deck is empty when nothing is requested`() {
        val deck = Deck.Builder().build()
        assert(deck.listOfCards().isEmpty())
    }

    @Test
    fun `building complete deck is easy`() {
        val deck = Deck.Builder().addCompleteDeck().build()
        assert(deck.listOfCards().size == 52)
    }

    @Test
    fun `multiple decks can be put together`() {
        val deck = Deck.Builder()
            .addCompleteDeck()
            .addCompleteDeck()
            .addCompleteDeck()
            .build()
        assert(deck.listOfCards().size == 156)
    }

    @Test
    fun `deck includes only the requested suites`() {
        var deck = Deck.Builder().addSuite(Suite.SPADES).build()
        assert(deck.listOfCards().size == 13)
        deck = Deck.Builder()
            .addSuite(Suite.SPADES)
            .addSuite(Suite.DIAMONDS)
            .build()
        assert(deck.listOfCards().size == 26)
        deck = Deck.Builder()
            .addSuite(Suite.SPADES)
            .addSuite(Suite.DIAMONDS)
            .addSuite(Suite.HEARTS)
            .build()
        assert(deck.listOfCards().size == 39)
        deck = Deck.Builder()
            .addSuite(Suite.SPADES)
            .addSuite(Suite.DIAMONDS)
            .addSuite(Suite.HEARTS)
            .addSuite(Suite.CLUBS)
            .build()
        assert(deck.listOfCards().size == 52)
    }

    @Test
    fun `a deck can have same suite multiple times`() {
        var deck = Deck.Builder()
            .addSuite(Suite.SPADES)
            .addSuite(Suite.SPADES)
            .addSuite(Suite.SPADES)
            .build()
        assert(deck.listOfCards().size == 39)
    }

    @Test
    fun `deck includes only requested ranks`() {
        var deck = Deck.Builder().addRank(Rank.TWO).build()
        assert(deck.listOfCards().size == 4)
        deck = Deck.Builder()
            .addRank(Rank.TWO)
            .addRank(Rank.ACE)
            .addRank(Rank.THREE)
            .addRank(Rank.EIGHT)
            .addRank(Rank.KING)
            .build()
        assert(deck.listOfCards().size == 20)
    }

    @Test
    fun `deck deals requested number of cards`() {
        class User: CardHolder(){}
        val user = User()
        val deck = Deck.Builder().addCompleteDeck().build()
        deck.deal(user, 15)
        assert(user.listOfCards().size == 15)
    }

    @Test
    fun `deck deals all available cards when requested more than available`() {
        class User: CardHolder(){}
        val user = User()
        val deck = Deck.Builder().addRank(Rank.KING).build()
        deck.deal(user, 15)
        assert(user.listOfCards().size == 4)
        assert(deck.listOfCards().size == 0)
    }
}