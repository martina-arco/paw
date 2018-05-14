package ar.edu.itba.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Match {

    private long id, homeId, awayId, leagueId;
    private Team home, away;
    private League league;
    private Date day;
    private int homeScore, awayScore, homePoints, awayPoints;
    private List<PlayerStats> stats;
    private boolean played;
    private List<Event> events;

    public Match(long id, Team home, Team away, League league, Date day, int homeScore, int awayScore, int homePoints,
                 int awayPoints, List<PlayerStats> stats, boolean played, List<Event> events) {
        this.id = id;
        this.home = home;
        this.away = away;
        this.league = league;
        this.day = day;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.homePoints = homePoints;
        this.awayPoints = awayPoints;
        this.stats = stats;
        this.played = played;
        this.events = events;
    }

    public Match(long id, long homeId, long awayId, long leagueId, Date day, int homeScore, int awayScore,
                 int homePoints, int awayPoints, boolean played) {
        this.id = id;
        this.homeId = homeId;
        this.awayId = awayId;
        this.leagueId = leagueId;
        this.day = day;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.homePoints = homePoints;
        this.awayPoints = awayPoints;
        this.played = played;
    }

    public void finish() {
        if(homeScore > awayScore)
            homePoints = 3;
        else if(awayScore > homeScore)
            awayPoints = 3;
        else {
            homePoints = 1;
            awayPoints = 1;
        }

        played = true;
    }

    public long getLeagueId() {
        if(league != null)
            return league.getId();
        return leagueId;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public long getHomeId() {
        if(home != null)
            return home.getId();
        return homeId;
    }

    public long getAwayId() {
        if(away != null)
            return away.getId();
        return awayId;
    }

    @Deprecated
    public List<Long> getStatsIds() {
        if(stats != null) {
            List<Long> ids = new LinkedList<>();
            for (PlayerStats ps : stats)
                ids.add(ps.getId());
            return ids;
        }
        return null;
    }

    @Deprecated
    public List<Long> getEventsIds() {
        if(events != null) {
            List<Long> ids = new LinkedList<>();
            for (Event e : events)
                ids.add(e.getId());
            return ids;
        }
        return null;
    }

    public void addHomeScore (int amount) {
        homeScore += amount;
    }

    public void addAwayScore(int amount) {
        awayScore += amount;
    }

    public void addStats(PlayerStats stat) {
        stats.add(stat);
    }

    public void setHome(Team home) {
        this.home = home;
    }

    public void setAway(Team away) {
        this.away = away;
    }

    public void setStats(List<PlayerStats> stats) {
        this.stats = stats;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void addEvent(Event e) {
        events.add(e);
    }

    public Team getHome() {
        return home;
    }

    public Team getAway() {
        return away;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public int getHomePoints() {
        return homePoints;
    }

    public int getAwayPoints() {
        return awayPoints;
    }

    public List<PlayerStats> getStats() {
        return stats;
    }

    public Boolean isPlayed() {
        return played;
    }

    public List<Event> getEvents() {
        return events;
    }

    public long getId() {
        return id;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    //
//    private Event addScore(long id, Player p, int minute) {
//        Event event = new Event(id, p, SCORE, minute);
//
//        if(p.getTeam().equals(home))
//            homeScore++;
//        else
//            awayScore++;
//
//        stats.get(p).addScore();
//        events.add(event);
//
//        return event;
//    }
//
//    private Event addYellowCard(long id, Player p, int minute){
//        Event event = new Event(id, p, YELLOW_CARD, minute);
//
//        stats.get(p).addYellowCard();
//        events.add(event);
//
//        return event;
//    }
//
//    private Event addRedCard(long id, Player p, int minute){
//        Event event = new Event(id, p, RED_CARD, minute);
//
//        stats.get(p).addRedCard();
//        events.add(event);
//
//        return event;
//    }
//
//    private Event addEvent(long id, Player p, EventType type, int minute){
//
//        switch (type) {
//            case RED_CARD:
//                return addRedCard(id, p, minute);
//            case SCORE:
//                return addScore(id, p, minute);
//            case YELLOW_CARD:
//                return addYellowCard(id, p, minute);
//            default:
//                break;
//        }
//
//        return null;
//    }
//
//    public Event addEvent(long id, Player p1, Player p2, EventType type, int minute) {
//
//        if(p2 == null)
//            addEvent(id, p1, type, minute);
//
//        if(type != SUBSTITUTE)
//            return null;
//
//        Event event = new Event(id, p1, p2, SUBSTITUTE, minute);
//        events.add(event);
//
//        return event;
//    }
//
//    public Event changeStats(Player p, EventType type, int amount) {
//
//        switch (type) {
//            case ASSIST:
//                stats.get(p).addAssist(amount);
//                break;
//            case PASS:
//                stats.get(p).addPass(amount);
//                break;
//            case SAVE:
//                stats.get(p).addSave(amount);
//                break;
//            case TACKLE:
//                stats.get(p).addTackle(amount);
//                break;
//            default:
//                break;
//        }
//
//        return null;
//    }
//
//    public void createStats(PlayerStats playerStats) {
//        stats.put(playerStats.getPlayer(), playerStats);
//    }
}
