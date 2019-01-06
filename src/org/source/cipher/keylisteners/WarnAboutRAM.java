package org.source.cipher.keylisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class WarnAboutRAM implements ActionListener
{
	private JFrame jfParent;
	private JCheckBox jcbFitToRAM;
	
	public WarnAboutRAM(JFrame jfParent, JCheckBox jbcFitToRAM)
	{
		this.jfParent = jfParent;
		this.jcbFitToRAM = jbcFitToRAM;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if(this.jcbFitToRAM.isSelected() == true)
		{
			JOptionPane.showMessageDialog(this.jfParent, "Please be cautios, the maximum file size you can fit\n"
					+ "is 2GB since Java doesn't support unsigned integers. If you try to fit larger file the application\n"
					+ "could crash. The advantage of this feature is that encoding is faster, but the downside\n"
					+ "what was mentioned before. Use this feature on small files and not on large files. Be \n"
					+ "careful!", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}
}