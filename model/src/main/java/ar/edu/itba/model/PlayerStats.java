package ar.edu.itba.model;

/**
 * Created by martina on 30/04/2018.
 */
public class PlayerStats {

    private Player player;
    private Integer saves, performance, passes, assists, scores, yellowCards, redCards;

    public PlayerStats(Player player, Integer saves, Integer performance, Integer passes,
                       Integer assists, Integer scores, Integer yellowCards, Integer redCards) {
        this.player = player;
        this.saves = saves;
        this.performance = performance;
        this.passes = passes;
        this.assists = assists;
        this.scores = scores;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
    }

    public void addSave(){
        saves++;
    }

    public void addAssist(){
        assists++;
    }

    public void addPass(){
        passes++;
    }

    public void addScore(){
        scores++;
    }

    public void addYellowCard(){
        yellowCards++;
    }

    public void addRedCard(){
        redCards++;
    }

    public Player getPlayer() {
        return player;
    }

    public Integer getSaves() {
        return saves;
    }

    public Integer getPerformance() {
        return performance;
    }

    public Integer getPasses() {
        return passes;
    }

    public Integer getAssists() {
        return assists;
    }

    public Integer getGoals() {
        return scores;
    }

    public Integer getYellowCards() {
        return yellowCards;
    }

    public Integer getRedCards() {
        return redCards;
    }

    public void setPerformance(Integer performance) {
        this.performance = performance;
    }
}
