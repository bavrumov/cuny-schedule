package optimizer;

public class MixedCourseList extends CourseList{

	private boolean[] classDays=new boolean[7];
	
	public MixedCourseList(CourseList c) {
		super(c);
		fillClassDays();
	}

	public boolean containsConflictingCourses() {
		for (Course c1 : getCourses())
			for (Course c2 : getCourses())
				if (getCourses().indexOf(c1)<getCourses().indexOf(c2) && c1.conflictsWith(c2))
					return true;
		return false;
	}

	private void fillClassDays() {
		for (Course c: getCourses())
			for (int i=0; i<7; i++)
				if (c.getDOTW()[i])
					classDays[i]=true;
	}
	
	public String toString() {
		int days=0;
		for (boolean day: classDays)
			if (day)
				days++;
		String o=days+" days a week. {\n ";
		for (int i=0; i<getCourses().size(); i++)
			o+=("Class "+(i+1)+": "+getCourses().get(i).getName().toUpperCase()+"\n "+getCourses().get(i)+"\n ");
		o=o.substring(0,o.length()-1)+"}"; //removes trailing space and adds bracket
		return o;
	}	
	
	public void setAllNames(String n) {
		return;	//Overrides parent, A MixedCourseList should never have a uniform name
	}
}