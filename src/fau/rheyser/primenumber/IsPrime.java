package fau.rheyser.primenumber;

/**
 * @author Ryan Heyser
 * Checks to see if a number is prime
 */
public class IsPrime {
	/**
	 * Checks an input integer th if it is prime
	 * @param th integer to check if prime  
	 * @return true if prime, false if notprime
	 */
	public static boolean isPrime(int th) {
		if(th < 2) return false;
		if(th == 2 || th == 3) return true;
		if(th%2 == 0 || th%3 == 0) return false;
		for (int i = 3; i <= Math.sqrt(th) + 1; i = i + 2) {
            if (th % i == 0) {
                return false;
            }
        }
		return true;
	}
}
