package optimizer;

public class MixedCourseList extends CourseList{

	private boolean[] classDays=new boolean[7];
	
	public MixedCourseList(CourseList c) {
		super(c);
		fillClassDays();
	}
	
	public MixedCourseList() {
		super("");
	}

	public void addCourse(Course c, String name) {
		c.setName(name);
		getCourses().add(c);
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
	
	/*public double duration() { //Tells you how many hours are spent on campus
		double dur=0;
		for (boolean day: classDays) {
			if (day) {
				
			}
		
		}	
		return dur;
	}*/
	
	public void sort() {	//Insertion sort, earliest class comes first, latest class last.
	      int i, j;
	      Course tmp;
	      for (i=1; i<size(); i++) {
	            tmp=getCourse(i);
	            j=i;
	            while (j>0 && getCourse(j-1).compareTo(tmp)>0) {
	                  getCourses().set(j, getCourse(j-1));
	                  j--;
	            }
	            getCourses().set(j,tmp);
	      }
	}
}