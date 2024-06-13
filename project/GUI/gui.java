import javax.swing.*;

import java.awt.*;
import java.lang.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


//input all caps
public class gui {
    static String key = "", id = "";
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            loginGUI loginGui = new loginGUI();
            loginGui.setVisible(true);
            loginGui.getLoginButton().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    key = loginGui.getUserKey();
                    id = loginGui.getUserId();
                    loginGui.dispose();
                    SwingUtilities.invokeLater(() -> {
                        mainGUI mainGui = new mainGUI();
                        mainGui.setVisible(true);
                    });
                }
            });
        });
        
    }
    
}



