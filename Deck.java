import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private ArrayList<Card> content;
    private int amount_cards;
    
    public Deck(int amount_subdecks) {
        this.content = new ArrayList<Card>();
        for (int i = 0; i < amount_subdecks; i++) {
            for (String suit : Card.suits) {
                for (String rank : Card.ranks) {
                    this.addCard(new Card(rank, suit));
                }
            }
        }
    }

    public void addCard(Card card) {
        this.content.add(card);
    }

    public Deck() {
        this(1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("_____" + this.content.size() + "_____");
        for (Card card : this.content) {
            sb.append("\n").append(card.toString());
        }
        sb.append("\n_____").append(this.content.size()).append("_____");
        return sb.toString();
    }


    public void shuffle() {
        Collections.shuffle(this.content);
    }

    public void tShuffle() {
        Collections.shuffle(this.content, new Random(42));
    }
}