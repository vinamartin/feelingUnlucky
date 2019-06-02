package org.vinamartin.bowling;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    List<Integer> rolls = new ArrayList<>();
    FrameType status = FrameType.INCOMPLETE;

    int score = 0;
    private int lastBonusIndex;

    FrameType getStatus() {
        return status;
    }

    void setLastBonusIndex(int lastBonusIndex) {
        this.lastBonusIndex = lastBonusIndex;
    }

    int getScore() {
        return score;
    }

    void setScore(int score) {
        this.score = score;
    }

    int getBonusIndex() {
        return lastBonusIndex;
    }

    public void addRoll(int pins) throws TooManyPinsException {
        if(pins > 10 || (rolls.size()+1 > 1 && score+pins > 10)) {
            throw new TooManyPinsException();
        }

        rolls.add(pins);
        score += pins;

        if (rolls.size() == 1 && score == 10) {
            status = FrameType.STRIKE;
            score = 0; // Score can't be calculated yet, reset score
        } else if (rolls.size() > 1 && score == 10) {
            status = FrameType.SPARE;
            score = 0; // Score can't be calculated yet, reset score
        } else if (rolls.size() > 1 && score < 10) {
            status = FrameType.OPEN;
        }
    }
}
