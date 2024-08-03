// Card class represents a playing card
public class Card {

	private final String face;
	private final String suit;
	
	public Card(String cardFace, String cardSuit) {
		this.face = cardFace;
		this.suit = cardSuit;
	}
	// toString override which returns a string representation of a card
	public String toString() {
		return face + " of " + suit;
	}
	
	//method which indicates the value of a face card and returns it to the user
	public int cardValue() {
		String[] faces = {"Ace", "Deuce", "Three", "Four", "Five", "Six", "Seven", "Eight",
				"Nine", "Ten", "Jack", "Queen", "King"};
		
		for(int i=0; i<faces.length; i++) {
			if(face == faces[i])
				return i+1;
		}
		return 0;
	}
	
}
