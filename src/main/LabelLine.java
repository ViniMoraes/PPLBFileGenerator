package main;

public class LabelLine {
	
	public enum Font{
		FONT_1(1),
		FONT_2(2),
		FONT_3(3),
		FONT_4(4),
		FONT_5(5),
		BARCODE_128_ABC(1);
		
		private final int value;
		private Font(int value){
			this.value = value;
		}
		
		public int getValue(){
			return value;
		}
	}
	
	public enum Rotation{
		DEGRESS_0(0),
		DEGRESS_90(1),
		DEGRESS_180(2),
		DEGRESS_270(3);
		
		private final int value;
		private Rotation(int value){
			this.value = value;
		}
		
		public int getValue(){
			return value;
		}
	}
	
	public enum Multiplier{
		MX_1(1),
		MX_2(2),
		MX_3(3),
		MX_4(4),
		MX_5(5),
		MX_6(6);
		private final int value;
		private Multiplier(int value){
			this.value = value;
		}
		
		public int getValue(){
			return value;
		}
	}
	
	private String message = "Message";
	private Font font = Font.FONT_3;
	private Rotation rotation = Rotation.DEGRESS_180;
	private boolean barcode = false;
	private Multiplier hMultiplier = Multiplier.MX_1;
	private Multiplier vMultiplier = Multiplier.MX_1;
	private boolean reverseImage = false;
	private int narrowBarWidht = 3;
	private int wideBarWidht = 200;
	private int barcodeHeight = 100;
	private int nextLabelInterval = 50;
	private int ySetted = 0;
	
	public LabelLine(String message){
		this(message,false);
	}
	
	public LabelLine(String message, boolean barcode){
		this.message = message;
		this.barcode = barcode;
		if (!barcode)
			this.barcodeHeight = 0;
		else
			this.font = Font.BARCODE_128_ABC;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Font getFont() {
		return font;
	}
	public void setFont(Font font) {
		this.font = font;
	}
	public Rotation getRotation() {
		return rotation;
	}
	public void setRotation(Rotation rotation) {
		this.rotation = rotation;
	}
	public boolean isBarcode() {
		return barcode;
	}
	public void setBarcode(boolean barcode) {
		this.barcode = barcode;
	}
	public Multiplier gethMultiplier() {
		return hMultiplier;
	}
	public void sethMultiplier(Multiplier hMultiplier) {
		this.hMultiplier = hMultiplier;
	}
	public Multiplier getvMultiplier() {
		return vMultiplier;
	}
	public void setvMultiplier(Multiplier vMultiplier) {
		this.vMultiplier = vMultiplier;
	}
	public boolean isReverseImage() {
		return reverseImage;
	}
	public void setReverseImage(boolean reverseImage) {
		this.reverseImage = reverseImage;
	}
	public int getBarcodeHeight() {
		return barcodeHeight;
	}
	public void setBarcodeHeight(int barcodeHeight) {
		this.barcodeHeight = barcodeHeight;
	}
	public int getNarrowBarWidht() {
		return narrowBarWidht;
	}
	public void setNarrowBarWidht(int narrowBarWidht) {
		this.narrowBarWidht = narrowBarWidht;
	}
	public int getWideBarWidht() {
		return wideBarWidht;
	}
	public void setWideBarWidht(int wideBarWidht) {
		this.wideBarWidht = wideBarWidht;
	}
	public int getNextLabelInterval() {
		return nextLabelInterval;
	}
	public void setNextLabelInterval(int nextLabelInterval) {
		this.nextLabelInterval = nextLabelInterval;
	}
	public int getySetted() {
		return ySetted;
	}
	public void setySetted(int ySetted) {
		this.ySetted = ySetted;
	}
}
