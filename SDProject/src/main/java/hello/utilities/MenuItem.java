package hello.utilities;

public class MenuItem implements Comparable<MenuItem> {

	private String title;
	private String style;
	private String leftComponentStyle;
	private String rightComponentStyle;
	
	public MenuItem(String title, String style, String leftComponentStyle, String rightComponentStyle) {
		this.title = title;
		this.style = style;
		this.leftComponentStyle = leftComponentStyle;
		this.rightComponentStyle = rightComponentStyle;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getStyle() {
		return style;
	}
	
	public String getLeftComponentStyle() {
		return leftComponentStyle;
	}
	
	public String getRightComponentStyle() {
		return rightComponentStyle;
	}

	@Override
	public int compareTo(MenuItem o) {
		return this.title.compareTo(o.title);
	}
	
}
