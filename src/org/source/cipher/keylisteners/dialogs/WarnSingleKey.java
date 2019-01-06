package org.source.cipher.keylisteners.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class WarnSingleKey implements ActionListener
{
	private JDialog jdParent;
	private JCheckBox jcbChecked;
	
	public WarnSingleKey(JDialog jdParent, JCheckBox jcbChecked)
	{
		this.jdParent = jdParent;
		this.jcbChecked = jcbChecked;
	}
	
	@Override
	public void actionPerformed(ActionEvent AE)
	{
		if(this.jcbChecked.isSelected() == true)
		{
			JOptionPane.showMessageDialog(jdParent,
					"Using this encryption key with single key\n"
					+ "could expose parts of the key on null\n"
					+ "bytes. An exposed key could lead to the\n"
					+ "risk of your file being decrypted by other\n"
					+ "than you. Be careful!", "Attention",
					JOptionPane.WARNING_MESSAGE);
		}
	}
}