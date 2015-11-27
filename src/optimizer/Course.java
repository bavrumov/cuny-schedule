package optimizer;

public class Course {

	private int classNumber; //UID
	private String section;
	private String days; //MoTuWeThFrSaSu 
	private boolean[] dotw=new boolean[7];
	private String room;
	private String professor;
	private String humanStart;
	private String humanEnd;
	private double startTime; //Time 9:30=9.5. 12:45=12.75.
	private double endTime;  //Time 4:55 PM = 16.91666.
	private String name;
	
	public Course(int cNum, String sec, String d, String start, String end, String loc, String prof ) {
		classNumber=cNum;
		section=sec;
		days=d;
		humanStart=start;
		humanEnd=end;
		startTime=parseTime(start);
		endTime=parseTime(end);
		room=loc;
		professor=prof;
		parseDays();
	}

	public boolean conflictsWith(Course c) { //true if courses overlap, false if courses touch and/or don't overlap
		boolean timeConflict=(c.startTime>=startTime && c.startTime<endTime) || (c.endTime>startTime && c.endTime<=endTime);
		boolean dateConflict=false;	 //(c.startTime<startTime && c.endTime>endTime) || (startTime<c.startTime && endTime>c.endTime);
		for (int i=0; i<7; i++)
			if (dotw[i]==true && dotw[i]==c.dotw[i]) {
				dateConflict=true;
				break;
			}//System.out.println(timeConflict+" and "+dateConflict);
		return timeConflict && dateConflict;
	}
	
	public String toString() {
		return (" "+classNumber+" "+section+" "+days+" From "+humanStart+" to "+humanEnd+"\n   With "+professor+" at "+room);
	}
	
	private void parseDays() {
		String[] d={"Su","Mo","Tu","We","Th","Fr","Sa"};
		for (int i=0; i<7; i++)
			if (days.indexOf(d[i])!=-1)
				dotw[i]=true;
	}
	
	private static double parseTime(String t) {
		String AorP=t.substring(t.length() - 2);
		String first=t.split(":")[0];
		String second=t.split(":")[1].substring(0, 2);
		if ((AorP.equals("AM")) == (first.equals("12")))	//Breaks down if 12AM
			return 12+Double.parseDouble(first)+Double.parseDouble(second)/60;
		return Double.parseDouble(first)+Double.parseDouble(second)/60;
	}
	
	public double duration() {
		return endTime-startTime;
	}
	
	public int getClassNumber() {
		return classNumber;
	}

	public String getSection() {
		return section;
	}

	public String getDays() {
		return days;
	}

	public String getRoom() {
		return room;
	}

	public String getProfessor() {
		return professor;
	}

	public String getHumanStart() {
		return humanStart;
	}
	
	public String getHumanEnd() {
		return humanEnd;
	}
	
	public double getStartTime() {
		return startTime;
	}

	public double getEndTime() {
		return endTime;
	}
	
	public boolean[] getDOTW() {
		return dotw;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name=n;
	}
}