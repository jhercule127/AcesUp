package edu.buffalo.cse116;

/**
* <h1>Rank Enum</h1>
* Is an enum representing the values of
* a card. 
* <p>
* <b>Note:</b> This is to be combined with the Suit enum to form
* the card class.
*
* @author  Corey Almonte
* @version 1.5
* @since   2017-09-29
*/
public enum Rank {
    /**
    *Enumeration of card ranks
    */
    TWO(2), THREE(3), FOUR(4), 
    FIVE(5), SIX(6), SEVEN(7), EIGHT(8), 
    NINE(9), TEN(10), JACK(11), QUEEN(12),
    KING(13),ACE(14) ;  
  
    /**
    * The value of each enum value.
    */
    private int value;
     
    /**
    *Gives back the rank of the enum
    *@param rank    Sets enum equal to the parameter rank.
    */
    Rank(int value) {
      this.value = value;
    }
    /**
    *   The Getter for the rank enum.
    */
    public int getValue() {
      return value;
    }
    
    @Override
   	public String toString() {
   		// TODO Auto-generated method stub
   		return  name();
   	}
}        

