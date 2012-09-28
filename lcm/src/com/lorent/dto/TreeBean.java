package com.lorent.dto;

public class TreeBean {
	private Integer id;		//	Number	Unique identity number.
	private Integer pid;	//	Number	Number refering to the parent node. The value for the root node has to be -1.
	private String name;	//	String	Text label for the node.
	private String url;		//String	Url for the node.
	private String title;	//String	Title for the node.
	private String target;	//String	Target for the node.
	private String icon;	//String	Image file to use as the icon. Uses default if not specified.
	private String iconOpen;	//String	Image file to use as the open icon. Uses default if not specified.
	private Boolean open;	//Boolean	Is the node open.
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIconOpen() {
		return iconOpen;
	}
	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}
	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
	
	
}
