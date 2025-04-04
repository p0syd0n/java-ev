package com.posydon.blackjack;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.ArrayList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import javax.management.Descriptor;

public class DeckTest {
    static Random random = new Random();

    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(input.getBytes());

            // Convert bytes to hex
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDeckCreationSingle() {
        Deck newDeck = new Deck();
        assertEquals(newDeck.getAmountCards(), 52);
    }

    @Test
    public void testDeckCreationMultiple() {
        int decks = random.nextInt(50) + 1;
        Deck newDeck = new Deck(decks);
        assertEquals(newDeck.getAmountCards(), decks*52);
    }

    @Test
    public void testShuffle() {
        Deck newDeck = new Deck(random.nextInt(50) + 1);
        String beforeShuffleHash = md5(newDeck.toString());

        newDeck.shuffle();
        assertNotEquals(md5(newDeck.toString()), beforeShuffleHash);
    }
}