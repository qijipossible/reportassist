package database;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		initdatabase a=new initdatabase();
//		a.initialize();
//		if(" ".equals("　"))
//			System.out.println("quan");
//		else if(" ".equals(" "))
//			System.out.println("banjiao");
//		else
//			System.out.println("none");
		String s=" gf　ｓｆ 54".replaceAll("　", "-").replaceAll(" ", "\n");
//		 Pattern pattern = Pattern.compile("[//s//p{Zs}]");
//	        Matcher re = pattern.matcher(s);
//	        s=re.replaceAll("");
		System.out.println(s);
	}

}
