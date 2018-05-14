package ar.edu.itba.interfaces.dao;

import ar.edu.itba.model.Formation;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.utils.Point;

import java.util.List;
import java.util.Map;

/**
 * Created by martina on 02/05/2018.
 */
public interface FormationDao {

    Formation create(Map<Player, Point> starters, List<Player> substitutes, int pressure, int attitude, Player captain, Player freeKickTaker, Player penaltyTaker);

    boolean save(Formation formation);

    Formation findById(long id);

}
