package ar.edu.itba.model;

import ar.edu.itba.model.utils.Point;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "formation")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class Formation {

    public static final Collection<Point> availablePositions = new HashSet<>();

    public static void init(){
        //availablePositions.add(new Point());
    }

    public enum PlaysAs {
        STARTER, SUBSTITUTE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "formation_formationid_seq")
    @SequenceGenerator(sequenceName = "formation_formationid_seq", name = "formation_formationid_seq", allocationSize = 1)
    @Column(name = "formationid")
    private long id;

    @Transient
    private long captainId, freeKickTakerId, penaltyTakerId;

    @OneToOne
    @JoinColumn(name = "captain")
    private Player captain;

    @OneToOne
    @JoinColumn(name = "freeKickTaker")
    private Player freeKickTaker;

    @OneToOne
    @JoinColumn(name = "penaltyTaker")
    private Player penaltyTaker;

    @ElementCollection
    private Map<Player, Point> starters;

    @Transient
    private Map<Long, Point> startersIds;

    @OneToMany
    private List<Player> substitutes;

    @Transient
    private List<Long> substitutesIds;

    @Column(nullable = false)
    private int pressure, attitude;

    public Formation(){}

    public Formation(Player captain, Player freeKickTaker, Player penaltyTaker, Map<Player, Point> starters, List<Player> substitutes, int pressure, int attitude) {
        this.captain = captain;
        this.freeKickTaker = freeKickTaker;
        this.penaltyTaker = penaltyTaker;
        this.starters = starters;
        this.substitutes = substitutes;
        this.pressure = pressure;
        this.attitude = attitude;
    }

    public Formation(long id, Player captain, Player freeKickTaker, Player penaltyTaker, Map<Player, Point> starters, List<Player> substitutes, int pressure, int attitude) {
        this.id = id;
        this.captain = captain;
        this.freeKickTaker = freeKickTaker;
        this.penaltyTaker = penaltyTaker;
        this.starters = starters;
        this.substitutes = substitutes;
        this.pressure = pressure;
        this.attitude = attitude;
    }

    public Formation(long id, long captainId, long freeKickTakerId, long penaltyTakerId, Map<Long, Point> startersIds, List<Long> substitutesIds, int pressure, int attitude) {
        this.id = id;
        this.captainId = captainId;
        this.freeKickTakerId = freeKickTakerId;
        this.penaltyTakerId = penaltyTakerId;
        this.startersIds = startersIds;
        this.substitutesIds = substitutesIds;
        this.pressure = pressure;
        this.attitude = attitude;
    }

    public long getCaptainId() {
        if(captain != null)
            return captain.getId();
        return captainId;
    }

    public long getFreeKickTakerId() {
        if(freeKickTaker != null)
            return freeKickTaker.getId();
        return freeKickTakerId;
    }

    public long getPenaltyTakerId() {
        if(penaltyTaker != null)
            return penaltyTaker.getId();
        return penaltyTakerId;
    }

    public Map<Long, Point> getStartersIds() {
        if(starters != null) {
            Map<Long, Point> ids = new HashMap<>();
            for (Map.Entry<Player, Point> entry : starters.entrySet()) {
                ids.put(entry.getKey().getId(), entry.getValue());
            }
            return ids;
        }
        return startersIds;
    }

    public List<Long> getSubstitutesIds() {
        if(substitutes != null){
            List<Long> ids = new LinkedList<>();
            for (Player p : substitutes)
                ids.add(p.getId());
            return ids;
        }
        return substitutesIds;
    }

    public void setStarters(Map<Player, Point> starters) {
        this.starters = starters;
    }

    public void movePlayer(final Player p1, final Point position){
        if(availablePositions.contains(position))
            starters.put(p1,position);
    }

    public void replacePlayer(final Player p1, final Player p2){
        Point position = starters.get(p1);
        starters.remove(p1);
        starters.put(p2,position);
    }

    public Player getGk(){
        for (Map.Entry<Player, Point> e : starters.entrySet()) {
            if(e.getValue().getX() == 0 && e.getValue().getY() == 4)
                return e.getKey();
        }
        return null;
    }

    public Player getLb(){
        for (Map.Entry<Player, Point> e : starters.entrySet()) {
            if(e.getValue().getX() == 1 && e.getValue().getY() == 7)
                return e.getKey();
        }
        return null;
    }

    public Player getRb(){
        for (Map.Entry<Player, Point> e : starters.entrySet()) {
            if(e.getValue().getX() == 1 && e.getValue().getY() == 1)
                return e.getKey();
        }
        return null;
    }

    public Player getLcb(){
        for (Map.Entry<Player, Point> e : starters.entrySet()) {
            if(e.getValue().getX() == 1 && e.getValue().getY() == 5)
                return e.getKey();
        }
        return null;
    }

    public Player getCb(){
        for (Map.Entry<Player, Point> e : starters.entrySet()) {
            if(e.getValue().getX() == 1 && e.getValue().getY() == 4)
                return e.getKey();
        }
        return null;
    }

    public Player getRcb(){
        for (Map.Entry<Player, Point> e : starters.entrySet()) {
            if(e.getValue().getX() == 1 && e.getValue().getY() == 3)
                return e.getKey();
        }
        return null;
    }

    public Player getLm(){
        for (Map.Entry<Player, Point> e : starters.entrySet()) {
            if(e.getValue().getX() == 4 && e.getValue().getY() == 7)
                return e.getKey();
        }
        return null;
    }

    public Player getLcm(){
        for (Map.Entry<Player, Point> e : starters.entrySet()) {
            if(e.getValue().getX() == 4 && e.getValue().getY() == 5)
                return e.getKey();
        }
        return null;
    }

    public Player getCdm(){
        for (Map.Entry<Player, Point> e : starters.entrySet()) {
            if(e.getValue().getX() == 3 && e.getValue().getY() == 4)
                return e.getKey();
        }
        return null;
    }

    public Player getCam(){
        for (Map.Entry<Player, Point> e : starters.entrySet()) {
            if(e.getValue().getX() == 5 && e.getValue().getY() == 4)
                return e.getKey();
        }
        return null;
    }

    public Player getRcm(){
        for (Map.Entry<Player, Point> e : starters.entrySet()) {
            if(e.getValue().getX() == 4 && e.getValue().getY() == 3)
                return e.getKey();
        }
        return null;
    }

    public Player getRm(){
        for (Map.Entry<Player, Point> e : starters.entrySet()) {
            if(e.getValue().getX() == 4 && e.getValue().getY() == 1)
                return e.getKey();
        }
        return null;
    }

    public Player getLw(){
        for (Map.Entry<Player, Point> e : starters.entrySet()) {
            if(e.getValue().getX() == 7 && e.getValue().getY() == 7)
                return e.getKey();
        }
        return null;
    }

    public Player getLf(){
        for (Map.Entry<Player, Point> e : starters.entrySet()) {
            if(e.getValue().getX() == 7 && e.getValue().getY() == 5)
                return e.getKey();
        }
        return null;
    }

    public Player getSt(){
        for (Map.Entry<Player, Point> e : starters.entrySet()) {
            if(e.getValue().getX() == 7 && e.getValue().getY() == 4)
                return e.getKey();
        }
        return null;
    }

    public Player getRf(){
        for (Map.Entry<Player, Point> e : starters.entrySet()) {
            if(e.getValue().getX() == 7 && e.getValue().getY() == 3)
                return e.getKey();
        }
        return null;
    }

    public Player getRw(){
        for (Map.Entry<Player, Point> e : starters.entrySet()) {
            if(e.getValue().getX() == 7 && e.getValue().getY() == 1)
                return e.getKey();
        }
        return null;
    }

    public List<Integer> getPossibleFormations(){
        List<Integer> list = new LinkedList<>();
        list.add(343);
        list.add(352);
        list.add(433);
        list.add(442);
        list.add(451);
        list.add(523);
        list.add(532);
        list.add(541);

        return list;
    }

    public int getFormationPositions(){
        int defenders = 0, mids = 0, forwards = 0;
        int position;
        for (Player p: starters.keySet()) {
            position = p.getPosition();
            if(position == 1)
                defenders++;
            else if(position == 2)
                mids++;
            else if(position == 3)
                forwards++;
        }
        return defenders*100 + mids*10 + forwards;
    }

    public enum Pressures {
        Low, Medium, High
    }

    public List<Integer> getPossiblePressures(){
        List<Integer> list = new LinkedList<>();
        list.add(Pressures.Low.ordinal());
        list.add(Pressures.Medium.ordinal());
        list.add(Pressures.High.ordinal());

        return list;
    }

    public enum Attitudes {
        Defensive, Balanced, Offensive
    }

    public List<Integer> getPossibleAttitudes(){
        List<Integer> list = new LinkedList<>();
        list.add(Attitudes.Defensive.ordinal());
        list.add(Attitudes.Balanced.ordinal());
        list.add(Attitudes.Offensive.ordinal());

        return list;
    }

    //Solo sirve para 442 por ahora
    public String getPlayerPosition(long playerId) {

        if(startersIds.containsKey(playerId)) {
            switch (startersIds.get(playerId).getY()) {
                case 0://Arquero
                    return "GK";
                case 1://Defensor
                    switch (startersIds.get(playerId).getX()) {
                        case 0:
                            return "LB";
                        case 1:
                            return "LCB";
                        case 2:
                            return "RCB";
                        case 3:
                            return "RB";
                    }
                case 2://Volante
                    switch (startersIds.get(playerId).getX()) {
                        case 0:
                            return "LM";
                        case 1:
                            return "LCM";
                        case 2:
                            return "RCM";
                        case 3:
                            return "RM";
                    }
                case 3://Delantero
                    switch (startersIds.get(playerId).getX()) {
                        case 0:
                            return "LF";
                        case 1:
                            return "RF";
                    }
            }
        }
        else if(substitutesIds.contains(playerId)) {
            return "SUB";
        }
        return "RES";
    }

    public void setStartersIds(Map<Long, Point> startersIds) {
        this.startersIds = startersIds;
    }

    public void setSubstitutesIds(List<Long> substitutesIds) {
        this.substitutesIds = substitutesIds;
    }

    public Map<Player, Point> getStarters() {
        return starters;
    }

    public List<Player> getSubstitutes() {
        return substitutes;
    }

    public List<Player> getReserves(List<Player> players){
        List<Player> reserves = new ArrayList<>();
        for (Player p : players) {
            if(!starters.containsValue(p) && !substitutes.contains(p))
                reserves.add(p);
        }
        return reserves;
    }

    public void setSubstitutes(List<Player> substitutes) {
        this.substitutes = substitutes;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getAttitude() {
        return attitude;
    }

    public void setAttitude(int attitude) {
        this.attitude = attitude;
    }

    public Player getCaptain() {
        return captain;
    }

    public void setCaptain(Player captain) {
        this.captain = captain;
    }

    public Player getFreeKickTaker() {
        return freeKickTaker;
    }

    public void setFreeKickTaker(Player freeKickTaker) {
        this.freeKickTaker = freeKickTaker;
    }

    public Player getPenaltyTaker() {
        return penaltyTaker;
    }

    public void setPenaltyTaker(Player penaltyTaker) {
        this.penaltyTaker = penaltyTaker;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
