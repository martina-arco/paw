package ar.edu.itba.model;

/**
 * Created by martina on 30/04/2018.
 */
public class Player {
    private String name;
    private Integer age, value, potential, skillLevel, goalkeeping, finish, defending, passing, fitness;
    private long id;
    private Contract contract;

    public Player(long id, String name, Integer age, Integer value, Integer potential, Integer skillLevel, Contract contract,
                  Integer goalkeeping, Integer finish, Integer defending, Integer passing, Integer fitness) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.value = value;
        this.potential = potential;
        this.skillLevel = skillLevel;
        this.goalkeeping = goalkeeping;
        this.finish = finish;
        this.defending = defending;
        this.passing = passing;
        this.fitness = fitness;
        this.contract = contract;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getValue() {
        return value;
    }

    public Integer getPotential() {
        return potential;
    }

    public Integer getSkillLevel() {
        return skillLevel;
    }

    public Integer getGoalkeeping() {
        return goalkeeping;
    }

    public Integer getFinish() {
        return finish;
    }

    public Integer getDefending() {
        return defending;
    }

    public Integer getPassing() {
        return passing;
    }

    public Integer getFitness() {
        return fitness;
    }

    public Contract getContract() {
        return contract;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setPotential(Integer potential) {
        this.potential = potential;
    }

    public void setSkillLevel(Integer skillLevel) {
        this.skillLevel = skillLevel;
    }

    public void setFitness(Integer fitness) {
        this.fitness = fitness;
    }

    public long getId() {
        return id;
    }

    public void increaseAge() {
        age++;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
