package primeNumberSieves.prime_sieves;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ErasthonesSieve {

	private static final Logger logger = Logger.getLogger(ErasthonesSieve.class.getName());
	
	public static void main(String[] args) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the finite number of length");
		try {
			int finiteLength = Integer.parseInt(br.readLine());
			Long time = System.nanoTime();
			Set<Integer> primeNumbers = findPrimes(finiteLength);
			System.out.println("Eratosthenes Sieve");
			System.out.println("The time captured : "+(System.nanoTime()-time)+" ns");
			System.out.print(primeNumbers);
		} catch (NumberFormatException e) {
			logger.log(Level.SEVERE, "Number Format Exception -- Buffered Reader Input issue");
			e.printStackTrace();
		} catch (IOException e) {
			logger.log(Level.SEVERE, "IO Exception -- Buffered Reader Input issue");
			e.printStackTrace();
		}
		
	}

	public static Set<Integer> findPrimes(int finiteLength) {
		// the length must be above two to obtain the prime numbers
		if(finiteLength<=1) {
			throw new RuntimeException("There are no prime numbers to be found for this bound");
		}
		
		Set<Integer> primeNumbers = new LinkedHashSet<Integer>();
		
		BitSet bitSet = new BitSet(finiteLength+1);
		bitSet.clear();
		/**
		 * The bitset is cleared initially and if the value is still false it will be prime
		 */
		for( int number=2; number*number <= finiteLength; number++) {
			if(bitSet.get(number) == false) {
				for(int i=number*number; i<=finiteLength; i+=number) {
					if(bitSet.get(i) == false) {
						bitSet.set(i);
					}
				}
			}
		}
		
		// finally after through with the logic the remaining numbers will be returned back as prime
		
		for(int i=2; i<=finiteLength; i++) {
			if(bitSet.get(i) == false) {
				primeNumbers.add(i);
			}
		}
		return primeNumbers;
	}
}
