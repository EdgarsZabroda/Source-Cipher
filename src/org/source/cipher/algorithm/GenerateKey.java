package org.source.cipher.algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class GenerateKey
{
	private String sFile;
	private short sBytes;
	
	public GenerateKey(String sFile, short sBytes)
	{
		this.sFile = sFile;
		this.sBytes = sBytes;
	}
	
	public final boolean Generate(boolean bSingle) throws FileNotFoundException, IOException
	{
		boolean bComplete = false;
		short sBytes = this.sBytes;
		File KeyFile = new File(this.sFile);
		byte bySingle[] = new byte[1];
		byte Key[];
		byte bShortToByte[] = new byte[sBytes*2];;
		Random Picker = new Random();
		byte capitals[] = { 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
				'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
				'Y', 'Z'};
		byte smalls[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
				'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
				'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
				'z'};
		byte numbers[] = { '0', '1', '2', '3', '4', '5',
				'6', '7', '8', '9'};
		byte symbols[] = { '~', '!', '@', '#', '$', '%',
				'^', '&', '*', '(', ')', '_', '+', '|', '{',
				'}', ':', '"', '<', '>', '?', '`', '-', '=',
				'\\', '[', ']', ';', '\'', ',', '.', '/',
				'\r', '\n', '\t', ' '};
		
		if(KeyFile.exists() == true)
		{
			if(KeyFile.delete() == false)
			{
				System.out.println("Failed to delete the file '"
						+ KeyFile.getAbsolutePath() + "\\"
						+ KeyFile.getName() + "'");
			}
		}
		
		RandomAccessFile rafKey = new RandomAccessFile(KeyFile, "rw");
		
		if(bSingle == true)
		{
			bySingle[0] = 0x01;
		}
		else
		{
			bySingle[0] = 0x00;
		}
		
		byte bBytes[] = new byte[2];
		
		bBytes[0] = (byte)(sBytes & 0xFF);
		bBytes[1] = (byte)((sBytes >> 8) & 0xFF);
		
		Key = new byte[sBytes];
		
		for(int x=0;x<sBytes;x++)
		{
			int nRandomSymbol = Picker.nextInt(4);
			
			switch(nRandomSymbol)
			{
			case 0:
				Key[x] = capitals[Picker.nextInt(capitals.length)];
				break;
			case 1:
				Key[x] = smalls[Picker.nextInt(smalls.length)];
				break;
			case 2:
				Key[x] = numbers[Picker.nextInt(numbers.length)];
				break;
			case 3:
				Key[x] = symbols[Picker.nextInt(symbols.length)];
				break;
			}
		}
		
		rafKey.seek(0);
		rafKey.write(bySingle, 0, 1);
		rafKey.seek(1);
		rafKey.write(bBytes, 0, 2);
		rafKey.seek(3);
		rafKey.write(Key, 0, sBytes);
		
		if(bSingle == false)
		{
			short sOffset[] = new short[Key.length];
			short sTaken[] = new short[Key.length];
			short sNextOffset = 0;
			
			while(sNextOffset<Key.length)
			{
				short sGuess = (short)Picker.nextInt(Key.length);
				
				if(sTaken[sGuess] != 1)
				{
					sOffset[sNextOffset] = sGuess;
					sTaken[sGuess] = 1;
					sNextOffset++;
				}
			}
			
			int nIndex = 0;
			
			for(int x=0;x<(sBytes*2);x+=2)
			{
				bShortToByte[x] = (byte)(sOffset[nIndex] & 0xFF);
				bShortToByte[x+1] = (byte)((sOffset[nIndex] >> 8));
				nIndex++;
			}
			
			rafKey.seek(3+sBytes);
			rafKey.write(bShortToByte, 0, sBytes*2);
			bComplete = true;
		}
		else
		{
			bComplete = true;
		}
		
		rafKey.close();
		
		return bComplete;
	}
}