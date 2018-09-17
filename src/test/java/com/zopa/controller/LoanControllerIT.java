package com.zopa.controller;

import com.zopa.repository.LenderCSVRepository;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.zopa.service.ErrorTypeEnum.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Bogdan Marcut on 17-Sep-18.
 */
@RunWith(CdiRunner.class) // Runs the test with CDI-Unit
@AdditionalClasses(LenderCSVRepository.class)
public class LoanControllerIT {

    @Inject
    private LoanConsoleController loanController;

    @Test
    public void
    return_a_loan() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        final String fileName = "market.csv";
        final String amount = "1000";

        this.loanController.calculateRate(new String[]{fileName, amount});

        assertThat(outContent.toString()).contains(amount).contains("7.0%").contains("30.").contains("1108.");
    }

    @Test
    public void
    print_error_when_amount_is_not_within_range() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        final String fileName = "market.csv";
        final String amount = "100000";

        this.loanController.calculateRate(new String[]{fileName, amount});

        assertThat(outContent.toString()).contains(BORROWING_AMOUNT_OUT_OF_RANGE.getError());
    }

    @Test
    public void
    print_error_file_does_not_exist() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        final String fileName = "TEST.csv";
        final String amount = "1000";

        this.loanController.calculateRate(new String[]{fileName, amount});

        assertThat(outContent.toString()).contains(FILE_NOT_FOUND.getError());
    }

    @Test
    public void
    print_error_file_format_is_invalid() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        final String fileName = "marketInvalid.csv";
        final String amount = "1000";

        this.loanController.calculateRate(new String[]{fileName, amount});

        assertThat(outContent.toString()).contains(FILE_FORMAT_INVALID.getError());
    }

    @Test
    public void
    print_error_no_loan_is_available() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        final String fileName = "market.csv";
        final String amount = "10000";

        this.loanController.calculateRate(new String[]{fileName, amount});

        assertThat(outContent.toString()).contains(LENDER_NOT_AVAILABLE.getError());
    }

    @Test
    public void
    print_error_requested_amount_is_invalid() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        final String fileName = "market.csv";
        final String amount = "asd";

        this.loanController.calculateRate(new String[]{fileName, amount});

        assertThat(outContent.toString()).contains(BORROWING_AMOUNT_INVALID.getError());
    }

    @Test
    public void
    print_error_requested_amount_is_not_multiply_of_100() throws Exception {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        final String fileName = "market.csv";
        final String amount = "1001";

        this.loanController.calculateRate(new String[]{fileName, amount});

        assertThat(outContent.toString()).contains(BORROWING_AMOUNT_NOT_MULTIPLE_OF_100.getError());
    }
}
