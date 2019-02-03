package org.source.cipher.dialogs;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import org.source.cipher.algorithm.Encode;
import org.source.cipher.closedisabler.DisableClose;
import org.source.cipher.disablers.EnableCloseButtonOnMax;
import org.source.cipher.keylisteners.dialogs.DismisProgressDialog;

public class Progress extends JDialog
{
	private static final long serialVersionUID = 0x8679FEDDL;
	
	private JProgressBar jProgressBar;
	private JButton jbClose;
	private String sKey, sTargetFile;
	private boolean bFitToRam;
	
	public Progress(JFrame jfParent, boolean bModule, String sKey, String sTargetFile, boolean bFitToRam) throws InterruptedException
	{
		super(jfParent, bModule);
		
		this.sKey = sKey;
		this.sTargetFile = sTargetFile;
		this.bFitToRam = bFitToRam;
		
		setTitle("Progress");
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		for(WindowListener wl : getWindowListeners())
		{
			removeWindowListener(wl);
		}
		
		addWindowListener(new DisableClose(this));
		
		this.jProgressBar = new JProgressBar();
		this.jbClose = new JButton("Close");
		
		this.jbClose.addActionListener(new DismisProgressDialog(this));
		this.jbClose.setEnabled(false);
		this.jProgressBar.setStringPainted(true);
		this.jProgressBar.setMinimum(0);
		this.jProgressBar.setMaximum(100);
		
		JPanel jPanel = new JPanel();
		
		jPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.EAST;
		jPanel.add(this.jProgressBar, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		jPanel.add(jbClose, gbc);
		
		getContentPane().add(jPanel, BorderLayout.CENTER);
		pack();
		setVisible(true);
		this.jProgressBar.addChangeListener(new EnableCloseButtonOnMax(jbClose));
		Thread.sleep(100);
	}
	
	private void Commit()
	{
		try
		{
			Encode enc = new Encode(this.sKey, this.jProgressBar);
			
			if(enc.Commit(this.sTargetFile, this.bFitToRam) == true)
			{
				JOptionPane.showMessageDialog(this, "Ciphering completed successfully!",
						"All done", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Something went wrong, ciphering did not complete successfully,\n"
						+ "Check the console.", "Unexpected error occured", JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(FileNotFoundException E)
		{
			E.getStackTrace();
		}
		catch(IOException IOE)
		{
			IOE.printStackTrace();
		}
	}
	
	public void Execute()
	{
		CompletableFuture.runAsync(() -> { Commit(); });
	}
}