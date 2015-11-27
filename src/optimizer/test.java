package optimizer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("unused")
public class test {
	
	public static void main(String[] args) {
		//System.out.println(mySched.getCourseList(0));
		//System.out.println("This courseList contains conflicting courses. "+mySched.getCourseList(0).containsConflictingCourses());
		//for (int i=0; i<7; i++)
		//	System.out.println(mySched.getCourseList(0).getCourse(2).getDOTW()[i]);
		Course c1=new Course(123,"sec1","MoWe","11:00AM","11:15AM","Ingersoll","Staff");
		Course c2=new Course(234,"sec2","MoWeTh","10:00AM","11:15AM","Boylan","Sibner");
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c1.compareTo(c2));
	}
}