package project.GUI;

import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * 跳出帳密錯誤的對話框
 * @author Hanklo831
 */
public class WrongInfo extends  JDialog {
    
    private JLabel wrong = null;

    public WrongInfo(Frame father) {
        super(father, "Error!!!", false);
        this.wrong = new JLabel();
        this.setLocationRelativeTo(father);
        this.setPreferredSize(new Dimension(350, 250));

        this.wrong = new JLabel("WRONG KEY OR ID!", SwingConstants.CENTER);
        this.add(this.wrong);
        
    }
}
