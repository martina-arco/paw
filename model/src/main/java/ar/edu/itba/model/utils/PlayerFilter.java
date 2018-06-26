package ar.edu.itba.model.utils;

import ar.edu.itba.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class PlayerFilter {
    enum Criteria {
        AGE, VALUE, SALARY, GOALKEEPING, DEFENSE, PASS, FINISH, SKILL, POTENTIAL
    }

    enum Condition {
        LESSTHAN, EQUALS, MORETHAN
    }

    public static List<String> values(){
        List<String> ret = new ArrayList<>();
        Criteria values[] = Criteria.values();
        for(Criteria criteria : values){
            ret.add(criteria.toString());
        }
        return ret;
    }

    private Criteria criteria;
    private Condition condition;
    private int parameter;

    private String type, name;
    private List<String> options;

    public PlayerFilter(String criteria, String parameter){
        this(Criteria.valueOf(criteria.split("%3B")[0]),Condition.valueOf(criteria.split("%3B")[1]), Integer.valueOf(parameter));
    }

    public PlayerFilter(Criteria criteria, Condition condition, int parameter){
        this.type = "number";
        this.name = criteria.name();
        this.options = new ArrayList<>();
        options.add("Equals");
        options.add("Greater than");
        options.add("Less than");
        this.criteria = criteria;
        this.condition = condition;
        this.parameter = parameter;
    }

    public Predicate<Player> toPredicate(){
        switch (criteria){
            case AGE:
                return player -> evaluate(player.getAge());
            case VALUE:
                return player -> evaluate(player.getValue());
            case PASS:
                return player -> evaluate(player.getPassing());
            case GOALKEEPING:
                return player -> evaluate(player.getGoalKeeping());
            case FINISH:
                return player -> evaluate(player.getFinishing());
            case SALARY:
                return player -> evaluate(player.getSalary());
            case DEFENSE:
                return player -> evaluate(player.getDefending());
            case SKILL:
                return player -> evaluate(player.getSkillLevel());
            case POTENTIAL:
                return player -> evaluate(player.getPotential());
            default:
                return null;
        }
    }

    private boolean evaluate(int playerParam){
        if (condition == Condition.LESSTHAN)
            return playerParam < parameter;
        if (condition == Condition.EQUALS)
            return playerParam == parameter;
        return playerParam > parameter;
    }
}
