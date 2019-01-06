package org.source.cipher.keylisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import org.source.cipher.dialogs.CreateSCKFFileDialog;

public class OpenCreateSCKFDialog implements ActionListener
{
	private JFrame jfParent;
	
	public OpenCreateSCKFDialog(JFrame jfParent)
	{
		this.jfParent = jfParent;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		CreateSCKFFileDialog csfd = new CreateSCKFFileDialog(this.jfParent, true);
		csfd.setSize(384, 256);
	}
}