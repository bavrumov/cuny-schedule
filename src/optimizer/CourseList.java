package optimizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseList {

	private ArrayList<Course> courses=new ArrayList<Course>();
	private String path;
	
	public CourseList(String f) {  //Constructor requires file path, if path isn't provided courses can be manually added.
		path=f;
		try {generateList();} catch (FileNotFoundException e) {path="none";}
	}
	
	public CourseList(CourseList c) {
		for (int i=0; i<c.size(); i++)
			getCourses().add(c.getCourse(i));
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
		for (Course c : getCourses())
			c.setName(s);
	}
	
	public void addCourse(Course c) {
		getCourses().add(c);
	}
	
	public Course getCourse(int i) {
		return getCourses().get(i);
	}
	
	public int size() {
		return getCourses().size();
	}
	
	public String toString() {
		String o="";
		for (int i=0; i<getCourses().size(); i++)
			o+=(" Section "+(i+1)+": \n "+getCourses().get(i)+"\n");
		return o;
	}
	
	public void remove(int i) { //Used when removing absurdities within ScheduleList
		getCourses().remove(i);
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}
}