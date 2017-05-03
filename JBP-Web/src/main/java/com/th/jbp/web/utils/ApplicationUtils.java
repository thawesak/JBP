package com.th.jbp.web.utils;
//package co.th.tobeone.beyond.web.utils;
//
//import java.awt.Color;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.StringReader;
//import java.math.BigDecimal;
//import java.net.URL;
//import java.sql.Timestamp;
//import java.text.DecimalFormat;
//import java.text.MessageFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang3.StringUtils;
//
//import com.lowagie.text.Document;
//import com.lowagie.text.DocumentException;
//import com.lowagie.text.Element;
//import com.lowagie.text.Font;
//import com.lowagie.text.PageSize;
//import com.lowagie.text.html.simpleparser.HTMLWorker;
//import com.lowagie.text.html.simpleparser.StyleSheet;
//import com.lowagie.text.pdf.BaseFont;
//import com.lowagie.text.pdf.PdfWriter;
//
//import co.th.tobeone.beyond.jpa.entity.BmmEWalletTrans;
//import co.th.tobeone.beyond.web.vm.BaseVM;
//
//public class ApplicationUtils {
//
//	private static ApplicationUtils applicationUtils = null;
//
//	private ApplicationUtils() {
//
//	}
//
//	public static ApplicationUtils getInstance() {
//		if (applicationUtils == null) {
//			applicationUtils = new ApplicationUtils();
//		}
//		return applicationUtils;
//	}
//
//	public String printInvoice(BmmEWalletTrans eWalletTrans, int vat) throws Exception {
//
//		Document document = null;
//		URL configTemplatePath = null;
//		String fileName = "";
//		try {
//			final DecimalFormat DF = new DecimalFormat("#,##0.00");
//
//			String taxId = "0893894983123";
//			String dateTime = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(eWalletTrans.getCreateDate());
//			String number = StringUtils.leftPad(String.valueOf(eWalletTrans.geteWalletTransId()), 6, "0");
//			String info = "Mobile Topup";
//			String price = DF.format(eWalletTrans.getAmount());
//			String excludeVat = DF.format(eWalletTrans.getAmount().subtract(
//					eWalletTrans.getAmount().multiply(BigDecimal.valueOf(vat).divide(BigDecimal.valueOf(100)))));
//			String _vat = DF
//					.format(eWalletTrans.getAmount().multiply(BigDecimal.valueOf(vat).divide(BigDecimal.valueOf(100))));
//
//			fileName = String.format("INV_%s.pdf", number);
//			configTemplatePath = getClass().getClassLoader().getResource("/config/pdf/invoice-layout.html");
//			String templatePath = configTemplatePath.getPath();
//			byte[] template = FileUtils.readFileToByteArray(new File(templatePath));
//			HashMap<String, Object> map = new HashMap<>();
//			map.put("font_factory", new com.lowagie.text.FontProvider() {
//				BaseFont baseFont = null;
//
//				@Override
//				public boolean isRegistered(String arg0) {
//					return true;
//				}
//
//				@Override
//				public Font getFont(String arg0, String arg1, boolean arg2, float arg3, int arg4, Color arg5) {
//					URL configFontPath = getClass().getClassLoader().getResource("/config/pdf/tahoma.ttf");
//					try {
//						String fontPath = configFontPath.getPath();
//						baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//					} catch (DocumentException e) {
//						e.printStackTrace();
//						return new Font();
//					} catch (IOException e) {
//						e.printStackTrace();
//						return new Font();
//					}
//					return new Font(baseFont, 8);
//				}
//			});
//
//			document = new Document(PageSize.A4);
//			PdfWriter.getInstance(document, new FileOutputStream(new File(BaseVM.TEMP_PATH, fileName)));
//			document.open();
//			StyleSheet styles = new StyleSheet();
//
//			String html = MessageFormat.format(new String(template, "utf-8"), taxId, dateTime, number, info, price,
//					price, excludeVat, _vat);
//			ArrayList<?> parts = HTMLWorker.parseToList(new StringReader(html), styles, map);
//			for (int i = 0; i < parts.size(); i++) {
//				Element element = (Element) parts.get(i);
//				document.add(element);
//			}
//		} finally {
//			if (document != null)
//				document.close();
//		}
//		return BaseVM.TEMP_PATH + File.separator + fileName;
//	}
//
//	public static void main(String[] args) throws Exception {
//		BmmEWalletTrans eWalletTrans = new BmmEWalletTrans();
//		eWalletTrans.setCreateDate(new Timestamp(System.currentTimeMillis()));
//		eWalletTrans.setAmount(BigDecimal.valueOf(300));
//		ApplicationUtils.getInstance().printInvoice(eWalletTrans, 7);
//	}
//
//}
