package org.source.cipher.algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JProgressBar;

public class Encode
{
	private String sKey;
	private JProgressBar jlProgress;
	
	public Encode(String sKey, JProgressBar jlProgress)
	{
		this.sKey = sKey;
		this.jlProgress = jlProgress;
	}
	
	public Encode(String sKey)
	{
		this.sKey = sKey;
		this.jlProgress = null;
	}
	
	public final boolean Commit(String sFile, boolean bFitToRAM) throws FileNotFoundException, IOException 
	{
		boolean bComplete = false;
		byte bSingle[] = new byte[1];
		byte bSplittedByteInfo[];
		short sBytes;
		byte Key[];
		byte bSplittedByteShuffledKey[];
		short sShuffledOffsets[];
		File KeyFile = new File(this.sKey);
		File TargetFile = new File(sFile);
		RandomAccessFile rafKey = new RandomAccessFile(KeyFile, "r");
		RandomAccessFile rafTargetFile = new RandomAccessFile(TargetFile, "rw");
		
		rafKey.seek(0);
		rafKey.read(bSingle, 0, 1);
		
		if(bSingle[0] == 1)
		{
			if(bFitToRAM == false)
			{
				bSplittedByteInfo = new byte[2];
				rafKey.seek(1);
				rafKey.read(bSplittedByteInfo, 0, 2);
				
				sBytes = (short)(((bSplittedByteInfo[1])<<8) | bSplittedByteInfo[0] & 0x00FF);
				Key = new byte[sBytes];
				
				rafKey.seek(3);
				rafKey.read(Key, 0, sBytes);
				
				long nOffset = 0;
				long nDone = 0;
				long nRemaining = TargetFile.length();
				long nSize = TargetFile.length();
				byte bChunk[] = new byte[sBytes*1024*1024];
				long nTargetBytesInCurrentPercent = nSize / 100;
				final long nOnePercent = nSize / 100;
				int nCurrentPercent = 0;
				
				while(nRemaining != 0)
				{	
					if(nRemaining >= (sBytes*1024*1024))
					{
						rafTargetFile.seek(nOffset);
						rafTargetFile.read(bChunk, 0, (sBytes*1024*1024));
						short sKeyOffset = 0;
						
						for(int x=0;x<bChunk.length;x++)
						{
							if(sKeyOffset == sBytes)
							{
								sKeyOffset = 0;
							}
							
							bChunk[x] ^= Key[sKeyOffset];
							
							sKeyOffset++;
							
							if(jlProgress != null)
							{
								nDone++;
								
								if(nDone == nTargetBytesInCurrentPercent)
								{
									nCurrentPercent++;
									this.jlProgress.setValue(nCurrentPercent);
									nTargetBytesInCurrentPercent += nOnePercent;
									
									if(nTargetBytesInCurrentPercent >= nSize)
									{
										nTargetBytesInCurrentPercent = nSize;
									}
									else if(nTargetBytesInCurrentPercent < nSize && nCurrentPercent == 99)
									{
										nTargetBytesInCurrentPercent = nSize;
									}
								}
							}
						}
						
						rafTargetFile.seek(nOffset);
						rafTargetFile.write(bChunk, 0, (sBytes*1024*1024));
						
						nOffset += sBytes*1024*1024;
						nRemaining -= sBytes*1024*1024;
					}
					else
					{
						rafTargetFile.seek(nOffset);
						rafTargetFile.read(bChunk, 0, (int)nRemaining);
						short sKeyOffset = 0;
						
						for(int x=0;x<nRemaining;x++)
						{
							if(sKeyOffset == sBytes)
							{
								sKeyOffset = 0;
							}
							
							bChunk[x] ^= Key[sKeyOffset];
							
							sKeyOffset++;
							
							if(jlProgress != null)
							{
								nDone++;
								
								if(nDone == nTargetBytesInCurrentPercent)
								{
									nCurrentPercent++;
									this.jlProgress.setValue(nCurrentPercent);
									nTargetBytesInCurrentPercent += nOnePercent;
									
									if(nTargetBytesInCurrentPercent >= nSize)
									{
										nTargetBytesInCurrentPercent = nSize;
									}
									else if(nTargetBytesInCurrentPercent < nSize && nCurrentPercent == 99)
									{
										nTargetBytesInCurrentPercent = nSize;
									}
								}
							}
						}
						
						rafTargetFile.seek(nOffset);
						rafTargetFile.write(bChunk, 0, (int)nRemaining);
						
						nRemaining = 0;
					}
					
					bComplete = true;
				}
			}
			else
			{
				byte bChunk[] = new byte[(int)TargetFile.length()];
				long nDone = 0;
				long nTotal = TargetFile.length();
				bSplittedByteInfo = new byte[2];
				rafKey.seek(1);
				rafKey.read(bSplittedByteInfo, 0, 2);
				long nTargetBytesInCurrentPercent = nTotal / 100;
				final long nOnePercent = nTotal / 100;
				int nCurrentPercent = 0;
				
				sBytes = (short)(((bSplittedByteInfo[1])<<8) | bSplittedByteInfo[0] & 0x00FF);
				Key = new byte[sBytes];
				
				rafKey.seek(3);
				rafKey.read(Key, 0, sBytes);
				rafTargetFile.seek(0);
				rafTargetFile.read(bChunk, 0, (int)TargetFile.length());
				
				short sKeyIndex = 0;
				
				for(int x=0;x<bChunk.length;x++)
				{
					if(sKeyIndex == sBytes)
					{
						sKeyIndex = 0;
					}
					
					bChunk[x] ^= Key[sKeyIndex];
					
					sKeyIndex++;
					
					if(this.jlProgress != null)
					{
						nDone++;
						
						if(nDone == nTargetBytesInCurrentPercent)
						{
							nCurrentPercent++;
							this.jlProgress.setValue(nCurrentPercent);
							nTargetBytesInCurrentPercent += nOnePercent;
							
							if(nTargetBytesInCurrentPercent >= nTotal)
							{
								nTargetBytesInCurrentPercent = nTotal;
							}
							else if(nTargetBytesInCurrentPercent < nTotal && nCurrentPercent == 99)
							{
								nTargetBytesInCurrentPercent = nTotal;
							}
						}
					}
				}
				
				rafTargetFile.seek(0);
				rafTargetFile.write(bChunk);
				bComplete = true;
			}
		}
		else
		{
			if(bFitToRAM == false)
			{
				bSplittedByteInfo = new byte[2];
				rafKey.seek(1);
				rafKey.read(bSplittedByteInfo, 0, 2);
				
				sBytes = (short)(((bSplittedByteInfo[1])<<8) | bSplittedByteInfo[0] & 0x00FF);
				Key = new byte[sBytes];
				bSplittedByteShuffledKey = new byte[sBytes * 2];
				sShuffledOffsets = new short[sBytes];
				
				
				rafKey.seek(3);
				rafKey.read(Key, 0, sBytes);
				rafKey.seek(3+sBytes);
				rafKey.read(bSplittedByteShuffledKey, 0, (sBytes*2));
				
				short sMergeToShortIndex = 0;
				
				for(int x=0;x<sBytes;x++)
				{
					sShuffledOffsets[x] = (short)(((bSplittedByteShuffledKey[sMergeToShortIndex+1]) << 8) | 0x00FF & bSplittedByteShuffledKey[sMergeToShortIndex]);
					sMergeToShortIndex += 2;
				}
				
				long nOffset = 0;
				long nDone = 0;
				long nRemaining = TargetFile.length();
				long nTotal = TargetFile.length();
				byte bChunk[] = new byte[sBytes*1024*1024];
				long nTargetBytesInCurrentPercent = nTotal / 100;
				final long nOnePercent = nTotal / 100;
				int nCurrentPercent = 0;
				
				while(nRemaining != 0)
				{
					if(nRemaining >= (sBytes*1024*1024))
					{
						rafTargetFile.seek(nOffset);
						rafTargetFile.read(bChunk, 0, (sBytes*1024*1024));
						short sKeyIndex = 0;
						
						for(int x=0;x<bChunk.length;x++)
						{
							if(sKeyIndex == sBytes)
							{
								sKeyIndex = 0;
							}
							
							bChunk[x] ^= Key[sKeyIndex];
							bChunk[x] ^= Key[sShuffledOffsets[sKeyIndex]];
							
							sKeyIndex++;
							
							if(this.jlProgress != null)
							{
								nDone++;
								
								if(nDone == nTargetBytesInCurrentPercent)
								{
									nCurrentPercent++;
									this.jlProgress.setValue(nCurrentPercent);
									nTargetBytesInCurrentPercent += nOnePercent;
									
									if(nTargetBytesInCurrentPercent >= nTotal)
									{
										nTargetBytesInCurrentPercent = nTotal;
									}
									else if(nTargetBytesInCurrentPercent < nTotal && nCurrentPercent == 99)
									{
										nTargetBytesInCurrentPercent = nTotal;
									}
								}
							}
						}
						
						rafTargetFile.seek(nOffset);
						rafTargetFile.write(bChunk, 0, (sBytes*1024*1024));
						
						nOffset += sBytes*1024*1024;
						nRemaining -= sBytes*1024*1024;
					}
					else
					{
						rafTargetFile.seek(nOffset);
						rafTargetFile.read(bChunk, 0, (int)nRemaining);
						short sKeyIndex = 0;
						
						for(int x=0;x<nRemaining;x++)
						{
							if(sKeyIndex == sBytes)
							{
								sKeyIndex = 0;
							}
							
							bChunk[x] ^= Key[sKeyIndex];
							bChunk[x] ^= Key[sShuffledOffsets[sKeyIndex]];
							
							sKeyIndex++;
							
							if(this.jlProgress != null)
							{
								nDone++;
								
								if(nDone == nTargetBytesInCurrentPercent)
								{
									nCurrentPercent++;
									this.jlProgress.setValue(nCurrentPercent);
									nTargetBytesInCurrentPercent += nOnePercent;
									
									if(nTargetBytesInCurrentPercent >= nTotal)
									{
										nTargetBytesInCurrentPercent = nTotal;
									}
									else if(nTargetBytesInCurrentPercent < nTotal && nCurrentPercent == 99)
									{
										nTargetBytesInCurrentPercent = nTotal;
									}
								}
							}
						}
						
						rafTargetFile.seek(nOffset);
						rafTargetFile.write(bChunk, 0, (int)nRemaining);
						
						nRemaining = 0;
					}
				}
				
				bComplete = true;
			}
			else
			{
				bSplittedByteInfo = new byte[2];
				rafKey.seek(1);
				rafKey.read(bSplittedByteInfo, 0, 2);
				
				sBytes = (short)(((bSplittedByteInfo[1])<<8) | bSplittedByteInfo[0] & 0x00FF);
				Key = new byte[sBytes];
				bSplittedByteShuffledKey = new byte[sBytes * 2];
				sShuffledOffsets = new short[sBytes];
				
				
				rafKey.seek(3);
				rafKey.read(Key, 0, sBytes);
				rafKey.seek(3+sBytes);
				rafKey.read(bSplittedByteShuffledKey, 0, (sBytes*2));
				
				short sMergeToShortIndex = 0;
				
				for(int x=0;x<sBytes;x++)
				{
					sShuffledOffsets[x] = (short)(((bSplittedByteShuffledKey[sMergeToShortIndex+1]) << 8) | 0x00FF & bSplittedByteShuffledKey[sMergeToShortIndex]);
					sMergeToShortIndex += 2;
				}
				
				long nDone = 0;
				long nTotal = TargetFile.length();
				byte bChunk[] = new byte[(int)TargetFile.length()];
				long nTargetBytesInCurrentPercent = nTotal / 100;
				final long nOnePercent = nTotal / 100;
				int nCurrentPercent = 0;
				
				rafTargetFile.seek(0);
				rafTargetFile.read(bChunk, 0, (int)TargetFile.length());
				
				short sKeyIndex = 0;
				
				for(int x=0;x<bChunk.length;x++)
				{
					if(sKeyIndex == sBytes)
					{
						sKeyIndex = 0;
					}
					
					bChunk[x] ^= Key[sKeyIndex];
					bChunk[x] ^= Key[sShuffledOffsets[sKeyIndex]];
					
					sKeyIndex++;
					
					if(this.jlProgress != null)
					{
						nDone++;
						
						if(nDone == nTargetBytesInCurrentPercent)
						{
							nCurrentPercent++;
							this.jlProgress.setValue(nCurrentPercent);
							nTargetBytesInCurrentPercent += nOnePercent;
							
							if(nTargetBytesInCurrentPercent >= nTotal)
							{
								nTargetBytesInCurrentPercent = nTotal;
							}
							else if(nTargetBytesInCurrentPercent < nTotal && nCurrentPercent == 99)
							{
								nTargetBytesInCurrentPercent = nTotal;
							}
						}
					}
				}
				
				rafTargetFile.seek(0);
				rafTargetFile.write(bChunk);
				bComplete = true;
			}
		}
		
		rafKey.close();
		rafTargetFile.close();
		
		return bComplete;
	}
	
	public GenerateKey CreateKey(short sBytes)
	{
		return new GenerateKey(this.sKey, sBytes);
	}
}