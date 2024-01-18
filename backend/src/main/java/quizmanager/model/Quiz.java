package quizmanager.model;

import quizmanager.model.strategy.RewardingStrategy;

import javax.persistence.*;
import java.util.List;

@SuppressWarnings("unused")
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "rewarding_strategy_id")
    private RewardingStrategy rewardingStrategy;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "quiz_id")
    private List<Record> recordSet;

    private int maxAnswers;

    public Quiz() {
    }

    public Quiz(String name, List<Record> recordSet, RewardingStrategy rewardingStrategy) {
        this.name = name;
        this.recordSet = recordSet;
        this.rewardingStrategy = rewardingStrategy;
    }

    public void assignPrizes() {
        rewardingStrategy.assignPrizes(recordSet);
    }

    public List<Record> getRecordSet() {
        return recordSet;
    }

    public RewardingStrategy getRewardingStrategy() {
        return rewardingStrategy;
    }

    @Override
    public String toString(){
        return name + " " + recordSet.get(0).getNickname();
    }
}
