package cu.slate;

import java.util.ArrayList;

import org.htmlparser.Node;
import org.htmlparser.lexer.Lexer;
import org.htmlparser.util.ParserException;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

// "cms/input/objects"

public class Courseworks extends Activity {

	private String uni = "nk2485";
	private String pass = "password";
	private ArrayList<Course> courseData;
	private ListView lv;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.courseworks);
		getResponse();
		lv = (ListView) findViewById(R.id.listView1);
		String[] arr = new String[courseData.size()];
		for(int i = 0; i < courseData.size(); i++)
			arr[i] = courseData.get(i).getCourseName();
		lv.setAdapter(new ArrayAdapter<String>(this, R.layout.coursetextview, arr));
	}

	public void getResponse() {
		if(courseData == null)
			courseData = new ArrayList<Course>();
		HttpRequest request = new HttpRequest();
		String response = request.sendPost("https://wind.columbia.edu/login?destination=https%3A%2F%2Fcourseworks%2Ecolumbia%2Eedu%2Fcms%2Fwind%2Flogin%2Ecfm%3Fskinbutton=2", "service=course-works&forcelogin=0&destination=https%3A%2F%2Fcourseworks.columbia.edu%2Fcms%2Fwind%2Flogin.cfm%3Fskinbutton%3D2&username="+uni+"&password="+pass+"&log+in=Log+in", null);
		ArrayList<String> inter = parseResponse(response);
		for(String s : inter) {
			String[] arr = s.split(",");
			courseData.add(new Course(arr[1], arr[2], arr[0]));
		}
	}

	public ArrayList<String> parseResponse(String response) {
		Lexer lexer = new Lexer(response);
		ArrayList<String> dataList = new ArrayList<String>();
		String currentClass = "";
		try {
			Node n;
			while((n = lexer.nextNode()) != null)
				if(n.getText().contains("nowrap") && n.getText().contains("courselisting")) {
					currentClass = "";
					lexer.nextNode();
					String rawURL = lexer.nextNode().getText();
					int a = rawURL.indexOf("\"");
					String parsedURL = rawURL.substring(a + 1, rawURL.indexOf("\"", a + 1));
					if(!parsedURL.contains("newcourseworks"))
						parsedURL = "https://courseworks.columbia.edu/cms/input/" + parsedURL.replaceAll("&amp;", "&");
					if(parsedURL != "")
						currentClass += parsedURL;
				} else if (n.getText().contains("courselisting")) {
					String tmp = lexer.nextNode().getText().trim();
					if(tmp != "" && tmp != " ") {
						if(tmp.toUpperCase().equals(tmp))
							currentClass += ", " + tmp;
						else {
							currentClass += ", " + tmp;
							dataList.add(currentClass);
						}
					}
				}
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return dataList;
	}

}