package com.ohhay.core.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.ohhay.core.filesutil.EvoAWSFileUtils;

/**
 * @author ThoaiNH create Apr 12, 2017
 */
@Scope("prototype")
public class QRImageGenerator {
	Logger log = Logger.getLogger(QRImageGenerator.class);

	/**
	 * 
	 * @param code ex: '123456'
	 * @return AWS link image ex: 'https://d2g7dc0hcuz3eo.cloudfront.net/resource/piepme/qrcode/123456'
	 */
	public String genPiepmeVoucherQR(String code) {
		try {
			byte[] data = generatorImageQR(code, 125);
			System.err.println(data);
			// init location File upload to amazon
			EvoAWSFileUtils awsFileUtils = new EvoAWSFileUtils("AS", 0);
			// file name image
			String fileName = "resource/piepme/qrcode/" + ApplicationHelper.generateFileName();
			// get link url image
			String urlImage = URLDecoder.decode(awsFileUtils.uploadObjectMutilPart(fileName, data), "UTF-8");
			return urlImage;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			log.info(e);
			return null;
		}
	}

	/**
	 * @param qrCodeText
	 * @param size
	 * @return byte array
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public byte[] generatorImageQR(String qrCodeText, int size) {
		// Create the ByteMatrix for the QR-Code that encodes the given String
		try {
			Hashtable hintMap = new Hashtable();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
			// Make the BufferedImage that are to hold the QRCode
			int matrixWidth = byteMatrix.getWidth();
			BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
			image.createGraphics();

			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, matrixWidth, matrixWidth);
			// Paint and save the image using the ByteMatrix
			graphics.setColor(Color.BLACK);

			for (int i = 0; i < matrixWidth; i++) {
				for (int j = 0; j < matrixWidth; j++) {
					if (byteMatrix.get(i, j)) {
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(image, "png", os);

			return os.toByteArray(); // Base64.getEncoder().encodeToString(os.toByteArray());
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e);
			return null;
		}

	}
}
