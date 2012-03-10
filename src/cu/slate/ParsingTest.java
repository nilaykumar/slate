package cu.slate;

import java.util.ArrayList;

/**
 * Designed to test out methods for parsing CW
 *
 */
public class ParsingTest {

	static ArrayList<Course> lists = new ArrayList<Course>();
	
	public static void main(String[] args) {
		lists = new ArrayList<Course>();
		HttpRequest request = new HttpRequest();
		String response = request.sendPost("https://wind.columbia.edu/login?destination=https%3A%2F%2Fcourseworks%2Ecolumbia%2Eedu%2Fcms%2Fwind%2Flogin%2Ecfm%3Fskinbutton=2", "service=course-works&forcelogin=0&destination=https%3A%2F%2Fcourseworks.columbia.edu%2Fcms%2Fwind%2Flogin.cfm%3Fskinbutton%3D2&username="+"nk2485"+"&password="+"z7qra2n10"+"&log+in=Log+in", null);
		System.out.println(response);
	}

}
