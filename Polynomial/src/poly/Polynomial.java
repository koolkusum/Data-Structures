package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		Node sumpoly= null;
		while (poly1!=null|| poly2!=null){
			if (poly1==null){//this if statement takes into account if polynomials are different lenghts or run to null before the other
					sumpoly= new Node(poly2.term.coeff,poly2.term.degree,sumpoly);
					poly2=poly2.next;System.out.println(poly2);
				}
				else if(poly2==null){//same with this one
					sumpoly= new Node(poly1.term.coeff,poly1.term.degree,sumpoly);
					poly1=poly1.next;
				}
				else{
					if(poly1.term.degree==poly2.term.degree){//adding terms
						float sum = poly1.term.coeff+poly2.term.coeff; 
						if (sum!=0){
						sumpoly= new Node(sum, poly1.term.degree, sumpoly);}
						poly1=poly1.next;
						poly2= poly2.next;
						
					}
					else{
						if (poly1.term.degree<poly2.term.degree){//put the higher degree term in order
							sumpoly = new Node(poly1.term.coeff,poly1.term.degree,sumpoly);
							poly1= poly1.next;
							
						}
						else if (poly1.term.degree>poly2.term.degree){//putting in order
							sumpoly = new Node(poly2.term.coeff,poly2.term.degree,sumpoly);
							poly2=poly2.next;
						}
					}
				}
			
		
			}
			Node addpoly =null;
			while(sumpoly!=null){//reversing the power
				addpoly = new Node(sumpoly.term.coeff,sumpoly.term.degree,addpoly);
				sumpoly=sumpoly.next;
			}
		 return addpoly;

		//add two polynomial equation, so basicially I have to figure out a way for it to read each node
		//the the list and add themm when there is a common degree, not exactly sure if it has to return
		//in the degree form they are looking for but it was to be all new nodes no recycling, 
		//figure out what values go in for i
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		//return null;
	}
	
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		Node prodpoly=null;
		Node ply22 = poly2;
		int max= 0;
		while(poly1!=null){//multiplying out
			while (ply22!=null){
				float coeff= poly1.term.coeff*ply22.term.coeff;
				int degree= poly1.term.degree+ply22.term.degree;
				if (coeff!=0){
				prodpoly= new Node(coeff, degree, prodpoly);
				}
				ply22 = ply22.next;
				if (degree>max){
					max=degree;//did this so i can combine like terms later on (taking the higest degree)
				}
			}
			ply22 = poly2; 
 
			poly1 = poly1.next; 
		}
		Node simplfy = null;
		for (int i = 0; i<= max; i++) {//this is where the max comes in
			float summation = 0;
			Node prodpoly2=prodpoly;
			while (prodpoly2 != null) {
				if (prodpoly2.term.degree == i)//while the degree of whichever the linked list node it is on is equal to i them combine like terms
					summation+=prodpoly2.term.coeff;
				prodpoly2 = prodpoly2.next;
			}
			if (summation != 0)
				simplfy = new Node(summation, i, simplfy);//adding the new combined term node
		}
		Node reversepoly =null;//reversing so powers are in order
		while(simplfy!=null){
			reversepoly= new Node(simplfy.term.coeff,simplfy.term.degree,reversepoly);
			simplfy=simplfy.next;
		}

		return reversepoly;
	}
		
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		float add=0;//basically plug in for x with the the float x into Node poly
		while (poly!=null){
			add+= poly.term.coeff*Math.pow(x, poly.term.degree);
			poly= poly.next;
		}
		return add;
	}
	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
}
