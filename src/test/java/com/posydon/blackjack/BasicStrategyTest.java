package com.posydon.blackjack;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import javax.management.Descriptor;


public class BasicStrategyTest {

    // Test when player has hard total (no Ace)
    @Test
    public void testHardStrategy() {
        // Test a hard total 8 against a dealer upcard of 6
        Hand hand = new Hand(new Card[] {
            new Card("3", "spade"), 
            new Card("5", "heart")
        });
        Card dealerUpcard = new Card("6", "diamond");

        BasicStrategy strategy = new BasicStrategy();
        
        // Expecting a HIT because hard total 8 (below 9)
        Decision decision = strategy.getMove(hand, dealerUpcard);
        assertEquals(Decision.HIT, decision);

        // Test a hard total 16 against a dealer upcard of 10
        hand = new Hand(new Card[] {
            new Card("10", "spade"),
            new Card("6", "heart")
        });
        dealerUpcard = new Card("10", "diamond");

        decision = strategy.getMove(hand, dealerUpcard);
        assertEquals(Decision.HIT, decision); // Expecting HIT since the dealer has a strong card
    }

    // Test soft strategy (with Ace)
    @Test
    public void testSoftStrategy() {
        // Setup a soft hand (Ace + 3, total of 14)
        Hand hand = new Hand(new Card[] {
            new Card("A", "spade"), 
            new Card("3", "heart")
        });
        Card dealerUpcard = new Card("5", "diamond");

        BasicStrategy strategy = new BasicStrategy();
        
        // Expecting DOUBLE because soft total 14 (Ace + 3)
        Decision decision = strategy.getMove(hand, dealerUpcard);
        assertEquals(Decision.DOUBLE, decision);

        // Test soft total 13 (Ace + 2) against dealer's 7 upcard
        hand = new Hand(new Card[] {
            new Card("A", "spade"), 
            new Card("2", "heart")
        });
        dealerUpcard = new Card("7", "diamond");

        decision = strategy.getMove(hand, dealerUpcard);
        assertEquals(Decision.HIT, decision);
    }

    // Test pair strategy
    @Test
    public void testPairStrategy() {
        // Test pair of Aces
        Hand hand = new Hand(new Card[] {
            new Card("A", "spade"), 
            new Card("A", "heart")
        });
        Card dealerUpcard = new Card("9", "diamond");

        BasicStrategy strategy = new BasicStrategy();
        
        // Expecting SPLIT because pair of Aces
        Decision decision = strategy.getMove(hand, dealerUpcard);
        assertEquals(Decision.SPLIT, decision);

        // Test pair of 8s
        hand = new Hand(new Card[] {
            new Card("8", "spade"), 
            new Card("8", "heart")
        });
        dealerUpcard = new Card("10", "diamond");

        decision = strategy.getMove(hand, dealerUpcard);
        assertEquals(Decision.SPLIT, decision);
    }

    // Test handling edge cases with invalid inputs
    @Test
    public void testInvalidInputs() {
        // Test when Hand has more than 2 cards
        Hand hand = new Hand(new Card[] {
            new Card("7", "spade"),
            new Card("6", "heart"),
            new Card("5", "club")
        });
        Card dealerUpcard = new Card("8", "diamond");

        BasicStrategy strategy = new BasicStrategy();
        
        // Expecting a HIT because it's a hard total of 18, and the dealer has an 8 upcard
        Decision decision = strategy.getMove(hand, dealerUpcard);
        assertEquals(Decision.STAND, decision);

    }

    // Test for the condition where amountOfHands > 4
    @Test
    public void testExceedingAmountOfHands() {
        Hand hand = new Hand(new Card[] {
            new Card("8", "spade"),
            new Card("8", "heart")
        });
        Card dealerUpcard = new Card("5", "diamond");

        BasicStrategy strategy = new BasicStrategy();
        
        // amountOfHands is set to 5, which exceeds the maximum of 4, so no split should be made
        Decision decision = strategy.getMove(hand, dealerUpcard, 5);
        assertThat(decision, is(not(Decision.SPLIT)));
    }

    // Test when the hand is a pair but has exceeded max allowed hands (i.e., more than 4)
    @Test
    public void testPairExceedingMaxHands() {
        Hand hand = new Hand(new Card[] {
            new Card("5", "spade"),
            new Card("5", "heart")
        });
        Card dealerUpcard = new Card("7", "diamond");

        BasicStrategy strategy = new BasicStrategy();
        
        // Expecting a HIT because it's a pair and the amount of hands exceeds the limit
        Decision decision = strategy.getMove(hand, dealerUpcard, 5);
        assertEquals(Decision.DOUBLE, decision);
    }
}
