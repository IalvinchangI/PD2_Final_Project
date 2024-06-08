package project;

import java.util.TimerTask;

import project.trade.TradingAgent;;


/**
 * 要在背景定時執行的程序
 * @author IalvinchangI
 */
public class BackgroundExexute extends TimerTask {
    /**
     * 定時執行
     * <p>
     * 1. 更新股票資料
     * <p>
     * 2. 執行交易 (透過{@code TradingAgent})
     * <p>
     * 3. 更新歷史資料
     */
    public void run() {

    }
}
