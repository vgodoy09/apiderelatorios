package com.novotempo.endofyeartoast.bankslip.barcode;

public class DispatchLabelBarcode {

//	private String barcode(String docEntry, boolean rotate) {
//		String pathname ="";
//		try {
//			if(!(new File(getPath())).exists()){
//				new File(getPath()).mkdir();
//			}			
//			
//			if (docEntry == null || docEntry.equals("")) {
//				return getPath()+"blank.png";
//			}
//			
//			BarCodeBuilder builder = new BarCodeBuilder();
//			
//			builder.setCodeText(docEntry);
//			if(rotate){
//				builder.setEncodeType(EncodeTypes.CODE_39_STANDARD);
//				builder.setRotationAngleF(270);
//				builder.setBarHeight(145);
//				pathname = getPath()+docEntry+".png";
//			}else{
//				builder.setEncodeType(EncodeTypes.CODE_128);
//				builder.setCode128Set(Code128Set.C);
//				builder.setWidth(200);
//				pathname = getPath()+docEntry+".png";
//			}
//			
//			builder.setCodeTextVisible(false);
//			builder.setBorderVisible(false);
//			builder.setMargins(null);
//			builder.save(pathname);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return pathname;
//	}
}
