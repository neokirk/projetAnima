package graphiques;

import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class JLabelButton extends JButton
{
	private static final long serialVersionUID = 123456;

	public JLabelButton(String texte, ActionListener listener)
	{
		super(texte) ;
		this.setHorizontalAlignment(SwingConstants.LEADING);
		this.setFocusPainted(false);
		this.setMargin(new Insets(0, 0, 0, 0));
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.setOpaque(false);
		this.addActionListener(listener);
	}
}
