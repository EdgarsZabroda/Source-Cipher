package org.source.cipher.closedisabler;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class DisableClose extends WindowAdapter
{
	private JDialog jdParent;
	
	public DisableClose(JDialog jdParent)
	{
		this.jdParent = jdParent;
	}
	
	@Override
	public void windowClosing(WindowEvent we)
	{
		JOptionPane.showMessageDialog(this.jdParent, "Please wait while encoding completes!\n"
				+ "Once it's done you'll be able to hit the close button", "Please wait", JOptionPane.INFORMATION_MESSAGE);
	}
}