package edu.buffalo.cse116;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
* <h1>Card Class<h1>
* Each Card instance is a combination of suite & rank.
* <p>
* @author Corey Almonte
* @version 0.5
* @since 2017-09-27
*/
public class Card {
    /**
    *An instance variable from the Suit enum.
    */
    private Suit suit;

    /**
    *An instance variable from the Rank enum.
    */
    private Rank rank;

    /**
    *Initializes Card with suit and rank
    *@param
    */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    /**
    *   The getter for the suit 
    */
    public Suit getSuit() {
        return suit;
    }
    /**
    *   The getter for the rank
    */
    public int getValueRank() {
        return rank.getValue();
    }
     public Rank getRank(){
    	 return rank;
     }
    
    
    public String getImageFileName() {
		return ("" + this.getSuit()) + ("" + this.getRank());
	}
    
    public ImageView getImage( ){
    	Image image;
    	ImageView imageView = null ;
		try {
			image = new Image (new FileInputStream ("moderncards/" + this.getImageFileName() + ".png"));
			imageView = new ImageView(image);
			imageView.setFitHeight(100);
			imageView.setFitWidth(70);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return imageView;
    }
    /**
    *   Overrides the toString method
    */
     @Override
    	public String toString() {
    		// TODO Auto-generated method stub
    		return getSuit() + " of " + getRank();
    	}
}
    