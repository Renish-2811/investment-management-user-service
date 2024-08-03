package com.investment_management.user_service.constants;

import java.util.ArrayList;
import java.util.List;

public class WalletType {

    public static List<String> walletTypes(){
        List<String> dematBalance = new ArrayList<>();
        dematBalance.add("STOCKS");
        dematBalance.add("F&O");
        dematBalance.add("COMMODITY");
        dematBalance.add("CURRENCY");
        dematBalance.add("MF");
        return dematBalance;
    }
}
