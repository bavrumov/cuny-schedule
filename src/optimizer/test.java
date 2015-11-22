package optimizer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("unused")
public class test {

	static ArrayList<String> raw=new ArrayList<String>(); //{a,b,c,d}
	static ArrayList<String> sec=new ArrayList<String>();
	static ArrayList<String> tert=new ArrayList<String>();
	static ArrayList<Course> courses=new ArrayList<Course>();
	
	public static void main(String[] args) {
		raw.add("a");raw.add("b");raw.add("c");raw.add("d");;
		sec.add("w");sec.add("x");sec.add("y");sec.add("z");;
		tert.add("1"); tert.add("2"); tert.add("3"); 
		ArrayList<ArrayList<String>> f=new ArrayList<ArrayList<String>>();
		f.add(raw); f.add(sec);
		ArrayList<ArrayList<String>> z=new ArrayList<ArrayList<String>>();
		System.out.println(f);
		System.out.println();
		ArrayList<String> empty=new ArrayList<String>();
		//z=c(f);
		System.out.println(z);
		/*f.add(tert);
		System.out.println();
		
		f.add(0, raw);
		System.out.println(f);
		System.out.println();
		z=allCombos(f,empty);
		System.out.println(z);*/
	}
	
}
