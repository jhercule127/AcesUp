package edu.buffalo.cse116;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class AcesUpController {

	private ArrayList<Card>[] tableauPiles;
	private ArrayList<Card> homecellPile;
	private ArrayList<Card> discarded;
	private ArrayList<Card> deck;
	private boolean checkdiscard;

	public ArrayList<Card> getDiscarded() {
		return discarded;
	}

	public void setDiscarded(ArrayList<Card> discarded) {
		this.discarded = discarded;
	}

	public ArrayList<Card> getHomecellPile() {
		return homecellPile;
	}

	public void setHomecellPiles_List(ArrayList<Card> homecellPile) {
		this.homecellPile = homecellPile;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}

	public void shuffleDeck() {
		Collections.shuffle(this.deck);
	}

	public AcesUpController() {

		tableauPiles = new ArrayList[4];
		homecellPile = new ArrayList<Card>();
		deck = new ArrayList<Card>();
		discarded = new ArrayList<Card>();

		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				Card card = new Card(suit, rank);
				this.deck.add(card);
			}
		}
		shuffleDeck();

		for (int i = 0; i < 4; i++) {
			ArrayList<Card> Arr = new ArrayList<Card>();
			tableauPiles[i] = Arr;
		}
		checkdiscard = false;
	}

	public Card getCard(int pileNumber, int index) {
		if (pileNumber <= -1 || pileNumber >= 4) {
			return null;
		}

		/*
		 * If the selected card in the selected list is out of bounds, returns
		 * null
		 */
		if (index <= -1 || index >= tableauPiles[pileNumber].size()) {
			return null;
		}

		/*
		 * If the selected list is empty or the card index is invalid, returns
		 * null
		 */
		if (tableauPiles[pileNumber] == null || tableauPiles[pileNumber].get(index) == null) {
			return null;
		}

		// Represents the selected card
		Card card = tableauPiles[pileNumber].get(index);

		// Returns the selected card from the selected list
		return card;
	}

	public boolean addToHomecell() {
		if (checkdiscard == true) {
			Card top = discarded.get(0);
			homecellPile.add(top);
			discarded.remove(0);
			checkdiscard = false;
			return true;
		} else {
			return false;
		}

	}

	public ArrayList<Card>[] getTableauPiles() {
		return tableauPiles;
	}

	public void setTableauPiles(ArrayList<Card>[] tableauPiles) {
		this.tableauPiles = tableauPiles;
	}

	public boolean moveTableau(int pilenumber) {

		if (tableauPiles[pilenumber].isEmpty()) {
			return false;
		}
		Card lastCard = tableauPiles[pilenumber].get(tableauPiles[pilenumber].size() - 1);
		for (ArrayList<Card> pcList : tableauPiles) {
			if (pcList.isEmpty()) {
				pcList.add(lastCard);
				tableauPiles[pilenumber].remove(tableauPiles[pilenumber].size() - 1);
				break;
			}
		}

		return false;
	}

	public void deal() {
		for (int i = 0; i < tableauPiles.length; i++) {
			if (!deck.isEmpty()) {
				tableauPiles[i].add(deck.get(0));
				deck.remove(0);
			}
		}
	}

	public boolean removeFromTableau(int pilenumber) {

		if (tableauPiles[pilenumber].isEmpty()) {
			return false;
		} else {
			ArrayList<Card> selected = tableauPiles[pilenumber];
			Card select = selected.get(selected.size() - 1);

			for (ArrayList<Card> pile : tableauPiles) {
				Card topofpile = pile.get(pile.size() - 1);
				if (topofpile.getSuit() == select.getSuit() && topofpile != select
						&& topofpile.getValueRank() < select.getValueRank()) {
					discarded.add(select);
					selected.remove(selected.size() - 1);
					checkdiscard = true;
					return true;
				}
			}
		}

		return false;

	}

	public static void main(String[] args) {

	}

}
