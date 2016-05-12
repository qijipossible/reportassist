package AutoComplete;

import java.io.*;
import java.io.*;

public class HistoricRecords {

	final String file_path = ".\\.history";

	OutputStreamWriter out;
	InputStreamReader in;
	BufferedWriter writer;
	BufferedReader reader;

	String records = null;

	public String getHistory() {
		File file = new File(file_path);
		try {
			if (file.isFile() && file.exists()) {
				in = new InputStreamReader(
						new FileInputStream(file), "UTF-8");// 考虑到编码格式
				reader = new BufferedReader(in);
				records = reader.readLine();
			}else{
				file.createNewFile();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		finally{
			try {
				reader.close();
				in.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return records;
	}

	public void addHistory(String content) {
		File file = new File(file_path);
		if(content.trim().equals(""))
			return;
		try {
			this.getHistory();
			out = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			writer = new BufferedWriter(out);
			if (records != null) {
				String[] temp = records.split("\\|");
				for(String item:temp){
					if(item.equals(content.toString())){
						writer.write(records);
						return;
					}
				}
				if (temp.length >= 10) {
					records.replace(
							records.substring(0, records.indexOf("|")),
							content);
					writer.write(records);
				} else {
					writer.write(content + "|" + records);
				}
			}
			else{
				writer.write(content);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				writer.flush();
				writer.close();
				out.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

}
