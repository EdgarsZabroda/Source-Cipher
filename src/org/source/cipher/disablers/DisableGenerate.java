package org.source.cipher.disablers;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DisableGenerate implements DocumentListener
{
	private JTextField jtfLocInput;
	private JButton jbTargetButton;
	
	private void Commit()
	{
		if(!this.jtfLocInput.getText().trim().isBlank() && !this.jtfLocInput.getText().trim().isEmpty())
		{
			this.jbTargetButton.setEnabled(true);
		}
		else
		{
			this.jbTargetButton.setEnabled(false);
		}
	}
	
	public DisableGenerate(JTextField jtfLocInput, JButton jbTargetButton)
	{
		this.jtfLocInput = jtfLocInput;
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