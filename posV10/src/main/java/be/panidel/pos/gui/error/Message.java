package be.panidel.pos.gui.error;

public class Message {
	
	public static int ERROR = -2; 
	public static int WARNING = -1; 
	public static int INFORMATION = 0;
	public static int MESSAGE = 1;

	public Message(int level, String text) {
		super();
		this.level = level;
		this.text = text;
	}

	int level;
	String text;

	public int getLevel() {
		return level;
	}
	public String getText() {
		return text;
	}
}