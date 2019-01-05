package org.source.cipher.disablers;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DisableCommit implements DocumentListener
{
	private JTextField jtfSource;
	private JTextField jtfKey;
	private JButton jbCommit;
	
	public DisableCommit(JTextField jtfSource, JTextField jtfKey, JButton jbCommit)
	{
		this.jtfSource = jtfSource;
		this.jtfKey = jtfKey;
		this.jbCommit = jbCommit;
	}
	
	private void DoIt(DocumentEvent E)
	{
		boolean bAllow = false;
		
		DocumentEvent.EventType eType = E.getType();
		
		if(eType.equals(DocumentEvent.EventType.CHANGE))
		{
			if(!this.jtfKey.getText().trim().isEmpty() && !this.jtfKey.getText().trim().isBlank())
			{
				if(!this.jtfSource.getText().trim().isEmpty() && !this.jtfSource.getText().trim().isBlank())
				{
					bAllow = true;
				}
				else
				{
					bAllow = false;
				}
			}
			else if(!this.jtfSource.getText().trim().isEmpty() && !this.jtfSource.getText().trim().isBlank())
			{
				if(!this.jtfKey.getText().trim().isEmpty() && !this.jtfKey.getText().trim().isBlank())
				{
					bAllow = true;
				}
				else
				{
					bAllow = false;
				}
			}
			else
			{
				bAllow = false;
			}
			
			if(bAllow == true)
			{
				this.jbCommit.setEnabled(true);
			}
			else
			{
				this.jbCommit.setEnabled(false);
			}
		}
		else if(eType.equals(DocumentEvent.EventType.INSERT))
		{
			if(!this.jtfKey.getText().trim().isEmpty() && !this.jtfKey.getText().trim().isBlank())
			{
				if(!this.jtfSource.getText().trim().isEmpty() && !this.jtfSource.getText().trim().isBlank())
				{
					bAllow = true;
				}
				else
				{
					bAllow = false;
				}
			}
			else if(!this.jtfSource.getText().trim().isEmpty() && !this.jtfSource.getText().trim().isBlank())
			{
				if(!this.jtfKey.getText().trim().isEmpty() && !this.jtfKey.getText().trim().isBlank())
				{
					bAllow = true;
				}
				else
				{
					bAllow = false;
				}
			}
			else
			{
				bAllow = false;
			}
			
			if(bAllow == true)
			{
				this.jbCommit.setEnabled(true);
			}
			else
			{
				this.jbCommit.setEnabled(false);
			}
		}
		else if(eType.equals(DocumentEvent.EventType.REMOVE))
		{
			if(!this.jtfKey.getText().trim().isEmpty() && !this.jtfKey.getText().trim().isBlank())
			{
				if(!this.jtfSource.getText().trim().isEmpty() && !this.jtfSource.getText().trim().isBlank())
				{
					bAllow = true;
				}
				else
				{
					bAllow = false;
				}
			}
			else if(!this.jtfSource.getText().trim().isEmpty() && !this.jtfSource.getText().trim().isBlank())
			{
				if(!this.jtfKey.getText().trim().isEmpty() && !this.jtfKey.getText().trim().isBlank())
				{
					bAllow = true;
				}
				else
				{
					bAllow = false;
				}
			}
			else
			{
				bAllow = false;
			}
			
			if(bAllow == true)
			{
				this.jbCommit.setEnabled(true);
			}
			else
			{
				this.jbCommit.setEnabled(false);
			}
		}
	}
	
	@Override
	public void changedUpdate(DocumentEvent E)
	{
		DoIt(E);
	}
	
	@Override
	public void insertUpdate(DocumentEvent E)
	{
		DoIt(E);
	}
	
	@Override
	public void removeUpdate(DocumentEvent E)
	{
		DoIt(E);
	}
}