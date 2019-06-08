package primeNumberSieves.prime_sieves;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AtkinSieve {

	private static Logger logger = Logger.getLogger(AtkinSieve.class.getName());
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter a valid number");
		try {
			Integer finiteLength = Integer.parseInt(br.readLine());
			Long time = System.nanoTime();
			Set<Integer> primeNumbers = findPrimes(finiteLength);
			System.out.println("The time captured : "+(System.nanoTime()-time)+" ns");
			System.out.println(primeNumbers);
		} catch (NumberFormatException e) {
			logger.log(Level.SEVERE,"Input reading Error - NumberFormat Exception");
			e.printStackTrace();
		} catch (IOException e) {
			logger.log(Level.SEVERE,"Input reading Error - IO Exception");
			e.printStackTrace();
		}
		
	}

	public static Set<Integer> findPrimes(Integer finiteLength) {
	
		// Expecting prime numbers after two
		if(finiteLength<=1) {
			throw new RuntimeException("There are no prime numbers to be found for this bound");
		}
		
		Set<Integer> primeNumbers = new LinkedHashSet<Integer>();

		// Since Atkins sieve works on modulo 60 basis we will add the first three numbers by default
		primeNumbers = handlePrimeNumbersIfLengthLessThanFive(finiteLength,primeNumbers);
		if(finiteLength<=5) {
			return primeNumbers;
		}
		
		//Initializing bitset of size n+1
		BitSet bitSet = new BitSet(finiteLength+1);
		bitSet.clear();
		/*
		 * Bit Set will be marked on the folllowing cases
		 * a) n = (4*x*x)+(y*y) has odd number  
           of solutions, i.e., there exist  
           odd number of distinct pairs  
           (x, y) that satisfy the equation  
           and    n % 12 = 1 or n % 12 = 5. 
        b) n = (3*x*x)+(y*y) has odd number  
           of solutions and n % 12 = 7 
        c) n = (3*x*x)-(y*y) has odd number  
           of solutions, x > y and n % 12 = 11 
		 */
		for(int i=1;i*i< finiteLength ; i++) {
			for(int j=1 ; j*j< finiteLength; j++) {
			
					int firstCondition = (4 * i * i) + ( j*j);
					if((firstCondition%12 == 1 || firstCondition%12 == 5)&&(firstCondition<=finiteLength)) {
						bitSet.flip(firstCondition);
					}
					
					
					int secondCondition = (3*i*i)+(j*j);
					if(secondCondition<= finiteLength && secondCondition%12 == 7) {
							bitSet.flip(secondCondition);
					}
					
					int thirdCondition = (3*i*i) - (j*j);
					if(i>j && thirdCondition<=finiteLength && thirdCondition%12 == 11) {
							bitSet.flip(thirdCondition);
					}
					
			}
		}
		
		
		// Now mark the remaining squares by using Erasthones Method
		for(int i=5; i*i<finiteLength;i++) {
			if(bitSet.get(i) == true) {
				for(int j = i*i; j< finiteLength; j+=i*i) {
					 bitSet.clear(j);
				}
			}
		}
		
		for(int i=6;i<=finiteLength;i++) {
			if(bitSet.get(i) == true) {
				primeNumbers.add(i);
			}
		}
		
		
		return primeNumbers;
	}

	private static Set<Integer> handlePrimeNumbersIfLengthLessThanFive(Integer finiteLength,
			Set<Integer> primeNumbers) {
		if(finiteLength >= 2) {
			primeNumbers.add(2);
		}
		if(finiteLength>=3) {
			primeNumbers.add(3);
		}
		if(finiteLength>=5) {
			primeNumbers.add(5);
		}
		return primeNumbers;
	}
}
