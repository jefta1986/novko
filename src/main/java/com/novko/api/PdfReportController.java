package com.novko.api;


import com.itextpdf.text.DocumentException;
import com.novko.internal.orders.Order;
import com.novko.internal.orders.OrderService;
import com.novko.pdf.GeneratePdf;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


@RestController
@RequestMapping("/rest/reports")
public class PdfReportController {

    private GeneratePdf generatePdfImpl;
    private OrderService orderService;

    @Autowired
    public PdfReportController(GeneratePdf generatePdfImpl, OrderService orderService) {
        this.generatePdfImpl = generatePdfImpl;
        this.orderService = orderService;
    }

    @GetMapping(path = "/download/{id}")
    @ApiOperation(value = "ADMIN: Download receipt (PDF file) by OrderId")
    @PreAuthorize("hasRole('ADMIN')")
    public HttpEntity<byte[]> downloadPdfReceipt(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {

        Order order = orderService.findById(id);
        if(order == null){
            throw new RuntimeException("order doesn't exist");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            generatePdfImpl.createPdfFromOrder(outputStream, order);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        byte[] bytes = outputStream.toByteArray();
        DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");

        if(order.getUser().getLanguage().equals("SR")){
            response.setHeader("Content-Disposition", "attachment; filename=Faktura_" + order.getId() + ".pdf");
        } else {
            response.setHeader("Content-Disposition", "attachment; filename=Receipt_" + order.getId() + ".pdf");
        }
//        response.setContentType("application/pdf");

        return new HttpEntity<byte[]>(IOUtils.toByteArray(dataSource.getInputStream()), headers);
    }




//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Pragma", "no-cache");
//        headers.add("Expires", "0");
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Faktura.pdf");

//        String filePath = "src/main/resources/jasper/Faktura" + order.getId() + ".pdf";
//        File file = new File(filePath);
//
//        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentLength(file.length())
//                .contentType(MediaType.parseMediaType("application/octet-stream"))
//                .body(resource);


//    @GetMapping(path = "/download/{id}")
//    @ApiOperation(value = "ADMIN: Download receipt (PDF file)")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<byte[]> downloadReceipt(@PathVariable("id") Long id) {
//        Order order = orderService.findById(id);
//        if (order != null) {
//
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            try {
//                generatePdfImpl.createPdfFromOrder(outputStream, order);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (DocumentException e) {
//                e.printStackTrace();
//            }
//            byte[] bytes = outputStream.toByteArray();
//
//            //construct the pdf body part
////            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.parseMediaType(outputStream.toString()));
//            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + order.getId());
//            return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

//    @PostMapping(value = "/save")
//    @ApiOperation(value = "PDF generate and save file")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
//    public ResponseEntity<String> savePdf(@RequestParam("id") Long id) {
//
//        Order order = orderService.findById(id);
////        String dest = "C:\\pdfFile\\pdfSaved\\order" + order.getId() + ".pdf";
////        File file = new File(dest);
////        file.getParentFile().mkdirs();
//
//            try {
//                emailServiceImpl.sendMessageWithAttachment(
//                        "jefticivan0@gmail.com",
//                        "Porudzbina Green Land",
//                        "Poštovani,\nUspešno ste poručili proizvode.\nU prilogu Vam šaljemo fakturu za plaćanje",
//                        order);
//            } catch (MessagingException e) {
//                return new ResponseEntity<>("IO EXCEPTION", HttpStatus.OK);
//            }
//
//        return new ResponseEntity<>("pdf saved", HttpStatus.OK);
//    }


//    @PostMapping(value = "/save")
//    @ApiOperation(value = "PDF generate and save file")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
//    public ResponseEntity<String> savePdf() {
//        String dest = "C:\\pdfFile\\pdfSaved\\pdfFile.pdf";
//        File file = new File(dest);
//        file.getParentFile().mkdirs();
//
//        try {
//            pdfService.createPdf(dest);
//        } catch (IOException e) {
//            return new ResponseEntity<>("IO EXCEPTION", HttpStatus.OK);
//        } catch (DocumentException e) {
//            return new ResponseEntity<>("DocumentException", HttpStatus.OK);
//        }
//
//    return new ResponseEntity<>("pdf saved", HttpStatus.OK);
//    }


//    @GetMapping(value = "/{id}")
//    @ResponseBody
//    public String saveReport(@PathVariable Long id) throws Exception {
//
//        Order order = orderService.findById(id);
//
//        EmailModel emailModel = generatePdfImpl.createPdfByteArrray(order);
//
//        //izmeniti
//        if (order.getUser().getLanguage().equals("SR")) {
//            emailServiceImpl.sendMessageWithAttachment(
//                    order.getUser().getUsername(),
//                    "Porudzbina Green Land",
//                    "Poštovani,\nUspešno ste poručili proizvode.\nU prilogu Vam šaljemo fakturu za plaćanje",
//                    emailModel);
//        }
//
//
//        if (order.getUser().getLanguage().equals("EN")) {
//            emailServiceImpl.sendMessageWithAttachment(
//                    order.getUser().getUsername(),
//                    "Order from Green Land",
//                    "Dear, \nIn attachment we send you receipt.",
//                    emailModel);
//        }
//
//
//        return "OK";
//    }


}
