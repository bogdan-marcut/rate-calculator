package com.zopa;

import com.zopa.controller.LoanController;

import javax.inject.Inject;

/**
 * Created by Bogdan Marcut on 15-Sep-18.
 */
public class RateCalculatorApp {

    @Inject
    private LoanController loanController;

    public static void main(String[] args){
        System.out.println("Lol");
    }
}
