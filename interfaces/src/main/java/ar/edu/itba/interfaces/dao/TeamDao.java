package ar.edu.itba.interfaces.dao;

import ar.edu.itba.model.*;

import java.util.List;

public interface TeamDao {
    Team create(String name, League league, Stadium stadium, Formation formation, List<Player> players, YouthAcademy youthAcademy, Integer fanTrust, Integer boardTrust, List<Receipt> finance, Integer money);

    Team findById(long id);
}