package kr.or.ddit.servlet04_01;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;

public class FileWrapper01 extends File{
	
	private String clz;
	private String fileURI;
	
	public FileWrapper01(File file, ServletContext application) {
		super(file.getAbsolutePath());
		
		clz = file.isDirectory() ? "folder" : "file" ;
		String fileAbPath = file.getAbsolutePath();
		String contextRealPath = application.getRealPath("/");
		String tmp = StringUtils.remove(fileAbPath, contextRealPath);
		fileURI = tmp.replace(File.separatorChar, '/');
		fileURI = fileURI.startsWith("/") ? fileURI : "/" + fileURI;
		
	}
	
	public String getClz() {
		return clz;
	}
	
	public String getFileURI() {
		return fileURI;
	}
	
	public String getKey() {
		return fileURI;
	}
	
	public String getTitle() {
		return getName();
	}
	
	public boolean isFolder() {
		return isDirectory();
	}
	
	public boolean isLazy() {
		return true;
	}
}
