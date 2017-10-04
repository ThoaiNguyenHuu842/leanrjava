package com.ohhay.core.validate;
//package com.ohhay.validate;
//
//import java.io.IOException;
//
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.ValidationUtils;
//import org.springframework.validation.Validator;
//
//import com.ohhay.entities.mongo.C900MG;
//import com.ohhay.utils.ApplicationConstant;
//import com.ohhay.utils.ApplicationHelper;
//
///**
// * @author ThoaiNH
// *
// */
//@Component(value = "c900MGValidator")
//public class C900MGValidator implements Validator {
//	private int maxImgSize = 10; // MB
//
//	@Override
//	public boolean supports(Class<?> paramClass) {
//		return C900MG.class.equals(paramClass);
//	}
//
//	private float getSizeMB(C900MG c900mg) {
//		if (c900mg.getFile() == null)
//			return -1;
//		return (float) c900mg.getFile().getSize() / (1024 * 1024);
//	}
//
//	@Override
//	public void validate(Object obj, Errors errors) {
//		C900MG c900mg = (C900MG) obj;
//		log.info("----validate c900 type:" + c900mg.getCv902());
//		log.info("----cv905:" + c900mg.getCv905());
//		if (c900mg.getCv902() != null) {
//			/*
//			 * validate text
//			 */
////			if (c900mg.getCv902().trim()
////					.equals(ApplicationConstant.OHHAY_DATA_QB_TYPE_TEXT)) {
////				log.info("---invail text");
////				// "cv905.required");
////				if (c900mg.getCv905() == null
////						|| c900mg.getCv905().trim().length() == 0) {
////					errors.rejectValue("cv905", "cv905.required",
////							"Your text can not be null!");
////				}
////			} else
//			/*
//			 * validate image
//			 */
//			if (c900mg.getCv902().trim()
//					.equals(ApplicationConstant.OHHAY_DATA_QB_TYPE_IMAGE)) {
//				float imgSize = getSizeMB(c900mg);
//				log.info("---invail img byte size:" + imgSize);
//				if (imgSize <= 0)
//					errors.rejectValue("file", "img.required",
//							"Your image can not be null!");
//				else if (imgSize > 10) {
//					errors.rejectValue("file", "img.toobig",
//							"Your image is too big! (Must be < 10Mb)");
//				} else if (ApplicationHelper.validateImageExtendtion(c900mg
//						.getFile().getOriginalFilename().replaceAll("\\s+","")) == false)
//					errors.rejectValue("file", "img.invalid",
//							"Your file extension must be image!");
//			} else
//			/*
//			 * validate percent
//			 */
//			if (c900mg.getCv902().trim()
//					.equals(ApplicationConstant.OHHAY_DATA_QB_TYPE_PERCENT)) {
//				log.info("---percent:" + c900mg.getCv905()
//						+ ",validate:"
//						+ ApplicationHelper.validatePercent(c900mg.getCv905()));
//				if (ApplicationHelper.validatePercent(c900mg.getCv905()) == false)
//					errors.rejectValue("cv905", "cv905.percentInvail",
//							"Yout must enter vaild percent non-digit number!");
//			} else
//			/*
//			 * validate link
//			 */
//			if (c900mg.getCv902().trim()
//					.equals(ApplicationConstant.OHHAY_DATA_QB_TYPE_LINK)) {
//
//				if (c900mg.getCv904() == null
//						|| c900mg.getCv904().trim().length() == 0) {
//					errors.rejectValue("cv905", "cv904.required",
//							"Your link is unconnectable!");
//				}
//				/*
//				 * if (c900mg.getCv905() == null ||
//				 * c900mg.getCv905().trim().length() == 0) {
//				 * errors.rejectValue("cv905", "cv905.required",
//				 * "Your text can not be null!"); } else if (c900mg.getCv904()
//				 * == null || c900mg.getCv904().trim().length() == 0) {
//				 * errors.rejectValue("cv905", "cv904.required",
//				 * "Your link is unconnectable!"); }
//				 */
//			} else
//			/*
//			 * validate gallery item
//			 */
//			if (c900mg
//					.getCv902()
//					.trim()
//					.equals(ApplicationConstant.OHHAY_DATA_QB_TYPE_GALLERY_ITEM_LINK_IMAGE)) {
//				System.out
//						.println("-----validate OHHAY_DATA_QB_TYPE_GALLERY_SUB_ITEM");
//				if (c900mg.getCv904() == null
//						|| c900mg.getCv904().trim().length() == 0) {
//					errors.rejectValue("cv905", "cv904.required",
//							"Your link is unconnectable!");
//					log.info("----error gallery item");
//				}
//
//				/*
//				 * log.info("----validate gallery item type");
//				 * log.info("----validate gallery item cv904:" +
//				 * c900mg.getCv904()); float imgSize = getSizeMB(c900mg); if
//				 * (imgSize <= 0) errors.rejectValue("file", "img.required",
//				 * "Your image can not be null!"); else if (imgSize > 10)
//				 * errors.rejectValue("file", "img.toobig",
//				 * "Your image is too big! (Must be < 10Mb)"); else if
//				 * (ApplicationHelper.validateImageExtendtion(c900mg
//				 * .getFile().getOriginalFilename()) == false)
//				 * errors.rejectValue("file", "img.invalid",
//				 * "Your file extension must be image!"); else if
//				 * (c900mg.getCv904() == null ||
//				 * c900mg.getCv904().trim().length() == 0) {
//				 * errors.rejectValue("cv905", "cv904.required",
//				 * "Your link is unconnectable!");
//				 * log.info("----error gallery item"); }
//				 */
//			} else
//			/*
//			 * validate color
//			 */
//			if (c900mg.getCv902().trim()
//					.equals(ApplicationConstant.OHHAY_DATA_QB_TYPE_BG_COLOR)) {
//				log.info("---invail color");
//				if (c900mg.getCv905() == null
//						|| c900mg.getCv905().trim().length() == 0) {
//					errors.rejectValue("cv905", "cv905Color.required",
//							"Your color can not be null!");
//				}
//			}
//		}
//	}
//}
