package com.zopa.service;

import com.zopa.LenderBuilder;
import com.zopa.service.lender.Lender;
import com.zopa.service.lender.LenderRepository;
import com.zopa.service.lender.LenderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Bogdan Marcut on 17-Sep-18.
 */
@RunWith(MockitoJUnitRunner.class)
public class LenderServiceShould {

    @InjectMocks
    private LenderService lenderService;

    @Mock
    private LenderRepository lenderRepository;

    @Test
    public void
    return_lenders() throws Exception {
        final String fileName = "file.csv";

        this.lenderService.getLenders(fileName);

        verify(this.lenderRepository, times(1)).getLenders(fileName);
    }

    @Test
    public void
    return_best_lender() throws Exception {
        final String fileName = "file.csv";
        final List<Lender> lenders = new ArrayList<>();
        lenders.add(new LenderBuilder()
                .withName("Bob")
                .withRate(0.07)
                .withMaximumAmount(100.0)
                .build());
        lenders.add(new LenderBuilder()
                .withName("Rob")
                .withRate(0.07)
                .withMaximumAmount(1000.0)
                .build());
        lenders.add(new LenderBuilder()
                .withName("Tom")
                .withRate(0.04)
                .withMaximumAmount(1000.0)
                .build());
        given(this.lenderRepository.getLenders(any())).willReturn(lenders);

        final Lender bestLender = this.lenderService.getLenderByBestInterest(fileName, 1000);

        assertThat(bestLender.getName()).isEqualTo("Tom");
    }

    @Test(expected = RateCalculatorException.class)
    public void
    throw_exception_if_no_lender_available() throws Exception {
        final String fileName = "file.csv";
        given(this.lenderRepository.getLenders(any())).willReturn(new ArrayList<>());

        final Lender bestLender = this.lenderService.getLenderByBestInterest(fileName, 1000);
    }

    @Test(expected = RateCalculatorException.class)
    public void
    throw_exception_if_arguments_are_invalid() throws Exception {
        final String fileName = "file.csv";
        given(this.lenderRepository.getLenders(any())).willThrow(new DataSourceNotAvailable());

        final Lender bestLender = this.lenderService.getLenderByBestInterest(fileName, 1000);
    }
}
