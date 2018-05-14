package ar.edu.itba.interfaces.dao;

import ar.edu.itba.model.Player;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.YouthAcademy;

import java.util.List;

@Deprecated
public interface YouthAcademyDao {

    YouthAcademy create(Team team, List<Player> players);

    YouthAcademy findById(long id);
}
