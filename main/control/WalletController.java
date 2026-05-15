package control;

import dao.WalletDAO;
import java.util.List;

public class WalletController {

    private final view.Wallet view;
    private final WalletDAO walletDAO;
    private final int userId;

    public WalletController(view.Wallet view, int userId) {
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
                    w.getWalletType().getDisplayName(),
                    w.getBalance()
            });
        }
    }
}