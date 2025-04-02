package com.posydon.blackjack;

class Main {
    public static void main(String[] args) {
        BasicStrategy strats = new BasicStrategy();

        Hand myHand = new Hand();
        Card c1 = new Card("5", "spade");
        Card c2 = new Card("Q", "spade");
        myHand.addCard(c1);
        myHand.addCard(c2);
        System.out.println(strats.getMove(myHand, new Card("A", "heart")));

    }
}