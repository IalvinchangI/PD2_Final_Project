# Documentation

## ChangeablePanel `JPanel`
### 功能：
> 可切換頁面的 Panel

### 新增方法：
> * 切換至名為 pageName 的頁面
> ```java
> public boolean showPage(String pageName)
> ```

> * 把頁面加入 ChangeablePanel，方便切換
> ```java
> public void add(Component comp, String pageName)
> ``` 

> * 設定一個 ActionListener，他的 actionPerformed 是將頁面切換到名為 pageName 的頁面，最後回傳設定好的 ActionListener 
> > return :  
> > 
> > 如果 pageName 在 ChangeablePanel 裡，則回傳 ActionListener (ChangeablePanel 內有儲存此頁面) 
> > 
> > 如果 pageName 不在 ChangeablePanel 裡，則回傳 null (ChangeablePanel 內沒儲存此頁面) 
> ```java
> public ActionListener createChangePagePerformed(String pageName)
> ``` 

> * 取得 所有頁面名稱
> ```java
> public ArrayList<String> getPageNames()
> ``` 

<br>

## ShadowPanel `JPanel`
### 功能：
> 有陰影的框 (Panel) 
> 
> **`construct 後，須執行 setPreferredSize`**

### 新增方法：
> * 框的邊角圓弧
> ```java
> public void setArc(int width, int height)
> ```

> * 設定陰影位移
> ```java
> public void setShadowShift(int shift)
> ``` 

> * 取得裝元件的 Panel
> ```java
> public JPanel getRoot()
> ```

> * 取得 背景色
> ```java
> public Color getRealBackground()
> ``` 

<br>

## RoundPanel `JPanel`
### 功能：
> 圓框 (Panel)  
> 
> **`construct 後，須執行 setPreferredSize`**

### 新增方法：
> * 框的邊角圓弧
> ```java
> public void setArc(int width, int height)
> ```

> * 取得裝元件的 Panel
> ```java
> public JPanel getRoot()
> ```

> * 取得 背景色
> ```java
> public Color getRealBackground()
> ``` 

<br>

## RoundButton `JButton`
### 功能：
> 圓框按鈕

### 新增方法：
> * 設定框的邊角圓弧
> ```java
> public void setArc(int width, int height)   // setter
> public int arcWidth = 30;  // 屬性
> public int arcHeight = 30;  // 屬性
> ```

<br>

## InputField `JPanel`
### 功能：
> 左 Label，右 TextField 的輸入框 (Panel)

### 方法：
> * 取得 label
> ```java
> public JLabel getLabel()
> ```

> * 取得 textField
> ```java
> public JTextField getTextField()
> ``` 

> * 取得 textField 內的字
> ```java
> public String getTextField()
> ``` 

> * 調整內部元件大小
> ```java
> public void setComponentSize(int labelWidth, int textLength, int height)
> public void setComponentSize(int labelWidth, int height)
> public void setComponentSize(int labelWidth)
> ``` 

> * 調整顯示字數
> ```java
> public void setTextLength(int labelWidth)
> ``` 

<br>

## PasswordField `InputField`
### 功能：
> 左 Label，右 PasswordField 的輸入框 (Panel)

### 方法：
> * 取得 label
> ```java
> public JLabel getLabel()
> ```

> * 取得 passwordField
> ```java
> public JPasswordField getTextField()
> ``` 

> * 取得 密碼
> ```java
> public String getText()
> ```

> * 調整內部元件大小
> ```java
> public void setComponentSize(int labelWidth, int textLength, int height)
> public void setComponentSize(int labelWidth, int height)
> public void setComponentSize(int labelWidth)
> ``` 

> * 調整顯示字數
> ```java
> public void setTextLength(int labelWidth)
> ``` 
