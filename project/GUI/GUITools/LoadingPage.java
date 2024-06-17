package project.GUI.GUITools;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;
import javax.swing.Timer;



/**
 * 載入頁面
 * @author IalvinchangI
 */
public class LoadingPage extends JPanel {

    /** 放 載入動畫的 Panel */
    private LoadingAnimation animation = null;


    /**
     * 設定載入頁面
     * @param bottomLayerColor 頁面下面那層的顏色
     */
    public LoadingPage(Color bottomLayerColor) {
        this.setBackground(bottomLayerColor);
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(600, 450));

        // animation setting
        this.animation = new LoadingAnimation(bottomLayerColor, Color.ORANGE);
        this.animation.setPreferredSize(this.getPreferredSize());

        // add
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.gridx = 0;
        constraint.gridy = 0;
        this.add(this.animation, constraint);

        // set listener
        this.setShowHideListener();
    }
    

    /**
     * listener 的內容 :
     * <p>
     * 監聽顯示或隱藏
     * <ul>
     * <li> 顯示 : 啟動動畫 </li>
     * <li> 隱藏 : 結束動畫 </li>
     * </ul>
     */
    private void setShowHideListener() {
        LoadingPage page = this;
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                page.animation.startAnimation();
            }
            @Override
            public void componentHidden(ComponentEvent e) {
                page.animation.stopAnimation();
            }
        });
    }

}


/**
 * 動畫畫面 : 跳動的球
 * @author IalvinchangI
 */
class LoadingAnimation extends JPanel {
    /** 球的顏色 */
    private Color color = null;


    /** 播出每幀動畫用的時鐘 */
    private Timer animationClock = null;


    /**
     * 設定動畫顏色
     * @param bottomLayerColor  畫面的背景色
     * @param color             球的顏色
     */
    public LoadingAnimation(Color bottomLayerColor, Color color) {
        this.setBackground(bottomLayerColor);
        this.color = color;
    }


    // constant
    private int baseX = 0;
    private int baseY = 300;
    private int ballDistance = 50;
    private int ballSize = 25;
    private int acceleration = -2;
    private int initVelocity = 20;

    // variable
    private int deltaY = 0;
    private int currentBall = 0;
    private int[] jump_TF = {1, 0, 0, 0, 0, 0, 0};
    private int moveSpeed = initVelocity;


    /**
     * 開始動畫
     */
    public void startAnimation() {
        LoadingAnimation page = this;
        this.animationClock = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page.updateAnimation();
                page.repaint();
            }
        });
        this.animationClock.start();
    }


    /**
     * 停止 / 結束 動畫
     */
    public void stopAnimation() {
        if (this.animationClock != null) {
            this.animationClock.stop();
        }
    }


    /**
     * 更新至下一幀動畫畫面
     */
    public void updateAnimation() {
        if (this.moveSpeed + this.acceleration < -this.initVelocity) {  // next ball?
            this.moveSpeed = this.initVelocity;  // reset velocity

            // change ball
            this.jump_TF[this.currentBall] = 0;
            if (this.currentBall == this.jump_TF.length - 1) {
                this.currentBall = 0;
            }
            else {
                this.currentBall++;
            }
            this.jump_TF[this.currentBall] = 1;
        }
        // 當前球的位置
        this.deltaY += this.moveSpeed + (int)(this.acceleration / 2);
        this.moveSpeed += this.acceleration;
    }


    /**
     * 重設動畫，讓動畫從頭開始播
     */
    public void resetAnimation() {
        this.jump_TF[this.currentBall] = 0;
        this.currentBall = 0;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // 繪製球
        g2d.setColor(this.color);
        for (int i = 0; i < this.jump_TF.length; i++) {
            g2d.drawOval(this.baseX + this.ballDistance * i, this.baseY - this.deltaY * this.jump_TF[i], this.ballSize, this.ballSize);
            g2d.fillOval(this.baseX + this.ballDistance * i, this.baseY - this.deltaY * this.jump_TF[i], this.ballSize, this.ballSize);
        }
        
        // delete g2d
        g2d.dispose();
    }


    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize);
        // loading 動畫調到 panel 正中間
        this.baseY = preferredSize.height / 7 * 4;
        this.baseX = preferredSize.width / 2 - (this.jump_TF.length / 2 * this.ballDistance);
    }
}
