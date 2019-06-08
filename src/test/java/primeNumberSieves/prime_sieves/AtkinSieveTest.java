package primeNumberSieves.prime_sieves;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Test;

class AtkinSieveTest {

	@Test
	void AtkinSieveTestZero() {
		RuntimeException re = assertThrows(RuntimeException.class, ()->{
			AtkinSieve.findPrimes(0);
		});
		assertThat(re.getMessage(),equalTo("There are no prime numbers to be found for this bound"));
	}
	
	@Test
	void AtkinSieveTestOne() {
		RuntimeException re = assertThrows(RuntimeException.class, ()->{
			AtkinSieve.findPrimes(1);
		});
		assertThat(re.getMessage(),equalTo("There are no prime numbers to be found for this bound"));
	}
	
	@Test
	void AtkinSieveTestNegative() {
		RuntimeException re = assertThrows(RuntimeException.class, ()->{
			AtkinSieve.findPrimes(-203);
		});
		assertThat(re.getMessage(),equalTo("There are no prime numbers to be found for this bound"));
	}
	
	@Test
	void AtkinSieveTestTwo() {
		Set<Integer> set = AtkinSieve.findPrimes(2);
		assertThat(set.size(),equalTo(1));
		assertThat(set.contains(2),equalTo(true));
	}
	
	@Test
	void AtkinSieveTestThree() {
		Set<Integer> set = AtkinSieve.findPrimes(3);
		assertThat(set.size(),equalTo(2));
		assertThat(set.contains(3),equalTo(true));
	}
	
	@Test
	void AtkinSieveTestFive() {
		Set<Integer> set = AtkinSieve.findPrimes(5);
		assertThat(set.size(),equalTo(3));
		assertThat(set.contains(5),equalTo(true));
	}
	
	@Test
	void AtkinSieveTestTwenty() {
		Set<Integer> set = AtkinSieve.findPrimes(20);
		assertThat(set.size(),equalTo(8));
		assertThat(set.contains(17),equalTo(true));
		assertThat(set.contains(14),equalTo(false));
	}
	
	@Test
	void AtkinSieveTest500() {
		Set<Integer> set = AtkinSieve.findPrimes(500);
		assertThat(set.size(),equalTo(95));
		assertThat(set.contains(379),equalTo(true));
		assertThat(set.contains(137),equalTo(true));
		assertThat(set.contains(499),equalTo(true));
	}
}
