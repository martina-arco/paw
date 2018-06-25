package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.ReceiptDao;
import ar.edu.itba.interfaces.service.EconomyService;
import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.model.Receipt;
import ar.edu.itba.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EconomyServiceImpl implements EconomyService {

    @Autowired
    private ReceiptDao receiptDao;

    @Autowired
    private TeamService teamService;

    @Override
    public void submitTransfer(Team from, Team to, int amount) {
        submitReceipt(from, Receipt.Type.SOLDPLAYER, amount);
        submitReceipt(to, Receipt.Type.BOUGHTPLAYER, -amount);
    }

    @Override
    public void submitReceipt(Team recv, Receipt.Type type, int amount) {
        teamService.setFinance(recv);
        recv.addReceipt(receiptDao.create(recv, amount, type));
        recv.addMoney(amount);
    }


}
