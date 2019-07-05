package ar.edu.itba.webapp.model.DTOs;

import ar.edu.itba.model.Player;

public class PlayerDTO {
    private String name;
    private int goalkeeping, defense, passing, finishing, skillLevel, value, salary, fitness, age, potential, position;
    private long id, teamId;

    public PlayerDTO(Player player){
        this(player.getId(), player.getName(), player.getTeamId(), player.getGoalKeeping(),
                player.getDefending(), player.getPassing(), player.getFinishing(), player.getSkillLevel(),
                player.getValue(), player.getSalary(), player.getFitness(), player.getAge(), player.getPotential(),
                player.getPosition());
    }

    public PlayerDTO(long id, String name, long teamId, int goalkeeping, int defense, int passing,
                     int finishing, int skillLevel, int value, int salary, int fitness, int age,
                     int potential, int position) {
        this.id = id;
        this.name = name;
        this.teamId = teamId;
        this.value = value;
        this.goalkeeping = goalkeeping;
        this.defense = defense;
        this.passing = passing;
        this.finishing = finishing;
        this.skillLevel = skillLevel;
        this.salary = salary;
        this.fitness = fitness;
        this.age = age;
        this.potential = potential;
        this.position = position;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public long getTeamId() {
        return teamId;
    }

    public int getGoalkeeping() {
        return goalkeeping;
    }

    public int getDefense() {
        return defense;
    }

    public int getPassing() {
        return passing;
    }

    public int getFinishing() {
        return finishing;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public int getSalary() {
        return salary;
    }

    public int getFitness() {
        return fitness;
    }

    public int getAge() {
        return age;
    }

    public int getPotential() {
        return potential;
    }

    public long getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGoalkeeping(int goalkeeping) {
        this.goalkeeping = goalkeeping;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setPassing(int passing) {
        this.passing = passing;
    }

    public void setFinishing(int finishing) {
        this.finishing = finishing;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPotential(int potential) {
        this.potential = potential;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }
}
