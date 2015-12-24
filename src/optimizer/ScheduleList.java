package optimizer;
import java.util.ArrayList;

public class ScheduleList {

	private ArrayList<MixedCourseList> result;
	private static CourseList current = new CourseList("");
	
	public ScheduleList(ArrayList<CourseList> Lists, boolean permut) {
		result=new ArrayList<MixedCourseList>();
		if (permut)
			GeneratePermutations(Lists,result,0);
	}
	
	public ScheduleList(ArrayList<MixedCourseList> courses) {
		result=courses;
	}
	
	public ScheduleList(ScheduleList c) {
		result=c.getPossibleSchedules();
	}
	
	private static void GeneratePermutations(ArrayList<CourseList> Lists, ArrayList<MixedCourseList> result, int depth)
	{
	    if(depth == Lists.size()) {
	       MixedCourseList temp = new MixedCourseList(current);
	       result.add(temp);	       
	       return;
	     }
	    for(int i = 0; i < Lists.get(depth).size(); ++i) {
		  current.addCourse(Lists.get(depth).getCourse(i));
	        GeneratePermutations(Lists, result, depth + 1);
	        current.remove(current.size()-1);
	    }
	}
	
	public int removeAbsurdities() {
		int removed=0;
		for (int i=result.size(); i>0; i--)
			if (result.get(i-1).containsConflictingCourses()) {
				result.remove(i-1);
				removed++;
			}
		return removed;
	}
	
	public ArrayList<MixedCourseList> getPossibleSchedules() {
		return result;
	}
	
	public MixedCourseList getList(int i) {
		return result.get(i);
	}
	
	public int size() {
		return result.size();
	}
	
	public String toString() {
		String o="";
		for (int i=0; i<result.size(); i++)
			o+=("Schedule "+(i+1)+": "+result.get(i)+"\n\n");
		return o;
	}
}