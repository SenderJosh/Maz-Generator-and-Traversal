import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/***************************************************************************************
 *
 * NAME: Joshua Nishiguchi
 *
 * HOMEWORK: 6
 *
 * CLASS: ICS 211
 *
 * INSTRUCTOR: Scott Robertson
 *
 * DATE: March 9, 2016
 *
 * FILE: Main.java
 *
 * DESCRIPTION: This file has the main method to go traverse through a maze file.
 *
 ***************************************************************************************/
public class Main extends JFrame{
	private static int rows;
	private static int cols;
	private static DefaultTableModel model;
	private static JTable table;

	/**
	 * 
	 */
	private static final long serialVersionUID = 4648172894076113183L;
	public static JFrame frame = new JFrame();

	/********************************************************************
	 *
	 * Method: main
	 *
	 * Description: The main method of this program traverses the maze file provided with a given row and column number given in the
	 * command line
	 *
	 * @param Args - CommandLine
	 *
	 * @return None
	 *
	 ********************************************************************/
	public static void main(String[] args)
	{
		rows = 15;
		cols = 15;
		int comp = 15;
		MazeGenerator mg = new MazeGenerator(comp, comp-1);
		mg.generateMaze();
		try
		{
			mg.saveToFile(new File("maze.txt"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		final Maze m = new Maze(rows,cols, "maze.txt");
		System.out.println(mg.toString());
		JFrame.setDefaultLookAndFeelDecorated(true);
		String rowData[][] = m.getCellData();
		String[] colNames = new String[cols];
		for(int i = 0; i<cols; i++)
		{
			colNames[i] = i + "";
		}
		model = new DefaultTableModel(rowData, colNames);
		table = new JTable(model);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(String.class, centerRenderer);

		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane, BorderLayout.NORTH);
		
		frame.setSize(300, 150);
		frame.setVisible(true);
		frame.pack();
		frame.setVisible(true);
		m.findExitUsingQueue();
		//m.findExitUsingRecursion(m.findStart());
		//m.findExitUsingStack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void updateTable(int row, int col, String ele)
	{
		table.prepareRenderer(table.getCellRenderer(row, col), row, col).setBackground(Color.CYAN);
		model.setValueAt(ele, row, col);
	}
}
