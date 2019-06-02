package org.vinamartin.bowling;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Boolean gameOver = false;

    private List<Frame> frames = new ArrayList<>();
    private List<Integer> rolls = new ArrayList<>();

    Game() {
        Frame frame = new Frame();
        frames.add(frame);
    }

    Boolean isGameOver() {
        if(frames.size() == 10 && frames.get(9).getStatus() != FrameType.INCOMPLETE) {
            gameOver = true;
        }
        return gameOver;
    }

    void rollBall(int pins) throws TooManyPinsException {
        rolls.add(pins);
        Frame currentFrame = frames.get(frames.size()-1);
        if(currentFrame.getStatus() == FrameType.INCOMPLETE) {
            bowlCurrentFrame(currentFrame, pins);
        } else {
            if(frames.size() == 9) {
                bowl10thFrame(pins);
            } else {
                bowlNewFrame(pins);
            }
        }
        updateScores();
    }

    int getNearestScore() {
        int score = 0;
        for(Frame frame : frames) {
            score += frame.getScore();
        }
        return score;
    }

    ArrayList<Integer> getFrameScores() {
        ArrayList<Integer> frameScores = new ArrayList<>();
        for(Frame frame : frames) {
            frameScores.add(frame.getScore());
        }
        return frameScores;
    }

    private void updateScores() {
        for(Frame frame : frames) {
            FrameType frameType = frame.getStatus();
            if(rolls.size() > frame.getBonusIndex()) {
                switch(frameType) {
                    case SPARE:
                        frame.setScore(10 + rolls.get(frame.getBonusIndex()));
                        break;
                    case STRIKE:
                        frame.setScore(10 + rolls.get(frame.getBonusIndex()-1) + rolls.get(frame.getBonusIndex()));
                        break;
                }
            }
        }
    }

    private void bowlCurrentFrame(Frame currentFrame, int pins) throws TooManyPinsException {
        currentFrame.addRoll(pins);
        if(currentFrame.getStatus() == FrameType.STRIKE) {
            currentFrame.setLastBonusIndex(rolls.size()+1);
        } else if (currentFrame.getStatus() == FrameType.SPARE) {
            currentFrame.setLastBonusIndex(rolls.size());
        }
    }

    private void bowlNewFrame(int pins) throws TooManyPinsException {
        Frame frame = new Frame();
        frame.addRoll(pins);
        if(frame.getStatus() == FrameType.STRIKE) {
            frame.setLastBonusIndex(rolls.size()+1);
        }
        frames.add(frame);
    }

    private void bowl10thFrame(int pins) throws TooManyPinsException {
        Frame frame = new TenthFrame();
        frame.addRoll(pins);
        frames.add(frame);
    }
}
