package project.System;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class HistoryRecord {

    public static final double DOUBLE_MAX_VALUE = Double.MAX_VALUE;
    public static final double DOUBLE_MIN_VALUE = Double.MIN_VALUE;

    HashMap< String, ArrayList<Deal> > record = null;
    double profitAndLoss = -DOUBLE_MAX_VALUE;

    public HistoryRecord() {
        record = new HashMap<>();
        profitAndLoss = 0;
    }

    /**
     * 將一筆交易存入歷史紀錄的陣列
     * @param deal : 單筆交易
     */
    public void addADeal(Deal deal) {

        assert deal.checkDealValid() == true : "deal is invalid";
        String stockName = deal.getStockName();

        if (record.containsKey(stockName)) {
            record.get(stockName).add(deal);
        }
        else {
            ArrayList<Deal> stockDeals = new ArrayList<>();
            stockDeals.add(deal);
            record.put(stockName, stockDeals);
        }
        profitAndLoss += deal.getProfitAndLoss();
        
    }

    /**
     * 取得特定股票的所有歷史交易紀錄
     * @return 存有歷史交易紀錄的陣列
     */
    public List<Deal> getRecords(String stockName) {

        assert stockName != null && stockName.length() > 0 : "stockName is null or \"\"";
        if (record.containsKey(stockName)) {
            return record.get(stockName);
        }
        else {
            return null;
        }
    }

    /**
     * 取得(所有歷史交易)累積的盈虧
     * @return 所有歷史交易累積的盈虧
     */
    public double getProfitAndLoss() {

        assert profitAndLoss >= -DOUBLE_MAX_VALUE : "profitAndLoss = -DOUBLE_MAX_VALUE, may not be initialized";
        return profitAndLoss;
    }
}
