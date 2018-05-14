package ar.edu.itba.model;

import java.util.Date;

public class Player {

    private long id, teamId;
    private Team team;
    private String name;
    private int age, value, potential, skillLevel, goalKeeping, finish, defending, passing, fitness, salary;
    private Date contractExpiration;
    private boolean youth;

    public Player(long id, Team team, String name, int age, int value, int potential, int skillLevel, int goalKeeping,
                  int finish, int defending, int passing, int fitness, int salary, Date contractExpiration, boolean youth) {
        this.id = id;
        this.team = team;
        this.name = name;
        this.age = age;
        this.value = value;
        this.potential = potential;
        this.skillLevel = skillLevel;
        this.goalKeeping = goalKeeping;
        this.finish = finish;
        this.defending = defending;
        this.passing = passing;
        this.fitness = fitness;
        this.salary = salary;
        this.contractExpiration = contractExpiration;
        this.youth = youth;
    }

    public Player(long id, long teamId, String name, int age, int value, int potential, int skillLevel, int goalkeeping,
                  int finish, int defending, int passing, int fitness, int salary, Date contractExpiration, boolean youth) {
        this.id = id;
        this.teamId = teamId;
        this.name = name;
        this.age = age;
        this.value = value;
        this.potential = potential;
        this.skillLevel = skillLevel;
        this.goalKeeping = goalkeeping;
        this.finish = finish;
        this.defending = defending;
        this.passing = passing;
        this.fitness = fitness;
        this.salary = salary;
        this.contractExpiration = contractExpiration;
        this.youth = youth;
    }

    public long getTeamId() {
        if(team != null)
            return team.getId();
        return teamId;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGoalKeeping(int goalKeeping) {
        this.goalKeeping = goalKeeping;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public void setDefending(int defending) {
        this.defending = defending;
    }

    public void setPassing(int passing) {
        this.passing = passing;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setContractExpiration(Date contractExpiration) {
        this.contractExpiration = contractExpiration;
    }

    public boolean isYouth() {
        return youth;
    }

    public void setYouth(boolean youth) {
        this.youth = youth;
    }

    public int getSalary() {
        return salary;
    }

    public Date getContractExpiration() {
        return contractExpiration;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getValue() {
        return value;
    }

    public int getPotential() {
        return potential;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public int getGoalKeeping() {
        return goalKeeping;
    }

    public int getFinish() {
        return finish;
    }

    public int getDefending() {
        return defending;
    }

    public int getPassing() {
        return passing;
    }

    public int getFitness() {
        return fitness;
    }

    public Team getTeam() {
        return team;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setPotential(int potential) {
        this.potential = potential;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public long getId() {
        return id;
    }

    public void increaseAge() {
        age++;
    }
}
