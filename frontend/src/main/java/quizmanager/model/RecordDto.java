package quizmanager.model;


import java.sql.Timestamp;
import java.util.List;

@SuppressWarnings("unused")

public class RecordDto {

    private final int id;

    private final String nickname;

    private final int score;

    private final Timestamp startTimestamp;

    private final Timestamp endTimestamp;


    private PrizeDto prize;

    private final List<PrizeDto> prizeList;


    public RecordDto(int id, String nickname, int score, Timestamp startTimestamp, Timestamp endTimestamp, PrizeDto prize, List<PrizeDto> prizeList) {
        this.id = id;
        this.nickname = nickname;
        this.score = score;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.prize = prize;
        this.prizeList = prizeList;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public Integer getScore() {
        return score;
    }


    public Timestamp getStartTimestamp() {
        return startTimestamp;
    }


    public Timestamp getEndTimestamp() {
        return endTimestamp;
    }


    public PrizeDto getPrize() {
        return prize;
    }

    public void setPrize(PrizeDto prize) {
        this.prize = prize;
    }


    public List<PrizeDto> getPrizeList() {
        return prizeList;
    }


}


