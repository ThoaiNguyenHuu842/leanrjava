package com.ohhay.web.other.superelement;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.ohhay.web.core.entities.V250;
@Service
public class BsellV250Element{
	public void mapElementToBox(Element element, int role, String key, String cv901, V250 v250) {
	//	log.info("===BsellV250Element mapElementToBox");
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
//		------TUNGNS----b78a3223503896721cca1303f776159b
//		------TUNGNS----be53a0541a6d36f6ecb879fa2c584b08
//		------TUNGNS----49ee3087348e8d44e1feda1917443987
//		------TUNGNS----478f8f88f798b564fe9a7e03ba46cfd0
//		------TUNGNS----44749712dbec183e983dcd78a7736c41
//		------TUNGNS----3b0649c72650c313a357338dcdfb64ec
//		------TUNGNS----32f5f73940c28b104e0b41704177e501
//		------TUNGNS----fddfb9b679e5c75cac3c3b39e0b7e604
//		------TUNGNS----3601146c4e948c32b6424d2c0a7f0118
//		------TUNGNS----e351ddd2ae050a1c26b611d27db25263
//		------TUNGNS----c13367945d5d4c91047b3b50234aa7ab
//		------TUNGNS----831a28f1e8df07c553fcd59546465d13

		switch(cv901){
			case "b78a3223503896721cca1303f776159b"://tieu de cua san pham
				element.append(v250.getVv502());
				break;
			case "be53a0541a6d36f6ecb879fa2c584b08"://hinh anh cua san pham
				element.append("");
				break;
			case "49ee3087348e8d44e1feda1917443987"://ten san pham
				element.append(v250.getVv502());
				break;
			case "478f8f88f798b564fe9a7e03ba46cfd0"://hinh anh cua san pham
				element.append("Nhà Sản Xuất: USA");
				break;
			case "44749712dbec183e983dcd78a7736c41"://hinh anh cua san pham
				element.append("Hạn Sử dụng: 15/8/2016");
				break;
			case "3b0649c72650c313a357338dcdfb64ec"://hinh anh cua san pham
				element.append("Công dụng: Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries.");
				break;
			case "32f5f73940c28b104e0b41704177e501"://hinh anh cua san pham
				element.append("287,800đ");
				break;
			case "fddfb9b679e5c75cac3c3b39e0b7e604"://hinh anh cua san pham
				element.append("Tiết kiệm: <b>143,400đ</b>");
				break;
			case "3601146c4e948c32b6424d2c0a7f0118"://hinh anh cua san pham
				element.append("143,400đ");
				break;
			case "e351ddd2ae050a1c26b611d27db25263"://hinh anh cua san pham
				element.append("Hàng nhập khẩu");
				break;
			case "c13367945d5d4c91047b3b50234aa7ab"://hinh anh cua san pham
				element.append("AMHIPP");
				break;
			case "831a28f1e8df07c553fcd59546465d13"://hinh anh cua san pham
				element.append("DAY LA GI");
				break;
		}
	}

}
