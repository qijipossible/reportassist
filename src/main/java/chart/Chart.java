package chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jfree.data.*; 
import org.jfree.data.category.*;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.*; 
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.LineRenderer3D;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;

import database.SQLop;;

public class Chart {
	static final int PIC_LENGTH = 300;
	static final int PIC_WIDTH = 400;
	
	static final int SITE = 1;
	static final int YEAR = 2;
	
	SQLop sqlop = new SQLop();
	String keyword = new String();
	
	public Chart(String kw){
		keyword = kw;
		sqlop.initialize();
		//System.out.println(sqlop.countAllResult(keyword));
		siteChart();
		yearChart();
		sqlop.close();
	}
	
	public void siteChart(){
		CategoryDataset dataset = getSiteDataset();
		JFreeChart chart = ChartFactory.createBarChart3D(
				"来源站点统计",
				"来源", 
				"数量", 
				dataset,
				PlotOrientation.VERTICAL,
				false,
				false,
				false);
		
		//以下部分为柱状图的美化
		TextTitle title = chart.getTitle();
		title.setFont(Fonts.title);//标题字体
		title.setPaint(Colors.a1);
		CategoryPlot plot = chart.getCategoryPlot();//plot用于设置外部属性，如坐标轴
		plot.setBackgroundPaint(Colors.a5);
		plot.setDomainGridlinePaint(Colors.a3);
		plot.setRangeGridlinePaint(Colors.a3);
		plot.setOutlinePaint(Color.BLACK);
		CategoryAxis domainAxis = plot.getDomainAxis();//DomainAxis
		domainAxis.setLabelFont(Fonts.axis_lable);
		domainAxis.setTickLabelFont(Fonts.axis);
		domainAxis.setLabelPaint(Colors.a1);
		domainAxis.setTickLabelPaint(Colors.a2);
		ValueAxis rangeAxis = plot.getRangeAxis();//RangAxis
		rangeAxis.setLabelFont(Fonts.axis_lable);
		rangeAxis.setTickLabelFont(Fonts.axis);
		rangeAxis.setLabelPaint(Colors.a1);
		rangeAxis.setTickLabelPaint(Colors.a2);
		BarRenderer3D renderer = new BarRenderer3D();//renderer用于设置内部属性，如柱子颜色
		renderer.setSeriesPaint(0, Colors.a3);
		renderer.setBaseOutlinePaint(Colors.a4);
		renderer.setWallPaint(Colors.a5.darker());
		//柱子上面的值
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseItemLabelPaint(Colors.a5);
		plot.setRenderer(renderer);
		
		
		
		
		
		
		FileOutputStream pic_out = null; 
        try { 
<<<<<<< HEAD
            fos_jpg = new FileOutputStream(".\\barchartTEST.jpg"); 
            ChartUtilities.writeChartAsJPEG(fos_jpg,(float)1.0,chart,400,300,null); 
=======
            pic_out = new FileOutputStream("D:\\barchartTEST.jpg"); 
            ChartUtilities.writeChartAsJPEG(pic_out,1.0f,chart,PIC_WIDTH,PIC_LENGTH,null); 
>>>>>>> origin/master
        } catch (Exception e) {
			e.printStackTrace();
		} finally { 
            try { 
                pic_out.close(); 
            } catch (Exception e) {} 
        } 
	}
	
	private CategoryDataset getSiteDataset(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		HashMap<String, Integer> hashmap = sqlop.count(SITE, keyword);
		List<Map.Entry<String, Integer>> entry =
			    new ArrayList<Map.Entry<String, Integer>>(hashmap.entrySet());
		for(Map.Entry<String, Integer> i: entry){
			dataset.addValue(i.getValue().intValue(), "source", i.getKey());
		}
		return dataset;
	}
	
	public void yearChart(){
		DefaultCategoryDataset dataset = getYearDataset();
		
		JFreeChart chart = ChartFactory.createLineChart3D("年份分布统计",
				"年份",
				"数量",
				dataset,
				PlotOrientation.VERTICAL,
				false,
				false,
				false);
		
		TextTitle title = chart.getTitle();
		title.setFont(Fonts.title);//标题字体
		title.setPaint(Colors.a1);
		CategoryPlot plot = chart.getCategoryPlot();//plot用于设置外部属性，如坐标轴
		plot.setBackgroundPaint(Colors.a5);
		plot.setDomainGridlinePaint(Colors.a3);
		plot.setRangeGridlinePaint(Colors.a3);
		plot.setOutlinePaint(Color.BLACK);
		CategoryAxis domainAxis = plot.getDomainAxis();//DomainAxis
		domainAxis.setLabelFont(Fonts.axis_lable);
		domainAxis.setTickLabelFont(Fonts.axis);
		domainAxis.setLabelPaint(Colors.a1);
		domainAxis.setTickLabelPaint(Colors.a2);
		ValueAxis rangeAxis = plot.getRangeAxis();//RangAxis
		rangeAxis.setLabelFont(Fonts.axis_lable);
		rangeAxis.setTickLabelFont(Fonts.axis);
		rangeAxis.setLabelPaint(Colors.a1);
		rangeAxis.setTickLabelPaint(Colors.a2);
		LineRenderer3D renderer = new LineRenderer3D();//renderer用于设置内部属性，如柱子颜色
		renderer.setSeriesPaint(0, Colors.a3);
		//renderer.setBaseOutlinePaint(Colors.a4);
		renderer.setWallPaint(Colors.a5.darker());
		//柱子上面的值
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseItemLabelPaint(Colors.a4);
		plot.setRenderer(renderer);
	    
		FileOutputStream pic_out = null; 
        try { 
<<<<<<< HEAD
            fos_jpg = new FileOutputStream(".\\linechartTEST.jpg"); 
            ChartUtilities.writeChartAsJPEG(fos_jpg,(float)1.0,chart,400,300,null); 
=======
            pic_out = new FileOutputStream("D:\\linechartTEST.jpg"); 
            ChartUtilities.writeChartAsJPEG(pic_out,1.0f,chart,PIC_WIDTH,PIC_LENGTH,null); 
>>>>>>> origin/master
        } catch (Exception e) {
			e.printStackTrace();
		} finally { 
            try { 
                pic_out.close(); 
            } catch (Exception e) {} 
        } 
	}
	
	private DefaultCategoryDataset getYearDataset(){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		HashMap<String, Integer> hashmap = sqlop.count(YEAR, keyword);
		
		List<Map.Entry<String, Integer>> entry =
			    new ArrayList<Map.Entry<String, Integer>>(hashmap.entrySet());
		Collections.sort(entry, new Comparator<Map.Entry<String, Integer>>() {   
		    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {   
		        return (o1.getKey()).toString().compareTo(o2.getKey());
		    }
		});
		
		for(Map.Entry<String, Integer> i: entry){
			dataset.addValue(i.getValue(),"year",i.getKey());
		}
		
		return dataset;
	}
	
	private static class Fonts{
		static final Font title = new Font("微软雅黑",Font.BOLD,20);
		static final Font axis_lable = new Font("微软雅黑",Font.BOLD,15);
		static final Font axis = new Font("微软雅黑",Font.PLAIN,12);
	}
	
	private static class Colors{
		static final Color a0 = Color.WHITE;
		static final Color a1 = new Color(20,26,55);
		static final Color a2 = new Color(40,71,92);
		static final Color a3 = new Color(74,108,116);
		static final Color a4 = new Color(139,166,147);
		static final Color a5 = new Color(240,227,192);
		
	}
}
