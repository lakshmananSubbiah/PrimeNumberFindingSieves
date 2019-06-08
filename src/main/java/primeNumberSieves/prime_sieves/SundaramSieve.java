package primeNumberSieves.prime_sieves;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SundaramSieve {

	private static Logger logger = Logger.getLogger(SundaramSieve.class.getName());
	
	public static void main(String[] args) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		/** Receiving Input upto which prime numbers has to be found **/
		
		System.out.println("Enter the finite series end");
		try {
			int finiteLength = Integer.parseInt(br.readLine());
			Long time = System.nanoTime();
			Set<Integer> primeNumbers = findPrimes(finiteLength);
			System.out.println("The time captured : "+(System.nanoTime()-time)+" ns");
			System.out.println(primeNumbers.size());
			System.out.println(primeNumbers);
		} catch (NumberFormatException e) {
			logger.log(Level.SEVERE, "Number Format Exception -- Input reception Exception");
			e.printStackTrace();
		} catch (IOException e) {
			logger.log(Level.SEVERE, "IO Exception - Input reception Exception");
			e.printStackTrace();
		}
		
	}

	public static Set<Integer> findPrimes(int finiteLength) {
		/**The prime numbers are only above two **/
		if(finiteLength <= 1) {
			throw new RuntimeException("There are no prime numbers to be found for this bound");
		}
		
		Set<Integer> primeNumbers = new LinkedHashSet<Integer>();
		//Explicitly added 2 because odd prime numbers with 2k+1 excludes 2 so it is explicitly added
		primeNumbers.add(2);
		
		if(finiteLength == 2) {
			return primeNumbers;
		}

		// Since we need only the numbers within 2n+2 we will reduce it to half
		
		int mid = finiteLength/2;

		//Bit set created to specific size
		BitSet bitSet = new BitSet(mid);
		bitSet.clear();
		
		// set the index number of the form
	    // (i + j + 2ij) as true such that 1<=i<=j
	    // this is the main logic of the sieve of sundaram
		for(int i=1;i<=mid;i++) {
			for(int j=i; ( i+j+(2*i*j)) <= mid; j++) {
				if(bitSet.get(i+j+(2*i*j)) == false) {
					bitSet.flip(i+j+(2*i*j));
				}
			}
		}
		
		//Now add the odd numbers to the prime list
		for(int i=1;i<=mid;i++) {
			if(bitSet.get(i) == false) {
				primeNumbers.add((2*i)+1);
			}
		}

		return primeNumbers;
	}
}
