package org.source.cipher.keylisteners.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class DismisProgressDialog implements ActionListener
{
	private JDialog jdTargetDialog;
	
	public DismisProgressDialog(JDialog jdTargetDialog)
	{
		this.jdTargetDialog = jdTargetDialog;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		this.jdTargetDialog.dispose();
	}
}