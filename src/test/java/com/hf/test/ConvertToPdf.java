package com.hf.test;

import java.io.File;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
/**
 * 
 * @author hefeng
 * 将jacob-1.14.3.zip里面的dll文件(32和64位)jacob-1.14.3-x86.dll  jacob-1.14.3-x64.dll放入JDK的bin目录下  maven引入jacob-1.14.3
 */
public class ConvertToPdf {
	/*转PDF格式值*/  
    private static final int wdFormatPDF = 17;  
    private static final int xlFormatPDF = 0;  
    private static final int ppFormatPDF = 32;  
    private static final int msoTrue = -1;  
    private static final int msofalse = 0;  
  
    /*转HTML格式值*/  
    private static final int wdFormatHTML = 8;  
    private static final int ppFormatHTML = 12;  
    private static final int xlFormatHTML = 44;  
  
    /*转TXT格式值*/  
    private static final int wdFormatTXT = 2;  
  
    public boolean convert2PDF(String inputFile, String pdfFile) {  
        String suffix = getFileSufix(inputFile);  
        File file = new File(inputFile);  
        if (!file.exists()) {  
            System.out.println("文件不存在！");  
            return false;  
        }  
        if (suffix.equals("pdf")) {  
            System.out.println("PDF not need to convert!");  
            return false;  
        }  
        if (suffix.equals("doc") || suffix.equals("docx") || suffix.equals("txt")) {  
            return word2PDF(inputFile, pdfFile);  
        } else if (suffix.equals("ppt") || suffix.equals("pptx")) {  
            return ppt2PDF(inputFile, pdfFile);  
        } else if (suffix.equals("xls") || suffix.equals("xlsx")) {  
            return excel2PDF(inputFile, pdfFile);  
        } else {  
            System.out.println("文件格式不支持转换!");  
            return false;  
        }  
    }  
  
    /** 
     * 获取文件后缀 
     *  
     * @param fileName 
     * @return 
     * @author hefeng 
     */  
    private String getFileSufix(String fileName) {  
        int splitIndex = fileName.lastIndexOf(".");  
        return fileName.substring(splitIndex + 1);  
    }  
  
    /** 
     * Word文档转换 
     *  
     * @param inputFile 
     * @param pdfFile 
     * @author hefeng 
     */  
    private boolean word2PDF(String inputFile, String pdfFile) {  
        ComThread.InitSTA();  
  
        long start = System.currentTimeMillis();  
        ActiveXComponent app = null;  
        Dispatch doc = null;  
        try {  
            app = new ActiveXComponent("Word.Application");// 创建一个word对象  
            app.setProperty("Visible", new Variant(false)); // 不可见打开word  
            app.setProperty("AutomationSecurity", new Variant(3)); // 禁用宏  
            Dispatch docs = app.getProperty("Documents").toDispatch();// 获取文挡属性  
  
            System.out.println("打开文档 >>> " + inputFile);  
            // Object[]第三个参数是表示“是否只读方式打开”  
            // 调用Documents对象中Open方法打开文档，并返回打开的文档对象Document  
            doc = Dispatch.call(docs, "Open", inputFile, false, true).toDispatch();  
            // 调用Document对象的SaveAs方法，将文档保存为pdf格式  
            System.out.println("转换文档 [" + inputFile + "] >>> [" + pdfFile + "]");  
            Dispatch.call(doc, "SaveAs", pdfFile, wdFormatPDF);//word保存为pdf格式宏，值为17  
//            Dispatch.call(doc, "ExportAsFixedFormat", pdfFile, wdFormatPDF); // word保存为pdf格式宏，值为17  
  
            long end = System.currentTimeMillis();  
  
            System.out.println("用时：" + (end - start) + "ms.");  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            System.out.println("========Error:文档转换失败：" + e.getMessage());  
        } finally {  
            Dispatch.call(doc, "Close", false);  
            System.out.println("关闭文档");  
            if (app != null)  
                app.invoke("Quit", new Variant[] {});  
        }  
        // 如果没有这句话,winword.exe进程将不会关闭  
        ComThread.Release();  
        ComThread.quitMainSTA();  
        return false;  
    }  
  
    /** 
     * PPT文档转换 
     *  
     * @param inputFile 
     * @param pdfFile 
     * @author hefeng 
     */  
    private boolean ppt2PDF(String inputFile, String pdfFile) {  
        ComThread.InitSTA();  
  
        long start = System.currentTimeMillis();  
        ActiveXComponent app = null;  
        Dispatch ppt = null;  
        try {  
            app = new ActiveXComponent("PowerPoint.Application");// 创建一个PPT对象  
            // app.setProperty("Visible", new Variant(false)); // 不可见打开（PPT转换不运行隐藏，所以这里要注释掉）  
            // app.setProperty("AutomationSecurity", new Variant(3)); // 禁用宏  
            Dispatch ppts = app.getProperty("Presentations").toDispatch();// 获取文挡属性  
  
            System.out.println("打开文档 >>> " + inputFile);  
            // 调用Documents对象中Open方法打开文档，并返回打开的文档对象Document  
            ppt = Dispatch.call(ppts, "Open", inputFile,   
                    true,// ReadOnly  
                    true,// Untitled指定文件是否有标题  
                    false// WithWindow指定文件是否可见  
                    ).toDispatch();  
              
            System.out.println("转换文档 [" + inputFile + "] >>> [" + pdfFile + "]");  
            Dispatch.call(ppt, "SaveAs", pdfFile, ppFormatPDF);  
  
            long end = System.currentTimeMillis();  
  
            System.out.println("用时：" + (end - start) + "ms.");  
  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            System.out.println("========Error:文档转换失败：" + e.getMessage());  
        } finally {  
            Dispatch.call(ppt, "Close");  
            System.out.println("关闭文档");  
            if (app != null)  
                app.invoke("Quit", new Variant[] {});  
        }  
        ComThread.Release();  
        ComThread.quitMainSTA();  
        return false;  
    }  
  
    /** 
     * Excel文档转换 
     *  
     * @param inputFile 
     * @param pdfFile 
     * @author hefeng 
     */  
    private boolean excel2PDF(String inputFile, String pdfFile) {  
        ComThread.InitSTA();  
  
        long start = System.currentTimeMillis();  
        ActiveXComponent app = null;  
        Dispatch excel = null;  
        try {  
            app = new ActiveXComponent("Excel.Application");// 创建一个PPT对象  
            app.setProperty("Visible", new Variant(false)); // 不可见打开  
            // app.setProperty("AutomationSecurity", new Variant(3)); // 禁用宏  
            Dispatch excels = app.getProperty("Workbooks").toDispatch();// 获取文挡属性  
  
            System.out.println("打开文档 >>> " + inputFile);  
            // 调用Documents对象中Open方法打开文档，并返回打开的文档对象Document  
            excel = Dispatch.call(excels, "Open", inputFile, false, true).toDispatch();  
            // 调用Document对象方法，将文档保存为pdf格式  
            System.out.println("转换文档 [" + inputFile + "] >>> [" + pdfFile + "]");  
            // Excel 不能调用SaveAs方法  
            Dispatch.call(excel, "ExportAsFixedFormat", xlFormatPDF, pdfFile);  
  
            long end = System.currentTimeMillis();  
  
            System.out.println("用时：" + (end - start) + "ms.");  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            System.out.println("========Error:文档转换失败：" + e.getMessage());  
        } finally {  
            Dispatch.call(excel, "Close", false);  
            System.out.println("关闭文档");  
            if (app != null)  
                app.invoke("Quit", new Variant[] {});  
        }  
        ComThread.Release();  
        ComThread.quitMainSTA();  
        return false;  
    }  
  
    /** 
     * 测试 
     *  
     * @param args 
     * @author hefeng 
     */  
    public static void main(String[] args) {  
        ConvertToPdf d = new ConvertToPdf();  
        //d.convert2PDF("E:\\MY文件夹\\大三\\终稿.docx", "d:\\test.pdf");  
        //d.convert2PDF("g:\\testppt.pptx", "g:\\testppt.pdf");  
        d.convert2PDF("E:/MY文件夹/大一/2014年最新CPU天梯图.xls", "d:\\test.pdf");  
    }  
}
