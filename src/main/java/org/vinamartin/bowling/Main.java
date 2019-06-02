package org.vinamartin.bowling;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String bowlingArt = "   ___             ___          \n" + "  / _ )___ _    __/ (_)__  ___ _\n"
                + " / _  / _ \\ |/|/ / / / _ \\/ _ `/\n" + "/____/\\___/__,__/_/_/_//_/\\_, / \n"
                + "                         /___/  ";

        Game game = new Game();
        Scanner scanner = new Scanner(System.in);

        System.out.println(bowlingArt);
        System.out.println("The command line bowling scoring app\nVersion 0.0.1-SNAPSHOT");
        System.out.println("Instructions: Type in pins hit on each roll and hit enter for current score.");

        do {
            try {
                game.rollBall(scanner.nextInt());
                System.out.println("Total Score (up to last complete frame): " + game.getNearestScore());
            } catch (TooManyPinsException e) {
                System.out.println("An invalid amount of pins was entered. Please try again.");
            }
        } while(!game.isGameOver());
        System.out.println("Game over.");
        System.out.println("FINAL SCORE: " + game.getNearestScore());
        System.out.println("Thanks for playing!");
    }
}
