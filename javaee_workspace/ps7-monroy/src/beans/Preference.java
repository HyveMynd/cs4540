package beans;

public class Preference {
	private final String bgColor;
	private final String fontColor;
	private final String fontSize;
	
	private Preference(String bgColor, String fontColor, String fontSize){
		this.bgColor = bgColor;
		this.fontColor = fontColor;
		this.fontSize = fontSize;
	}
	
	public String getBgColor() {
		return bgColor;
	}
	public String getFontColor() {
		return fontColor;
	}
	public String getFontSize() {
		return fontSize;
	}
	
	public static Preference CreatePreference(String bgColor, String fontColor, String fontSize){
		return new Preference(bgColor, fontColor, fontSize);
	}
}
