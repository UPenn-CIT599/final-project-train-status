/** @author Pammi Yeung
 * a data structure used to hold multiple values of generic type
 *
 */
 
public class Tuple<A,B> {
	A first;
	B second;

	public Tuple(A s1, B s2) {
		first = s1;
		second = s2;
	}
}

