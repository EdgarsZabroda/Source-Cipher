package org.source.cipher.changelistener;

import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EnableCloseButtonOnMax implements ChangeListener
{
	private JButton jbTargetButton;
	
	public EnableCloseButtonOnMax(JButton jbTargetButton)
	{
		this.jbTargetButton = jbTargetButton;
	}
	
	@Override
	public void stateChanged(ChangeEvent ce)
	{
		JProgressBar jProgressBar = (JProgressBar)ce.getSource();
		
		if(jProgressBar.getValue() == jProgressBar.getMaximum())
		{
			this.jbTargetButton.setEnabled(true);
		}
		else
		{
			this.jbTargetButton.setEnabled(false);
		}
	}
}