package project.GUI.GUITools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


/**
 * 圓框
 * @author IalvinchangI
 */
public class RoundPanel extends JPanel {
    
    /** 圓框的下面那層的顏色 */
    private Color bottomLayerColor = null;
    /** 圓框的背景色 */
    private Color backgroundColor = null;
    /** 邊框的顏色 */
    private Color borderColor = null;


    /** 框的邊角圓弧 */
    public int arcWidth = 30, arcHeight = 30;

    /**  設定框的邊角圓弧 */
    public void setArc(int width, int height) {
        this.arcWidth = width;
        this.arcHeight = height;
        // repaint();
    }


    /** 裝元件的 */
    private JPanel root = null;


    /**
     * 取得裝元件的 Panel
     * @return 裝元件的 Panel
     */
    public JPanel getRoot() {
        return this.root;
    }



     /**
     * 設定圓框
     * @param bottomLayerColor      圓框的下面那層的顏色
     * @param backgroundColor       圓框的背景色
     * @param borderColor           邊框的顏色
     * @apiNote construct 後，須執行 {@code setPreferredSize}
     */
    public RoundPanel(Color bottomLayerColor, Color backgroundColor, Color borderColor) {
        this.setBackground(bottomLayerColor);
        this.bottomLayerColor = bottomLayerColor;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        
        this.root = new JPanel();
        this.root.setBackground(backgroundColor);
        this.root.setBorder(BorderFactory.createLineBorder(Color.black));
        super.add(this.root);
    }

    /**
     * 設定圓框
     * @param text              顯示的字
     * @param bottomLayerColor  圓框的下面那層的顏色
     * @apiNote construct 後，須執行 {@code setPreferredSize}
     */
    public RoundPanel(Color bottomLayerColor) {
        this(bottomLayerColor, new Color(133, 205, 144), new Color(86, 171, 99));
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
        g2d.setColor(this.backgroundColor);
        g2d.fillRoundRect(
            PAINT_CENTER_INDENTATION, PAINT_CENTER_INDENTATION, 
            getWidth() - PAINT_CENTER_INDENTATION * 2 - 1, getHeight() - PAINT_CENTER_INDENTATION * 2 - 1, 
            arcWidth, arcHeight
        );
        
        // border
        g2d.setColor(this.borderColor);
        g2d.drawRoundRect(
            PAINT_CENTER_INDENTATION, PAINT_CENTER_INDENTATION, 
            getWidth() - PAINT_CENTER_INDENTATION * 2 - 1, getHeight() - PAINT_CENTER_INDENTATION * 2 - 1, 
            arcWidth, arcHeight
        );
        
        // change root size
        // this.setPreferredSize(getSize());

        // delete g2d
        g2d.dispose();
    }


    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize);
        if (this.root != null) {
            int xStart = Math.max(10, this.arcWidth);
            int yStart = Math.max(10, this.arcWidth);
            this.root.setBounds(
                xStart, yStart, 
                preferredSize.width - xStart * 2, preferredSize.height - yStart * 2
            );
        }
    }


    /**
     * 設定元件的 Layout
     * @apiNote 若傳入 BoxLayout，須在 target 傳入 ShadowPanel.getRoot()
     */
    @Override
    public void setLayout(LayoutManager mgr) {
        if (this.root != null) {
            this.root.setLayout(mgr);
        }
    }


    /**
     * 取得 背景色
     * @return 背景色
     */
    public Color getRealBackground() {
        return this.backgroundColor;
    }


    @Override
    public Component add(Component comp) {
        return this.root.add(comp);
    }
    @Override
    public void add(Component comp, Object constraints) {
        this.root.add(comp, constraints);
    }
    @Override
    public Component add(Component comp, int index) {
        return this.root.add(comp, index);
    }
    @Override
    public Component add(String name, Component comp) {
        return this.root.add(name, comp);
    }
    @Override
    public void add(Component comp, Object constraints, int index) {
        this.root.add(comp, constraints, index);
    }
}
