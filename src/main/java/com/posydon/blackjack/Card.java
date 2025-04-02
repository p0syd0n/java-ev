package com.posydon.blackjack;

import java.util.List;
import java.util.ArrayList;


public class Card {
    public static String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    public static String[] suits = {"spade", "heart", "club", "diamond"};
    private String rank;
    private String suit;
    public boolean countValue;
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return this.rank;
    }

    public String toString() {
        return this.rank + " of " + this.suit + "s";
    }

    public int getValue() {
        return (this.rank.equals("10") || this.rank.equals("J") || this.rank.equals("Q") || this.rank.equals("K") ? 10 : (this.rank.equals("A") ? 11 : Integer.valueOf(this.rank)));
    }

}