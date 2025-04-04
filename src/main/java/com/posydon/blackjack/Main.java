package com.posydon.blackjack;

public class Main {
    public static void main(String[] args) {
        while (true) {
            Game game = new Game(1, 99, true, 10000, 10, 100);
            game.mainloop();
        }

        

    }
}