package studentCoursesBackup.myTree;
import java.util.ArrayList;

/**
 * 
 * A class which inserts and deletes data in a node of a tree. 
 * The code for insert, find and display is taken from 
 * https://algorithms.tutorialhorizon.com/binary-search-tree-complete-implementation/
 * 
 * the code for cloning for reference is taken from the below provided site
 * https://www.geeksforgeeks.org/clone-method-in-java-2/
 *
 */
public class Node implements ObserverI,SubjectI,Cloneable{

	public int Bnumber;
	public String[] courses=new String[11];
	public Node left=null,right=null;
	public ArrayList<Node> backups=new ArrayList<Node>();
	
	/**
	 * Empty constructor
	 */
	public Node()
	{
		
	}


	/**
	 * 
	 * @param bNumIn - B-Number
	 * @param courseIn - course for that specific B-Number
	 */
	public Node(int bNumIn,String courseIn)
	{
		Bnumber=bNumIn;
		courses[0]=courseIn;
	}


	/**
	 * getters and setters for B-Number and courses
	 */
	public int getBnumber() {
		return Bnumber;
	}

	public void setBnumber(int bnumberIn) {
		Bnumber = bnumberIn;
	}

	public String[] getCourses() {
		return courses;
	}

	public void setCourses(String[] coursesIn) {
		courses = coursesIn;
	}
	
	public Object clone() throws CloneNotSupportedException
   	{
		return super.clone();
        }
	

	/**
	 * 
	 * @param rootIn - root node of the tree
	 * @param BnumIn - B-Number
	 * @return - a boolean value is returned to show the existence of the node
	 */
	public boolean find(Node rootIn,int BnumIn){
		Node current = rootIn;
		while(current!=null){
			if(current.Bnumber==BnumIn){
				return true;
			}else if(current.Bnumber>BnumIn){
				current = current.left;
			}else{
				current = current.right;
			}
		}
		return false;
	}
	
	

	/**
	 * @param rootIn - root node of the tree
	 * @param bNumIn - B-Number
	 * @param courseIn - course of that specific B-Number
	 * @return - returns the root node
	 */
	public Node register(Node rootIn,int bNumIn,String courseIn)
	{
		Node node=new Node(bNumIn,courseIn);
		try {
			Node backup_node1 = (Node)node.clone();
			Node backup_node2=(Node)node.clone();
			node.backups.add(backup_node1);
			node.backups.add(backup_node2);
			rootIn=insert(rootIn,node,bNumIn,courseIn);
			rootIn=insert(rootIn,node.backups.get(0),bNumIn,courseIn);
			rootIn=insert(rootIn,node.backups.get(1),bNumIn,courseIn);
			return rootIn;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		finally
		{
			
		}
		return rootIn;		
	}
	

	/**
	 * Method to unregister the reference to backup trees
	 */
	public void unregister()
	{

	}

	
	
	/**
	 * @param rootIn - root node of the tree
	 * @param nodeIn - node where insertion should be done
	 * @param bNumIn - B-Number
	 * @param courseIn - course of that specific B-Number
	 * @return - returns the root node
	 */
	public Node insert(Node rootIn,Node nodeIn,int bNumIn,String courseIn)
	{
		if(rootIn==null)
		{
			rootIn=nodeIn;	
			return rootIn;
			
		}
		
		Node current=rootIn;
		Node parent=null;
		while(true)
		{
			parent=current;
	
			if(bNumIn<current.Bnumber)
			{
				current=current.left;
				if(current==null)
				{
					parent.left=nodeIn;
					return rootIn;
					
				}
			}
			else if(bNumIn>current.Bnumber)
			{
				current=current.right;
				if(current==null)
				{
					parent.right=nodeIn;
					return rootIn;
				}
			}
			else 
				return rootIn;
					
		}	
	}
		


	/**
	 * @param operationIn - specifies whether operation to do is insertion or deletion
	 * @param rootIn - root node of the tree
	 * @param bNumIn - B-Number
	 * @param courseIn - course of that specific B-Number
	 * @return - returns the root node
	 */
	public Node update(String operationIn,Node rootIn,int bNumIn,String courseIn)
	{
		Node current = rootIn;
		Node parent = null;
		int i=0;
		int size=current.courses.length;
		while(current!=null){
			
			parent = current;
			if(bNumIn==current.Bnumber){
				for(i=0;i<size;i++)
				{
					
					if((current.courses[i]==null || current.courses[i].equalsIgnoreCase(courseIn)) && operationIn.equalsIgnoreCase("insert"))
						{
							current.courses[i]=courseIn;
							notifyall(operationIn,current.backups.get(0),courseIn);
							notifyall(operationIn,current.backups.get(1),courseIn);
							return rootIn;
						}
					else if(operationIn.equalsIgnoreCase("delete"))
					{

						try{
						if(current.courses[i].equalsIgnoreCase(courseIn))
						{
							current.courses[i]=null;
							notifyall(operationIn,current.backups.get(0),courseIn);
							notifyall(operationIn,current.backups.get(1),courseIn);
							return rootIn;
						}						
						}catch(NullPointerException e)
						{
							return rootIn;
						}
						finally
						{
							
						}
					}
					else if(i==size)
						return rootIn;
										
				}
			}

			else if(current.Bnumber>bNumIn){
				current = current.left;
			}else{
				current = current.right;
			}
		}	
		
	return rootIn;
	}
	
	

	/**
	 * @param operationIn - specifies whether operation is insertion or deletion
	 * @param backupIn - backup node which needs to be updated
	 * @param courseIn - course to be inserted or deleted
	 */
	public void notifyall(String operationIn,Node backupIn,String courseIn)
	{
			int i=0;
			int size=backupIn.courses.length;
			for(i=0;i<size;i++)
			{
				
				if((backupIn.courses[i]==null || backupIn.courses[i].equalsIgnoreCase(courseIn)) && operationIn.equalsIgnoreCase("insert"))
					{
						backupIn.courses[i]=courseIn;
						return;
					}
				else if(operationIn.equalsIgnoreCase("delete"))
				{

					try{
					if(backupIn.courses[i].equalsIgnoreCase(courseIn))
					{
						backupIn.courses[i]=null;
						return;
					}
					}catch(NullPointerException e)
					{
						return;
					}
					finally
					{
						
					}
					
				}
				else if(i==size)
					return;				
											
			}		
		
	}
	

	
}
