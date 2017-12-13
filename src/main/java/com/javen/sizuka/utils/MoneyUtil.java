package com.javen.sizuka.utils;

import java.text.DecimalFormat;

/**
 * Created by javen on 2017/12/4.
 */
public class MoneyUtil {
    public static String save2Digit(double source){
        DecimalFormat df = new DecimalFormat("#,###.00");
        return df.format(source);
    }
}
