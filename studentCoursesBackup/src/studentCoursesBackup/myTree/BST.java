package studentCoursesBackup.myTree;

/**
 * 
 * A class to create a tree
 *
 */
public class BST {
	public Node root;

        /*
	 * constructor
	 */
	public BST()
	{
		root=null;		
	}	
		
	public Node getRoot() {
		return root;
	}

	public void setRoot(Node rootIn) {
		root = rootIn;
	}	


	@Override
	public String toString() {
		return "BST [root=" + root + "]";
	}
}