package studentCoursesBackup.myTree;

/*
 * Interface of the subject tree 
 */
public interface SubjectI {

	public Node register(Node rootIn,int bNumIn,String courseIn);
	public Node insert(Node rootIn,Node nodeIn,int bNumIn,String courseIn);
	public Node update(String operationIn,Node rootIn,int bNumIn,String courseIn);
	public void unregister();

}
