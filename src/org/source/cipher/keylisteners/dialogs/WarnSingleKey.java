package org.source.cipher.keylisteners.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class WarnSingleKey implements ActionListener
{
	private JDialog jdParent;
	
	public WarnSingleKey(JDialog jdParent)
	{
		this.jdParent = jdParent;
	}
	
	@Override
	public void actionPerformed(ActionEvent AE)
	{
		JOptionPane.showMessageDialog(jdParent,
				"Using this encryption key with single key "
				+ "could expose parts of the key on null "
				+ "bytes. An exposed key could lead to the "
				+ "risk of your file being decrypted. Be "
				+ "careful", "Attention", JOptionPane.WARNING_MESSAGE);
	}
}