# KCards
Kotlin Library providing an Implementation of  **Playing Cards**. Could be used to jump-start **a card game** or anything that uses playing cards.

## Usage

To create a **Deck of cards** instantiate of the *Deck* class provided. 

### Creating a standard deck
A standard deck of playing cards has 52 cards. Creating one is easy:

    val deck = Deck.Builder().addCompleteDeck().build()
### Creating a deck with multiple standard decks
Some games need more than one deck. Just add any number of standard decks just like this:

    val deck = Deck.Builder()
        .addCompleteDeck()
        .addCompleteDeck()
        .build()

### Custom decks
Some games are not using all cards of a standard deck.
Deck with a subset of ranks can be created like this:

    val deck = Deck.Builder()
        .addRank(Rank.TWO)
        .addRank(Rank.ACE)
        .addRank(Rank.KING)
        .build()

You can build a deck with only a subset of suites like this

    var deck = Deck.Builder()
        .addSuite(Suite.SPADES)
        .addSuite(Suite.CLUBS)
        .build()

### Creating Players
Simply extend the *CardHolder* abstract class to create any entity that holds cards (players, table, discarded piles etc.)
Card games 

## Concepts 

### Suite
Can be CLUBS, DIAMONDS, HEARTS or SPADES

### Rank
Can be ACE, ONE ... TEN, JACK, QUEEN OR KING

### Card
Has a Suite and a Rank. Can not be changed after creation (immutable)

### CardHolder (abstract class)  
Any entity that holds cards (decks, players etc.) can be created by extending *CardHolder* abstract class.
CardHolder provides storage for holding cards and functions to manipulate them (add, remove, shuffle, exchange with other *CardHoder*s). 

