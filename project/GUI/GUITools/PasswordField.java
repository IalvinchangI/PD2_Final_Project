package project.GUI.GUITools;

import java.awt.FlowLayout;

import javax.swing.JPasswordField;





/**
 * 左 Label，右 PasswordField 的輸入框
 * @author IalvinchangI
 */
public class PasswordField extends InputField {

    /**
     * 取得 密碼
     * @return 密碼
     */
    @Override
    public String getText() {
        return new String(((JPasswordField) this.textField).getPassword());
    }


    /**
     * 左 Label，右 PasswordField 的輸入框
     * @param label         要顯示的字
     * @param echoChar      隱藏 密碼 的字元  (echoChar = 0 => 不會隱藏 密碼)
     * @param defaultText   預設在 PasswordField 填入的字
     * @param fieldLength   顯示字數
     * @param align         靠 ? 對齊 (說明: {@link FlowLayout})
     * @param hGap          上下間隔
     * @param vGap          左右間隔
     */
    public PasswordField(String label, char echoChar, String defaultText, int fieldLength, int align, int hGap, int vGap) {
        super(new JPasswordField(defaultText, fieldLength), label, align, hGap, vGap);

        ((JPasswordField) this.textField).setEchoChar(echoChar);
    }


    /**
     * 左 Label，右 TextField 的輸入框
     * @param label         要顯示的字
     * @param echoChar      隱藏 密碼 的字元  (echoChar = 0 => 不會隱藏 密碼)
     * @param defaultText   預設在 TextField 填入的字
     * @param fieldLength   顯示字數
     */
    public PasswordField(String label, char echoChar, String defaultText, int fieldLength) {
        this(label, echoChar, defaultText, fieldLength, FlowLayout.LEFT, 5, 5);
    }


    /**
     * 左 Label，右 TextField 的輸入框
     * @param label         要顯示的字
     * @param echoChar      隱藏 密碼 的字元  (echoChar = 0 => 不會隱藏 密碼)
     * @param align         靠 ? 對齊 (說明: {@link FlowLayout})
     * @param hGap          上下間隔
     * @param vGap          左右間隔
     */
    public PasswordField(String label, char echoChar, int align, int hGap, int vGap) {
        this(label, echoChar, "", 20, align, hGap, vGap);
    }

    
    /**
     * 左 Label，右 TextField 的輸入框
     * @param label         要顯示的字
     * @param echoChar      隱藏 密碼 的字元  (echoChar = 0 => 不會隱藏 密碼)
     */
    public PasswordField(String label, char echoChar) {
        this(label, echoChar, "", 20, FlowLayout.LEFT, 5, 5);
    }


    /**
     * 左 Label，右 TextField 的輸入框
     * @param label         要顯示的字
     */
    public PasswordField(String label) {
        this(label, (char) 0, "", 20, FlowLayout.LEFT, 5, 5);
    }
}
