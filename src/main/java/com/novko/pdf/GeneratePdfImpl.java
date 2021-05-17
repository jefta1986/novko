package com.novko.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.novko.internal.cart.Cart;
import com.novko.internal.orders.Order;
import com.novko.internal.orders.OrderService;

import com.novko.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

@Service
public class GeneratePdfImpl implements GeneratePdf {

    public static final String FONT = "./src/main/resources/fonts/FreeSans.ttf";

    private OrderService orderService;

    @Lazy
    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }


    public void createPdfFromOrder(OutputStream outputStream, Order order) throws IOException, DocumentException {

        Document document = new Document(PageSize.A4);
        document.setMargins(20f, 20f, 20f, 20f);

        // test: lokalno kreira pdf fajl a ne salje mejl
//        File file = new File("c:\\images\\pdf.pdf");
//        PdfWriter.getInstance(document, new FileOutputStream(file));

        PdfWriter.getInstance(document, outputStream);

        if (order.getUser().getLanguage().equals("SR")) {
            serbianOutputStreamData(document, order);  //throws new DocumentException
        }else if(order.getUser().getLanguage().equals("EN")) {
            englishOutputStreamData(document, order); //throws new DocumentException
        }

    }

    private void englishOutputStreamData(Document document, Order order) throws DocumentException {
        User user = order.getUser();

        document.open();

        Font english = FontFactory.getFont(FONT, "Cp1250", true);
        english.setSize(10f);

        Font englishBold = FontFactory.getFont(FONT, "Cp1250", true, 10f,  Font.BOLD, BaseColor.BLACK);
//        Font serbian = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true);


        //header pocetak
        PdfPTable header = new PdfPTable(new float[]{ 12, 1, 12 });
        header.setWidthPercentage(100);

//header left: informacije o KOMPANIJI KUPCA
        PdfPCell headerLeft = new PdfPCell();

        headerLeft.setBorderColor(BaseColor.BLACK);
        headerLeft.setPaddingTop(5f);
        headerLeft.setPaddingBottom(5f);

        Paragraph customerCompanyName = new Paragraph(user.getFirma(), english);
        customerCompanyName.setAlignment(Paragraph.ALIGN_CENTER);
        headerLeft.addElement(customerCompanyName);

        Paragraph customerStreet = new Paragraph(user.getUlica(), english);
        customerStreet.setAlignment(Paragraph.ALIGN_CENTER);
        headerLeft.addElement(customerStreet);

        Paragraph customerCity = new Paragraph(user.getGrad(), english);
        customerCity.setAlignment(Paragraph.ALIGN_CENTER);
        headerLeft.addElement(customerCity);

        Paragraph customerPib = new Paragraph("PIB: " + user.getPib(), english);
        customerPib.setAlignment(Paragraph.ALIGN_CENTER);
        headerLeft.addElement(customerPib);

        Paragraph customerMb = new Paragraph("M.b. " + user.getMb(), english);
        customerMb.setAlignment(Paragraph.ALIGN_CENTER);
        headerLeft.addElement(customerMb);

        header.addCell(headerLeft);


//header middle: prazan prostor izmedju leve i desne tabele
        PdfPCell headerMiddle = new PdfPCell();
        headerMiddle.setBorder(0);
//        headerMiddle.setBorderColor(BaseColor.WHITE);

        header.addCell(headerMiddle);


//header right: informacije i green land solutions firmi
        PdfPCell headerRight = new PdfPCell();
        headerRight.setBorderColor(BaseColor.BLACK);
        headerLeft.setPaddingTop(5f);
        headerLeft.setPaddingBottom(5f);

        Paragraph greenLandSolutions = new Paragraph("GREEN LAND SOLUTIONS doo", englishBold);
        greenLandSolutions.setAlignment(Paragraph.ALIGN_CENTER);
        headerRight.addElement(greenLandSolutions);

        Paragraph kraljaMilutina = new Paragraph("Kralja Milutina 8/22", english);
        kraljaMilutina.setAlignment(Paragraph.ALIGN_CENTER);
        headerRight.addElement(kraljaMilutina);

        Paragraph smederevo = new Paragraph("11300 Smederevo", english);
        smederevo.setAlignment(Paragraph.ALIGN_CENTER);
        headerRight.addElement(smederevo);

        Paragraph pib = new Paragraph("PIB: 111776376", english);
        pib.setAlignment(Paragraph.ALIGN_CENTER);
        headerRight.addElement(pib);

//        Paragraph mb = new Paragraph("M.b. 21538337", english);
//        mb.setAlignment(Paragraph.ALIGN_CENTER);
//        headerRight.addElement(mb);

        header.addCell(headerRight);

        document.add(header); //header kraj (dodaje ceo header u pdf dokument)


        //podaci o useru: sa leve strane
        PdfPTable tekst = new PdfPTable(new float[]{ 12, 1, 12 });
        tekst.setWidthPercentage(100);

        PdfPCell tekstLeft = new PdfPCell();
        tekstLeft.setBorder(0);

//        headerLeft.setBorderColor(BaseColor.BLACK);

        tekstLeft.addElement(new Paragraph("\n\nCustomer code:  " + order.getUser().getCode(), english));
        tekstLeft.addElement(new Paragraph("Valute of payment:  ADVANCE PAYMENT", english)); //uradi: pitaj za ovaj datum sta znaci!!!


        tekst.addCell(tekstLeft);

        PdfPCell tekstMiddle = new PdfPCell();
        tekstMiddle.setBorder(0);
//        headerMiddle.setBorderColor(BaseColor.WHITE);

        tekst.addCell(tekstMiddle);

        PdfPCell tekstRight = new PdfPCell();
        tekstRight.setBorder(0);
//        tekstRight.setBorderColor(BaseColor.BLACK);

        Paragraph tekuciRacun = new Paragraph("Receipt of seller:  170-30047511001-19", english);
        tekuciRacun.setAlignment(Paragraph.ALIGN_CENTER);
        tekstRight.addElement(tekuciRacun);

//        tekstRight.addElement(new Paragraph(""));

        Paragraph racunOtpremnica = new Paragraph("\nRECEIPT NUMBER:  " + Order.getRacunBroj(), englishBold);
        tekstRight.addElement(racunOtpremnica);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.YYYY.");
        tekstRight.addElement(new Paragraph("Dispose date:  " + order.getOrderDate().toLocalDate().format(dateFormatter), english));
        tekstRight.addElement(new Paragraph("Date of tax:  " + order.getOrderDate().toLocalDate().format(dateFormatter), english));
        tekstRight.addElement(new Paragraph("Location of magacine:  Smederevo", english));

        tekst.addCell(tekstRight);

        document.add(tekst);

        //tabela
        PdfPTable table = new PdfPTable(new float[]{ 1, 3, 6, 1, 2, 3, 2, 2, 5 }); //custom width
        table.getDefaultCell().setBorder(0);
        table.setWidthPercentage(100);
        table.setSpacingAfter(15f);
        table.setSpacingBefore(15f);

        List<List<String>> dataset = getOrderDataEnglish(order);

        int brojac = 1;
        for (List<String> record : dataset) {
            for (String field : record) {
                if(brojac <= 9) {
                    table.addCell(new Phrase(field, englishBold));
                }else {
                    table.addCell(new Phrase(field, english));
                }
                brojac++;
            }
        }
        document.add(table);

        // racunica START
        //header pocetak
        PdfPTable totalTable = new PdfPTable(new float[]{ 14, 11 });
        totalTable.setWidthPercentage(100);

//header left: informacije o KOMPANIJI KUPCA
        PdfPCell totalLeft = new PdfPCell();

//        totalLeft.setBorderColorTop(BaseColor.WHITE);
//        totalLeft.setBorderColorLeft(BaseColor.WHITE);
//        totalLeft.setBorderColorRight(BaseColor.WHITE);

        totalLeft.setBorder(0);
        totalLeft.setBorderColorBottom(BaseColor.BLACK);

        totalLeft.setPaddingRight(15f); //RAZMAK IZMEDJU TABELA!!
        totalLeft.setPaddingTop(5f);
        totalLeft.setPaddingBottom(5f);

        Paragraph poreskaOsnovica = new Paragraph("Poreska osnovica sa ukupnim popustom:  " + order.getUser().getRabat().toString(), englishBold);
        poreskaOsnovica.setAlignment(Paragraph.ALIGN_RIGHT);
        totalLeft.addElement(poreskaOsnovica);

        Paragraph racunPrimio = new Paragraph("\n\n\n\n\nRačun primio:", english);
        racunPrimio.setAlignment(Paragraph.ALIGN_CENTER);
        totalLeft.addElement(racunPrimio);

        Paragraph totalMP = new Paragraph("M.P:", english);
        totalMP.setAlignment(Paragraph.ALIGN_RIGHT);
        totalLeft.addElement(totalMP);

        Paragraph crtaLevo = new Paragraph("______________________", english);
        crtaLevo.setAlignment(Paragraph.ALIGN_CENTER);
        totalLeft.addElement(crtaLevo);

        totalTable.addCell(totalLeft);


////header middle: prazan prostor izmedju leve i desne tabele
//        PdfPCell totalMiddle = new PdfPCell();
//        totalMiddle.setBorder(0);
////        headerMiddle.setBorderColor(BaseColor.WHITE);
//
//        totalMiddle.setPaddingTop(5f);
//        totalMiddle.setPaddingBottom(5f);
//
//        Paragraph totalMb = new Paragraph("\nM.P.", english);
//        totalMb.setAlignment(Paragraph.ALIGN_CENTER);
//        totalMiddle.addElement(totalMb);
//
//        totalTable.addCell(totalMiddle);


//header right: informacije i green land solutions firmi
        PdfPCell totalRight = new PdfPCell();

//        totalRight.setBorderColorTop(BaseColor.WHITE);
//        totalRight.setBorderColorLeft(BaseColor.WHITE);
//        totalRight.setBorderColorRight(BaseColor.WHITE);

        totalRight.setBorder(0);
        totalRight.setBorderColorBottom(BaseColor.BLACK);
        totalRight.setPaddingTop(5f);
        totalRight.setPaddingBottom(5f);

        Paragraph totalPDV = new Paragraph("PDV:  0", englishBold);
        greenLandSolutions.setAlignment(Paragraph.ALIGN_LEFT);
        totalRight.addElement(totalPDV);

        Paragraph ukupnoZaUplatu = new Paragraph("\nUkupno za uplatu sa\nPDV-om RSD  " + order.getTotalOrderPriceEuro().toString(), englishBold);
        kraljaMilutina.setAlignment(Paragraph.ALIGN_LEFT);
        totalRight.addElement(ukupnoZaUplatu);

        Paragraph racunIzdao = new Paragraph("\n\nRačun izdao", english);
        racunIzdao.setAlignment(Paragraph.ALIGN_CENTER);
        totalRight.addElement(racunIzdao);

        Paragraph crtaDesno = new Paragraph("\n______________________", english);
        crtaDesno.setAlignment(Paragraph.ALIGN_CENTER);
        totalRight.addElement(crtaDesno);

        totalTable.addCell(totalRight);

        document.add(totalTable);
        // racunica END


        document.add(new Paragraph("\nNAPOMENA: Ovaj dokument je sačinjen u skladu sa \"Zakonom o računovodstvu\", čl. 8. i 9. (Službeni glasnik RS, br.62/2013) i validan je bez potpisa i pečata.", english));

        document.close();
    }

    private void serbianOutputStreamData(Document document, Order order) throws DocumentException {
        User user = order.getUser();

        document.open();

        Font serbian = FontFactory.getFont(FONT, "Cp1250", true);
        serbian.setSize(10f);

        Font serbianBold = FontFactory.getFont(FONT, "Cp1250", true, 10f,  Font.BOLD, BaseColor.BLACK);
//        Font serbian = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, true);


        //header pocetak
        PdfPTable header = new PdfPTable(new float[]{ 12, 1, 12 });
        header.setWidthPercentage(100);

//header left: informacije o KOMPANIJI KUPCA
        PdfPCell headerLeft = new PdfPCell();

        headerLeft.setBorderColor(BaseColor.BLACK);
        headerLeft.setPaddingTop(5f);
        headerLeft.setPaddingBottom(5f);

        Paragraph customerCompanyName = new Paragraph(user.getFirma(), serbian);
        customerCompanyName.setAlignment(Paragraph.ALIGN_CENTER);
        headerLeft.addElement(customerCompanyName);

        Paragraph customerStreet = new Paragraph(user.getUlica(), serbian);
        customerStreet.setAlignment(Paragraph.ALIGN_CENTER);
        headerLeft.addElement(customerStreet);

        Paragraph customerCity = new Paragraph(user.getGrad(), serbian);
        customerCity.setAlignment(Paragraph.ALIGN_CENTER);
        headerLeft.addElement(customerCity);

        Paragraph customerPib = new Paragraph("PIB: " + user.getPib(), serbian);
        customerPib.setAlignment(Paragraph.ALIGN_CENTER);
        headerLeft.addElement(customerPib);

        Paragraph customerMb = new Paragraph("M.b. " + user.getMb(), serbian);
        customerMb.setAlignment(Paragraph.ALIGN_CENTER);
        headerLeft.addElement(customerMb);

        header.addCell(headerLeft);


//header middle: prazan prostor izmedju leve i desne tabele
        PdfPCell headerMiddle = new PdfPCell();
        headerMiddle.setBorder(0);
//        headerMiddle.setBorderColor(BaseColor.WHITE);

        header.addCell(headerMiddle);


//header right: informacije i green land solutions firmi
        PdfPCell headerRight = new PdfPCell();
        headerRight.setBorderColor(BaseColor.BLACK);
        headerLeft.setPaddingTop(5f);
        headerLeft.setPaddingBottom(5f);

        Paragraph greenLandSolutions = new Paragraph("GREEN LAND SOLUTIONS doo", serbianBold);
        greenLandSolutions.setAlignment(Paragraph.ALIGN_CENTER);
        headerRight.addElement(greenLandSolutions);

        Paragraph kraljaMilutina = new Paragraph("Kralja Milutina 8/22", serbian);
        kraljaMilutina.setAlignment(Paragraph.ALIGN_CENTER);
        headerRight.addElement(kraljaMilutina);

        Paragraph smederevo = new Paragraph("11300 Smederevo", serbian);
        smederevo.setAlignment(Paragraph.ALIGN_CENTER);
        headerRight.addElement(smederevo);

        Paragraph pib = new Paragraph("PIB: 111776376", serbian);
        pib.setAlignment(Paragraph.ALIGN_CENTER);
        headerRight.addElement(pib);

//        Paragraph mb = new Paragraph("M.b. 21538337", serbian);
//        mb.setAlignment(Paragraph.ALIGN_CENTER);
//        headerRight.addElement(mb);

        header.addCell(headerRight);

        document.add(header); //header kraj (dodaje ceo header u pdf dokument)


        //podaci o useru: sa leve strane
        PdfPTable tekst = new PdfPTable(new float[]{ 12, 1, 12 });
        tekst.setWidthPercentage(100);

        PdfPCell tekstLeft = new PdfPCell();
        tekstLeft.setBorder(0);

//        headerLeft.setBorderColor(BaseColor.BLACK);

        tekstLeft.addElement(new Paragraph("\n\nŠifra kupca:  " + order.getUser().getCode(), serbian));
        tekstLeft.addElement(new Paragraph("Valuta plaćanja:  AVANS", serbian)); //uradi: pitaj za ovaj datum sta znaci!!!


        tekst.addCell(tekstLeft);

        PdfPCell tekstMiddle = new PdfPCell();
        tekstMiddle.setBorder(0);
//        headerMiddle.setBorderColor(BaseColor.WHITE);

        tekst.addCell(tekstMiddle);


        //podaci o green land firmi: sa desne strane (tekuci racun, datum izdavanja, mesto izdavanja racuna)
        PdfPCell tekstRight = new PdfPCell();
        tekstRight.setBorder(0);
//        tekstRight.setBorderColor(BaseColor.BLACK);

        Paragraph tekuciRacun = new Paragraph("Tekući račun:  170-30047511001-19", serbian);
        tekuciRacun.setAlignment(Paragraph.ALIGN_CENTER);
        tekstRight.addElement(tekuciRacun);

//        tekstRight.addElement(new Paragraph(""));

        Paragraph racunOtpremnica = new Paragraph("\nRAČUN BROJ:  " + Order.getRacunBroj(), serbianBold);
        tekstRight.addElement(racunOtpremnica);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.YYYY.");
        tekstRight.addElement(new Paragraph("Datum računa:  " + order.getOrderDate().toLocalDate().format(dateFormatter), serbian));
        tekstRight.addElement(new Paragraph("Datum prometa:  " + order.getOrderDate().toLocalDate().format(dateFormatter), serbian));
        tekstRight.addElement(new Paragraph("Mesto izdavanja računa:  Smederevo", serbian));

        tekst.addCell(tekstRight);

        document.add(tekst);

        //tabela
        PdfPTable table = new PdfPTable(new float[]{ 1, 3, 6, 1, 2, 3, 2, 2, 5 }); //custom width
        table.getDefaultCell().setBorder(0);
        table.setWidthPercentage(100);
        table.setSpacingAfter(15f);
        table.setSpacingBefore(15f);

        List<List<String>> dataset = getOrderDataSerbian(order);

        int brojac = 1;
        for (List<String> record : dataset) {
            for (String field : record) {
                if(brojac <= 9) {
                    table.addCell(new Phrase(field, serbianBold));
                }else {
                    table.addCell(new Phrase(field, serbian));
                }
                brojac++;
            }
        }
        document.add(table);

        // racunica START
        //header pocetak
        PdfPTable totalTable = new PdfPTable(new float[]{ 14, 11 });
        totalTable.setWidthPercentage(100);

//header left: informacije o KOMPANIJI KUPCA
        PdfPCell totalLeft = new PdfPCell();

//        totalLeft.setBorderColorTop(BaseColor.WHITE);
//        totalLeft.setBorderColorLeft(BaseColor.WHITE);
//        totalLeft.setBorderColorRight(BaseColor.WHITE);

        totalLeft.setBorder(0);
        totalLeft.setBorderColorBottom(BaseColor.BLACK);

        totalLeft.setPaddingRight(15f); //RAZMAK IZMEDJU TABELA!!
        totalLeft.setPaddingTop(5f);
        totalLeft.setPaddingBottom(5f);

        Paragraph poreskaOsnovica = new Paragraph("Poreska osnovica sa ukupnim popustom:  " + order.getUser().getRabat().toString(), serbianBold);
        poreskaOsnovica.setAlignment(Paragraph.ALIGN_RIGHT);
        totalLeft.addElement(poreskaOsnovica);

        Paragraph racunPrimio = new Paragraph("\n\n\n\n\nRačun primio:", serbian);
        racunPrimio.setAlignment(Paragraph.ALIGN_CENTER);
        totalLeft.addElement(racunPrimio);

        Paragraph totalMP = new Paragraph("M.P:", serbian);
        totalMP.setAlignment(Paragraph.ALIGN_RIGHT);
        totalLeft.addElement(totalMP);

        Paragraph crtaLevo = new Paragraph("______________________", serbian);
        crtaLevo.setAlignment(Paragraph.ALIGN_CENTER);
        totalLeft.addElement(crtaLevo);

        totalTable.addCell(totalLeft);


////header middle: prazan prostor izmedju leve i desne tabele
//        PdfPCell totalMiddle = new PdfPCell();
//        totalMiddle.setBorder(0);
////        headerMiddle.setBorderColor(BaseColor.WHITE);
//
//        totalMiddle.setPaddingTop(5f);
//        totalMiddle.setPaddingBottom(5f);
//
//        Paragraph totalMb = new Paragraph("\nM.P.", english);
//        totalMb.setAlignment(Paragraph.ALIGN_CENTER);
//        totalMiddle.addElement(totalMb);
//
//        totalTable.addCell(totalMiddle);


//header right: informacije i green land solutions firmi
        PdfPCell totalRight = new PdfPCell();

//        totalRight.setBorderColorTop(BaseColor.WHITE);
//        totalRight.setBorderColorLeft(BaseColor.WHITE);
//        totalRight.setBorderColorRight(BaseColor.WHITE);

        totalRight.setBorder(0);
        totalRight.setBorderColorBottom(BaseColor.BLACK);
        totalRight.setPaddingTop(5f);
        totalRight.setPaddingBottom(5f);

        Paragraph totalPDV = new Paragraph("PDV:  0", serbianBold);
        greenLandSolutions.setAlignment(Paragraph.ALIGN_LEFT);
        totalRight.addElement(totalPDV);

        Paragraph ukupnoZaUplatu = new Paragraph("\nUkupno za uplatu sa\nPDV-om RSD  " + order.getTotalOrderPriceDin().toString(), serbianBold);
        kraljaMilutina.setAlignment(Paragraph.ALIGN_LEFT);
        totalRight.addElement(ukupnoZaUplatu);

        Paragraph racunIzdao = new Paragraph("\n\nRačun izdao", serbian);
        racunIzdao.setAlignment(Paragraph.ALIGN_CENTER);
        totalRight.addElement(racunIzdao);

        Paragraph crtaDesno = new Paragraph("\n______________________", serbian);
        crtaDesno.setAlignment(Paragraph.ALIGN_CENTER);
        totalRight.addElement(crtaDesno);

        totalTable.addCell(totalRight);

        document.add(totalTable);
        // racunica END


        document.add(new Paragraph("\nNAPOMENA: Ovaj dokument je sačinjen u skladu sa \"Zakonom o računovodstvu\", čl. 8. i 9. (Službeni glasnik RS, br.62/2013) i validan je bez potpisa i pečata.", serbian));

        document.close();
    }

    private List<List<String>> getOrderDataSerbian(Order order) {
        List<List<String>> data = new ArrayList<>();
        String[] headers = {"R. br.", "Šifra artikla", "Naziv artikla", "Kol.", "JM", "Cena JM -\nRSD", "Akcija/Popust %", "PDV", "Ukupno RSD"};

        final String popust  = Double.valueOf(order.getUser().getRabat() * 100).toString();
        final String pdv = "0";

        data.add(Arrays.asList(headers)); //dodaje header-e

        List<Cart> carts = order.getCarts();
        for (int i = 0; i < carts.size(); i++) {
            List<String> dataLine = new ArrayList<>();

            String[] row = {String.valueOf(i + 1), carts.get(i).getProduct().getCode(), carts.get(i).getProduct().getName(), carts.get(i).getQuantity().toString(), "KOM", carts.get(i).getAmountDin().toString(), popust, pdv, String.valueOf(carts.get(i).getQuantity() * carts.get(i).getAmountDin())};
            for (String column : row) {
                dataLine.add(column);
            }
            data.add(dataLine);
        }
        return data;
    }

    private List<List<String>> getOrderDataEnglish(Order order) {
        List<List<String>> data = new ArrayList<>();
        String[] headers = {"R.Num.", "Product code", "Product name", "Quantity", "Number", "Price by num -\nEUR", "Discount in %", "PDV", "Total in EUR"};

        final String popust  = Double.valueOf(order.getUser().getRabat() * 100).toString();
        final String pdv = "0";

        data.add(Arrays.asList(headers)); //dodaje header-e

        List<Cart> carts = order.getCarts();
        for (int i = 0; i < carts.size(); i++) {
            List<String> dataLine = new ArrayList<>();

            String[] row = {String.valueOf(i + 1), carts.get(i).getProduct().getCode(), carts.get(i).getProduct().getName(), carts.get(i).getQuantity().toString(), "KOM", carts.get(i).getAmountEuro().toString(), popust, pdv, String.valueOf(carts.get(i).getQuantity() * carts.get(i).getAmountEuro())};
            for (String column : row) {
                dataLine.add(column);
            }
            data.add(dataLine);
        }
        return data;
    }

}
