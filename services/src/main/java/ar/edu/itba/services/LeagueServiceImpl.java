package ar.edu.itba.services;

import ar.edu.itba.interfaces.service.LeagueService;
import ar.edu.itba.model.League;
import org.springframework.stereotype.Service;

@Service
public class LeagueServiceImpl implements LeagueService {

    @Override
    public League findByUserId(long userid) {
        return null;
    }
}
