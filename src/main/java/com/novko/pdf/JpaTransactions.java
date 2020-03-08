package com.novko.pdf;

import java.util.List;

public interface JpaTransactions {

    void save(JasperReportModel model);
    JasperReportModel getById(Long id);
    List<JasperReportModel> getByOrderId(Long id);
    List<JasperReportModel> getAll();
}
