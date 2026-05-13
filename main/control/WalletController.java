package main.control;

import java.util.List;

import main.dao.WalletDAO;

public class WalletController {

    private final main.view.Wallet view;
    private final WalletDAO walletDAO;
    private final int userId;

    public WalletController(main.view.Wallet view, int userId) {
        this.view = view;
        this.userId = userId;
        this.walletDAO = new WalletDAO();

        loadWallets();
    }

    public void loadWallets() {
        view.getModel_Wallet().setRowCount(0);

        List<model.Wallet> wallets = walletDAO.getWalletsByUser(userId);

        for (model.Wallet w : wallets) {
            view.getModel_Wallet().addRow(new Object[]{
                    w.getWalletId(),
                    w.getWalletName(),
                    w.getWalletType(),
                    w.getBalance()
            });
        }
    }
}