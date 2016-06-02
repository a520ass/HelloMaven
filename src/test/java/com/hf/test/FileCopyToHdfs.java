package com.hf.test;


public class FileCopyToHdfs {
	
	public static void main(String[] args) {
	}

	/*private static void uploadToHdfs() throws IOException {
		String localSrc = "d:/configuration.yml";
		String dst = "hdfs://192.168.202.137:32775/user/root/configuration.yml";
		InputStream in = new BufferedInputStream(new FileInputStream(localSrc));
		Configuration conf = new Configuration();

		FileSystem fs = FileSystem.get(URI.create(dst), conf);
		OutputStream out = fs.create(new Path(dst), new Progressable() {
			public void progress() {
				System.out.print(".");
			}
		});
		IOUtils.copyBytes(in, out, 4096, true);
	}*/
}
