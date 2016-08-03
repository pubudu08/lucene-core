/** Simple helper class to recursively return files in a directory with 
 *  a given extension.
 * 
 * @author Scott Sanner
 */

package search;

import java.io.File;
import java.util.ArrayList;

public class FileFinder {
	
	/** Main file finder
	 * 
	 * @param src source directory
	 * @param ext null if any extension OK
	 * @param recurse recurse on subdirectories
	 * @return
	 */
	public static ArrayList<File> GetAllFiles(String src, String ext, boolean recurse) {
		
		ArrayList<File> ret_files = new ArrayList<File>();
		File[] files = new File(src).listFiles();

		for (File f : files) {			
			if (f.isDirectory()) {
				if (recurse)
					ret_files.addAll(GetAllFiles(f.getPath(), ext, recurse));
			} else {
				if (ext == null || f.toString().endsWith(ext))
					ret_files.add(f);
			}
		}
		
		return ret_files;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		System.out.println("\nTest 1: ");
//		for (File f : FileFinder.GetAllFiles("src/search", ".txt", true))
//			System.out.println("- " + f);
//
//		System.out.println("\nTest 2: ");
//		for (File f : FileFinder.GetAllFiles("src/search", null, true))
//			System.out.println("- " + f);
//
//		System.out.println("\nTest 3: ");
//		for (File f : FileFinder.GetAllFiles("src/search", null, false))
//			System.out.println("- " + f);

		System.out.println("\nTest 4: ");
		for (File f : FileFinder.GetAllFiles("src/search/email/documents", null, false))
			System.out.println("- " + f);

		System.out.println("\nTest 5: ");
		for (int x = 0 ; x <100 ;x++){
			if (x<10){
				for (File f : FileFinder.GetAllFiles("src/search/government/documents/0"+x+ "/", null, false))
					System.out.println("- " + f);
			}else{
				for (File f : FileFinder.GetAllFiles("src/search/government/documents/"+x+"/", null, false))
					System.out.println("- " + f);
			}
		}
	}

}
