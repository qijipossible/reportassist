package motion;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import database.SQLop;

public class Motion {
	private static int[] count = new int[11];
	private static double aver;
	private static double sum = 0;
	private static int n = 0;
	private SQLop sqlop;
	private List<Map<String, String>> result;
	private int resultSize;
	public static int[] get_count(){
		return count;
	}
	public static double get_aver(){
		return aver;
	}
	public Motion(String keyword){
		init(keyword);
	}
	public void init(String keyword) {
		Dict opt_motion = new Dict("/dict/opt_motion.txt");
		Dict content1 = new Dict("/dict/content1.txt");
		Dict content2 = new Dict("/dict/content2.txt");
		Dict content3 = new Dict("/dict/content3.txt");
		Dict content4 = new Dict("/dict/content4.txt");
		Dict content5 = new Dict("/dict/content5.txt");
		Dict neg_motion = new Dict("/dict/neg_motion.txt");
		Dict deny_words = new Dict("/dict/deny_words.txt");
		//Dict reviews = new Dict("reviews.txt"); 


		sqlop = new SQLop();
		sqlop.initialize();
		result = sqlop.search(keyword, 3);
		sqlop.close();
		resultSize = result.size();
		
//		System.out.println("hello");
//		String regEx = "[\\u4e00-\\u9fa5]";
//		String str = "Internet 网络 is 真好 very  good ^_^!";
//		Pattern p = Pattern.compile(regEx);
//		Matcher m = p.matcher(str);
//		System.out.print("提取出来的中文有：");
//		while (m.find()) {
//			System.out.print(m.group(0)+" ");
//		}
//		System.out.println();
//		System.out.println(p.matches(regEx, "中"));
//		System.out.println(p.matches(regEx, "a"));
		String reg = "[，。！？~（）《》……、：——【】；’”‘“]";
		for (int row = 0;row<resultSize;row++){
			String review = result.get(row).get("content");
			//System.out.println(review);
			char[] chars=review.toCharArray(); 
			String sentence = "";
			int ans = 0;
			for (int i=0;i<review.length();i++){
				String w=review.substring(i, i+1);
				if(reg.indexOf(w)!=-1){//
					int k = reg.indexOf(w);
					if (k != 3) k = 1;
					int res = 0;
					int num = 0;
					int pos[] = new int[1000];
					for (String word : opt_motion.name){
						for (int j=0;j<sentence.length()-word.length()+1;j++){
							if (word.equals(sentence.substring(j, j+word.length()))){
								pos[++num] = j;
							}
						}
					}
					
					for (int j=0;j<num;j++){
						int tmp = 1;
						for (int l=pos[j];l<pos[j+1];l++){
							for (String con : content1.name){
								if (l+con.length()<=sentence.length() && con.equals(sentence.substring(l, l+con.length()))){
									tmp *= 1;
								}
							}
							for (String con : content2.name){
								if (l+con.length()<=sentence.length() && con.equals(sentence.substring(l, l+con.length()))){
									tmp *= 2;
								}
							}
							for (String con : content3.name){
								if (l+con.length()<=sentence.length() && con.equals(sentence.substring(l, l+con.length()))){
									tmp *= 3;
								}
							}
							for (String con : content4.name){
								if (l+con.length()<=sentence.length() && con.equals(sentence.substring(l, l+con.length()))){
									tmp *= 4;
								}
							}
							for (String con : content5.name){
								if (l+con.length()<=sentence.length() && con.equals(sentence.substring(l, l+con.length()))){
									tmp *= 5;
								}
							}
							for (String deny : deny_words.name){
								if (l+deny.length()<=sentence.length() && deny.equals(sentence.substring(l, l+deny.length()))){
									tmp *= -1;
									break;
								}
							}
						}
						res += tmp;
					}
					
					num = 0;
					for (String word : neg_motion.name){
						for (int j=0;j<sentence.length()-word.length()+1;j++){
							if (word.equals(sentence.substring(j, j+word.length()))){
								pos[++num] = j;
							}
						}
					}
					
					for (int j=0;j<num;j++){
						int tmp = -1;
						for (int l=pos[j];l<pos[j+1];l++){
							for (String con : content1.name){
								if (l+con.length()<=sentence.length() && con.equals(sentence.substring(l, l+con.length()))){
									tmp *= 1;
								}
							}
							for (String con : content2.name){
								if (l+con.length()<=sentence.length() && con.equals(sentence.substring(l, l+con.length()))){
									tmp *= 2;
								}
							}
							for (String con : content3.name){
								if (l+con.length()<=sentence.length() && con.equals(sentence.substring(l, l+con.length()))){
									tmp *= 3;
								}
							}
							for (String con : content4.name){
								if (l+con.length()<=sentence.length() && con.equals(sentence.substring(l, l+con.length()))){
									tmp *= 4;
								}
							}
							for (String con : content5.name){
								if (l+con.length()<=sentence.length() && con.equals(sentence.substring(l, l+con.length()))){
									tmp *= 5;
								}
							}
							for (String deny : deny_words.name){
								if (l+deny.length()<=sentence.length() && deny.equals(sentence.substring(l, l+deny.length()))){
									tmp *= -1;
									break;
								}
							}
						}
						res += tmp;
					}
					res *= k;
					//System.out.println(sentence + " " + res);
					ans += res;
					sentence = "";
				}else{
					sentence += chars[i];
				}
//	     		byte[] bytes=(""+chars[i]).getBytes(); 
//	     		if(bytes.length==2){ 
//		 			int[] ints=new int[2];
//		 			ints[0]=bytes[0]& 0xff; 
//		 			ints[1]=bytes[1]& 0xff; 
//		 			if(ints[0]>=0x81 && ints[0]<=0xFE && nts[1]>=0x40 && ints[1]<=0xFE){ 
//		 				sentence += chars[i];
//		 			}else{
//		 				System.out.println(sentence);
//		 				sentence = "";
//		 			}
//	     		}
			}
			if (!sentence.equals("")){
				int res = 0;
				int num = 0;
				int pos[] = new int[1000];
				for (String word : opt_motion.name){
					for (int j=0;j<sentence.length()-word.length()+1;j++){
						if (word.equals(sentence.substring(j, j+word.length()))){
							pos[++num] = j;
						}
					}
				}
				for (int j=0;j<num;j++){
					int tmp = 1;
					for (int l=pos[j];l<pos[j+1];l++){
						for (String con : content1.name){
							if (l+con.length()<=sentence.length() && con.equals(sentence.substring(l, l+con.length()))){
								tmp *= 1;
							}
						}
						for (String con : content2.name){
							if (l+con.length()<=sentence.length() && con.equals(sentence.substring(l, l+con.length()))){
								tmp *= 2;
							}
						}
						for (String con : content3.name){
							if (l+con.length()<=sentence.length() && con.equals(sentence.substring(l, l+con.length()))){
								tmp *= 3;
							}
						}
						for (String con : content4.name){
							if (l+con.length()<=sentence.length() && con.equals(sentence.substring(l, l+con.length()))){
								tmp *= 4;
							}
						}
						for (String con : content5.name){
							if (l+con.length()<=sentence.length() && con.equals(sentence.substring(l, l+con.length()))){
								tmp *= 5;
							}
						}
						for (String deny : deny_words.name){
							if (l+deny.length()<=sentence.length() && deny.equals(sentence.substring(l, l+deny.length()))){
								tmp *= -1;
								break;
							}
						}
					}
					res += tmp;
				}
				num = 0;
				for (String word : neg_motion.name){
					for (int j=0;j<sentence.length()-word.length()+1;j++){
						if (word.equals(sentence.substring(j, j+word.length()))){
							pos[++num] = j;
						}
					}
				}
				for (int j=0;j<num;j++){
					int tmp = -1;
					for (int l=pos[j];l<pos[j+1];l++){
						for (String con : content1.name){
							if (l+con.length()<=sentence.length() && con.equals(sentence.substring(l, l+con.length()))){
								tmp *= 1;
							}
						}
						for (String con : content2.name){
							if (l+con.length()<=sentence.length() && con.equals(sentence.substring(l, l+con.length()))){
								tmp *= 2;
							}
						}
						for (String con : content3.name){
							if (l+con.length()<=sentence.length() && con.equals(sentence.substring(l, l+con.length()))){
								tmp *= 3;
							}
						}
						for (String con : content4.name){
							if (l+con.length()<=sentence.length() && con.equals(sentence.substring(l, l+con.length()))){
								tmp *= 4;
							}
						}
						for (String con : content5.name){
							if (l+con.length()<=sentence.length() && con.equals(sentence.substring(l, l+con.length()))){
								tmp *= 5;
							}
						}
						for (String deny : deny_words.name){
							if (l+deny.length()<=sentence.length() && deny.equals(sentence.substring(l, l+deny.length()))){
								tmp *= -1;
								break;
							}
						}
					}
					res += tmp;
				}
				//System.out.println(sentence + " " + res);
				ans += res;
				sentence = "";
			}
			System.out.println(ans);
			if (ans < -5) ans = -5;
			if (ans > 5) ans = 5;
			count[ans+5]++;
			sum += ans;
			n++;
		}
		aver = sum/n;
		//System.out.println(get_aver());
		//int out[] = get_count();
		//for (int i=0;i<11;i++)
		//	System.out.println(out[i]);
	}
}
