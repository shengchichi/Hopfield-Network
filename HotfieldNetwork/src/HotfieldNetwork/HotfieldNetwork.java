package HotfieldNetwork;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HotfieldNetwork implements ActionListener{
	String[] test = {"Basic_Train","Basic_Test","HOPFIELD_Training","HOPFIELD_Testing"};
	JFrame frame = new JFrame("Hopfield");
	JPanel before = new JPanel();
	JComboBox select = new JComboBox(test);
	String selectedFile;
	ArrayList<String> string = new ArrayList<String>(); 
	JPanel after = new JPanel();
	JPanel gui = new JPanel();
	JButton back = new JButton("recall");
	JButton next = new JButton("next");
	
	JLabel arr[];
	int column;
	int temp =0;
	int alpha =0;
	int nums =0;
	int data;
	double w[][];
	double theta[];
	double output[];
	boolean train;
	boolean change = false;
	boolean keep = false;
	//JLabel before = new JLabel("ddd");
	//JLabel after = new JLabel("aaa");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new HotfieldNetwork(); 
	}
	HotfieldNetwork()
	{
		
		frame.setSize(900,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		gui.add(select);
		gui.add(back);
		gui.add(next);
		back.addActionListener(this);
		next.addActionListener(this);
		select.addActionListener(this);
		before.setBackground(Color.WHITE);
		after.setBackground(Color.WHITE);
		before.setBounds(0,100,420,500);
		after.setBounds(480,100,420,500);
		gui.setBounds(0,0,800,100);
		frame.add(gui);
		frame.add(before);
		frame.add(after);
		frame.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==next)
		{
			
			change = true;
			keep =false;
			alpha++;
			if(alpha==nums-1)
				next.setEnabled(false);
			
			
		}
		String s = (String) select.getSelectedItem();//get the selected item
        switch (s) 
        {//check for a match
            case "Basic_Train":
            	data=1;
                train = true;
                break;
            case "Basic_Test":
            	data=1;
            	train = false;
                break;
            case "HOPFIELD_Training":
            	data =2;
            	train =true;
            	break;
            case "HOPFIELD_Testing":
            	data=2;
            	train = false;
            	break;
        }
        
        if(train == true||change==true)
        {
        	if(keep==false)
        	{
        		if(data == 1)
            		selectedFile = "基本題Training.txt";
            	if(data == 2)
            		selectedFile = "HOPFIELD_Training.txt";
            	
            	scan();
            	System.out.println("train");
            	before.removeAll();
                JLabel arr[] = new JLabel[string.get(alpha).length()];
                before.setLayout(new GridLayout(string.get(alpha).length()/column,column));
                for(int i=0;i<string.get(alpha).length();i++)
                {
                	if(string.get(alpha).charAt(i) == '1')
                	{
                		arr[i] = new JLabel();
                		arr[i].setBackground(Color.BLACK);
                		
                	}
                	else
                	{
                		arr[i] = new JLabel();
                		arr[i].setBackground(Color.GRAY);
                	}
                	arr[i].setOpaque(true);	
                	before.add(arr[i]);
                }
                frame.add(before);
                before.revalidate();
                frame.repaint();
                
            	getW();
            	gettheta();
        	}
        	
        }
        if(train == false||change==true)
        {
        	if(keep==false)
        	{
        		if(data == 1)
            		selectedFile = "基本題Testing.txt";
            	if(data == 2)
            		selectedFile = "HOPFIELD_Testing.txt";
            	scan();
            	System.out.println("test");
            	after.removeAll();
                JLabel arr[] = new JLabel[string.get(alpha).length()];
                after.setLayout(new GridLayout(string.get(alpha).length()/column,column));
                for(int i=0;i<string.get(alpha).length();i++)
                {
                	if(string.get(alpha).charAt(i) == '1')
                	{
                		arr[i] = new JLabel();
                		arr[i].setBackground(Color.BLACK);
                		
                	}
                	else
                	{
                		arr[i] = new JLabel();
                		arr[i].setBackground(Color.GRAY);
                	}
                	arr[i].setOpaque(true);	
                	after.add(arr[i]);
                }
                frame.add(after);
                after.revalidate();
                frame.repaint();
        	}
        	
        }
        if(e.getSource()==back)
		{
        	keep = true;
			test();
			System.out.println("change");
        	after.removeAll();
            JLabel arr[] = new JLabel[string.get(alpha).length()];
            after.setLayout(new GridLayout(string.get(alpha).length()/column,column));
            for(int i=0;i<string.get(alpha).length();i++)
            {
            	if(string.get(alpha).charAt(i) == '1')
            	{
            		arr[i] = new JLabel();
            		arr[i].setBackground(Color.BLACK);
            		
            	}
            	else
            	{
            		arr[i] = new JLabel();
            		arr[i].setBackground(Color.GRAY);
            	}
            	arr[i].setOpaque(true);	
            	after.add(arr[i]);
            }
            frame.add(after);
            after.revalidate();
            frame.repaint();
			
		}
        
        
        
    	
	}
	public void scan()
	{
		String buf = "";
		Scanner sc = null;
		try{
            sc = new Scanner(new File(selectedFile));
        }catch(FileNotFoundException e){  
            e.printStackTrace();  
            System.exit(0);
        } 
		column = 0;
		string.clear();
		nums =0;
		 while(sc.hasNextLine())
	      {
	    	  String s=sc.nextLine();
	    	  if(s.equals(""))
	    	  {
	    		  string.add(buf);
	    		  buf="";
	    		  nums++;
	    	  }
	    	  else
	    	  {
	    		  buf+=s;
	    		  column = s.length();
	    		  //System.out.println(s.length());
	    	  }
	      	  
	      	  
	       }
		 //System.out.println(string.get(0).length());

	}
	public void getW()
	{	
		
		String str;
		for(int input=0;input<nums;input++)
		{
			str = string.get(input);
			str = str.replace(" ", "/");
			string.set(input, str);
		}
		
		w= new double[string.get(alpha).length()][string.get(alpha).length()];
		for(int i=0;i<string.get(alpha).length();i++)
			for(int j=0;j<string.get(alpha).length();j++)
				w[i][j] =0;		
		
		for(int input=0;input<nums;input++)
		{
			double p = 1 / (double)((string.get(input).length()));
			for(int i=0;i<string.get(input).length();i++)
			{
				for(int j=0;j<string.get(input).length();j++)
				{
					if(i==j)
						w[i][j] =0;
					else
						w[i][j] += p*(string.get(input).charAt(i)-'0')*(string.get(input).charAt(j)-'0');	
				}
			}
			
		}
		/*
		for(int i=0;i<string.get(alpha).length();i++)
		{
			for(int j=0;j<string.get(alpha).length();j++)
			{
				System.out.print(w[i][j] + " ");
			}
			System.out.println();
		}
		*/
		
		

		
			
	}
	public void gettheta()
	{
		theta = new double[string.get(alpha).length()];
		int k=0;
		for(int i=0;i<string.get(alpha).length();i++)
		{
			for(int j=0;j<string.get(alpha).length();j++)
			{
				theta[k]+=w[i][j];
			}
			k++;
		}
	}
	public void test()
	{
		String str;
		for(int input=0;input<nums;input++)
		{
			str = string.get(input);
			str = str.replace(" ", "/");
			string.set(input, str);
		}
		System.out.println("aa:"+string.get(alpha));
	
		output = new double[string.get(alpha).length()];
		
		for (int i = 0; i < string.get(alpha).length(); i++) 
		{
			for (int j = 0; j < string.get(alpha).length(); j++) 
	        {
				//System.out.println(w[i][j]);
				output[i] += w[i][j] * (string.get(alpha).charAt(j)-'0');
	        }
			//System.out.println();
	    }
		String s="";
		for(int i = 0; i < string.get(alpha).length(); i++)
		{
			if(Math.abs(output[i]) < 1E-6)
			{
				if(string.get(alpha).charAt(i)==' ')
					s=s.concat(" ");
				else
					s=s.concat("1");
			}				
			else if(output[i] < 0)
				s=s.concat(" ");
			else			
				s=s.concat("1");			
		}
		System.out.println("bb:"+s);
		string.set(alpha, s);
		System.out.println("cc:"+string.get(alpha));
		
	}
	
	
	
}
