import java.util.NoSuchElementException;
import java.util.Random;

public class RandomWords {
	public static void main(String[] args) {
	    long time = System.currentTimeMillis();
	    generate("luca");
	    generate("matt");
	    generate("glen");
	    generate("mina");

	    System.out.println("Took " + (System.currentTimeMillis() - time) + " ms");
	}

	private static void generate(String goal) {
	    long seed = generateSeed(goal);
	    System.out.println(seed);
	    System.out.println(randomString(seed));
	}

	public static long generateSeed(String goal) {
	    char[] input = goal.toCharArray();
	    char[] pool = new char[input.length];
	    
        for (long seed = 0; seed < Long.MAX_VALUE; seed++) {
	    	boolean abort = false;
	        Random random = new Random(seed);

	        for (int i = 0; i < input.length; i++)
	            pool[i] = (char) random.nextInt(27);

	        if (random.nextInt(27) == 0) {
	            for (int i = 0; i < input.length && !abort; i++) {
	                if (input[i] - pool[i] != (int) '`')
	                    abort = true;
	            }
	            if (!abort)
	            	return seed;
	        }

	    	abort = false;
	        random = new Random(-seed);

	        for (int i = 0; i < input.length; i++)
	            pool[i] = (char) random.nextInt(27);

	        if (random.nextInt(27) == 0) {
	            for (int i = 0; i < input.length && !abort; i++) {
	                if (input[i] - pool[i] != (int) '`')
	                    abort = true;
	            }
	            if (!abort)
	            	return -seed;
	        }
	    }

	    throw new NoSuchElementException("Sorry :/");
	}

	public static String randomString(long i) {
	    Random ran = new Random(i);
	    StringBuilder sb = new StringBuilder();
	    for (int n = 0; ; n++) {
	        int k = ran.nextInt(27);
	        if (k == 0)
	            break;

	        sb.append((char) ('`' + k));
	    }

	    return sb.toString();
	}
}
