package com.posydon.blackjack;

import java.util.Random;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

public class Game {
    Random random = new Random();
    int decks;
    int shoe_penetration;
    boolean DAS;
    boolean lastHand = false;
    int current_bankroll;
    int min_bet;
    int max_bet;
    Card dealerUpCard = new Card("TEMP", "TEMP");
    Deck main_deck;
    ArrayList<Hand> playerHands;
    Hand dealerHand;
    int risk_constant = 1;
    BasicStrategy basicStrategy;
    AtomicInteger player_money_left;
    double shoe_position;

    public Game(int decks, int shoe_penetration, boolean DAS, int start_bankroll, int min_bet, int max_bet) {
        this.decks = decks;
        this.shoe_penetration = shoe_penetration;
        this.DAS = DAS;
        this.current_bankroll = start_bankroll;
        this.min_bet = min_bet;
        this.decks = decks;
        this.max_bet = max_bet;
        this.shoe_position = this.decks * 52 * (shoe_penetration / 100.0);
        this.player_money_left = new AtomicInteger(this.current_bankroll); 
        /*
        This is the atomic integer to be used within the algorithm for keeping 
        track of how much money the player has in front of him as opposed to the amount he has bet
         */ 

        this.main_deck = new Deck(this.decks);
        this.playerHands = new ArrayList<Hand>();
        this.dealerHand = new Hand();
        this.basicStrategy = new BasicStrategy();
    }

    public void makePlayerDecisions(Hand hand, Card dealerUpCard, boolean justSplitThisHand) {
        Decision decision = basicStrategy.getMove(hand, dealerUpCard, this.playerHands.size());
        switch (decision) {
            case Decision.STAND:
                System.out.println("Standing");
                return;
            case Decision.DOUBLE:
                if (!(justSplitThisHand && !this.DAS)) {
                    System.out.println("We didnt just split this hand without das. So, we are doubling.");
                    if (hand.bet * 2 > player_money_left.get()) return;
                    player_money_left.addAndGet(-hand.bet);
                    hand.bet *= 2;
                    hand.addCard(this.main_deck.draw());
                    return;
                }
            case Decision.HIT:
            case Decision.NO_SPLIT:
                if (hand.bet * this.risk_constant > player_money_left.get()) return;
                    hand.addCard(this.main_deck.draw());
                    makePlayerDecisions(hand, dealerUpCard, justSplitThisHand);
                    break;
            case Decision.DOUBLE_S:
                if (hand.bet * 2 > player_money_left.get()) return;
                if (!this.DAS && justSplitThisHand) return;
                player_money_left.addAndGet(-hand.bet);
                hand.bet *= 2;
                hand.addCard(this.main_deck.draw());
                return;

            case Decision.SPLIT:
                if (hand.bet * 2 * this.risk_constant > player_money_left.get()) return;
                
                Hand newHand = new Hand(new Card[] {hand.getCard(0)});
                Hand newHand2 = new Hand(new Card[] {hand.getCard(1)});
                newHand.addCard(this.main_deck.draw());
                newHand2.addCard(this.main_deck.draw());

                this.playerHands.remove(this.playerHands.size()-1);

                this.playerHands.add(newHand);
                this.playerHands.add(newHand2);
                System.out.println("Just created two new cards and  added them to each hand. The hands are  now: ");
                for (Hand han1d : this.playerHands) {
                    System.out.println(han1d);
                }
                makePlayerDecisions(newHand, dealerUpCard, true);
                makePlayerDecisions(newHand2, dealerUpCard, true);
            }
            
        }
    

    public int mainloop() {
        while (!this.lastHand) {

            int player_bet = bet();
            int player_money_left = current_bankroll - player_bet;

            this.main_deck.shuffle();

            dealFirstTime();
            this.playerHands.get(0).bet = player_bet;
            
            System.out.println("About to make decisions......");
            for (Hand hand : this.playerHands) {
                System.out.println(hand);
            }
            makePlayerDecisions(playerHands.get(0), this.dealerUpCard, this.DAS);
            for (Hand hand : this.playerHands) {
                System.out.println(hand);
            }
            
            


            this.lastHand = true;
            
            // logic
            
            // Loss case:
            // current_bankroll -= player_bet

            // Win case:
            // current_bankroll += player_bet

            // Push case:
            // current_bankroll += 0

        }
        return 1;
    }

    public int bet() {
        return random.nextInt(this.max_bet  - this.min_bet) + this.min_bet+1;
    }

    private void dealFirstTime() {
        playerHands.add(new Hand(new Card[] {this.main_deck.draw(), this.main_deck.draw()}));
        dealerHand.addCard(this.main_deck.draw());
        dealerHand.addCard(this.main_deck.draw());
        this.dealerUpCard = dealerHand.getCard(1);
    }
}