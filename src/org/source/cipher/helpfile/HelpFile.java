package org.source.cipher.helpfile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HelpFile implements ActionListener
{
	private JFrame jmParent;
	
	public HelpFile(JFrame jmParent)
	{
		this.jmParent = jmParent;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		JOptionPane.showMessageDialog(this.jmParent, "To be implemented",
				"Not functioning yet", JOptionPane.INFORMATION_MESSAGE);
	}
}