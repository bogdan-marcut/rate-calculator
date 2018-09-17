package com.zopa.service.lender;

import com.zopa.service.DataSourceIsInvalid;
import com.zopa.service.DataSourceNotAvailable;

import java.util.List;

/**
 * Created by Bogdan Marcut on 16-Sep-18.
 */
public interface LenderRepository {
    List<Lender> getLenders(String fileName) throws DataSourceNotAvailable, DataSourceIsInvalid;
}
