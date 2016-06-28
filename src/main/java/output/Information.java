package output;

import java.util.ArrayList;
import java.util.List;

public class Information {  
    private String motion_jpg = null;//����̬�ȷֲ�
    private String source_jpg = null;//��ע������������������ý�壬��ṫ�ڣ���״ͼ��
    private String year_comments_jpg = null;//��ע����ʱ��ı仯����״ͼ��
    
    private List<String> source_theme = null;//��ͬ����(������ý�塢����)��ע������
    private List<String> year_theme = null;//���Է�ֵ��ȵ�����
   
    private String global_attitude = null;//ȫ�����������ָ��
    private String gov_attitude = null;// ��������̬��ָ��
    private String media_attitude = null;// ý��������
    private String public_attitude = null;// ���ڵ������
    
    private List<String> hot_theme = null;//�ȵ�����,�����ȵ�����⣬�ȵ����£��ȵ�ý�壬�ȵ�����
    private List<String> other = null;//���жȽϸߵĸ���۵㣬���жȽϸߵ�����۵�
    
    private Information() {
    	
    }  
  
    private static volatile Information instance = null;  
  
    public static Information getInstance() {  
           if (instance == null) {    
             synchronized (Information.class) {    
                if (instance == null) {    
                	instance = new Information();   
                }    
             }    
           }   
           return instance;  
    }  
 
    
  
    public String getMotion_jpg() {
		return motion_jpg;
	}



	public void setMotion_jpg(String motion_jpg) {
		this.motion_jpg = motion_jpg;
	}



	public String getSource_jpg() {
		return source_jpg;
	}



	public void setSource_jpg(String source_jpg) {
		this.source_jpg = source_jpg;
	}



	public String getYear_comments_jpg() {
		return year_comments_jpg;
	}



	public void setYear_comments_jpg(String year_comments_jpg) {
		this.year_comments_jpg = year_comments_jpg;
	}



	public List<String> getSource_theme() {
		return source_theme;
	}



	public void setSource_theme(List<String> source_theme) {
		this.source_theme = source_theme;
	}



	public List<String> getYear_theme() {
		return year_theme;
	}



	public void setYear_theme(List<String> year_theme) {
		this.year_theme = year_theme;
	}



	public String getGlobal_attitude() {
		return global_attitude;
	}



	public void setGlobal_attitude(String global_attitude) {
		this.global_attitude = global_attitude;
	}



	public String getGov_attitude() {
		return gov_attitude;
	}



	public void setGov_attitude(String gov_attitude) {
		this.gov_attitude = gov_attitude;
	}



	public String getMedia_attitude() {
		return media_attitude;
	}



	public void setMedia_attitude(String media_attitude) {
		this.media_attitude = media_attitude;
	}



	public String getPublic_attitude() {
		return public_attitude;
	}



	public void setPublic_attitude(String public_attitude) {
		this.public_attitude = public_attitude;
	}



	public List<String> getHot_theme() {
		return hot_theme;
	}



	public void setHot_theme(List<String> hot_theme) {
		this.hot_theme = hot_theme;
	}



	public List<String> getOther() {
		return other;
	}



	public void setOther(List<String> other) {
		this.other = other;
	}



	public void printInfo() {  
        System.out.println("the name is " );  
    }  
  
}  

