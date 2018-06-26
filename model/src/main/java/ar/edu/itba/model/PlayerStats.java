package ar.edu.itba.model;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "playerstats")
public class PlayerStats {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playerstats_playerstatsid_seq")
    @SequenceGenerator(sequenceName = "playerstats_playerstatsid_seq", name = "playerstats_playerstatsid_seq", allocationSize = 1)
    @Column(name = "playerstatsid")
    private long id;

    @Transient
    private long matchId, playerId;

    @ManyToOne
    @JoinColumn(name = "match")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "player")
    private Player player;

    private int saves, performance, passes, assists, tackles;

    @Transient
    private List<Event> events;

    @Transient
    private int scores, yellowCards, redCards;


    private void postLoad() {
        for (Event e : events) {
            switch (e.getType()) {
                case HOMESCORE:
                    scores++;
                    break;
                case AWAYSCORE:
                    scores++;
                    break;
                case RED_CARD:
                    redCards++;
                    break;
                case YELLOW_CARD:
                    yellowCards++;
                    break;
            }
        }
    }


    public PlayerStats(){}

    public PlayerStats(Match match, Player player, int saves, int performance, int passes, int assists, int tackles,
                       int scores, int yellowCards, int redCards) {
        this.match = match;
        this.player = player;
        this.saves = saves;
        this.performance = performance;
        this.passes = passes;
        this.assists = assists;
        this.tackles = tackles;
        this.scores = scores;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
    }

    public PlayerStats(long id, Match match, Player player, int saves, int performance, int passes, int assists,
                       int scores, int yellowCards, int redCards, int tackles) {
        this.id = id;
        this.match = match;
        this.player = player;
        this.saves = saves;
        this.performance = performance;
        this.passes = passes;
        this.assists = assists;
        this.scores = scores;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.tackles = tackles;
    }

    public PlayerStats(long id, long matchId, long playerId, int saves, int performance, int passes, int assists,
                       int scores, int yellowCards, int redCards, int tackles) {
        this.id = id;
        this.matchId = matchId;
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

    public long getMatchId() {
        if(match != null)
            return match.getId();
        return matchId;
    }

    public long getPlayerId() {
        if(player != null)
            return player.getId();
        return playerId;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public void setSaves(int saves) {
        this.saves = saves;
    }

    public void setPasses(int passes) {
        this.passes = passes;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public void setScores(int scores) {
        this.scores = scores;
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

    public void setEvents(List<Event> events) {
        this.events = events;
        scores = 0;
        yellowCards = 0;
        redCards = 0;
        postLoad();
    }
}
