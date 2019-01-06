package org.source.cipher.keylisteners.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JTextField;

import org.source.cipher.dialogs.KeyInfoDialog;

public class KeyInfoListener implements ActionListener
{
	private JTextField sFile;
	private JDialog jdParent;
	
	public KeyInfoListener(JTextField sFile, JDialog jdParent)
	{
		this.jdParent = jdParent;
		this.sFile = sFile;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		KeyInfoDialog kid = new KeyInfoDialog(this.jdParent, false, this.sFile.getText());
		kid.setSize(384, 320);
		kid.GetInfo();
	}
}