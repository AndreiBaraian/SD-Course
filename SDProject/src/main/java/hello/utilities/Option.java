package hello.utilities;

import java.util.ArrayList;
import java.util.List;

public class Option implements Comparable<Option> {

	private String title;
	private String href;
	private String leftComponentStyle;
	private String style;
	private String rightComponentStyle;
	private List<String> userTypes;
	
	public Option(String title, String href, String leftComponentStyle, String style, String rightComponentStyle, String userTypes) {
		this.title = title;
		this.href = href;
		this.leftComponentStyle = leftComponentStyle;
		this.style = style;
		this.rightComponentStyle = rightComponentStyle;
		this.userTypes = new ArrayList<String>();
		String[] uTypes = userTypes.split(", ");
		for(String ut : uTypes) {
			this.userTypes.add(ut);
		}
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getHref() {
		return href;
	}
	
	public String getLeftComponentStyle() {
		return leftComponentStyle;
	}
	
	public String getRightComponentStyle() {
		return rightComponentStyle;
	}
	
	public String getStyle() {
		return style;
	}

	@Override
	public int compareTo(Option o) {
		return this.title.compareTo(o.title);
	}

	@Override
	public String toString() {
		return "Option [title=" + title + ", href=" + href + ", leftComponentStyle=" + leftComponentStyle + ", style="
				+ style + ", rightComponentStyle=" + rightComponentStyle + ", userTypes=" + userTypes + "]";
	}

	public String getUserTypes() {
		StringBuilder sb = new StringBuilder();
		for(String ut : userTypes) {
			if(sb.toString().equals("")) {
				sb.append(ut);
			} else {
				sb.append(", ");
				sb.append(ut);
			}
		}
		return sb.toString();
	}

	public void setUserTypes(List<String> userTypes) {
		this.userTypes = userTypes;
	}
	
}
