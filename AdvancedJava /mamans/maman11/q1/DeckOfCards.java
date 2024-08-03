// DeckOfCards class represents a deck of playing cards
import java.security.SecureRandom;
import java.util.ArrayList;
public class DeckOfCards {
	private ArrayList<Card> deck = new ArrayList<Card>();
	private static final int NUMBER_OF_CARDS = 52;
	private static final SecureRandom randomNumbers = new SecureRandom(); //random number generator
	
	// constructor which fills a deck of cards
	public DeckOfCards() {
		this.deck = new ArrayList<Card>();
		String[] faces = {"Ace", "Deuce", "Three", "Four", "Five", "Six", "Seven", "Eight",
				"Nine", "Ten", "Jack", "Queen", "King"};
		String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
		
		// for loop that populates the deck with cards
        for (String face : faces) {
            for (String suit : suits) {
                this.deck.add(new Card(face, suit));
            }
        }
       
	}
	
	// method used for shuffling a playing cards deck
	public void shuffle() {
		for(int first = 0; first < deck.size(); first++) {
			int second = randomNumbers.nextInt(NUMBER_OF_CARDS);
			Card temp = deck.get(first);
			deck.set(first, deck.get(second));
			deck.set(second, temp);
		}
	}
	
	// boolean method used for checking if a deck is empty
	public boolean isEmpty() {
		return deck.size() == 0;
	}
	
	
	// method that deals a single card
	public Card dealCard() {
		if(!deck.isEmpty()) {
			return deck.remove(0);
		}
		else
			return null;
	}

	
	// method that adds a new card to deck
	public void addCard(Card card) {
		deck.add(card);
	}
	
	// method used for adding a whole deck to another
	public void addDeck(DeckOfCards otherDeck) {
		while(!otherDeck.isEmpty()) {
			deck.add(otherDeck.dealCard());
		}
	}
	
	// getter for deck size
	public int sizeOfDeck() {
		return deck.size();
	}
	
	// method for clearing a deck
	public void clearDeck() {
		deck.clear();
	}
}
