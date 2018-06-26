package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.Receipt;
import ar.edu.itba.model.Team;

public interface EconomyService {
    void submitTransfer(Team from, Team to, int amount);

    void submitReceipt(Team recv, Receipt.Type type, int amount);
}
