package project.GUI.GUITools;

import java.awt.FlowLayout;
import java.awt.Label;

import javax.swing.JPanel;
import javax.swing.JTextField;





/**
 * 左 Label，右 TextField 的輸入框
 * @author IalvinchangI
 */
public class InputField extends JPanel {
    /** Label */
    protected Label label = null;

    /**
     * 取得 label
     * @return label
     */
    public Label getLabel() {
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


    /** FlowLayout */
    protected FlowLayout layout = null;



    /**
     * 建造 : 左 Label，右 TextField 的輸入框
     * @param textField     要放入的 TextField 物件
     * @param label         要顯示的字
     * @param align         靠 ? 對齊 (說明: {@link FlowLayout})
     * @param hGap          上下間隔
     * @param vGap          左右間隔
     */
    protected InputField(JTextField textField, String label, int align, int hGap, int vGap) {
        // layout
        this.layout = new FlowLayout(align, hGap, vGap);
        
        // label
        this.label = new Label(label);
        
        // textField
        this.textField = textField;
        
        
        // build
        this.setLayout(layout);
        this.add(this.label);
        this.add(this.textField);
    }


    /**
     * 左 Label，右 TextField 的輸入框
     * @param label         要顯示的字
     * @param defaultText   預設在 TextField 填入的字
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
     * @param defaultText   預設在 TextField 填入的字
     * @param fieldLength   顯示字數
     */
    public InputField(String label, String defaultText, int fieldLength) {
        this(label, defaultText, fieldLength, FlowLayout.LEFT, 5, 5);
    }


    /**
     * 左 Label，右 TextField 的輸入框
     * @param label         要顯示的字
     * @param align         靠 ? 對齊 (說明: {@link FlowLayout})
     * @param hGap          上下間隔
     * @param vGap          左右間隔
     */
    public InputField(String label, int align, int hGap, int vGap) {
        this(label, "", 20, align, hGap, vGap);
    }


    /**
     * 左 Label，右 TextField 的輸入框
     * @param label         要顯示的字
     */
    public InputField(String label) {
        this(label, "", 20, FlowLayout.LEFT, 5, 5);
    }
}
