package ar.edu.itba.model.DTOs;

import ar.edu.itba.model.Player;

public class PlayerDTO {
    private final String name, team;
    private final int goalkeeping, defense, passing, finishing, skillLevel, value, salary, fitness, age, potential, position;
    private long id;

    public PlayerDTO(Player player){
        this(player.getId(), player.getName(), player.getTeam().getName(), player.getGoalKeeping(),
                player.getDefending(), player.getPassing(), player.getFinishing(), player.getSkillLevel(),
                player.getValue(), player.getSalary(), player.getFitness(), player.getAge(), player.getPotential(),
                player.getPosition());
    }

    public PlayerDTO(long id, String name, String team, int goalkeeping, int defense, int passing,
                     int finishing, int skillLevel, int value, int salary, int fitness, int age,
                     int potential, int position) {
        this.id = id;
        this.name = name;
        this.team = team;
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

    public String getTeam() {
        return team;
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
}
