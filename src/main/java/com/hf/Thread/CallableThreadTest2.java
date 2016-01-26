package com.hf.Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CallableThreadTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String result = "";
		ExecutorService executor = Executors.newSingleThreadExecutor();
		FutureTask<String> future = new FutureTask<String>(
				new Callable<String>() {// ʹ��Callable�ӿ���Ϊ�������
					public String call() {
						// ����������������ִ�У�����ķ���ֵ����ΪString������Ϊ��������
						try {
							Thread.sleep(1000L);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							// exception = e;
							// e.printStackTrace();
						}
						return "11111";
					}
				});
		executor.execute(future);
		// ���������������κ�����
		try {
			result = future.get(5000, TimeUnit.MILLISECONDS); // ȡ�ý����ͬʱ���ó�ʱִ��ʱ��Ϊ5�롣ͬ��������future.get()��������ִ�г�ʱʱ��ȡ�ý��
		} catch (InterruptedException e) {
			// System.out.println("�����Ѿ�ȡ��");
			future.cancel(true);
		} catch (ExecutionException e) {
			future.cancel(true);
		} catch (TimeoutException e) {
			future.cancel(true);
		} finally {
			executor.shutdown();
		}
		System.out.println("result:" + result);
	}
}
