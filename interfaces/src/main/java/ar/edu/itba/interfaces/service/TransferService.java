package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.Player;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.User;

import java.util.List;
import java.util.function.Predicate;

public interface TransferService {
    boolean performTransfer(User user, long playerId);

    boolean transferPlayer(Team from, Team to, Player player);

    List<Player> playersByCriteria(User user, Predicate<Player> criteria);

    Predicate<Player> criteria(String filters);
}
