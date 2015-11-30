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
		Course c2=new Course(234,"sec2","MoWeTh","9:00AM","10:15AM","Boylan","Sibner");
		Course c3=new Course(345,"sec3","WeTh","2:00PM","3:15PM","Boylan","Foax");
		Course c4=new Course(984,"sec4","ThFrSu","10:15AM","11:15AM","WEB","Neng");
		Course c5=new Course(100,"sec0","Fr","10:15PM","11:15PM","WEB","test");
		/*System.out.println(c1);
		System.out.println(c2);
		System.out.println(c1.compareTo(c2));*/
		//c1.setName("1"); c2.setName("2"); c3.setName("3"); c4.setName("4"); c5.setName("5");
		MixedCourseList unsorted=new MixedCourseList();
		unsorted.addCourse(c5,"5");
		unsorted.addCourse(c1,"1");
		unsorted.addCourse(c2,"2");
		unsorted.addCourse(c3,"3");
		unsorted.addCourse(c4,"4");
		System.out.println(unsorted);
		System.out.println(unsorted.containsConflictingCourses()+"\n\n");
		insertionSort(unsorted);
		System.out.println(unsorted);
	}
	
	public static void insertionSort(MixedCourseList arr) {
	      int i, j;
	      Course newValue;
	      for (i=1; i<arr.size(); i++) {
	            newValue = arr.getCourse(i);
	            j=i;
	            while (j>0 && arr.getCourse(j - 1).compareTo(newValue)>0) {
	                  arr.getCourses().set(j, arr.getCourse(j-1));
	                  j--;
	            }
	            arr.getCourses().set(j,newValue);
	      }
	}
}