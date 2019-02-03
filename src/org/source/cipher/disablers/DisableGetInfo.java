package org.source.cipher.disablers;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DisableGetInfo implements DocumentListener
{
	private JTextField jtfPath;
	private JButton jbTargetButton;
	
	private void Commit()
	{
		if(!this.jtfPath.getText().trim().isBlank() && !this.jtfPath.getText().trim().isEmpty())
		{
			this.jbTargetButton.setEnabled(true);
		}
		else
		{
			this.jbTargetButton.setEnabled(false);
		}
	}
	
	public DisableGetInfo(JTextField jtfPath, JButton jbTargetButton)
	{
		this.jtfPath = jtfPath;
		this.jbTargetButton = jbTargetButton;
	}
	
	@Override
	public void changedUpdate(DocumentEvent E)
	{
		Commit();
	}
	
	@Override
	public void insertUpdate(DocumentEvent E)
	{
		Commit();
	}
	
	@Override
	public void removeUpdate(DocumentEvent E)
	{
		Commit();
	}
}