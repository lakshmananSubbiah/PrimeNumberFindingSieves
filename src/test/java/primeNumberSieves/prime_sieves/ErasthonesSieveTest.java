package primeNumberSieves.prime_sieves;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

class ErasthonesSieveTest {

	@Test
	void erasthonesSieveZeroCase() {
		RuntimeException re = assertThrows(RuntimeException.class, ()->{
			ErasthonesSieve.findPrimes(0);
		});
		assertThat(re.getMessage(),equalTo("There are no prime numbers to be found for this bound"));
	}
	
	@Test
	void erasthonesSieveOneCase() {
		RuntimeException re = assertThrows(RuntimeException.class, ()->{
			ErasthonesSieve.findPrimes(1);
		});
		assertThat(re.getMessage(),equalTo("There are no prime numbers to be found for this bound"));
	}
	
	@Test
	void erasthonesSieveNegativeCase() {
		RuntimeException re = assertThrows(RuntimeException.class, ()->{
			ErasthonesSieve.findPrimes(-90);
		});
		assertThat(re.getMessage(),equalTo("There are no prime numbers to be found for this bound"));
	}
	
	@Test
	void findPrimeNumberTwoCase() {
		Set<Integer> set = ErasthonesSieve.findPrimes(2);
		assertThat(set.size(),equalTo(1));
		assertThat(set.contains(2),equalTo(true));
	}
	
	@Test
	void findPrimeNumberThreeCase() {
		Set<Integer> set = ErasthonesSieve.findPrimes(3);
		assertThat(set.size(),equalTo(2));
		assertThat(set.contains(2),equalTo(true));
		assertThat(set.contains(3),equalTo(true));
	}
	
	@Test
	void findPrimeNumber500Case() {
		Set<Integer> set = ErasthonesSieve.findPrimes(500);
		assertThat(set.size(),equalTo(95));
		assertThat(set.contains(379),equalTo(true));
		assertThat(set.contains(137),equalTo(true));
		assertThat(set.contains(499),equalTo(true));
	}

}
