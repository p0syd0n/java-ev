package com.posydon.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import java.util.ArrayDeque;

public class Deck {
    private ArrayDeque<Card> content;
    private boolean test = false;    
    
    public Deck(int amount_subdecks) {
        this.content = new ArrayDeque<Card>();
        for (int i = 0; i < amount_subdecks; i++) {
            for (String suit : Card.suits) {
                for (String rank : Card.ranks) {
                    this.addCard(new Card(rank, suit));
                }
            }
        }
    }

    public Deck() {
        this(1);
    }

    public void addCard(Card card) {
        this.content.offerLast(card);
    }

    public void setTrueTest() {
        this.test = true;
    }

   public void shuffle() {
        if (this.test) return;
        List<Card> tempList = new ArrayList<Card>(this.content); // Convert Deque to List
        Collections.shuffle(tempList); // Shuffle the List
        this.content.clear(); // Clear the deque
        this.content.addAll(tempList); // Put shuffled cards back
    }


    public int getAmountCards() {
        return this.content.size();
    }

    public ArrayDeque<Card> getDeck() {
        return this.content;
    }

    public Card draw() {
        return this.content.pollLast();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : this.content) {
            sb.append(card.toString()).append(",");
        }
        return sb.toString();
    }




}