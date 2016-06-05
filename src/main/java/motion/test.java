package motion;

public class test {

	public static void main(String[] args) {
		Motion t = new Motion("公车改革");
		System.out.println(t.get_aver());
		for (int i=0;i<11;i++)
			System.out.println(t.get_count()[i]);
	}

}