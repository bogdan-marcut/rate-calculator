package com.zopa.repository;

import com.zopa.service.DataSourceIsInvalid;
import com.zopa.service.DataSourceNotAvailable;
import com.zopa.service.lender.Lender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.*;

/**
 * Created by Bogdan Marcut on 17-Sep-18.
 */
@RunWith(MockitoJUnitRunner.class)
public class LenderCSVRepositoryShould {

    @InjectMocks
    private LenderCSVRepository lenderRepository;

    @Mock
    private FileReader fileReader;

    @Test
    public void
    return_lenders() throws Exception {
        final String fileName = "file.csv";
        final String[] fileLines = {"Header, Header, Header", "Bob, 0.1, 1000"};
        given(this.fileReader.lines(any())).willReturn(Stream.of(fileLines));

        final List<Lender> response = this.lenderRepository.getLenders(fileName);

        assertThat(response).hasSize(1);
    }

    @Test(expected = DataSourceNotAvailable.class)
    public void
    throw_exception_when_file_not_found() throws Exception {
        final String fileName = "file.csv";
        given(this.fileReader.lines(any())).willThrow(new IOException());

        final List<Lender> response = this.lenderRepository.getLenders(fileName);
    }

    @Test(expected = DataSourceIsInvalid.class)
    public void
    throw_exception_when_file_format_is_invalid() throws Exception {
        final String fileName = "file.csv";
        final String[] fileLines = {"Header, Header, Header", "Bob, 0.1"};
        given(this.fileReader.lines(any())).willReturn(Stream.of(fileLines));

        final List<Lender> response = this.lenderRepository.getLenders(fileName);
    }

    @Test(expected = DataSourceIsInvalid.class)
    public void
    throw_exception_when_file_lines_are_invalid() throws Exception {
        final String fileName = "file.csv";
        final String[] fileLines = {"Header, Header, Header", "Bob, invalid, 100"};
        given(this.fileReader.lines(any())).willReturn(Stream.of(fileLines));

        final List<Lender> response = this.lenderRepository.getLenders(fileName);
    }
}
