import com.softserve.edu.dashboard.services.SQLProperty;

public class MainTest {

	public static void main(String[] args) {


		SQLProperty sqlp = new SQLProperty();
		
		System.out.println(SQLProperty.get("user.insert"));
	}

}
