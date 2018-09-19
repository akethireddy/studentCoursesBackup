package studentCoursesBackup.util;

import studentCoursesBackup.myTree.Node;


/*
 * Interface for standard output display
 */
public interface StdoutDisplayInterface {

	public void OutLength();
	public void fileOpen();
	public void emptyFile();
	public void unEmptyFile();
	public void unreachableFile();
	public void display(Node root);
}
