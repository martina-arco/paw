package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.FormationDao;
import ar.edu.itba.interfaces.service.FormationService;
import ar.edu.itba.model.Formation;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.utils.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class FormationServiceImpl implements FormationService {

    @Autowired
    private FormationDao formationDao;

    @Override
    @Transactional
    public Formation create(Map<Player, Point> starters, List<Player> substitutes, int pressure, int attitude, Player captain, Player freeKickTaker, Player penaltyTaker) {
        return formationDao.create(starters, substitutes, pressure, attitude, captain, freeKickTaker, penaltyTaker);
    }

    @Override
    public Formation findById(long id) {
        return formationDao.findById(id);
    }

    @Override
    public Formation save(Formation formation) {
        return formationDao.save(formation);
    }

    @Override
    public boolean isValid(Formation formation) {
        if(formation.getStarters().size() != 11)
            return false;
        if(formation.getSubstitutes().size() != 7)
            return false;
        for(Map.Entry<Player, Point> entry : formation.getStarters().entrySet()){
            for(Player player : formation.getSubstitutes()){
                if(player.equals(entry.getKey()))
                    return false;
            }
        }
        Set<Player> starters = formation.getStarters().keySet();
        if(!starters.contains(formation.getCaptain()))
            return false;
        if(!starters.contains(formation.getFreeKickTaker()))
            return false;
        if(!starters.contains(formation.getPenaltyTaker()))
            return false;

        return formation.getCaptain() != null && formation.getFreeKickTaker() != null && formation.getPenaltyTaker() != null;
    }
}
