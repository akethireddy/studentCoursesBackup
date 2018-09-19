package studentCoursesBackup.util;

import java.io.BufferedWriter;
import java.io.IOException;
import studentCoursesBackup.myTree.Node;


/**
 * 
 * @author Abinaya Kethireddy
 * Class to print the results to standard output or to the output files
 *
 */
public class Results implements StdoutDisplayInterface,FileDisplayInterface{

	/**
	 * Empty constructor
	 */
	public Results() {
		
	}
	
	@Override
	public String toString() {
		return "Results [getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	

	/**
	 * Method called if the number of arguments provided is in correct
	 */
	public void OutLength()
	{
		System.out.println("\nThere should be 5 input text files given as arguments\n");
		System.exit(0);
	}
	

	/**
	 * Method called if the file is not closed
	 */
	public void fileOpen()
	{
		System.out.println("\nFiles are not closed\n");
		System.exit(0);
	}
	


	/**
	 * Method called if the input files are empty
	 */
	public void emptyFile()
	{
		System.out.println("\nInput File should not be empty\n");
		System.exit(0);
	}
	


	/**
	 * Method called if the output files are not empty
	 */
	public void unEmptyFile()
	{
		System.out.println("\nOutput Files should be empty\n");
		System.exit(0);
	}
	


	/**
	 * Method called if the input files provided cannot be located
	 */
	public void unreachableFile()
	{
		System.out.println("\nFiles provided as arguments are not present at the specified location\n");
		System.exit(0);
	}
	


	/**
	 * @param root - root of the tree
	 */
	public void display(Node root){
		
		int i=0;
		if(root!=null){
			display(root.left);
			System.out.println(" " + root.Bnumber +" and backup1 node= "+root.backups.get(0).Bnumber +" and backup2 node= "+root.backups.get(1).Bnumber);
			while(i<11)
			{
				if(root.backups.get(0).courses[i]!=null)
				System.out.print(root.backups.get(0).courses[i]);
				i++;
			}
			display(root.right);
		}
		
	}
	


	/**
	 * @param root- root of the tree
	 * @param bw1 - buffer to store the written content
	 * @param bw2 - buffer to store the written content
	 * @param bw3 - buffer to store the written content
	 */
	public void fileDisplay(Node root,BufferedWriter bw1,BufferedWriter bw2,BufferedWriter bw3){
		
		int i=0;
		
			try {
				if(root!=null){
				fileDisplay(root.left,bw1,bw2,bw3);
				bw1.write(root.Bnumber+":");
				bw2.write(root.backups.get(0).Bnumber+":");
				bw3.write(root.backups.get(1).Bnumber+":");
				while(i<11)
				{
					if(root.backups.get(0).courses[i]!=null)
					{
					bw1.write(root.courses[i]+" ");
					bw2.write(root.backups.get(0).courses[i]+" ");
					bw3.write(root.backups.get(1).courses[i]+" ");
					}
					i++;
				}
				bw1.write("\n");
				bw2.write("\n");
				bw3.write("\n");
				fileDisplay(root.right,bw1,bw2,bw3);
				
				}
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			finally
			{
				
			}
					
	}

	

}
