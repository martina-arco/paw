package ar.edu.itba.interfaces.dao;

import ar.edu.itba.model.Receipt;
import ar.edu.itba.model.Team;

import java.util.List;

/**
 * Created by martina on 01/05/2018.
 */
public interface ReceiptDao {

    Receipt create(Team t, int amount, Receipt.ReceiptType type);
    List<Receipt> findReceiptbyTeam(Team t);
}
