package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Rom {

	public static final int MAX_ROM_SIZE = 2097152;
	
	private int[] rom;
	private int size;
	private String name;

	public Rom(File romToLoad) throws IOException {
		rom = new int[2097152];
		load(romToLoad);
	}
	
	public void load( File romToLoad ) throws IOException {
		if ( !romToLoad.canRead() )
			throw new FileNotFoundException("File '" + romToLoad.getPath() + "' is unreadable.");
		
		try (FileInputStream is = new FileInputStream(romToLoad)) {
			int i=0;
			while ( is.available() > 0 ) {
				rom[i] = is.read();
				i++;
			}
			size = i;
			name = romToLoad.getName();
		}
	}
	
	public String getName() {
		return name;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getByteAt(int position) {
		if (position <= size) 
			return rom[position];
		else
			throw new IllegalAccessError( String.format(
				"Attempted to fetch byte %s of ROM, but ROM is only %s bytes long.", 
				position, size
			));
	}
}
