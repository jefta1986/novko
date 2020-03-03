package com.novko.api;


import com.novko.pdf.JasperReportModel;
import com.novko.pdf.JpaTransactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/transactions")
public class TransactionsController {


    private JpaTransactions jpaTransactionsImpl;

    @Autowired
    public void setJpaTransactionsImpl(JpaTransactions jpaTransactionsImpl) {
        this.jpaTransactionsImpl = jpaTransactionsImpl;
    }




    @GetMapping(value = "")
    public ResponseEntity<List<JasperReportModel>> getAllTransactions() {
        List<JasperReportModel> transactions = jpaTransactionsImpl.getAll();
        return new ResponseEntity<List<JasperReportModel>>(transactions, HttpStatus.OK);
    }


//    @GetMapping(value = "/{id}")
//    public ResponseEntity<JasperReportModel> getTransactionById(@PathVariable("id") Long id) {
//        JasperReportModel transaction = jpaTransactionsImpl.getById(id);
//        return new ResponseEntity<JasperReportModel>(transaction, HttpStatus.OK);
//    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<List<JasperReportModel>> getTransactionByOrderId(@PathVariable("id") Long id) {
        List<JasperReportModel> transaction = jpaTransactionsImpl.getByOrderId(id);
        return new ResponseEntity<List<JasperReportModel>>(transaction, HttpStatus.OK);
    }


    @PostMapping(value = "")
    public ResponseEntity<String> saveTransaction(@RequestBody JasperReportModel model) {
        jpaTransactionsImpl.save(model);
        return new ResponseEntity<String>("Transaction saved", HttpStatus.OK);
    }




}
