package project.GUI.GUITools;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;

import javax.swing.JPanel;



/**
 * 有陰影的框
 * @author IalvinchangI
 */
public class ShadowPanel extends JPanel {

    /** 背景色 */
    private Color backgroundColor = null;
    
    /** 陰影色 */
    private Color shadowColor = null;


    /** 框的邊角圓弧 */
    public int arcWidth = 50, arcHeight = 50;

    /**  設定框的邊角圓弧 */
    public void setArc(int width, int height) {
        this.arcWidth = width;
        this.arcHeight = height;
        // repaint();
    }


    /** 陰影位移 */
    public int shadowShift = 7;

    /** 設定陰影位移 */
    public void setShadowShift(int shift) {
        this.shadowShift = shift;
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
     * 設定有陰影的框
     * @param bottomLayerColor  框的下面那層的顏色
     * @param backgroundColor   框的背景色
     * @param shadowColor       陰影色
     */
    public ShadowPanel(Color bottomLayerColor, Color backgroundColor, Color shadowColor) {
        this.setBackground(bottomLayerColor);
        this.backgroundColor = backgroundColor;
        this.shadowColor = shadowColor;

        this.root = new JPanel();
        this.root.setBackground(backgroundColor);
        super.add(this.root);
    }
    
    /**
     * 設定有陰影的框
     * @param bottomLayerColor  框的下面那層的顏色
     * @param backgroundColor   框的背景色
     */
    public ShadowPanel(Color bottomLayerColor, Color backgroundColor) {
        this(bottomLayerColor, backgroundColor, new Color(150, 150, 150));
    }

    /**
     * 設定有陰影的框
     * @param bottomLayerColor  框的下面那層的顏色
     */
    public ShadowPanel(Color bottomLayerColor) {
        this(bottomLayerColor, new Color(121, 206, 172), new Color(150, 150, 150));
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // shadow
        g2d.setColor(shadowColor);
        g2d.fillRoundRect(shadowShift, shadowShift, getWidth() - shadowShift -1, getHeight() - shadowShift - 1, arcWidth, arcHeight);
        g2d.drawRoundRect(shadowShift, shadowShift, getWidth() - shadowShift -1, getHeight() - shadowShift - 1, arcWidth, arcHeight);
        
        // background
        g2d.setColor(backgroundColor);
        g2d.fillRoundRect(0, 0, getWidth() - shadowShift - 1, getHeight() - shadowShift - 1, arcWidth, arcHeight);
        g2d.drawRoundRect(0, 0, getWidth() - shadowShift - 1, getHeight() - shadowShift - 1, arcWidth, arcHeight);

        
        // show
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
                preferredSize.width - xStart * 2 - this.shadowShift, preferredSize.height - yStart * 2 - this.shadowShift
            );
        }
    }


    /**
     * 設定元件的 Layout
     * @implNote 若傳入 BoxLayout，須在 target 傳入 ShadowPanel.getRoot()
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
