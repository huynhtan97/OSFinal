import java.io.BufferedReader;
import java.io.InputStreamReader;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

public class ReadingFromFile {

	public static void main(String[] args) {
		String user = null;
	    String pass = null;
	    String sharedFolder = "all";
		SmbFile file = null;
		
		try {
		    String path = "smb://10.12.60.58/"+sharedFolder+"/test.txt";
		    NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, user, pass);
		    file = new SmbFile(path, auth);
		    SmbFileInputStream in = new SmbFileInputStream(file);
		    //Using BufferedReader to read in file
		    BufferedReader buffR = new BufferedReader(new InputStreamReader(in));
		    System.out.println(buffR.readLine());
		} 
		catch(Exception e) {
		    System.out.println(e.getMessage());
		}

	}

}
