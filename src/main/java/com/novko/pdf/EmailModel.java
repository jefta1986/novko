package com.novko.pdf;

import javax.activation.DataSource;

public class EmailModel {

    DataSource dataSource;
    String fileName;


    public EmailModel(DataSource dataSource, String fileName) {
        this.dataSource = dataSource;
        this.fileName = fileName;
    }

    public EmailModel(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    @Override
    public String toString() {
        return "EmailModel{" +
                "dataSource=" + dataSource +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
