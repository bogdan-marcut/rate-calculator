package com.zopa;

import com.zopa.controller.LoanConsoleController;
import com.zopa.controller.LoanController;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

/**
 * Created by Bogdan Marcut on 15-Sep-18.
 */
public class RateCalculatorApp {


    public static void main(final String[] args) {
        try (final SeContainer container = SeContainerInitializer.newInstance().disableDiscovery().addPackages(true, RateCalculatorApp.class).initialize()) {

            final LoanController loanController = container.select(LoanConsoleController.class).get();
            loanController.calculateRate(args);

        }

    }
}
