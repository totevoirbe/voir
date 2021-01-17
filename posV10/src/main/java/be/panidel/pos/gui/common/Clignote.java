package be.panidel.pos.gui.common;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.Timer;

public class Clignote {
	
	private Timer timer;
	private int delay = 500;
	private Color unBlinkColor;
	private JComponent jComponent;
	private TimerListener timerListener;
	
	public Clignote(JComponent jComponent) {
		this.jComponent = jComponent;
	}

	public void start(Color blinkColor) {
		this.unBlinkColor = jComponent.getBackground();
		if (timer == null) {
			timerListener = new TimerListener(jComponent, blinkColor);
			timer = new Timer(delay, new TimerListener(jComponent, blinkColor));
		}
		timer.start();
	}

	public void stop() {
		if (timer != null) {
			timer.stop();
			timer.removeActionListener(timerListener);
			timerListener = null;
			timer = null;
			jComponent.setBackground(unBlinkColor);
		}
	}
}

class TimerListener implements ActionListener  {

	private boolean blink = true;
	private Color unBlinkColor;
	private Color blinkColor;
	private JComponent jComponent;
	
	TimerListener(JComponent jComponent, Color blinkColor){
		this.jComponent = jComponent;
		this.blinkColor = blinkColor;
		unBlinkColor = jComponent.getBackground();
	}
	
	public void actionPerformed(ActionEvent evt) {
		if(blink = !blink) {
			jComponent.setBackground(unBlinkColor);
		} else {
			jComponent.setBackground(blinkColor);
		}
	}

	public Color getUnBlinkColor() {
		return unBlinkColor;
	}
}
