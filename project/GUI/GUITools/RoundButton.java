package project.GUI.GUITools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;


/**
 * 圓框按鈕
 * @author IalvinchangI
 */
public class RoundButton extends JButton {
    
    /** 背景色 */
    private Color backgroundColor = null;
    /** 背景色 */
    private Color borderColor = null;
    /** 背景色 */
    private Color clickBackgroundColor = null;
    /** 背景色 */
    private Color clickBorderColor = null;


    private String text = null;


    /** 框的邊角圓弧 */
    public int arcWidth = 30, arcHeight = 30;

    /**  設定框的邊角圓弧 */
    public void setArc(int width, int height) {
        this.arcWidth = width;
        this.arcHeight = height;
        // repaint();
    }


     /**
     * 設定圓框按鈕
     * @param text                  顯示的字
     * @param bottomLayerColor      圓框按鈕的下面那層的顏色
     * @param backgroundColor       圓框按鈕的背景色
     * @param borderColor           邊框的背景色
     * @param clickBackgroundColor  按下後，圓框按鈕的背景色
     * @param clickBorderColor      按下後，邊框的背景色
     */
    public RoundButton(String text, Color bottomLayerColor, Color backgroundColor, Color borderColor, Color clickBackgroundColor, Color clickBorderColor) {
        super(text);
        this.setBackground(bottomLayerColor);
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.clickBackgroundColor = clickBackgroundColor;
        this.clickBorderColor = clickBorderColor;

        this.text = text;

        // setContentAreaFilled(false);  // 不繪製按鈕區域的內容
        setFocusPainted(false);  // 不繪製焦點框
        setBorderPainted(false);  // 不繪製按鈕邊框
    }

    /**
     * 設定圓框按鈕
     * @param text              顯示的字
     * @param bottomLayerColor  圓框按鈕的下面那層的顏色
     */
    public RoundButton(String text, Color bottomLayerColor) {
        this(text, bottomLayerColor, new Color(133, 205, 144), new Color(86, 171, 99), new Color(133, 205, 144), new Color(182, 226, 189));
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        
        g2d.setStroke(new BasicStroke(4));

        // background and border
        if (this.getModel().isArmed()) {
            g2d.setColor(this.clickBackgroundColor);
            g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
            g2d.setColor(this.clickBorderColor);
            g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
        } else {
            g2d.setColor(this.backgroundColor);
            g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
            g2d.setColor(this.borderColor);
            g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
        }

        int textWidth = this.text.length() * 2;
        g2d.drawString(this.text, getWidth() / 2 - textWidth - 3, getHeight() / 2 + 3);
        
        // show
        g2d.dispose();
    }
}
