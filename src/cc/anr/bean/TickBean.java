package cc.anr.bean;

import java.io.Serializable;

public class TickBean implements Serializable {

	private static final long serialVersionUID = 7790875172109700230L;

	
	
	private String time;
	private String channelID;
	private String remoteAddress;
	
	
	public TickBean(){}
	
	public TickBean(String time, String channelID, String remoteAddress) {
		this.time = time;
		this.channelID = channelID;
		this.remoteAddress = remoteAddress;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getChannelID() {
		return channelID;
	}
	public void setChannelID(String channelID) {
		this.channelID = channelID;
	}
	public String getRemoteAddress() {
		return remoteAddress;
	}
	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}
	
	
}
