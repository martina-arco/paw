package ar.edu.itba.interfaces.dao;

import ar.edu.itba.model.Receipt;
import ar.edu.itba.model.Team;

import java.util.List;


public interface ReceiptDao {

    Receipt create(Team t, int amount, Receipt.Type type);
    Receipt create(long t, int amount, Receipt.Type type);

    List<Receipt> findAllByTeamId(long id);
}
