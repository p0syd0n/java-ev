package com.posydon.blackjack;

import java.util.Map;
import java.util.HashMap;


public class BasicStrategy {
    private Map<String, Map<Integer, Decision[]>> strategy;
    public BasicStrategy() {
        Map<Integer, Decision[]> hardStrategy = Map.ofEntries(
            Map.entry(7, new Decision[] {Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT}),
            Map.entry(6, new Decision[] {Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT}),
            Map.entry(5, new Decision[] {Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT}),
            Map.entry(4, new Decision[] {Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT}),
            Map.entry(3, new Decision[] {Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT}),
            Map.entry(2, new Decision[] {Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT}),

            Map.entry(8, new Decision[] {Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT}),
            Map.entry(9, new Decision[] {Decision.HIT, Decision.DOUBLE, Decision.DOUBLE, Decision.DOUBLE, Decision.DOUBLE, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT}),
            Map.entry(10, new Decision[] {Decision.DOUBLE, Decision.DOUBLE, Decision.DOUBLE, Decision.DOUBLE, Decision.DOUBLE, Decision.DOUBLE, Decision.DOUBLE, Decision.DOUBLE, Decision.HIT, Decision.HIT}),
            Map.entry(11, new Decision[] {Decision.DOUBLE, Decision.DOUBLE, Decision.DOUBLE, Decision.DOUBLE, Decision.DOUBLE, Decision.DOUBLE, Decision.DOUBLE, Decision.DOUBLE, Decision.DOUBLE, Decision.DOUBLE}),
            Map.entry(12, new Decision[] {Decision.HIT, Decision.HIT, Decision.STAND, Decision.STAND, Decision.STAND, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT}),
            Map.entry(13, new Decision[] {Decision.STAND, Decision.STAND, Decision.STAND, Decision.STAND, Decision.STAND, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT}),
            Map.entry(14, new Decision[] {Decision.STAND, Decision.STAND, Decision.STAND, Decision.STAND, Decision.STAND, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT}),
            Map.entry(15, new Decision[] {Decision.STAND, Decision.STAND, Decision.STAND, Decision.STAND, Decision.STAND, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT}),
            Map.entry(16, new Decision[] {Decision.STAND, Decision.STAND, Decision.STAND, Decision.STAND, Decision.STAND, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT})
        );

        Map<Integer, Decision[]> softStrategy = Map.ofEntries(
            Map.entry(2, new Decision[] {Decision.HIT, Decision.HIT, Decision.HIT, Decision.DOUBLE, Decision.DOUBLE, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT}),
            Map.entry(3, new Decision[] {Decision.HIT, Decision.HIT, Decision.HIT, Decision.DOUBLE, Decision.DOUBLE, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT}),
            Map.entry(4, new Decision[] {Decision.HIT, Decision.HIT, Decision.DOUBLE, Decision.DOUBLE, Decision.DOUBLE, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT}),
            Map.entry(5, new Decision[] {Decision.HIT, Decision.HIT, Decision.DOUBLE, Decision.DOUBLE, Decision.DOUBLE, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT}),
            Map.entry(6, new Decision[] {Decision.HIT, Decision.DOUBLE, Decision.DOUBLE, Decision.DOUBLE, Decision.DOUBLE, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT, Decision.HIT}),
            Map.entry(7, new Decision[] {Decision.DOUBLE_S, Decision.DOUBLE_S, Decision.DOUBLE_S, Decision.DOUBLE_S, Decision.DOUBLE_S, Decision.STAND, Decision.STAND, Decision.HIT, Decision.HIT, Decision.HIT}),
            Map.entry(8, new Decision[] {Decision.STAND, Decision.STAND, Decision.STAND, Decision.STAND, Decision.DOUBLE_S, Decision.STAND, Decision.STAND, Decision.STAND, Decision.STAND, Decision.STAND}),
            Map.entry(9, new Decision[] {Decision.STAND, Decision.STAND, Decision.STAND, Decision.STAND, Decision.STAND, Decision.STAND, Decision.STAND, Decision.STAND, Decision.STAND, Decision.STAND})
        );
        Map<Integer, Decision[]> pairStrategy = Map.ofEntries(
            Map.entry(2, new Decision[] {Decision.SPLIT_DAS, Decision.SPLIT_DAS, Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT}),
            Map.entry(3, new Decision[] {Decision.SPLIT_DAS, Decision.SPLIT_DAS, Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT}),
            Map.entry(4, new Decision[] {Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.SPLIT_DAS, Decision.SPLIT_DAS, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT}),
            Map.entry(5, new Decision[] {Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT}),
            Map.entry(6, new Decision[] {Decision.SPLIT_DAS, Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT}),
            Map.entry(7, new Decision[] {Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT}),
            Map.entry(8, new Decision[] {Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.SPLIT}),
            Map.entry(9, new Decision[] {Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.NO_SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT}),
            Map.entry(10, new Decision[] {Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT, Decision.NO_SPLIT}),
            Map.entry(11, new Decision[] {Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.SPLIT, Decision.SPLIT})
        );

        this.strategy = Map.ofEntries(
            Map.entry("hard", hardStrategy),
            Map.entry("soft", softStrategy),
            Map.entry("pair", pairStrategy)
        );
    }

    public Decision getMove(Hand hand, Card dealerUpcard, int amountOfHands){
        // total variable for later reference
        int[] total = hand.total();
        if (total[0] >= 21) return Decision.STAND;
        // Check for bust and blackjack
        System.out.println("Hand gotten: " + hand);
        System.out.println("Dealer upcard: " + dealerUpcard);

        // If the amount of cards is 2 and they are the same rank, we get split decisions
        if (hand.amountOfCards() == 2 && hand.getCard(0).getRank().equals(hand.getCard(1).getRank())) {
            System.out.println("Getting split decisions.");
            // if the pair strategies say not to split, we resort to hard strategies
            if (strategy.get("pair").get(hand.getCard(0).getValue())[dealerUpcard.getValue()-2] == Decision.NO_SPLIT) {
                return ((total[0] <= 8) ? Decision.HIT : (total[0] >= 17 ? Decision.STAND : strategy.get("hard").get(total[0])[dealerUpcard.getValue()-2]));
            }
            // Otherwise, we use the pair strategy specified
            return strategy.get("pair").get(hand.getCard(0).getValue())[dealerUpcard.getValue()-2];
            /// -2 because index starts at 0 and dealer totals start at 2
        } else { // Otherwise: it is not a pair
            if (total[1] == 1) { // It is soft (total is an array, the second element is a 1/0 to  indicate softness)
                return strategy.get("soft").get(total[0]-11)[dealerUpcard.getValue()-2];
                // total[0] is the whole total. The map is A + [lookup], therefore we need to get rid of said ace
            }
            System.out.println("Going hard, total: " + total[0]);
            System.out.println("Versus a " + dealerUpcard.getValue());
            return ((total[0] <= 8) ? Decision.HIT : (total[0] >= 17 ? Decision.STAND : strategy.get("hard").get(total[0])[dealerUpcard.getValue()-2]));

        }
    }

    public Decision getMove(Hand hand, Card dealerUpcard) {
        // Call the real one with default stuff - can split and double
        Decision move = this.getMove(hand, dealerUpcard, 1);
        System.out.println(move);
        return move;
    }
}