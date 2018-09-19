package studentCoursesBackup.driver;
import java.util.ArrayList;
import studentCoursesBackup.util.FileProcessor;
import studentCoursesBackup.myTree.BST;
import studentCoursesBackup.util.Results;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


/**
 * 
 * @author Abinaya Kethireddy
 * 
 *This Driver class accepts the user inputs, creates a tree and sends the results to files
 */

public class Driver {

	/**
	 * @param args
	 * This class takes 5 input files as arguments and creates 3 trees
	 */
	public static void main(String[] args) {
		
		ArrayList<String> listOfFiles=new ArrayList<String>();
		if(args.length>5)
		{
			System.out.println("There should be only 5 input files provided\n");
			System.exit(0);
		}
		listOfFiles.add(args[0]);
		listOfFiles.add(args[1]);
		listOfFiles.add(args[2]);
		listOfFiles.add(args[3]);
		listOfFiles.add(args[4]);
		FileProcessor fp=new FileProcessor(listOfFiles);
		fp.fileParser();
		
		BST bst=new BST();
		bst.root=fp.fileRead(bst);
		Results r= new Results();
		//r.display(bst.root);	

		FileWriter fw1,fw2,fw3;
		BufferedWriter bw1,bw2,bw3;
		try {
			fw1=new FileWriter(listOfFiles.get(2));
			fw2=new FileWriter(listOfFiles.get(3));
			fw3=new FileWriter(listOfFiles.get(4));
			bw1=new BufferedWriter(fw1);
			bw2=new BufferedWriter(fw2);
			bw3=new BufferedWriter(fw3);
			r.fileDisplay(bst.root, bw1,bw2,bw3);
			bw1.close();
			bw2.close();
			bw3.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		finally
		{
			System.out.println("\nThe trees data is sent to the output files provided\n");
		}
	}

}
