import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

public class CreatingFile {

	public static void main(String[] args) throws IOException {
		String user = null;
	    String pass = null;
	    try {
	    		//shared
		    String sharedFolder="all";
		    String path="smb://10.12.60.39/"+sharedFolder+"/test.txt";
		    NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication("",user, pass);
		    SmbFile smbFile = new SmbFile(path,auth);
		    SmbFileOutputStream smbfos = new SmbFileOutputStream(smbFile);
		    //Create a while loop so that until the user hits esc, then stop taking input for the file
		    smbfos.write("Testing Creating File".getBytes());
		    //update permissions
		    smbFile.setReadWrite();
		    System.out.println("completed ...nice !");
	    }
	    catch(Exception e) {
	    		System.out.println(e.getMessage());
	    }

	}

}
