package com.zopa.repository;

import com.zopa.service.DataSourceNotAvailable;
import com.zopa.service.Lender;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Bogdan Marcut on 15-Sep-18.
 */
public class LenderRepositoryImpl implements LenderRepository {

    @Override
    public List<Lender> getLenders(final String fileName) throws DataSourceNotAvailable {
        try (final Stream<String> stream = Files.lines(Paths.get(fileName))) {
            final List<Lender> lenders = stream
                    .map(line -> line.split(","))
                    .skip(1)
                    .map(this::buildLender)
                    .collect(Collectors.toList());

            return lenders;
        } catch (final IOException e) {
            throw new DataSourceNotAvailable("File " + fileName + " not found!");
        }
    }

    private Lender buildLender(final String[] line) {
        final Lender lender = new Lender();

        lender.setName(line[0]);
        lender.setRate(Double.parseDouble(line[1]));
        lender.setMaximumAmount(Double.parseDouble(line[2]));

        return lender;
    }
}
