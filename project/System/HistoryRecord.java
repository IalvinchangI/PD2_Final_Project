package project.System;

public interface HistoryRecord {
    /**
     * 取得所有的歷史交易紀錄
     * @return 存有歷史交易紀錄的陣列, Deal[]
     */
    public abstract Deal[] getRecords();

    /**
     * 取得(所有歷史交易)累積的盈虧
     * @return 所有歷史交易累積的盈虧
     */
    public abstract double getProfitAndLoss();
}
