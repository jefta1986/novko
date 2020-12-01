package com.novko.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.novko.internal.cart.Cart;
import com.novko.internal.orders.Order;
import com.novko.internal.orders.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service("pdfService")
public class GeneratePdfImpl implements GeneratePdf {


    private OrderService orderService;

    @Autowired
    public GeneratePdfImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void createPdf(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100);
        List<List<String>> dataset = getData();
        for (List<String> record : dataset) {
            for (String field : record) {
                table.addCell(field);
            }
        }
        document.add(table);
        document.close();
    }

    public void createPdfFromOrder(OutputStream outputStream, Order order) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);
        document.open();
//        PdfPTable table = new PdfPTable(9);
//        table.setWidthPercentage(100);

        PdfPTable table = new PdfPTable(new float[]{ 1, 3, 4, 1, 2, 3, 2, 2, 4 }); //custom width
        table.setWidthPercentage(100);


        List<List<String>> dataset = getOrderData(order);
        for (List<String> record : dataset) {
            for (String field : record) {
                table.addCell(field);
            }
        }
        document.add(table);
        document.close();
    }

    @Override
    public EmailModel createPdfByteArrray(Order order) throws IOException {
        return null;
    }

    private List<List<String>> getOrderData(Order order) {
        List<List<String>> data = new ArrayList<>();
        String[] headers = {"R.Br.", "Šifra artikla", "Naziv artikla", "Kol.", "J.M.", "Cena JM - RSD", "Akcija/\nPopust %", "PDV", "Ukupno RSD"};

        final String popust = "20";  //is user objecta uzmi marzu
        final String pdv = "0";

        data.add(Arrays.asList(headers)); //dodaje header-e

        List<Cart> carts = order.getCarts();
        for (int i = 0; i < carts.size(); i++) {
            List<String> dataLine = new ArrayList<>();

            String[] row = {String.valueOf(i + 1), carts.get(i).getProduct().getCode(), carts.get(i).getProduct().getName(), carts.get(i).getQuantity().toString(), "KOM", carts.get(i).getAmountDin().toString(), popust, pdv, order.getTotalAmountDin().toString()};
            for (String column : row) {
                dataLine.add(column);
            }
            data.add(dataLine);
        }
        return data;
    }

    private List<List<String>> getData() {
        List<List<String>> data = new ArrayList<List<String>>();
        String[] tableTitleList = {" Title", " (Re)set", " Obs", " Mean", " Std.Dev", " Min", " Max", "Unit"};
        data.add(Arrays.asList(tableTitleList));
        for (int i = 0; i < 10; ) {
            List<String> dataLine = new ArrayList<String>();
            i++;
            for (int j = 0; j < tableTitleList.length; j++) {
                dataLine.add(tableTitleList[j] + " " + i);
            }
            data.add(dataLine);
        }
        return data;
    }


    //kreira byteArrayStream da bi se ubacio kao atachment na mail
//    @Override
//    public EmailModel createPdfByteArrray(Order order) throws IOException {
//
//        List<JasperReportModel> jasperReportModelList = new ArrayList<>();
//        String language = order.getUser().getLanguage();
//
//        for (Cart cart : order.getCarts()) {
//            JasperReportModel jasperReportModel = new JasperReportModel();
//            jasperReportModel.setOrderId(order.getId());  //order id
//
//            //placanje po productu (quantity*cenaProizvoda)
//
//
//
//            //ukupno za uplatu
//            jasperReportModel.setTotalAmountDin(order.getTotalAmountDin()); //din
//            jasperReportModel.setTotalAmountEuro(order.getTotalAmountEuro()); //euro
//
//
//            jasperReportModel.setRabat(order.getUser().getRabat()); //pa se u jasperu konvertuje i prikazuje u procentima %
//
//
//            jasperReportModel.setProductcode(cart.getProduct().getCode());
//            jasperReportModel.setProductName(cart.getProduct().getName());
//            jasperReportModel.setProductQuantity(cart.getQuantity());
//
//            if (language.equals("SR")) {
//                jasperReportModel.setProductAmountDin(cart.getProduct().getAmountDin()); //din
//            } else {
//                jasperReportModel.setProductAmountEuro(cart.getProduct().getAmountEuro());  //euro
//            }
//
//            jasperReportModelList.add(jasperReportModel);
//        }
//
//        DataSource attachment = null;
//        String fileName = null;
//        try {
//
////            JRDataSource ds = new JRBeanCollectionDataSource(jasperReportModelList);
//
////            Resource report = new ClassPathResource("jasper/pdf.jasper");
//
////            JasperPrint jasperPrint = JasperFillManager.fillReport(report.getInputStream(), Collections.EMPTY_MAP, ds);
//            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(jasperReportModelList);
//
//            Map<String, Object> parameters = new HashMap<>();
//            parameters.put("OrderDataSource", dataSource);
//
//            JasperPrint jasperPrint;
//            if (language.equals("SR")) {
//                jasperPrint = JasperFillManager.fillReport("src/main/resources/jasper/pdf_sr.jasper", parameters, new JREmptyDataSource());
//                fileName = new String("Faktura_" + order.getId() + ".pdf");
//            } else {
//                jasperPrint = JasperFillManager.fillReport("src/main/resources/jasper/pdf_en.jasper", parameters, new JREmptyDataSource());
//                fileName = new String("Receipt_" + order.getId() + ".pdf");
//            }
////            JasperPrint jasperPrint = JasperFillManager.fillReport("src/main/resources/jasper/pdf.jasper", parameters, new JREmptyDataSource());
//
//
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
//            attachment = new ByteArrayDataSource(baos.toByteArray(), "application/pdf");
//
//            if (attachment != null) {
//                for (JasperReportModel transation : jasperReportModelList) {
//                    jpaTransactionsImpl.save(transation);
//                }
//            }
//
//        } catch (JRException ex) {
//            ex.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (fileName == null) {
//            return new EmailModel(attachment);
//        }
//
//        return new EmailModel(attachment, fileName);
//    }
//
//
//    //kreira pdf i smesta u resources/jasper folder
//    @Override
//    public void createPdf(Order order) throws IOException {
//
//        String language = order.getUser().getLanguage();
//        List<JasperReportModel> jasperReportModelList = new ArrayList<>();
//
//        for (Cart cart : order.getCarts()) {
//            JasperReportModel jasperReportModel = new JasperReportModel();
//            jasperReportModel.setOrderId(order.getId());  //order id
//            jasperReportModel.setTotalAmountDin(order.getTotalAmountDin()); //din
//            jasperReportModel.setTotalAmountEuro(order.getTotalAmountEuro()); //euro
//            jasperReportModel.setRabat(order.getUser().getRabat());  //pa se u jasperu konvertuje i prikazuje u procentima %
//
//            jasperReportModel.setProductcode(cart.getProduct().getCode());
//            jasperReportModel.setProductName(cart.getProduct().getName());
//            jasperReportModel.setProductQuantity(cart.getQuantity());
//
//
//            if (language.equals("SR")) {
//                jasperReportModel.setProductAmountDin(cart.getProduct().getAmountDin()); //din
//            } else {
//                jasperReportModel.setProductAmountEuro(cart.getProduct().getAmountEuro()); //euro
//            }
//
//            jasperReportModelList.add(jasperReportModel);
//        }
//
//        try {
////            JasperReport jasperReport = JasperCompileManager.compileReport("C:\\TEMP\\order.jrxml");
////            JRDataSource dataSource = new JREmptyDataSource();
//
////            InputStream inputStream = GeneratePdfImpl.class.getResourceAsStream("")
//            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(jasperReportModelList);
//
//            Map<String, Object> parameters = new HashMap<>();
//            parameters.put("OrderDataSource", dataSource);
//
//
//            JasperPrint jasperPrint;
//            if (language.equals("SR")) {
//                jasperPrint = JasperFillManager.fillReport("src/main/resources/jasper/pdf_sr.jasper", parameters, new JREmptyDataSource());
//
//            } else {
//                jasperPrint = JasperFillManager.fillReport("src/main/resources/jasper/pdf_en.jasper", parameters, new JREmptyDataSource());
//
//            }
////            JasperPrint jasperPrint = JasperFillManager.fillReport("src/main/resources/jasper/pdf.jasper", parameters, new JREmptyDataSource());
//
////            OutputStream outputStream = new FileOutputStream(new File("src/main/resources/jasper/reportFile.pdf"));
////            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
//
//            String pdfPath = "src/main/resources/jasper/Faktura_" + order.getId();
//            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfPath + ".pdf");
//
//        } catch (JRException ex) {
//            ex.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
////        File pdffile = File.createTempFile("my-invoice", ".pdf");
////
////        try(FileOutputStream fo = new FileOutputStream(pdffile)) {
////            final JasperReport report = loadTemplate();
////            final Map<String, Object> parameters = parameters(order);
////            final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(parameters);
////
////            // Render the PDF file
////            JasperReportsUtils.renderAsPdf(report, parameters, dataSource, fo);
////
////        }catch (Exception e){
////            e.printStackTrace();
////        }
//
//    }
//
//
////    private JasperReport loadTemplate() throws JRException {
////        final InputStream reportInputStream = getClass().getResourceAsStream(invoice_template_path);
////        final JasperDesign jasperDesign = JRXmlLoader.load(reportInputStream);
////
////        return JasperCompileManager.compileReport(jasperDesign);
////    }
//
//
////    private Map<String, Object> parameters(List<JasperReportModel>  list) {
////        final Map<String, Object> parameters = new HashMap<>();
////        parameters.put("orderData",  list);
////
//////        parameters.put("logo", getClass().getResourceAsStream(logo_path));
//////        parameters.put("totalAmount",  order.getTotalAmount());
//////        parameters.put("price",  order.getTotalOrderPriceDin());
////
////
//////        parameters.put("REPORT_LOCALE", locale);
////
////        return parameters;
////    }


}
