package main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.*;
import javax.swing.table.TableCellEditor;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
 
public class TableRendererPanel extends JFrame
{
    public TableRendererPanel()
    {
        JTable table = new JTable(20, 1);
        table.setRowHeight(50);
        JScrollPane scrollPane = new JScrollPane( table );
        getContentPane().add( scrollPane );
 
        table.setDefaultRenderer(Object.class, new MultiLabelRenderer());
    }
 
    public static void main(String[] args)
    {
        TableRendererPanel frame = new TableRendererPanel();
        frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
        frame.pack();
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
    }
 
    class MultiLabelRenderer implements TableCellRenderer
    {
 
        public MultiLabelRenderer()
        {
        	
        }
 
        public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, final int row, final int column)
        {
 
            JTextPane textPane = new JTextPane();
            textPane.setText("123");
            
            
            return textPane;
        }
    }
 
}