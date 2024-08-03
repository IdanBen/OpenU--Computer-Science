//@Author: Idan Benayoun
//This class represents the War Game engine
import javax.swing.*;
public class Main {
	public static void main(String[] args) {
		warGame();
	}

	// The method that implements the game
	public static void warGame() {
		String message;
		DeckOfCards player1Deck = new DeckOfCards();
		DeckOfCards player2Deck = new DeckOfCards();
		DeckOfCards tempDeck = new DeckOfCards();
		player1Deck.clearDeck();
		player2Deck.clearDeck();
		tempDeck.shuffle();
		divide(player1Deck, player2Deck, tempDeck);
		tempDeck.clearDeck();

		while (!player1Deck.isEmpty() && !player2Deck.isEmpty()) {
			Card player1Card = player1Deck.dealCard();
			Card player2Card = player2Deck.dealCard();
			message = "Player 1 Card is: " + player1Card + "\nPlayer 2 Card is: " + player2Card.toString();
			if (player1Card.cardValue() > player2Card.cardValue()) {
				addToDeck(player1Card, player2Card, player1Deck, tempDeck);
				message += "\nPlayer 1 won this round!";
				JOptionPane.showMessageDialog(null, message);
			} else if (player1Card.cardValue() < player2Card.cardValue()) {
				addToDeck(player2Card, player1Card, player2Deck, tempDeck);
				message += "\nPlayer 2 won this round!";
				JOptionPane.showMessageDialog(null, message);
			}
			// War mode
			else {
				message += "\nIt's war time!";
				JOptionPane.showMessageDialog(null, message);
				// We will add 2 cards from each player to the tempDeck deck and after the war
				// will give them to the winner
				for (int i = 0; i < 2; i++) {
					player1Card = player1Deck.dealCard();
					player2Card = player2Deck.dealCard();
					tempDeck.addCard(player1Card);
					tempDeck.addCard(player2Card);
				}
			}
		} // 
		winner(player1Deck, player2Deck);
	}

	// a method which divides in half the cards from a single deck to 2 players' decks
	public static void divide(DeckOfCards player1Deck, DeckOfCards player2Deck, DeckOfCards MainDeck) {
		// for each of the 52 cards, divide by regular cards division
		for (int i = 0; i < 52; i++) {
			if (i % 2 == 0)
				player1Deck.addCard(MainDeck.dealCard());
			else
				player2Deck.addCard(MainDeck.dealCard());
		}
	}
	
	// this method decides who the winner of the game is and creates a following message
	public static void winner(DeckOfCards deck1, DeckOfCards deck2) {
		if (deck1.isEmpty())
			JOptionPane.showMessageDialog(null, "Player 2 won the game!");
		else
			JOptionPane.showMessageDialog(null, "Player 1 won the game!");
	}

	// this method is used to store the current playing cards into a different deck, and then deciding by the reuslt of the round it adds it to the round winner' deck
	public static void addToDeck(Card player1Card, Card player2Card, DeckOfCards deck, DeckOfCards tempDeck) {
		tempDeck.addCard(player1Card);
		tempDeck.addCard(player2Card);
		deck.addDeck(tempDeck);
		tempDeck.clearDeck();
	}
}