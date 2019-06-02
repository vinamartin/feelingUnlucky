package org.vinamartin.bowling;

public class TenthFrame extends Frame {

    private FrameType condition = FrameType.INCOMPLETE;

    @Override
    public void addRoll(int pins) throws TooManyPinsException {
        if(pins > 10) {
            throw new TooManyPinsException();
        }
        if(condition != FrameType.STRIKE) {
            if(rolls.size()+1 == 2 && score+pins > 10) {
                throw new TooManyPinsException();
            }
        }

        rolls.add(pins);
        score += pins;
        if (rolls.size() == 1 && score == 10) {
            condition = FrameType.STRIKE;
        } else if (rolls.size() > 1 && score == 10) {
            condition = FrameType.SPARE;
        } else if (rolls.size() > 1 && score < 10) {
            condition = FrameType.OPEN;
        }

        if((condition == FrameType.STRIKE || condition == FrameType.SPARE) && rolls.size() == 3) {
            //This frame follows the 'open' frame scoring of only counting the pins
            //knocked down in this frame
            status = FrameType.OPEN;
        }
    }
}
