package com.novko.pdf;

import com.novko.internal.orders.Order;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.stereotype.Service;
import org.springframework.ui.jasperreports.JasperReportsUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class GeneratePdfImpl implements GeneratePdf{

    private final String invoice_template_path = "/jasper/invoice_template.jrxml";
    private static final String logo_path = "/jasper/images/stackextend-logo.png";


    @Override
    public void createPdf(Order order, Locale locale) throws IOException {

        File pdffile = File.createTempFile("my-invoice", ".pdf");

        try(FileOutputStream fo = new FileOutputStream(pdffile)) {
            final JasperReport report = loadTemplate();
            final Map<String, Object> parameters = parameters(order, locale);
            final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList("Invoice"));

            // Render the PDF file
            JasperReportsUtils.renderAsPdf(report, parameters, dataSource, fo);

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    private JasperReport loadTemplate() throws JRException {
        final InputStream reportInputStream = getClass().getResourceAsStream(invoice_template_path);
        final JasperDesign jasperDesign = JRXmlLoader.load(reportInputStream);

        return JasperCompileManager.compileReport(jasperDesign);
    }


    private Map<String, Object> parameters(Order order, Locale locale) {
        final Map<String, Object> parameters = new HashMap<>();

        parameters.put("logo", getClass().getResourceAsStream(logo_path));
        parameters.put("order",  order);
        parameters.put("REPORT_LOCALE", locale);

        return parameters;
    }


}
