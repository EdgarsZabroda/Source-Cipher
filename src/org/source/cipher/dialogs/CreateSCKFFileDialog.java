package org.source.cipher.dialogs;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.source.cipher.checkboxstate.CheckBoxState;
import org.source.cipher.keylisteners.dialogs.CancelKeyGeneration;
import org.source.cipher.keylisteners.dialogs.GenerateEncryptionKey;
import org.source.cipher.keylisteners.dialogs.SaveSCKFFile;
import org.source.cipher.keylisteners.dialogs.WarnSingleKey;

public class CreateSCKFFileDialog extends JDialog
{
	private static final long serialVersionUID = 0x8679FEDDL;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CreateSCKFFileDialog(Frame Parent, boolean bModule)
	{
		super(Parent, bModule);
		
		setTitle("Create SCKF file");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JLabel jlOutput = new JLabel("SCKF file save location:");
		JTextField jtfOutput = new JTextField(15);
		JButton jbBrowse = new JButton("Save as");
		JLabel jlBytes = new JLabel("Key length:");
		String sBytes[] = { "8", "16", "32", "64", "128",
				"256", "512" };
		JComboBox jcbBytes = new JComboBox(sBytes);
		JCheckBox jcbSingle = new JCheckBox("Generate single key");
		JButton jbGenerate = new JButton("Generate");
		JButton jbCancel = new JButton("Cancel");
		
		CheckBoxState chk = new CheckBoxState();
		
		jcbSingle.addItemListener(chk);
		jbBrowse.addActionListener(new SaveSCKFFile(jtfOutput, this));
		jbGenerate.addActionListener(new GenerateEncryptionKey(jtfOutput, jcbBytes, chk.bChecked));
		jbCancel.addActionListener(new CancelKeyGeneration(this));
		jcbSingle.addActionListener(new WarnSingleKey(this));
		
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(15, 15, 0, 0);
		gbc.anchor = GridBagConstraints.EAST;
		jPanel.add(jlOutput, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		jPanel.add(jtfOutput, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		jPanel.add(jbBrowse, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.EAST;
		jPanel.add(jlBytes, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.WEST;
		jPanel.add(jcbBytes, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		jPanel.add(jcbSingle, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.EAST;
		jPanel.add(jbGenerate, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		jPanel.add(jbCancel, gbc);
		
		getContentPane().add(jPanel, BorderLayout.NORTH);
		pack();
		setVisible(true);
	}
}