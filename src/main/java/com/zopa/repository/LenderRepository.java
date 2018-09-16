package com.zopa.repository;

import com.zopa.service.DataSourceNotAvailable;
import com.zopa.service.Lender;

import java.util.List;

/**
 * Created by Bogdan Marcut on 16-Sep-18.
 */
public interface LenderRepository {
    List<Lender> getLenders(String fileName) throws DataSourceNotAvailable;
}
