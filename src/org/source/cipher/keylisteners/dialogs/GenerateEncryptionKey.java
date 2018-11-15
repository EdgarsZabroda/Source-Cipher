package org.source.cipher.keylisteners.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.source.cipher.algorithm.Encode;

public class GenerateEncryptionKey implements ActionListener
{
	private JTextField jtfSaveTo;
	
	@SuppressWarnings("rawtypes")
	private JComboBox jcbBytes;
	private boolean bSingleKey;
	
	@SuppressWarnings("rawtypes")
	public GenerateEncryptionKey(JTextField jtfSaveTo, JComboBox jcbBytes, boolean bSingleKey)
	{
		this.jcbBytes = jcbBytes;
		this.jtfSaveTo = jtfSaveTo;
		this.bSingleKey = bSingleKey;
	}
	
	@Override
	public void actionPerformed(ActionEvent AE)
	{
		String sOutputFile = this.jtfSaveTo.getText();
		String strBytes = String.valueOf(this.jcbBytes.getSelectedItem());
		short sBytes = (short)Integer.parseInt(strBytes);
		
		Encode Enc = new Encode(sOutputFile);
		
		try
		{
			Enc.CreateKey(sBytes).Generate(this.bSingleKey);
		}
		catch(IOException IOE)
		{
			IOE.printStackTrace();
		}
	}
}