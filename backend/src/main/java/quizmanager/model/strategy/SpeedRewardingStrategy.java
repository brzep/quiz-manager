package quizmanager.model.strategy;

import quizmanager.model.Quiz;
import quizmanager.model.Record;
import quizmanager.model.prize.Prize;
import quizmanager.model.prize.PrizeType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@SuppressWarnings("unused")
@Entity
@DiscriminatorValue("SPEED")
public class SpeedRewardingStrategy extends RewardingStrategy{

    private double topSpeedPercentage;
    private int maxAnswers;

    public SpeedRewardingStrategy(String name, PrizeType prizeTypeIfPassed, PrizeType prizeTypeIfFailed, double topSpeedPercentage, int maxAnswers) {
        super(name, prizeTypeIfPassed, prizeTypeIfFailed);
        this.topSpeedPercentage = topSpeedPercentage;
        this.maxAnswers = maxAnswers;
    }

    public SpeedRewardingStrategy() {
        super();
    }

    public double getTopSpeedPercentage() {
        return topSpeedPercentage;
    }

    public int getMaxAnswers() {
        return maxAnswers;
    }

    @Override
    public void accept(Visitor visitor, Quiz quiz, Prize nonePrize) {
        visitor.assignPrizesSpeed(this, quiz, nonePrize);
    }

    //PASS HERE RECORD SORTED BY SPEED
    @Override
    public void assignPrizes(List<Record> records) {
        int howManyToPass = (int) Math.floor((topSpeedPercentage * records.size()));
        int counter = 0;
        for(Record record: records){
            if(record.getScore() == maxAnswers && counter < howManyToPass){
                record.setPrize(prizeTypeIfPassed);
                counter += 1;
            }
            else{
                record.setPrize(prizeTypeIfFailed);
            }
        }


    }
}
