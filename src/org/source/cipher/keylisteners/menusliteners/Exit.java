package org.source.cipher.keylisteners.menusliteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exit implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		System.exit(0);
	}
}