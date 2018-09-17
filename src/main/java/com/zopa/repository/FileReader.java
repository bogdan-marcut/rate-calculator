package com.zopa.repository;

import javax.enterprise.context.Dependent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by Bogdan Marcut on 17-Sep-18.
 */
@Dependent
public class FileReader {

    public Stream<String> lines(final String fileName) throws IOException {
        return Files.lines(Paths.get(fileName));
    }
}
