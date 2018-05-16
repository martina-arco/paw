package ar.edu.itba.interfaces.dao;

import ar.edu.itba.model.Stadium;
import ar.edu.itba.model.Team;

public interface StadiumDao {

    Stadium create(String name, Team team, int lowClass, int lowClassPrice, int mediumClass, int mediumClassPrice, int highClass, int highClassPrice);

    Stadium create(String name, long team, int lowClass, int lowClassPrice, int mediumClass, int mediumClassPrice, int highClass, int highClassPrice);

    boolean save(Stadium stadium);

    Stadium findById(long id);

    Stadium findByTeamId(long id);

}
