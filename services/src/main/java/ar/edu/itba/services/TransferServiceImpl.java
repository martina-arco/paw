package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.PlayerDao;
import ar.edu.itba.interfaces.dao.TeamDao;
import ar.edu.itba.interfaces.service.*;
import ar.edu.itba.model.League;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.User;
import ar.edu.itba.model.PlayerFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransferServiceImpl implements TransferService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferServiceImpl.class);

    @Autowired
    private EconomyService economyService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private LeagueService leagueService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private PlayerDao playerDao;

    @Override
    public boolean performTransfer(User user, long playerId) {
        Player player = playerService.findById(playerId);
        return transferPlayer(player.getTeam(), user.getTeam(), player);
    }

    @Override
    public boolean transferPlayer(Team from, Team to, Player player) {
        from = teamService.findByIdAndFetchPlayersAndFinance(from.getId());
        to = teamService.findByIdAndFetchPlayersAndFinance(to.getId());

        if(to.getMoney() <= player.getValue())
            return false;

        player.setTeam(to);
        from.getPlayers().remove(player);
        to.getPlayers().add(player);
        playerDao.save(player);
        economyService.submitTransfer(from, to, player.getValue());
        teamDao.save(from);
        teamDao.save(to);
        return true;
    }

    @Override
    public List<Player> playersByCriteria(User user, Predicate<Player> criteria) {
        List<League> leagues = leagueService.findByUser(user);
        List<Player> players = new ArrayList<>();
        Team userTeam = teamService.findByUserId(user.getId());
        for(League league : leagues){
            List<Team> teams = teamService.findByLeagueAndFetchPlayers(league);
            for(Team team : teams){
                if(!team.equals(userTeam))
                    players.addAll(team.getPlayers().stream().filter(criteria).collect(Collectors.toList()));
            }
        }

        List<Player> ret = new ArrayList<>();
        for(Player player : players){
            ret.add(player);
        }

        return ret;
    }

    @Override
    public Predicate<Player> criteria(String filters) {
        Predicate<Player> ret = o -> true;
        if (!filters.isEmpty()) {
            try {
                String array[] = filters.split("&");
                for (int i = 0; i < array.length; i += 2) {
                    if (!array[i].split("=")[1].equals("Any")) {
                        PlayerFilter playerFilter = new PlayerFilter(array[i].split("=")[1], array[i + 1].split("=")[1]);
                        ret = ret.and(playerFilter.toPredicate());
                    }
                }
            } catch (Exception e) {
                LOGGER.debug("Filtro invalido: "+filters, e);
            }
        }
        return ret;
    }
}
