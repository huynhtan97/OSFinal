import java.util.*;
import java.io.*;
import jcifs.smb.*;
import jcifs.ntlmssp.*;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class NAS_Service {
	public static void main(String[] args) throws IOException, InterruptedException {
		mapnetworkdrive();
	}

	public static void mapnetworkdrive() throws InterruptedException {
		try {
			// Execute a command without arguments
			String command = "C:\\Windows\\system32\\net.exe use F: \\\\10.12.60.21\\all ";
			Process child = Runtime.getRuntime().exec(command);
			System.out.println("Drive mapped");
			Thread.sleep(100);
			createfile();
		} catch (IOException e) {
		}
	}

	public static void createfile() throws IOException, InterruptedException {
		File yourFile = new File("\\\\10.12.60.21\\all\\test.txt");
		yourFile.createNewFile(); // if file already exists will do nothing
		FileOutputStream oFile = new FileOutputStream(yourFile, false);
		Thread.sleep(100);
		readandwrite(yourFile);
	}

	public static void readandwrite(File myfile) throws IOException, InterruptedException {
		String str = "Hello";
		byte[] strtobytes = str.getBytes();
		FileOutputStream writer = new FileOutputStream(myfile,true);
		writer.write(strtobytes);
		System.out.println("File Written to");
		String line = null;
		BufferedReader br = new BufferedReader(new FileReader(myfile));
		while((line = br.readLine()) != null) {
            System.out.println(myfile);
        } 
		FileChannel channel = new RandomAccessFile(myfile, "rw").getChannel();
		System.out.println("Channel initiated. ");
		FileLock lock = channel.tryLock(0, Long.MAX_VALUE, true);
		boolean isShared = lock.isShared();
		Thread.sleep(30000);
		lock.release();
		channel.close();
		writer.close();
	}
}

class Multithread implements Runnable {
	public void run() {
		try {
			/*
			 * String user = null; String pass = null; try { // shared String
			 * sharedFolder = "all"; String path = "smb://10.12.60.39/" +
			 * sharedFolder + "/test1.txt"; NtlmPasswordAuthentication auth =
			 * new NtlmPasswordAuthentication("", user, pass); SmbFile smbfile =
			 * new SmbFile(path, auth); try(final RandomAccessFile raf = new
			 * RandomAccessFile(smbfile, "rw")){ final FileChannel fc =
			 * raf.getChannel();
			 * 
			 * }
			 * 
			 * SmbFileOutputStream smbfos = new SmbFileOutputStream(smbfile);
			 * smbfos.write("Writing to a file, haha yes, oof owieasffs"
			 * .getBytes()); //smbfile.setReadOnly(); System.out.println(
			 * "completed ...nice !");
			 * 
			 * BufferedReader br = new BufferedReader(new
			 * InputStreamReader(smbfile.getInputStream())); String line = null;
			 * while ((line = br.readLine()) != null) {
			 * System.out.println(line); }
			 * 
			 * smbfos.flush(); smbfos.close(); } catch (Exception e) {
			 * System.out.println(e.getMessage()); }
			 */
			String str = "Hello";
			byte[] strtobytes = str.getBytes();
			File myfile = new File("\\\\10.12.60.39\\all\\test2.txt");
			FileOutputStream writer = new FileOutputStream(myfile);
			writer.write(strtobytes);
			System.out.println("File Written to");
			FileChannel channel = new RandomAccessFile(myfile, "rw").getChannel();
			System.out.println("Channel initiated. ");
			FileLock lock = channel.tryLock(0, Long.MAX_VALUE, true);
			boolean isShared = lock.isShared();
			lock.release();
			channel.close();
			writer.close();

		} catch (Exception e) {
			System.out.println("Exception is caught");
		}
	}
}
