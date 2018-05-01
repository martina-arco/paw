package ar.edu.itba.model;

/**
 * Created by martina on 30/04/2018.
 */
public class PlayerStats {

    private Player player;
    private int saves, performance, passes, assists, scores, yellowCards, redCards, tackles;
    private long id;

    public PlayerStats(long id, Player player, int saves, int performance, int passes,  int tackles,
                       int assists, int scores, int yellowCards, int redCards) {
        this.player = player;
        this.saves = saves;
        this.performance = performance;
        this.passes = passes;
        this.assists = assists;
        this.scores = scores;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.tackles = tackles;
        this.id = id;
    }

    public void addSave(int amount){
        saves += amount;
    }

    public void addTackle(int amount){
        tackles += amount;
    }

    public void addAssist(int amount){
        assists += amount;
    }

    public void addPass(int amount){
        passes += amount;
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

    public long getId() {
        return id;
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

    public Integer getTackles() {
        return tackles;
    }

    public Integer getScores() {
        return scores;
    }

    public void setPerformance(Integer performance) {
        this.performance = performance;
    }
}
