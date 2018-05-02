package ar.edu.itba.model;

import ar.edu.itba.model.utils.Date;

public class Contract {
    private final Team team;
    private final Integer salary;
    private final Date term;
    private final long id;

    public Contract(long id, Team team, Integer salary, Date term) {
        this.team = team;
        this.salary = salary;
        this.term = term;
        this.id = id;
    }

    //Esto va en Service? @lemery
    public boolean isDone(Date date){
        return term.compareTo(date) < 0;
    }

    public Date getTerm() {
        return term;
    }

    public Integer getSalary() {
        return salary;
    }

    public Team getTeam() {
        return team;
    }

    public long getId() {
        return id;
    }
}
