package com.novko.pdf;

import com.novko.internal.cart.Cart;
import com.novko.internal.orders.Order;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;
import java.io.*;
import java.util.*;

@Service
public class GeneratePdfImpl implements GeneratePdf {

    //kreira byteArrayStream da bi se ubacio kao atachment na mail
    @Override
    public DataSource createPdfByteArrray(Order order) throws IOException {
        List<JasperReportModel> jasperReportModelList = new ArrayList<>();

        for (Cart cart : order.getCarts()) {
            JasperReportModel jasperReportModel = new JasperReportModel();
            jasperReportModel.setId(order.getId());  //order id
            jasperReportModel.setTotalAmount(order.getTotalAmount()); //mozda

            jasperReportModel.setProductcode(cart.getProduct().getCode());
            jasperReportModel.setProductName(cart.getProduct().getName());
            jasperReportModel.setProductQuantity(cart.getQuantity());
            jasperReportModel.setProductaAmountDin(cart.getProduct().getAmountDin()); //din
            jasperReportModel.setRabat(new Integer(20));  //20%

            jasperReportModelList.add(jasperReportModel);
        }

        DataSource attachment = null;
        try {

//            JRDataSource ds = new JRBeanCollectionDataSource(jasperReportModelList);

//            Resource report = new ClassPathResource("jasper/pdf.jasper");

//            JasperPrint jasperPrint = JasperFillManager.fillReport(report.getInputStream(), Collections.EMPTY_MAP, ds);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(jasperReportModelList);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("OrderDataSource", dataSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport("src/main/resources/jasper/pdf.jasper", parameters, new JREmptyDataSource());




            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
            attachment =  new ByteArrayDataSource(baos.toByteArray(), "application/pdf");

        } catch (JRException ex) {
            ex.printStackTrace();
        } catch (Exception  e) {
            e.printStackTrace();
        }

        return attachment;
    }


    //kreira pdf i smesta u resources/jasper folder
    @Override
    public void createPdf(Order order) throws IOException {

        List<JasperReportModel> jasperReportModelList = new ArrayList<>();

        for (Cart cart : order.getCarts()) {
            JasperReportModel jasperReportModel = new JasperReportModel();
            jasperReportModel.setId(order.getId());  //order id
            jasperReportModel.setTotalAmount(order.getTotalAmount()); //mozda

            jasperReportModel.setProductcode(cart.getProduct().getCode());
            jasperReportModel.setProductName(cart.getProduct().getName());
            jasperReportModel.setProductQuantity(cart.getQuantity());
            jasperReportModel.setProductaAmountDin(cart.getProduct().getAmountDin()); //din
            jasperReportModel.setRabat(new Integer(20));  //20%

            jasperReportModelList.add(jasperReportModel);
        }

        try {
//            JasperReport jasperReport = JasperCompileManager.compileReport("C:\\TEMP\\order.jrxml");
//            JRDataSource dataSource = new JREmptyDataSource();

//            InputStream inputStream = GeneratePdfImpl.class.getResourceAsStream("")
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(jasperReportModelList);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("OrderDataSource", dataSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport("src/main/resources/jasper/pdf.jasper", parameters, new JREmptyDataSource());

//            OutputStream outputStream = new FileOutputStream(new File("src/main/resources/jasper/reportFile.pdf"));
//            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            String pdfPath = "src/main/resources/jasper/reportFile" + order.getId();
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfPath + ".pdf");

        } catch (JRException ex) {
            ex.printStackTrace();
        } catch (Exception  e) {
            e.printStackTrace();
        }

//        File pdffile = File.createTempFile("my-invoice", ".pdf");
//
//        try(FileOutputStream fo = new FileOutputStream(pdffile)) {
//            final JasperReport report = loadTemplate();
//            final Map<String, Object> parameters = parameters(order);
//            final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(parameters);
//
//            // Render the PDF file
//            JasperReportsUtils.renderAsPdf(report, parameters, dataSource, fo);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }

    }


//    private JasperReport loadTemplate() throws JRException {
//        final InputStream reportInputStream = getClass().getResourceAsStream(invoice_template_path);
//        final JasperDesign jasperDesign = JRXmlLoader.load(reportInputStream);
//
//        return JasperCompileManager.compileReport(jasperDesign);
//    }


//    private Map<String, Object> parameters(List<JasperReportModel>  list) {
//        final Map<String, Object> parameters = new HashMap<>();
//        parameters.put("orderData",  list);
//
////        parameters.put("logo", getClass().getResourceAsStream(logo_path));
////        parameters.put("totalAmount",  order.getTotalAmount());
////        parameters.put("price",  order.getTotalOrderPriceDin());
//
//
////        parameters.put("REPORT_LOCALE", locale);
//
//        return parameters;
//    }


}
