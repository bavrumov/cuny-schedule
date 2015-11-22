package optimizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseList {

	private ArrayList<Course> courses=new ArrayList<Course>();
	private String path;
	private boolean mixedClasses=false;
	
	public CourseList(String f) {  //Constructor requires file path, if path isn't provided courses can be manually added.
		path=f;
		try {generateList();} catch (FileNotFoundException e) {path="none";}
	}
	
	public CourseList(CourseList c) {
		for (int i=0; i<c.size(); i++)
			courses.add(c.getCourse(i));
	}
	
	private void generateList() throws FileNotFoundException {
		int k=0;
		ArrayList<String> raw=new ArrayList<String>();
		Scanner sc = new Scanner(new File(path));
		while (sc.hasNextLine()) {
			raw.add( sc.nextLine());
			k++;
		}
		sc.close();  //Closes scanner, next part begin parsing file data
		String[] datetime;
		int a=0;
		String b="",c="",d="",e="",f="",g="";
		for (int i=0; i<k; i++) {
			if (i%9==0) //classNum
				a=Integer.parseInt(raw.get(i));
			else
				if (i%9==1) //section
					b=raw.get(i);
				else
					if (i%9==3) {
						datetime=raw.get(i).split(" ");
						c=datetime[0]; //days
						d=datetime[1]; //start
						e=datetime[3]; //end
					}
					else
						if (i%9==4)//room
							f=raw.get(i);
						else
							if (i%9==5) //professor
								g=raw.get(i);
							else
								if (i%9==8)
									courses.add(new Course(a,b,c,d,e,f,g));
		}
	}

	public void setAllNames(String s) {
		for (Course c : courses)
			c.setName(s);
	}
	
	public void addCourse(Course c) {
		courses.add(c);
	}
	
	public Course getCourse(int i) {
		return courses.get(i);
	}
	
	public void setMixed() {
		mixedClasses=true;
	}
	
	public boolean containsConflictingCourses() { //true if any course in list overlaps
		for (Course c1 : courses)
			for (Course c2 : courses)
				if (courses.indexOf(c1)<courses.indexOf(c2) && c1.conflictsWith(c2))
					return true;
		return false;
	}
	
	public int size() {
		return courses.size();
	}
	
	public String toString() {
		if (mixedClasses)
			return mixedString();
		String o="";
		for (int i=0; i<courses.size(); i++)
			o+=(" Section "+(i+1)+": \n "+courses.get(i)+"\n");
		return o;
	}
	
	public String mixedString() {
		String o="";
		for (int i=0; i<courses.size(); i++)
			o+=("Class "+(i+1)+": "+courses.get(i).getName().toUpperCase()+"\n "+courses.get(i)+"\n ");
		return o;
	}
	
	public void remove(int i) {
		courses.remove(i);
	}
}