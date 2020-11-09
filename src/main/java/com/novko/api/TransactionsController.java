//package com.novko.api;
//
//
//import com.novko.internal.orders.JpaOrdersRepository;
//import com.novko.internal.orders.Order;
//import com.novko.pdf.EmailModel;
//import com.novko.pdf.GeneratePdf;
//import com.novko.pdf.JasperReportModel;
//import com.novko.pdf.JpaTransactions;
//import org.apache.commons.io.IOUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.core.io.Resource;
//import org.springframework.http.*;
//import org.springframework.web.bind.annotation.*;
//
//import javax.activation.DataSource;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//
//@RestController
//@RequestMapping("/rest/transactions")
//public class TransactionsController {
//
//
//    private JpaTransactions jpaTransactionsImpl;
//    private JpaOrdersRepository jpaOrdersRepositoryImpl;
//    private GeneratePdf generatePdfImpl;
//
//    @Autowired
//    public void setJpaTransactionsImpl(JpaTransactions jpaTransactionsImpl) {
//        this.jpaTransactionsImpl = jpaTransactionsImpl;
//    }
//
//    @Autowired
//    public void setJpaOrdersRepositoryImpl(JpaOrdersRepository jpaOrdersRepositoryImpl) {
//        this.jpaOrdersRepositoryImpl = jpaOrdersRepositoryImpl;
//    }
//
//    @Autowired
//    public void setGeneratePdfImpl(GeneratePdf generatePdfImpl) {
//        this.generatePdfImpl = generatePdfImpl;
//    }
//
//    @GetMapping(value = "")
//    public ResponseEntity<List<JasperReportModel>> getAllTransactions() {
//        List<JasperReportModel> transactions = jpaTransactionsImpl.getAll();
//        return new ResponseEntity<List<JasperReportModel>>(transactions, HttpStatus.OK);
//    }
//
//
////    @GetMapping(value = "/{id}")
////    public ResponseEntity<JasperReportModel> getTransactionById(@PathVariable("id") Long id) {
////        JasperReportModel transaction = jpaTransactionsImpl.getById(id);
////        return new ResponseEntity<JasperReportModel>(transaction, HttpStatus.OK);
////    }
//
//
//    @GetMapping(value = "/{id}")
//    public ResponseEntity<List<JasperReportModel>> getTransactionByOrderId(@PathVariable("id") Long id) {
//        List<JasperReportModel> transaction = jpaTransactionsImpl.getByOrderId(id);
//        return new ResponseEntity<List<JasperReportModel>>(transaction, HttpStatus.OK);
//    }
//
//
//    @PostMapping(value = "")
//    public ResponseEntity<String> saveTransaction(@RequestBody JasperReportModel model) {
//        jpaTransactionsImpl.save(model);
//        return new ResponseEntity<String>("Transaction saved", HttpStatus.OK);
//    }
//
//
//    @GetMapping(value = "/download/{orderId}")
//    public HttpEntity<byte[]> downloadPdfOrder(@PathVariable("orderId") Long orderId, HttpServletResponse response) throws IOException {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//
//        Order order = jpaOrdersRepositoryImpl.get(orderId);
//        if(order==null){
//            throw new RuntimeException("order doesn't exist");
//        }
//
//        EmailModel emailModel = generatePdfImpl.createPdfByteArrray(order);
//
//
//        if(order.getUser().getLanguage().equals("SR")){
//            response.setHeader("Content-Disposition", "attachment; filename=Faktura_" + order.getId() + ".pdf");
//        }
//
//        if(order.getUser().getLanguage().equals("EN")){
//            response.setHeader("Content-Disposition", "attachment; filename=Receipt_" + order.getId() + ".pdf");
//        }
////        response.setContentType("application/pdf");
//
//        return new HttpEntity<byte[]>(IOUtils.toByteArray(emailModel.getDataSource().getInputStream()), headers);
//
//
//
////        HttpHeaders headers = new HttpHeaders();
////        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
////        headers.add("Pragma", "no-cache");
////        headers.add("Expires", "0");
////        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Faktura.pdf");
//
////        String filePath = "src/main/resources/jasper/Faktura" + order.getId() + ".pdf";
////        File file = new File(filePath);
////
////        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
////
////        return ResponseEntity.ok()
////                .headers(headers)
////                .contentLength(file.length())
////                .contentType(MediaType.parseMediaType("application/octet-stream"))
////                .body(resource);
//    }
//
//
//}
