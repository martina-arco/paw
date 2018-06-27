package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.StadiumDao;
import ar.edu.itba.interfaces.service.EconomyService;
import ar.edu.itba.interfaces.service.StadiumService;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Receipt;
import ar.edu.itba.model.Stadium;
import ar.edu.itba.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class StadiumServiceImpl implements StadiumService {

    @Autowired
    private StadiumDao stadiumDao;

    @Autowired
    private EconomyService economyService;

    @Override
    public Stadium findByTeam(Team team) {
        return stadiumDao.findByTeamId(team.getId());
    }

    @Override
    public boolean upgradeStadium(Team team, int lowClass, int mediumClass, int highClass){
        Stadium stadium = findByTeam(team);
        int deltaLow =  lowClass - stadium.getLowClass();
        int deltaMid = mediumClass - stadium.getMediumClass();
        int deltaHigh = highClass - stadium.getHighClass();

        if(deltaHigh < 0 || deltaMid < 0 || deltaLow < 0)
            return false;

        if(lowClass > 1e6 || mediumClass > 1e6 || highClass > 1e6)
            return false;

        int expense = deltaLow*Stadium.getLowCost() + deltaMid*Stadium.getMediumCost() + deltaHigh*Stadium.getHighCost();

        if(expense > team.getMoney())
            return false;

        if(deltaLow + deltaMid + deltaHigh == 0)
            return true;

        stadium.addSeats(Stadium.SeatType.LOW, deltaLow);
        stadium.addSeats(Stadium.SeatType.MEDIUM, deltaMid);
        stadium.addSeats(Stadium.SeatType.HIGH, deltaHigh);

        stadiumDao.save(stadium);
        economyService.submitReceipt(team, Receipt.Type.EXPANDEDSTADIUM, -expense);

        return true;
    }

    @Override
    public void setStadium(Set<Team> teams) {
        //Not necessary with hibernate, default is EAGER
    }

    @Override
    public void setStadium(List<Match> matches) {
        //Not necessary with hibernate, default is EAGER
    }
}
