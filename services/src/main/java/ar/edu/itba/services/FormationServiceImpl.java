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
    public boolean save(Formation formation) {
        return formationDao.save(formation);
    }
}
