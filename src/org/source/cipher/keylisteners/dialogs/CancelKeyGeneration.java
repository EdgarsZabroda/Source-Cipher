package org.source.cipher.keylisteners.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class CancelKeyGeneration implements ActionListener
{
	private JDialog jdTarget;
	
	public CancelKeyGeneration(JDialog jdTarget)
	{
		this.jdTarget = jdTarget;
	}
	
	@Override
	public void actionPerformed(ActionEvent AE)
	{
		this.jdTarget.dispose();
	}
}