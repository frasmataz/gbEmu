package test;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import junit.framework.TestCase;
import model.Rom;

public class RomTest extends TestCase {

	Rom testRom;
	File testFile = new File("src/test/Tetris.gb");
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		try {
			testRom = new Rom(testFile);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Loading ROM failed.");
		}
	}
	
	@Test
	public void testGetName() {
		assertEquals("Tetris.gb", testRom.getName());
	}
	
	@Test
	public void testRomBytes() {
		assertEquals(0xE0, testRom.getByteAt(100));
		assertEquals(0xCD, testRom.getByteAt(200));
		assertEquals(0xDD, testRom.getByteAt(300));
	}
	
	@Test
	public void testSize() {
		assertEquals(32768, testRom.getSize());
	}
	
	@Test
	public void testOverRead() {
		try {
			testRom.getByteAt(32769);
			fail();
		} catch (IllegalAccessError e) {
			
		}
	}
}
