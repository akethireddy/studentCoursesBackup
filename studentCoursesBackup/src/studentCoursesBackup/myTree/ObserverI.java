package studentCoursesBackup.myTree;

/*
 * Interface of the observer trees
 */
public interface ObserverI {

	public Node insert(Node rootIn,Node nodeIn,int bNumIn,String courseIn);
	public void notifyall(String operationIn,Node backupIn,String courseIn);

}
