import java.io.File;
import java.io.IOException;

public class createFiles {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createFiles c = new createFiles();
		String rootFile = "D:/卢艺/101-128";
		c.createFiles(rootFile);
	}

	public void createFiles(String rootFilePath){
		File rootFile = new File(rootFilePath);
		File[] files = rootFile.listFiles();
		for(File f: files){
			String stockId = f.getName().split("\\.")[0];
			File newFile = new File(rootFile, stockId+".txt");
			try {
				newFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
