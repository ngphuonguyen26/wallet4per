package control;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.User;
import view.UserInfo;

public class UserInfoController {

    private UserInfo view;

    public UserInfoController(UserInfo view) {

        this.view = view;
        addEvents();
    }

    private void addEvents() {

        view.getButton_Exit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CardLayout cl =
                        (CardLayout) view.getMainChildForm().getLayout();

                cl.previous(view.getMainChildForm());

                view.getMainChildForm().remove(
                        view.getContentPanePanel()
                );

                view.getMainChildForm().revalidate();
                view.getMainChildForm().repaint();
            }
        });
    }
}