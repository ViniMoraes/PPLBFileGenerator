package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import main.LabelLine.Font;
import main.LabelLine.Multiplier;


public class FileGenerator {
	
	public static final String FILE_TO_SEND = "tt.txt";
	public static final int xCenter = 280;
	public static final int yInit = 300;

	public static void generateFile(LabelLine[] labelLines) throws IOException{
		generateFile(labelLines, 0, 0);
	}
	
	public static void generateFile(LabelLine[] labelLines, int rightPadding, int botPadding) throws IOException{
		File fileToSend = new File(FILE_TO_SEND);
		if (!fileToSend.exists()) fileToSend.createNewFile();
		FileWriter fw = new FileWriter(fileToSend, false);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write("N");
		bw.newLine();
		
		for (int i = 0; i < labelLines.length; i++){
			if (i >= 1) 
				labelLines[i].setySetted(labelLines[i -1].getySetted() - labelLines[i-1].getNextLabelInterval());
			else 
				labelLines[i].setySetted(yInit);
			
			if (labelLines[i].getMessage().length() > 36){
				String subString = labelLines[i].getMessage().substring(36);
				labelLines[i].setMessage(labelLines[i].getMessage().substring(0, 36));
				bw.write(getPPLBCode(labelLines[i]));
				bw.newLine();
				
				LabelLine newLabelLine = new LabelLine("");
				newLabelLine=  labelLines[i];
				labelLines[i].setySetted(labelLines[i].getySetted() - labelLines[i].getBarcodeHeight() - 20);
				newLabelLine.setMessage(subString);
				bw.write(getPPLBCode(newLabelLine));
				bw.newLine();
			} else {
				bw.write(getPPLBCode(labelLines[i]));
				bw.newLine();
			}
			if (labelLines[i].isBarcode()){
				labelLines[i].setFont(Font.FONT_3);
				labelLines[i].sethMultiplier(Multiplier.MX_1);
				labelLines[i].setvMultiplier(Multiplier.MX_1);
				labelLines[i].setBarcode(false);
				labelLines[i].setySetted(labelLines[i].getySetted() - labelLines[i].getBarcodeHeight() - 10);
				bw.write(getPPLBCode(labelLines[i]));
				bw.newLine();
			}
		}
		bw.write("P");
		bw.newLine();
		bw.close();
		fw.close();
	}

	private static String getPPLBCode(LabelLine labelLine) {
		String type, p1, p2, p3, p4, p5, p6, p7, p8, data;
		///// Label ///// Barcode
		// P1 - Horizontal Position
		// P2 - Vertical Position
		// P3 - Rotation
		// P4 - Font Selection ///// Barcode Selection
		// P5 - Horizontal Multip ///// Narrow bar widht
		// P6 - Vertical Multip ///// Wide bar widht
		// P7 - Reverse image ///// Barcode height
		// P8 - ///// Print barcode message
		// Data - Message

		float fontWidht = getFontWidht(labelLine.getFont());
		int labelXPos = Math.round(((fontWidht * labelLine.getMessage().length()) / 2) + xCenter);
		int labelYPos = labelLine.getySetted();

		p1 = String.valueOf(labelXPos);
		p2 = String.valueOf(labelYPos);
		p3 = String.valueOf(labelLine.getRotation().getValue());
		p4 = String.valueOf(labelLine.getFont().getValue());
		if (!labelLine.isBarcode()) {
			type = "A";
			p5 = String.valueOf(labelLine.gethMultiplier().getValue());
			p6 = String.valueOf(labelLine.getvMultiplier().getValue());
			p7 = labelLine.isReverseImage() ? "R" : "N";
			p8 = "";
		} else {
			type = "B";
			p5 = String.valueOf(labelLine.getNarrowBarWidht());
			p6 = String.valueOf(labelLine.getWideBarWidht());
			p7 = String.valueOf(labelLine.getBarcodeHeight());
			p8 = "N";
		}

		data = labelLine.getMessage();
		StringBuilder returnString = new StringBuilder("");
		returnString.append("" + type + p1 + "," + p2 + "," + p3 + "," + p4 + "," + p5 + "," + p6 + "," + p7 + ",");
		
		if (p8.isEmpty())
			returnString.append('"' + data + '"');
		else
			returnString.append("" + p8 + "," +'"' + data + '"');
		
		return returnString.toString();
	}

	private static float getFontWidht(Font font) {
		float returnInt = 0;
		switch (font) {
		case FONT_1:
			returnInt = 8;
			break;
		case FONT_2:
			returnInt = 10;
			break;
		case FONT_3:
			returnInt = 12;
			break;
		case FONT_4:
			returnInt = 14;
			break;
		case FONT_5:
			returnInt = 32;
			break;
		case BARCODE_128_ABC:
			returnInt = 20f;
			break;
		default:
			returnInt = 0;
			break;
		}
		return returnInt;
	}

}
