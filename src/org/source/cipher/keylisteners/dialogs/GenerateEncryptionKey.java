package org.source.cipher.keylisteners.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.source.cipher.algorithm.Encode;

public class GenerateEncryptionKey implements ActionListener
{
	private JTextField jtfSaveTo;
	
	@SuppressWarnings("rawtypes")
	private JComboBox jcbBytes;
	private JCheckBox jcbSingleKey;
	private JDialog jdParent;
	
	@SuppressWarnings("rawtypes")
	public GenerateEncryptionKey(JDialog jdParent, JTextField jtfSaveTo, JComboBox jcbBytes, JCheckBox jcbSingleKey)
	{
		this.jcbBytes = jcbBytes;
		this.jtfSaveTo = jtfSaveTo;
		this.jcbSingleKey = jcbSingleKey;
		this.jdParent = jdParent;
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
			if(Enc.CreateKey(sBytes).Generate(this.jcbSingleKey.isSelected()) == true)
			{
				JOptionPane.showMessageDialog(this.jdParent, "Encryption key file was generated\n"
						+ "successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(this.jdParent, "An unknown error occured while generating SCKF Encryption key file",
						"Error!", JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(FileNotFoundException FNFE)
		{
			FNFE.printStackTrace();
		}
		catch(IOException IOE)
		{
			IOE.printStackTrace();
		}
	}
}