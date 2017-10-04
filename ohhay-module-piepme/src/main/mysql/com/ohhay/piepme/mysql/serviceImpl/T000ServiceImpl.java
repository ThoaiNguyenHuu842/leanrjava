package com.ohhay.piepme.mysql.serviceImpl;

import java.io.ByteArrayOutputStream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.ohhay.base.constant.ApplicationConstant;
import com.ohhay.core.constant.SpringBeanNames;
import com.ohhay.core.filesutil.AWSFileUtilsWithoutSession;
import com.ohhay.piepme.mysql.dao.T000Dao;
import com.ohhay.piepme.mysql.service.T000Servcie;

@Service(value = SpringBeanNames.SERVICE_NAME_T000P)
@Scope("prototype")
public class T000ServiceImpl implements T000Servcie {
	private static Logger log = Logger.getLogger(T000ServiceImpl.class);
	@Autowired
	private T000Dao t000Dao;
	private Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
	/**
	 * @param document
	 */
	private void addMetaData(Document document) {
		document.addTitle("PiepMe - iTan list");
		document.addSubject("PiepMe - iTan list");
		document.addKeywords("PiepMe - iTan list");
		document.addAuthor("Queenb JSC");
		document.addCreator("Queenb JSC");
	}

	/**
	 * @param document
	 * @throws DocumentException
	 */
	private void addContent(Document document, String itanList) throws DocumentException {
		Paragraph preface = new Paragraph();
		addEmptyLine(preface, 1);
		preface.setAlignment(Element.ALIGN_CENTER);
		preface.add(new Paragraph("Your Itans", catFont));
		addEmptyLine(preface, 1);
		createTable(preface, itanList);
		document.add(preface);
	}

	/**
	 * @param subCatPart
	 * @throws BadElementException
	 */
	private void createTable(Paragraph preface, String itanList)
			throws BadElementException {
		PdfPTable table = new PdfPTable(12);
		PdfPCell c1 = new PdfPCell(new Phrase("No.",subFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(BaseColor.GRAY);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("iTan",subFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(BaseColor.GRAY);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("No.",subFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(BaseColor.GRAY);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("iTan",subFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(BaseColor.GRAY);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("No.",subFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(BaseColor.GRAY);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("iTan",subFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(BaseColor.GRAY);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("No.",subFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(BaseColor.GRAY);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("iTan",subFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(BaseColor.GRAY);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("No.",subFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(BaseColor.GRAY);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("iTan",subFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(BaseColor.GRAY);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("No.",subFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(BaseColor.GRAY);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("iTan",subFont));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		c1.setBackgroundColor(BaseColor.GRAY);
		table.addCell(c1);
		
		String tempList[] = itanList.split(";");
		int colsNo = 0;//current column added cell
		int index = 0;//index must get from array ITANs
		int offset = 0;//bound from 0 to 20
		for(int i = 0; i< tempList.length; i ++){
			//log.info("i:"+i+",token:"+tempList[i]);			
			//log.info("--index:"+index);
			switch (colsNo) {
			case 0:
				index = offset;
				break;
			case 1:
				index = 20 + offset;
				break;
			case 2:
				index = 40 + offset;
				break;
			case 3:
				index = 60 + offset;
				break;
			case 4:
				index = 80 + offset;
				break;
			case 5:
				index = 100 + offset;
				break;
			default:
				break;
			}
			colsNo ++;
			if(colsNo == 6)
			{
				colsNo = 0;
				offset ++;
			}
			
			//log.info("index:"+index+"");
			if(index < tempList.length){
				String token = tempList[index];
				String ss[] = token.split("=");
				if(ss.length == 2){
					PdfPCell c11 = new PdfPCell(new Phrase(ss[0],subFont));
					c11.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(c11);
					
					PdfPCell c21 = new PdfPCell(new Phrase(ss[1],subFont));
					c21.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(c21);
				}
			}
		}
		preface.add(table);
	}
	/**
	 * @param paragraph
	 * @param number
	 */
	private void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	@Override
	public String uploadTanListPDFToAWS(int fo100) {
		// TODO Auto-generated method stub
		String tanList = t000Dao.getTabTabT000(fo100, ApplicationConstant.PVLOGIN_ANONYMOUS);
		System.out.println(tanList);
		try {
			Document document = new Document();
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, byteArrayOutputStream);
			document.open();
			addMetaData(document);
			addContent(document, tanList);
			AWSFileUtilsWithoutSession aws = new AWSFileUtilsWithoutSession(fo100,"AS");
			String fileName = "itans/itans"+System.currentTimeMillis()+".pdf";
			document.close();
			aws.uploadObjectMutilPart(fileName,byteArrayOutputStream.toByteArray(),"application/pdf");
			return "https://as-oohhay.s3.amazonaws.com/"+fo100+"/"+fileName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertTabT000(int fo100, String pvLogin) {
		// TODO Auto-generated method stub
		return t000Dao.insertTabT000(fo100, pvLogin);
	}

}
