package org.source.cipher.keylisteners.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import org.source.cipher.dialogs.KeyInfoDialog;

public class KeyInfoListener implements ActionListener
{
	private String sFile;
	private JDialog jdParent;
	
	public KeyInfoListener(String sFile, JDialog jdParent)
	{
		this.jdParent = jdParent;
		this.sFile = sFile;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		KeyInfoDialog kid = new KeyInfoDialog(this.jdParent, true, this.sFile);
		kid.setSize(384, 512);
	}
}