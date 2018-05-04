package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.Formation;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.utils.Point;

import java.util.Map;

public interface FormationService {

    Formation create(Map<Player, Point> formation, int pressure, int attitude, Player captain, Player freeKickTaker, Player penaltyTaker);

    Formation findById(long id);

}
