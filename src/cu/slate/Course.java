package cu.slate;

public class Course {

	private String courseName;
	private String instructor;
	private String url;
	
	public Course(String courseName, String instructor, String url) {
		this.courseName = courseName;
		this.instructor = instructor;
		this.url = url;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
