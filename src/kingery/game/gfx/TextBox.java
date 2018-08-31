package kingery.game.gfx;

import java.awt.*;

import javax.swing.JOptionPane;

import kingery.game.engine.Engine;

public class TextBox {

	private Engine e;

	public TextBox(Engine e) {
		this.e = e;
	}

	public void drawTextBox(String s, String s2) {

		// JOptionPane.showMessageDialog(e.frame, s);
		JOptionPane.showMessageDialog(e.frame, s, s2, JOptionPane.PLAIN_MESSAGE);

	}

	public void drawOptionBox(String s, String s2) {

		JOptionPane.showInputDialog("Please input a name");

	}
	
	public int drawQuestionBox(String s, String s2) {
		int value = JOptionPane.showConfirmDialog(null, "Do you want to trade?");
		System.out.println(value);
		return value;
		
	}

	public Object drawConfirmDialog(String s, String s2, Object[] strings) {
		return JOptionPane.showInputDialog(
                e.frame,
                "Complete the sentence:\n"
                + "\"Green eggs and...\"",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                strings,
                "ham");
	}

}
