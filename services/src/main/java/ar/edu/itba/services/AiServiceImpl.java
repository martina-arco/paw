package ar.edu.itba.services;

import ar.edu.itba.interfaces.service.AiService;
import ar.edu.itba.model.Formation;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.utils.Point;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AiServiceImpl implements AiService {

    @Override
    public Formation getFormation(List<Player> players) {

        Map<Player,Point> starters = new HashMap<>();
        List<Player> tired = new ArrayList<>();
        Player captain, fkTaker, penaltyTaker;

        for(Player player : players){
            if(player.getFitness() < 65)
                tired.add(player);
        }

        players.removeAll(tired);

        tired.sort((player, t1) -> player.getFitness() - t1.getFitness());

        players.sort((player, t1) -> player.getAge() - t1.getAge());
        captain = useLast(players,false);

        players.sort((player, t1) -> player.getFinish() - t1.getFinish());
        penaltyTaker = useLast(players, false);

        players.sort((player, t1) -> player.getPassing() - t1.getPassing());
        fkTaker = useLast(players, false);

        players.sort((player, t1) -> player.getGoalKeeping() - t1.getGoalKeeping());
        starters.put(useLast(players, true), new Point(0,4));

        players.sort((player, t1) -> player.getDefending() - t1.getDefending());
        for (int i = 0; i < 4; i++) {
            starters.put(useLast(players, true), new Point(1, 1 + 2*i));
        }

        players.sort((player, t1) -> player.getPassing() - t1.getPassing());
        starters.put(useLast(players,true), new Point(5, 4));
        starters.put(useLast(players,true), new Point(4, 1));
        starters.put(useLast(players,true), new Point(4, 7));
        starters.put(useLast(players,true), new Point(3, 4));

        players.sort((player, t1) -> player.getFinish() - t1.getFinish());
        starters.put(useLast(players,true), new Point(7,3));
        starters.put(useLast(players,true), new Point(7, 5));

        return new Formation(0,captain,fkTaker,penaltyTaker,starters,tired,50,50);
    }

    private Player useLast(List<Player> list, boolean remove){
        Player ret = list.get(list.size() - 1);
        if(remove)
            list.remove(list.size() - 1);
        return ret;
    }
}
