package com.posydon.blackjack;


import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Hand {
    private ArrayList<Card> content;
    public int bet;
    public Hand(Card cards[]) {
        this.content = new ArrayList<Card>();
        for (int i  = 0; i < cards.length; i++)  {
            this.content.add(cards[i]);

        }
    }

    public Hand() {
        //Card cards[] = {};
        this(new Card[] {});
    }

    public void addCard(Card card) {
        this.content.add(card);
    } 

    /**
     * Returns an integer array where the first value is the total and the second is a 1/0 representing squishiness
     */
    public int[] total() {
        int[] returned = new int[2];
        int soft = 0;
        int total = 0;
        String rank;
        for (Card card : this.content) {
            rank = card.getRank();
            if (rank == "A") {
                soft = 1;
                total += 11;
            } else {
                total += ((rank.equals("10") || rank.equals("J") || rank.equals("Q") || rank.equals("K")) ? 10 : Integer.valueOf(rank));
            }
        }
        if (total > 21 && soft == 1)  {
            total -= 10;
            soft = 0;
        }
        returned[0] = total;
        returned[1] = soft;

        return returned;
    }

    public int amountOfCards() {
        return this.content.size();
    }

    public Card getCard(int index) {
        return this.content.get(index);
    }

    @Override
    public String toString() {
        String returned = "(";
        for (Card card : this.content) {
            returned += card.toString() + ", ";
        }
        returned += (") : " + ((this.total()[1] == 1) ? "soft " : "")  + this.total()[0]);
        return returned;
    }


}