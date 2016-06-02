package com.hf.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Test;

public class JavaIO {
	//字节流操作
		@Test
		public void testloadresource() throws IOException{
			InputStream resourceAsStream = getClass().getResourceAsStream("/mybitsbak.txt");
			
			FileOutputStream fos=new FileOutputStream(new File("D:/fos.txt"));//等于D:\\fos.txt
			StringBuffer sb=new StringBuffer();
			int n=0;
			
			byte[] buffer=new byte[4096];//Java I/O默认是不缓冲流的，所谓“缓冲”就是先把从流中得到的一块字节序列暂存在一个被称为buffer的内部字节数组里
			
			try {
				while((n=resourceAsStream.read(buffer))!=-1){
					sb.append(new String(buffer, 0, n));
					fos.write(buffer, 0, n);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				resourceAsStream.close();
				fos.close();
			}
			String s=new String(sb.toString().getBytes("UTF-8"));
			System.out.println(s);
		}
		
		//字符流
		@Test
		public void textCharStream() throws IOException, URISyntaxException{
			URL url = getClass().getResource("/mybitsbak.txt");
			
			Reader reader=new FileReader(new File(url.toURI()));
			char[] buffer=new char[512];
			int n;
			PrintWriter pw=new PrintWriter(System.out);
			while((n=reader.read(buffer))!=-1){
				pw.write(buffer, 0, n);
			}
			reader.close();
			pw.close();
		}
		
		@Test
		public void test1(){
			System.err.println(this.getClass().getName());
			Runtime rt = Runtime.getRuntime();
			long maxMemory = rt.maxMemory();
			try {
				Process process = rt.exec("java -version");
				
				InputStream stderr = process.getErrorStream();
				//InputStream stderr = process.getInputStream();
				InputStreamReader isr = new InputStreamReader(stderr);
				/*BufferedReader br = new BufferedReader(isr);
				String line = null;
				while ((line = br.readLine()) != null)
				System.out.println(line);
				System.out.println("");
				*/
				LineNumberReader input = new LineNumberReader (isr); 
	            String line; 
	            while ((line = input.readLine ()) != null) 

	                System.out.println(line); 
	            int exitVal = process.waitFor();
				System.out.println("Process exitValue: " + exitVal);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
}
