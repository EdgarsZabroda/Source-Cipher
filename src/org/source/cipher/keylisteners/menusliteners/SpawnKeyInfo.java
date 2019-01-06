package org.source.cipher.keylisteners.menusliteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import org.source.cipher.dialogs.BeforeKeyInfo;

public class SpawnKeyInfo implements ActionListener
{
	private JFrame jfParent;
	
	public SpawnKeyInfo(JFrame jfParent)
	{
		this.jfParent = jfParent;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		BeforeKeyInfo bki = new BeforeKeyInfo(this.jfParent, true);
		bki.setSize(256, 96);
	}
}