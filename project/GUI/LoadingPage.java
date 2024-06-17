package project.GUI;

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

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;



/**
 * 載入頁面
 * @author IalvinchangI
 */
public class LoadingPage extends JPanel {

    private LoadingAnimation animation = null;


    public LoadingPage(Color bottomLayerColor) {
        this.setBackground(bottomLayerColor);
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(600, 450));

        
        this.animation = new LoadingAnimation(bottomLayerColor, Color.ORANGE);
        this.animation.setPreferredSize(this.getPreferredSize());

        GridBagConstraints constraint = new GridBagConstraints();
        constraint.gridx = 0;
        constraint.gridy = 0;
        this.add(this.animation, constraint);

        this.setShowHideListener();
    }
    

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
 * 動畫畫面
 */
class LoadingAnimation extends JPanel {

    private Color color = null;

    private Timer animationClock = null;


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
     * 停止動畫
     */
    public void stopAnimation() {
        if (this.animationClock != null) {
            this.animationClock.stop();
        }
    }


    /**
     * 更新至下一張動畫畫面
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
        this.deltaY += this.moveSpeed + (int)(this.acceleration / 2);
        this.moveSpeed += this.acceleration;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // circle
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
        this.baseY = preferredSize.height / 7 * 4;
        this.baseX = preferredSize.width / 2 - (this.jump_TF.length / 2 * this.ballDistance);
    }
}
