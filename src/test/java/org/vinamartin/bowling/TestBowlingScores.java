package org.vinamartin.bowling;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class TestBowlingScores {

    private Game game;

    @Before
    public void setup() {
        game = new Game();
    }

    @Test
    public void defaultState() {
        assertEquals(game.getNearestScore(), 0);
    }

    @Test (expected = TooManyPinsException.class)
    public void testHittingTooManyPinsInOneRoll() throws TooManyPinsException {
        game.rollBall(13);
    }

    @Test (expected = TooManyPinsException.class)
    public void testHittingTooManyPinsInSpare() throws TooManyPinsException {
        game.rollBall(9);
        game.rollBall(2);
    }

    @Test (expected = TooManyPinsException.class)
    public void testHittingTooManyPinsInOneRollTenthFrame() throws TooManyPinsException {
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(13);
    }

    @Test (expected = TooManyPinsException.class)
    public void testHittingTooManyPinsInSpareTenthFrame() throws TooManyPinsException {
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(9);
        game.rollBall(2);
    }

    @Test
    public void testGutterBall() throws TooManyPinsException {
        game.rollBall(0);
        game.rollBall(0);
        assertEquals(game.getNearestScore(), 0);
    }

    @Test
    public void testOpenFrame() throws TooManyPinsException {
        game.rollBall(3);
        game.rollBall(4);

        assertEquals(game.getNearestScore(), 7);
    }

    @Test
    public void testSpare() throws TooManyPinsException {
        game.rollBall(3);
        game.rollBall(7);
        game.rollBall(2);
        game.rollBall(3);

        assertEquals(game.getNearestScore(), 17);
    }

    @Test
    public void testStrike() throws TooManyPinsException {
        game.rollBall(10);
        game.rollBall(2);
        game.rollBall(3);

        assertEquals(game.getNearestScore(), 20);
    }

    @Test
    public void testTripleStrike() throws TooManyPinsException {
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);

        assertEquals(game.getNearestScore(), 30);
    }

    @Test
    public void testGetNearestScoreInStrikeGame() throws TooManyPinsException {
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);

        assertEquals(game.getNearestScore(), 30);
    }

    @Test
    public void testGetNearestScoreInSpareGame() throws TooManyPinsException {
        game.rollBall(2);
        game.rollBall(8);

        assertEquals(game.getNearestScore(), 0);
    }

    @Test
    public void testStrikeIn10thFrame() throws TooManyPinsException {
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);

        game.rollBall(10);
        game.rollBall(2);
        game.rollBall(3);

        int lastFrameScore = game.getFrameScores().get(9);
        assertEquals(lastFrameScore, 15);
    }

    @Test
    public void testSpareIn10thFrame() throws TooManyPinsException {
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);

        game.rollBall(7);
        game.rollBall(3);
        game.rollBall(2);

        int lastFrameScore = game.getFrameScores().get(9);
        assertEquals(lastFrameScore, 12);
    }

    @Test
    public void testOpen10thFrame() throws TooManyPinsException {
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);
        game.rollBall(10);

        game.rollBall(7);
        game.rollBall(2);

        int lastFrameScore = game.getFrameScores().get(9);
        assertEquals(lastFrameScore, 9);
    }
}
