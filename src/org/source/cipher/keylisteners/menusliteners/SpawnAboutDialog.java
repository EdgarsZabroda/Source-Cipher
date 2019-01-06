package org.source.cipher.keylisteners.menusliteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import org.source.cipher.dialogs.AboutDialog;

public class SpawnAboutDialog implements ActionListener
{
	private JFrame jfParent;
	
	public SpawnAboutDialog(JFrame jfParent)
	{
		this.jfParent = jfParent;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		AboutDialog ad = new AboutDialog(this.jfParent, true);
		ad.setSize(192, 96);
	}
}