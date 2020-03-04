package com.novko.pdf;

import com.novko.internal.cart.Cart;
import com.novko.internal.orders.Order;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class GeneratePdfImpl implements GeneratePdf {


    private JpaTransactions jpaTransactionsImpl;

    @Autowired
    public void setJpaTransactionsImpl(JpaTransactions jpaTransactionsImpl) {
        this.jpaTransactionsImpl = jpaTransactionsImpl;
    }


    //kreira byteArrayStream da bi se ubacio kao atachment na mail
    @Override
    public EmailModel createPdfByteArrray(Order order) throws IOException {

        List<JasperReportModel> jasperReportModelList = new ArrayList<>();
        String language = order.getUser().getLanguage();

        for (Cart cart : order.getCarts()) {
            JasperReportModel jasperReportModel = new JasperReportModel();
            jasperReportModel.setOrderId(order.getId());  //order id

            //placanje po productu (quantity*cenaProizvoda)



            //ukupno za uplatu
            jasperReportModel.setTotalAmountDin(order.getTotalAmountDin()); //din
            jasperReportModel.setTotalAmountEuro(order.getTotalAmountEuro()); //euro


            jasperReportModel.setRabat(order.getUser().getRabat()); //pa se u jasperu konvertuje i prikazuje u procentima %


            jasperReportModel.setProductcode(cart.getProduct().getCode());
            jasperReportModel.setProductName(cart.getProduct().getName());
            jasperReportModel.setProductQuantity(cart.getQuantity());

            if (language.equals("SR")) {
                jasperReportModel.setProductAmountDin(cart.getProduct().getAmountDin()); //din
            } else {
                jasperReportModel.setProductAmountEuro(cart.getProduct().getAmountEuro());  //euro
            }

            jasperReportModelList.add(jasperReportModel);
        }

        DataSource attachment = null;
        String fileName = null;
        try {

//            JRDataSource ds = new JRBeanCollectionDataSource(jasperReportModelList);

//            Resource report = new ClassPathResource("jasper/pdf.jasper");

//            JasperPrint jasperPrint = JasperFillManager.fillReport(report.getInputStream(), Collections.EMPTY_MAP, ds);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(jasperReportModelList);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("OrderDataSource", dataSource);

            JasperPrint jasperPrint;
            if (language.equals("SR")) {
                jasperPrint = JasperFillManager.fillReport("src/main/resources/jasper/pdf_sr.jasper", parameters, new JREmptyDataSource());
                fileName = new String("Faktura_" + order.getId() + ".pdf");
            } else {
                jasperPrint = JasperFillManager.fillReport("src/main/resources/jasper/pdf_en.jasper", parameters, new JREmptyDataSource());
                fileName = new String("Receipt_" + order.getId() + ".pdf");
            }
//            JasperPrint jasperPrint = JasperFillManager.fillReport("src/main/resources/jasper/pdf.jasper", parameters, new JREmptyDataSource());


            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
            attachment = new ByteArrayDataSource(baos.toByteArray(), "application/pdf");

            if (attachment != null) {
                for (JasperReportModel transation : jasperReportModelList) {
                    jpaTransactionsImpl.save(transation);
                }
            }

        } catch (JRException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (fileName == null) {
            return new EmailModel(attachment);
        }

        return new EmailModel(attachment, fileName);
    }


    //kreira pdf i smesta u resources/jasper folder
    @Override
    public void createPdf(Order order) throws IOException {

        String language = order.getUser().getLanguage();
        List<JasperReportModel> jasperReportModelList = new ArrayList<>();

        for (Cart cart : order.getCarts()) {
            JasperReportModel jasperReportModel = new JasperReportModel();
            jasperReportModel.setOrderId(order.getId());  //order id
            jasperReportModel.setTotalAmountDin(order.getTotalAmountDin()); //din
            jasperReportModel.setTotalAmountEuro(order.getTotalAmountEuro()); //euro
            jasperReportModel.setRabat(order.getUser().getRabat());  //pa se u jasperu konvertuje i prikazuje u procentima %

            jasperReportModel.setProductcode(cart.getProduct().getCode());
            jasperReportModel.setProductName(cart.getProduct().getName());
            jasperReportModel.setProductQuantity(cart.getQuantity());


            if (language.equals("SR")) {
                jasperReportModel.setProductAmountDin(cart.getProduct().getAmountDin()); //din
            } else {
                jasperReportModel.setProductAmountEuro(cart.getProduct().getAmountEuro()); //euro
            }

            jasperReportModelList.add(jasperReportModel);
        }

        try {
//            JasperReport jasperReport = JasperCompileManager.compileReport("C:\\TEMP\\order.jrxml");
//            JRDataSource dataSource = new JREmptyDataSource();

//            InputStream inputStream = GeneratePdfImpl.class.getResourceAsStream("")
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(jasperReportModelList);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("OrderDataSource", dataSource);


            JasperPrint jasperPrint;
            if (language.equals("SR")) {
                jasperPrint = JasperFillManager.fillReport("src/main/resources/jasper/pdf_sr.jasper", parameters, new JREmptyDataSource());

            } else {
                jasperPrint = JasperFillManager.fillReport("src/main/resources/jasper/pdf_en.jasper", parameters, new JREmptyDataSource());

            }
//            JasperPrint jasperPrint = JasperFillManager.fillReport("src/main/resources/jasper/pdf.jasper", parameters, new JREmptyDataSource());

//            OutputStream outputStream = new FileOutputStream(new File("src/main/resources/jasper/reportFile.pdf"));
//            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            String pdfPath = "src/main/resources/jasper/Faktura_" + order.getId();
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfPath + ".pdf");

        } catch (JRException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
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
