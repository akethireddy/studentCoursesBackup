package studentCoursesBackup.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import studentCoursesBackup.myTree.BST;
import studentCoursesBackup.myTree.Node;


/**
 * 
 * @author Abinaya Kethireddy
 * Class to parse lines in the files and create trees 
 *
 */
public class FileProcessor {
	public static ArrayList<String> listOfFiles=new ArrayList<String>();

	/**
	 * Empty constructor
	 */
	public FileProcessor()
	{
		
	}
	

	/**
	 * constructor with parameters
	 * listOfFilesIn - has the array list of files provided as the arguments
	 */
	public FileProcessor(ArrayList<String> listOfFilesIn) 
	{
		listOfFiles=listOfFilesIn;
	}

	@Override
	public String toString() {
		return "FileProcessor [getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}


	/**
	 * Method to validate the file
	 */
	public void fileParser()
	{
		Results r=new Results();
		File f1=new File(listOfFiles.get(0));
		File f2=new File(listOfFiles.get(1));
		File f3=new File(listOfFiles.get(2));
		File f4=new File(listOfFiles.get(3));
		File f5=new File(listOfFiles.get(4));


		if(listOfFiles.size()!=5 || listOfFiles.get(0).equals("${arg0}") || listOfFiles.get(1).equals("${arg1}")||listOfFiles.get(2).equals("${arg2}")||listOfFiles.get(3).equals("${arg3}")||listOfFiles.get(4).equals("${arg4}"))
		{
			r.OutLength();
		}
		else if(f1.exists()==false || f2.exists()==false)
		{
			r.unreachableFile();
		}
		else if(f1.length()==0 || f2.length()==0)
		{	
			r.emptyFile();
		}
		else if(f3.length()!=0 || f4.length()!=0 || f5.length()!=0)
		{
			r.unEmptyFile();
		}
		else
		{
			return;
		}
		
		return;
	
	}



	/**
	 * 
	 * @param bstIn - reference to the tree
	 * @return - returns the root node
	 */
	public Node fileRead(BST bstIn)
	{
	
		String str;
		FileReader f1,f2;
		BufferedReader b1=null,b2=null;
		Node node=new Node();
		try {	
			f1 = new FileReader(listOfFiles.get(0));
			b1=new BufferedReader(f1);
			boolean insertDecider;
			while((str=b1.readLine())!=null)
			{
				
				String[] splitString=str.split(":");
				int firstNode=Integer.parseInt(splitString[0]);
				insertDecider=node.find(bstIn.root,firstNode);
				if(insertDecider==false)
				{
					
					bstIn.root=node.register(bstIn.root,firstNode,splitString[1]);
					
				}
				else
				{
					bstIn.root=node.update("insert",bstIn.root,firstNode,splitString[1]);
				}
				
			}

			f1.close();
			b1.close();
			f2 = new FileReader(listOfFiles.get(1));
			b2=new BufferedReader(f2);
			boolean deleteDecider=true;
			while((str=b2.readLine())!=null)
			{
				
				String[] splitString=str.split(":");
				int firstNode=Integer.parseInt(splitString[0]);
				deleteDecider=node.find(bstIn.root,firstNode);
				if(deleteDecider==true)
				{
					
					bstIn.root=node.update("delete",bstIn.root,firstNode,splitString[1]);
					
				}
				else
				{
	
				}
				
			}
			b2.close();
			return bstIn.root;
					
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			
		}
		return bstIn.root;

		
	}

	

}
