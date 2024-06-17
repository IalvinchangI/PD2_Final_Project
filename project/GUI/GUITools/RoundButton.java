package project.GUI.GUITools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;


/**
 * 圓框按鈕
 * @author IalvinchangI
 */
public class RoundButton extends JButton {
    
    /** 圓框按鈕的下面那層的顏色 */
    private Color bottomLayerColor = null;
    /** 圓框按鈕的背景色 */
    private Color backgroundColor = null;
    /** 邊框的背景色 */
    private Color borderColor = null;
    /** 按下後，圓框按鈕的背景色 */
    private Color clickBackgroundColor = null;
    /** 按下後，邊框的背景色 */
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
        this.text = text;
        this.setBackground(bottomLayerColor);
        this.bottomLayerColor = bottomLayerColor;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.clickBackgroundColor = clickBackgroundColor;
        this.clickBorderColor = clickBorderColor;

        this.text = text;

        setContentAreaFilled(false);
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


    /** 畫框時，要往內縮的距離 */
    private final static int PAINT_CENTER_INDENTATION = 2;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        
        g2d.setStroke(new BasicStroke(4));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // background
        if (this.getModel().isArmed()) {
            g2d.setColor(this.backgroundColor);
        } else if (this.getModel().isRollover()) {
            g2d.setColor(this.clickBackgroundColor);
        } else {
            g2d.setColor(this.backgroundColor);
        }
        g2d.fillRoundRect(
            PAINT_CENTER_INDENTATION, PAINT_CENTER_INDENTATION, 
            getWidth() - PAINT_CENTER_INDENTATION * 2 - 1, getHeight() - PAINT_CENTER_INDENTATION * 2 - 1, 
            arcWidth, arcHeight
        );
        
        // border
        if (this.getModel().isArmed()) {
            g2d.setColor(this.borderColor);
        } else if (this.getModel().isRollover()) {
            g2d.setColor(this.clickBorderColor);
        } else {
            g2d.setColor(this.borderColor);
        }
        g2d.drawRoundRect(
            PAINT_CENTER_INDENTATION, PAINT_CENTER_INDENTATION, 
            getWidth() - PAINT_CENTER_INDENTATION * 2 - 1, getHeight() - PAINT_CENTER_INDENTATION * 2 - 1, 
            arcWidth, arcHeight
        );

        // text
        FontMetrics fm = g2d.getFontMetrics();
        Rectangle stringBounds = fm.getStringBounds(getText(), g2d).getBounds();
        int textX = (getWidth() - stringBounds.width) / 2;
        int textY = (getHeight() - stringBounds.height) / 2 + fm.getAscent() + 2;
        g2d.setColor(getForeground());
        g2d.drawString(getText(), textX, textY);
        
        // delete g2d
        g2d.dispose();
    }
}
