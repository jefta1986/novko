package com.novko.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.novko.internal.cart.Cart;
import com.novko.internal.orders.Order;
import com.novko.internal.orders.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.List;

@Service("pdfService")
public class GeneratePdfImpl implements GeneratePdf {

    public static final String FONT = "./src/main/resources/fonts/FreeSans.ttf";
//    public static final String DEST = "C:\\itext\\czech_example.pdf";


    private OrderService orderService;

    @Autowired
    public GeneratePdfImpl(OrderService orderService) {
        this.orderService = orderService;
    }

//    @Override
//    public void createPdf(String dest) throws IOException, DocumentException {
//        Document document = new Document();
//        PdfWriter.getInstance(document, new FileOutputStream(dest));
//        document.open();
//        PdfPTable table = new PdfPTable(8);
//        table.setWidthPercentage(100);
//        List<List<String>> dataset = getData();  //proveri
//        for (List<String> record : dataset) {
//            for (String field : record) {
//                table.addCell(field);
//            }
//        }
//        document.add(table);
//        document.close();
//    }

    public void createPdfFromOrder(OutputStream outputStream, Order order) throws IOException, DocumentException {
//        File file = new File(DEST);
//        file.getParentFile().mkdirs();

        Document document = new Document(PageSize.A4);
        document.setMargins(20f, 20f, 20f, 20f);

//        PdfWriter.getInstance(document, new FileOutputStream(file));

        PdfWriter.getInstance(document, outputStream);

        document.open();

        Font serbian = FontFactory.getFont(FONT, "Cp1250", true);
        serbian.setSize(10f);
//        Font serbian = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true);


        //header
        PdfPTable header = new PdfPTable(new float[]{ 12, 1, 12 });
        header.setWidthPercentage(100);

        PdfPCell headerLeft = new PdfPCell();
        headerLeft.setBorderColor(BaseColor.BLACK);
        headerLeft.setPaddingTop(5f);
        headerLeft.setPaddingBottom(5f);



        headerLeft.addElement(new Paragraph(""));

        header.addCell(headerLeft);

        PdfPCell headerMiddle = new PdfPCell();
        headerMiddle.setBorder(0);
//        headerMiddle.setBorderColor(BaseColor.WHITE);

        header.addCell(headerMiddle);

        PdfPCell headerRight = new PdfPCell();
        headerRight.setBorderColor(BaseColor.BLACK);
        headerLeft.setPaddingTop(5f);
        headerLeft.setPaddingBottom(5f);

        headerRight.addElement(new Paragraph("GREEN LAND SOLUTIONS doo", serbian));

//        headerRight.addElement(new Paragraph("Testing of letters Č,Ć,Š,Ž,Đ Testing of letters \u010c,\u0106,\u0160,\u017d,\u0110", serbian));
        headerRight.addElement(new Paragraph("Kralja Milana 8/22", serbian));
        headerRight.addElement(new Paragraph("11300 Smederevo", serbian));


        header.addCell(headerRight);

        document.add(header);


        //podaci o useru
        PdfPTable tekst = new PdfPTable(new float[]{ 12, 1, 12 });
        tekst.setWidthPercentage(100);

        PdfPCell tekstLeft = new PdfPCell();
        tekstLeft.setBorder(0);

//        headerLeft.setBorderColor(BaseColor.BLACK);

        tekstLeft.addElement(new Paragraph("\nŠifra kupca:", serbian));
        tekstLeft.addElement(new Paragraph("Valuta plaćanja:  AVANS", serbian));


        tekst.addCell(tekstLeft);

        PdfPCell tekstMiddle = new PdfPCell();
        tekstMiddle.setBorder(0);
//        headerMiddle.setBorderColor(BaseColor.WHITE);

        tekst.addCell(tekstMiddle);

        PdfPCell tekstRight = new PdfPCell();
        tekstRight.setBorder(0);
//        tekstRight.setBorderColor(BaseColor.BLACK);

        tekstRight.addElement(new Paragraph("Tekući račun:  170-30047511001-19", serbian));
        tekstRight.addElement(new Paragraph("RAČUN BROJ", serbian));
        tekstRight.addElement(new Paragraph("Datum računa:", serbian));
        tekstRight.addElement(new Paragraph("Datum prometa:", serbian));
        tekstRight.addElement(new Paragraph("Mesto izdavanja računa:  Smederevo", serbian));

        tekst.addCell(tekstRight);

        document.add(tekst);

        //tabela
        PdfPTable table = new PdfPTable(new float[]{ 2, 3, 4, 2, 2, 4, 2, 2, 4 }); //custom width
        table.setWidthPercentage(100);
        table.setSpacingAfter(10f);
        table.setSpacingBefore(10f);

        //proveri: treba da budu dve metode za SR i EN, takodje i sve ovo iznad treba da se smesti u dve metode
        List<List<String>> dataset = getOrderData(order);
//        List<List<String>> dataset = getOrderDataEN(order);
//        List<List<String>> dataset = getOrderDataSR(order);


        for (List<String> record : dataset) {
            for (String field : record) {
                table.addCell(new Phrase(field, serbian));
            }
        }
        document.add(table);

        document.add(new Paragraph("NAPOMENA: Ovaj dokument je sačinjen u skladu sa \"Zakonom o računovodstvu\", čl. 8. i 9. (Službeni glasnik RS, br.62/2013) i validan je bez potpisa i pečata.", serbian));

        document.close();
    }

//    @Override
//    public EmailModel createPdfByteArrray(Order order) throws IOException {
//        return null;
//    }

    private List<List<String>> getOrderData(Order order) {
        List<List<String>> data = new ArrayList<>();
        String[] headers = {"R.br.", "Šifra artikla", "Naziv artikla", "Kol.", "JM", "Cena JM - RSD", "Akcija/\nPopust %", "PDV", "Ukupno RSD"};

        final String popust  = Double.valueOf(order.getUser().getRabat() * 100).toString();
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

//    private List<List<String>> getData() {
//        List<List<String>> data = new ArrayList<List<String>>();
//        String[] tableTitleList = {" Title", " (Re)set", " Obs", " Mean", " Std.Dev", " Min", " Max", "Unit"};
//        data.add(Arrays.asList(tableTitleList));
//        for (int i = 0; i < 10; ) {
//            List<String> dataLine = new ArrayList<String>();
//            i++;
//            for (int j = 0; j < tableTitleList.length; j++) {
//                dataLine.add(tableTitleList[j] + " " + i);
//            }
//            data.add(dataLine);
//        }
//        return data;
//    }


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
