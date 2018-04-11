import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.testng.Assert.assertEquals;

class ProblemTest {
    Solution.Problem problem = new Solution.Problem();

    @Test
    void test2CS0() {
        assertEquals(problem.solve(new BigInteger("2"), "CS"), 0);
    }

    @Test
    void test4CSS0() {
        assertEquals(problem.solve(new BigInteger("4"), "CCS"), 0);
    }

    @Test
    void test1Switch() {
        assertEquals(problem.solve(new BigInteger("1"), "CS"),1);
    }

    @Test
    void test1Switch1() {
        assertEquals(problem.solve(new BigInteger("1"), "CSC"),1);
    }

    @Test
    void test1Switch2() {
        assertEquals(problem.solve(new BigInteger("1"), "CCS"),2);
    }

    @Test
    void test1Switch2Impossible() {
        assertEquals(problem.solve(new BigInteger("1"), "CCS"),2);
    }

    @Test
    void test2Switch() {
        assertEquals(problem.solve(new BigInteger("6"), "SCCSSC"), 2);
    }

    @Test
    void test5Switch() {
        assertEquals(problem.solve(new BigInteger("3"), "CSCSS"), 5);
    }

    @Test
    void testImpossible() {
        assertEquals(problem.solve(new BigInteger("1"), "SS"), -1);
    }

    @Test
    void testNoShots() {
        assertEquals(problem.solve(new BigInteger("2"), "CC"), 0);
    }
}