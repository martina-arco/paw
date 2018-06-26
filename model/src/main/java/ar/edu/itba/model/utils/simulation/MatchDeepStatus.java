package ar.edu.itba.model.utils.simulation;

import ar.edu.itba.model.Match;
import ar.edu.itba.model.utils.Point;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "matchstate")
public class MatchDeepStatus {

    @Embeddable
    private static class State {

        private Point position;

        private int minute;

        @Enumerated(value = EnumType.STRING)
        private MyTeam team;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "matchstate_matchstateid_seq")
    @SequenceGenerator(sequenceName = "matchstate_matchstateid_seq", name = "matchstate_matchstateid_seq", allocationSize = 1)
    @Column(name = "matchstateid")
    private long id;

    @OneToOne
    @JoinColumn(name = "match", nullable = false)
    private Match match;

    @ElementCollection
    @CollectionTable(name = "matchstate_states", joinColumns = @JoinColumn(name = "matchstate"))
    private List<State> states;
    
    public MatchDeepStatus(){}

    public MatchDeepStatus(Match match){
        this.match = match;
        states = new ArrayList<>();
    }

    public void registerState(Point pos, int minute, MyTeam team){
        State ret = new State();
        ret.position = new Point(pos.getX(), pos.getY());
        ret.minute = minute;
        ret.team = team;
        states.add(minute, ret);
    }

    public Point positionByMinute(int minute){
        return states.get(minute).position;
    }

    public MyTeam ownerByMinute(int minute){
        return states.get(minute).team;
    }

    public Match getMatch() {
        return match;
    }
}
