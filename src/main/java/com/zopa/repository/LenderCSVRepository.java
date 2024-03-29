package com.zopa.repository;

import com.zopa.service.DataSourceIsInvalid;
import com.zopa.service.DataSourceNotAvailable;
import com.zopa.service.lender.Lender;
import com.zopa.service.lender.LenderRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.zopa.service.ErrorTypeEnum.FILE_FORMAT_INVALID;
import static com.zopa.service.ErrorTypeEnum.FILE_NOT_FOUND;

/**
 * Created by Bogdan Marcut on 15-Sep-18.
 */
@Dependent
public class LenderCSVRepository implements LenderRepository {

    @Inject
    private FileReader fileReader;

    @Override
    public List<Lender> getLenders(final String fileName) throws DataSourceNotAvailable, DataSourceIsInvalid {
        try (final Stream<String> stream = this.fileReader.lines(fileName)) {
            return stream
                    .map(line -> line.split(","))
                    .skip(1)
                    .map(this::buildLender)
                    .collect(Collectors.toList());
        } catch (final IOException e) {
            throw new DataSourceNotAvailable(FILE_NOT_FOUND.getError());
        }
    }

    private Lender buildLender(final String[] line) throws DataSourceIsInvalid {
        if (line == null || line.length != 3) throw new DataSourceIsInvalid(FILE_FORMAT_INVALID.getError());

        final Lender lender = new Lender();
        lender.setName(line[0]);
        lender.setRate(this.parseDouble(line[1]));
        lender.setMaximumAmount(this.parseDouble(line[2]));

        return lender;
    }

    private double parseDouble(final String cell) throws DataSourceIsInvalid {
        try {
            return Double.parseDouble(cell);
        } catch (final NumberFormatException e) {
            throw new DataSourceIsInvalid(FILE_FORMAT_INVALID.getError());
        }
    }
}
