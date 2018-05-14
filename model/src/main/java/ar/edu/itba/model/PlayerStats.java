package ar.edu.itba.model;

public class PlayerStats {

    private long id, playerId;
    private Player player;
    private int saves, performance, passes, assists, scores, yellowCards, redCards, tackles;

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

    public PlayerStats(long id, long playerId, int saves, int performance, int passes, int assists, int scores,
                       int yellowCards, int redCards, int tackles) {
        this.id = id;
        this.playerId = playerId;
        this.saves = saves;
        this.performance = performance;
        this.passes = passes;
        this.assists = assists;
        this.scores = scores;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.tackles = tackles;
    }

    public long getPlayerId() {
        if(player != null)
            return player.getId();
        return playerId;
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

    public int getSaves() {
        return saves;
    }

    public int getPerformance() {
        return performance;
    }

    public int getPasses() {
        return passes;
    }

    public int getAssists() {
        return assists;
    }

    public int getGoals() {
        return scores;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public int getRedCards() {
        return redCards;
    }

    public int getTackles() {
        return tackles;
    }

    public int getScores() {
        return scores;
    }

    public void setPerformance(int performance) {
        this.performance = performance;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
