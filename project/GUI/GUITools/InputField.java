package project.GUI.GUITools;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;





/**
 * 左 Label，右 TextField 的輸入框
 * @author IalvinchangI
 */
public class InputField extends JPanel {

    /** Label */
    protected JLabel label = null;

    /**
     * 取得 label
     * @return label
     */
    public JLabel getLabel() {
        return this.label;
    }


    /** TextField */
    protected JTextField textField = null;

    /**
     * 取得 textField
     * @return textField
     */
    public JTextField getTextField() {
        return this.textField;
    }

    /**
     * 取得 TextField 內的字
     * @return 輸入的字
     */
    public String getText() {
        return this.textField.getText();
    }

    
    @Override
    public void setFont(Font font) {
        if (this.textField != null) {
            this.textField.setFont(font);
        }
        if (this.label != null) {
            this.label.setFont(font);
        }
    }


    /**
     * 調整內部元件大小
     * @param labelWidth    label 的寬
     * @param textLength    顯示字數
     * @param height        label, textField 的高
     */
    public void setSize(int labelWidth, int textLength, int height) {
        this.textField.setColumns(textLength);
        this.label.setPreferredSize(new Dimension(labelWidth, height));
        this.textField.setPreferredSize(new Dimension(this.textField.getPreferredSize().width, height));
    }
    
    /**
     * 調整內部元件大小
     * @param labelWidth    label 的寬
     * @param height        label, textField 的高
     */
    public void setSize(int labelWidth, int height) {
        this.label.setPreferredSize(new Dimension(labelWidth, height));
        this.textField.setPreferredSize(new Dimension(this.textField.getPreferredSize().width, height));
    }
    
    /**
     * 調整內部元件大小
     * @param labelWidth    label 的寬
     */
    public void setSize(int labelWidth) {
        this.label.setPreferredSize(new Dimension(labelWidth, this.label.getPreferredSize().height));
    }

    /**
     * 調整顯示字數
     * @param textLength    顯示字數
     */
    public void setTextLength(int labelWidth) {
        this.label.setPreferredSize(new Dimension(labelWidth, this.label.getPreferredSize().height));
    }


    /**
     * 建造 : 左 Label，右 TextField 的輸入框
     * @param textField     要放入的 TextField 物件
     * @param label         要顯示的字
     * @param align         靠 ? 對齊 (說明: {@link FlowLayout})
     * @param hGap          上下間隔
     * @param vGap          左右間隔
     */
    protected InputField(JTextField textField, String label, int align, int hGap, int vGap) {
        // label
        this.label = new JLabel(label);
        
        // textField
        this.textField = textField;
        
        
        // build
        this.setLayout(new FlowLayout(align, hGap, vGap));
        this.textField.setPreferredSize(new Dimension(this.textField.getPreferredSize().width, this.label.getPreferredSize().height));  // 讓 textField 和 label 同高
        this.add(this.label);
        this.add(this.textField);
    }


    /**
     * 左 Label，右 TextField 的輸入框
     * @param label         要顯示的字
     * @param defaultText   預設在 TextField 填入的字 (null 代表沒有)
     * @param fieldLength   顯示字數
     * @param align         靠 ? 對齊 (說明: {@link FlowLayout})
     * @param hGap          上下間隔
     * @param vGap          左右間隔
     */
    public InputField(String label, String defaultText, int fieldLength, int align, int hGap, int vGap) {
        this(new JTextField(defaultText, fieldLength), label, align, hGap, vGap);
    }


    /**
     * 左 Label，右 TextField 的輸入框
     * @param label         要顯示的字
     * @param defaultText   預設在 TextField 填入的字 (null 代表沒有)
     * @param fieldLength   顯示字數
     */
    public InputField(String label, String defaultText, int fieldLength) {
        this(label, defaultText, fieldLength, FlowLayout.CENTER, 5, 5);
    }


    /**
     * 左 Label，右 TextField 的輸入框
     * @param label         要顯示的字
     * @param align         靠 ? 對齊 (說明: {@link FlowLayout})
     * @param hGap          上下間隔
     * @param vGap          左右間隔
     */
    public InputField(String label, int align, int hGap, int vGap) {
        this(label, null, 20, align, hGap, vGap);
    }


    /**
     * 左 Label，右 TextField 的輸入框
     * @param label         要顯示的字
     */
    public InputField(String label) {
        this(label, null, 20, FlowLayout.CENTER, 5, 5);
    }
}
