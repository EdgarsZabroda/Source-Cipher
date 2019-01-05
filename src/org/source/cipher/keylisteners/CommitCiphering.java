package org.source.cipher.keylisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
//import javax.swing.JProgressBar;
import javax.swing.JTextField;

import org.source.cipher.algorithm.Encode;
import org.source.cipher.dialogs.Progress;

public class CommitCiphering implements ActionListener
{
	private JTextField jtfTargetFile;
	private JTextField jtfSCKFFile;
	private JCheckBox jcbFitToRam;
	private JFrame jfParent;
	
	public CommitCiphering(JTextField jtfTargetFile, JTextField jtfSCKFFile, JCheckBox jcbFitToRam, JFrame jfParent)
	{
		this.jtfTargetFile = jtfTargetFile;
		this.jtfSCKFFile = jtfSCKFFile;
		this.jcbFitToRam =jcbFitToRam;
		this.jfParent = jfParent;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		String sTargetFile = this.jtfTargetFile.getText();
		String sSCKFFile = this.jtfSCKFFile.getText();
		
		if(this.jcbFitToRam.isSelected() == false)
		{
			int nChoice = JOptionPane.showConfirmDialog(this.jfParent, "Do you wish to see the progress?",
					"Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(nChoice == JOptionPane.YES_OPTION)
			{
				try
				{
					Progress prg = new Progress(this.jfParent, false, sSCKFFile,
							sTargetFile, false);
					prg.setSize(256, 96);
					prg.Execute();
				}
				catch(InterruptedException IE)
				{
					IE.printStackTrace();
					Thread.currentThread().interrupt();
				}
			}
			else
			{
				try
				{
					Encode enc = new Encode(sSCKFFile);
					
					if(enc.Commit(sTargetFile, false) == true)
					{
						JOptionPane.showMessageDialog(this.jfParent, "Ciphering completed successfully",
								"Success!", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(this.jfParent, "An unexpected error occured,\n"
								+ "ciphering did not complete successfully", "Unexpected error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(FileNotFoundException FNFE)
				{
					FNFE.printStackTrace();
				}
				catch(IOException IE)
				{
					IE.printStackTrace();
				}
			}
		}
		else
		{
			int nChoice = JOptionPane.showConfirmDialog(this.jfParent, "Do you wish to see the progress?",
					"Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(nChoice == JOptionPane.YES_OPTION)
			{
				try
				{
					Progress prg = new Progress(this.jfParent, false, sSCKFFile,
							sTargetFile, true);
					prg.setSize(256, 96);
					prg.Execute();
				}
				catch(InterruptedException IE)
				{
					IE.printStackTrace();
					Thread.currentThread().interrupt();
				}
			}
			else
			{
				try
				{
					Encode enc = new Encode(sSCKFFile);
					
					if(enc.Commit(sTargetFile, true) == true)
					{
						JOptionPane.showMessageDialog(this.jfParent, "Ciphering completed successfully",
								"Success!", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(this.jfParent, "An unexpected error occured,\n"
								+ "ciphering did not complete successfully", "Unexpected error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(IOException IE)
				{
					IE.printStackTrace();
				}
			}
		}
	}
}