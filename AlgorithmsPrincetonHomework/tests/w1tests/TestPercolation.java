package w1tests;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import w1percolation.Percolation;

public class TestPercolation {
	private static Percolation p;
	private static int size;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		size = 10;
		p = new Percolation(size);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		p = null;
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPercolation() {

		Percolation p1 = null;
		boolean correct1 = false;
		boolean correct2 = false;
		try {
			p1 = new Percolation(0);
		} catch (IllegalArgumentException e) {
			correct1 = true;
		}

		try {
			p1 = new Percolation(-1);
		} catch (IllegalArgumentException e) {
			correct2 = true;
		}

		assertTrue("Method should throw IllegalArgumentException for N = 0",
				correct1);
		assertTrue("Method should throw IllegalArgumentException for N < 0",
				correct2);
	}

	@Test
	public void testOpenForException() {
		testAllBounds();

	}

	private void testAllBounds() {
		boolean correct1 = false, correct2 = false, correct3 = false;
		boolean correct4 = false, correct5 = false, correct6 = false;
		boolean correct7 = true;
		correct1 = testBoundsForException(0, 1);
		correct2 = testBoundsForException(1,0);
		correct3 = testBoundsForException(-1,1);
		correct4 = testBoundsForException(1,-1);
		correct5 = testBoundsForException(size + 1, size);
		correct6 = testBoundsForException(size, size + 1);
		
		try {
			p.isOpen(size, size);
		} catch (Exception e) {
			correct7 = false;
			e.printStackTrace();
		}

		assertTrue("Method should throw IndexOutOfBoundsException for i = 0",
				correct1);
		assertTrue("Method should throw IndexOutOfBoundsException for j = 0",
				correct2);
		assertTrue("Method should throw IndexOutOfBoundsException for i < 0",
				correct3);
		assertTrue("Method should throw IndexOutOfBoundsException for j < 0",
				correct4);
		assertTrue(
				"Method should throw IndexOutOfBoundsException for i > array size",
				correct5);
		assertTrue(
				"Method should throw IndexOutOfBoundsException for j > array size",
				correct6);
		assertTrue(
				"Method shouldn't throw any exception for i, j in [1, array size]",
				correct7);
	}

	private boolean testBoundsForException(int i, int j) {
		boolean correct = false;
		try {
			p.isOpen(i, j);
		} catch (IndexOutOfBoundsException e) {
			correct = true;
		}
		return correct;
	}

	@Test
	public void testIsOpen() {
		boolean correct1 = false, correct2 = false, correct3 = false;
		boolean correct4 = false, correct5 = false, correct6 = false;
		boolean correct7 = true;
		try {
			p.isOpen(0, 1);
		} catch (IndexOutOfBoundsException e) {
			correct1 = true;
		}

		try {
			p.isOpen(1, 0);
		} catch (IndexOutOfBoundsException e) {
			correct2 = true;
		}
		try {
			p.isOpen(-1, 1);
		} catch (IndexOutOfBoundsException e) {
			correct3 = true;
		}

		try {
			p.isOpen(1, -1);
		} catch (IndexOutOfBoundsException e) {
			correct4 = true;
		}

		try {
			p.isOpen(size + 1, size);
		} catch (IndexOutOfBoundsException e) {
			correct5 = true;
		}

		try {
			p.isOpen(size, size + 1);
		} catch (IndexOutOfBoundsException e) {
			correct6 = true;
		}

		try {
			p.isOpen(size, size);
		} catch (Exception e) {
			e.printStackTrace();
			correct7 = false;
		}

		assertTrue("Method should throw IndexOutOfBoundsException for i = 0",
				correct1);
		assertTrue("Method should throw IndexOutOfBoundsException for j = 0",
				correct2);
		assertTrue("Method should throw IndexOutOfBoundsException for i < 0",
				correct3);
		assertTrue("Method should throw IndexOutOfBoundsException for j < 0",
				correct4);
		assertTrue(
				"Method should throw IndexOutOfBoundsException for i > array size",
				correct5);
		assertTrue(
				"Method should throw IndexOutOfBoundsException for j > array size",
				correct6);
		assertTrue(
				"Method shouldn't throw any exception for i, j in [1, array size]",
				correct7);
	}

	@Test
	public void testIsFull() {
		boolean correct1 = false, correct2 = false, correct3 = false;
		boolean correct4 = false, correct5 = false, correct6 = false;
		boolean correct7 = true;
		try {
			p.isFull(0, 1);
		} catch (IndexOutOfBoundsException e) {
			correct1 = true;
		}

		try {
			p.isFull(1, 0);
		} catch (IndexOutOfBoundsException e) {
			correct2 = true;
		}
		try {
			p.isFull(-1, 1);
		} catch (IndexOutOfBoundsException e) {
			correct3 = true;
		}

		try {
			p.isFull(1, -1);
		} catch (IndexOutOfBoundsException e) {
			correct4 = true;
		}

		try {
			p.isFull(size + 1, size);
		} catch (IndexOutOfBoundsException e) {
			correct5 = true;
		}

		try {
			p.isFull(size, size + 1);
		} catch (IndexOutOfBoundsException e) {
			correct6 = true;
		}

		try {
			p.isFull(size, size);
		} catch (Exception e) {
			correct7 = false;
			e.printStackTrace();
		}

		assertTrue("Method should throw IndexOutOfBoundsException for i = 0",
				correct1);
		assertTrue("Method should throw IndexOutOfBoundsException for j = 0",
				correct2);
		assertTrue("Method should throw IndexOutOfBoundsException for i < 0",
				correct3);
		assertTrue("Method should throw IndexOutOfBoundsException for j < 0",
				correct4);
		assertTrue(
				"Method should throw IndexOutOfBoundsException for i > array size",
				correct5);
		assertTrue(
				"Method should throw IndexOutOfBoundsException for j > array size",
				correct6);
		assertTrue(
				"Method shouldn't throw any exception for i, j in [1, array size]",
				correct7);
	}

	@Test
	public void testPercolates() {
		Percolation p1 = new Percolation(3);
		p1.open(1, 1);
		p1.open(3, 3);

		boolean percolated = p1.percolates();
		assertFalse(
				"Shouldn't be percolated after only top and bottom are open",
				percolated);

		p1.open(2, 2);
		percolated = p1.percolates();
		assertFalse(
				"Shouldn't be percolated after only top and bottom are open",
				percolated);

		p1.open(1, 2);
		p1.open(3, 2);
		percolated = p1.percolates();
		assertTrue("Should be percolated.", percolated);

	}

}
