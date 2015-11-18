package com.master;

import java.util.ArrayList;

public class Directory {
	
	private ArrayList<Directory> dir;
	private ArrayList<TinyFSFile> files;
	private String dirName;
	
	// root creation
	public Directory(){
		this.dir = new ArrayList<Directory>();
		this.files = new ArrayList<TinyFSFile>();
		this.dirName = "";
	}
	
	public Directory(String dirName){
		this.dir = new ArrayList<Directory>();
		this.files = new ArrayList<TinyFSFile>();
		this.dirName = dirName;
	}
	
	public synchronized Directory returnDirectory (String tgtdir){
		// path => / or /d1 or /d1/d2
		if (dirName.equals(tgtdir)){
			return this;
		}
		else {
			for (int i = 0; i < dir.size(); i++){
				if (dir.get(i).getDirectoryName().equals(tgtdir)){
					return dir.get(i);
				}
			}
			
			return null;
		}		
	}
	
	public synchronized void createDir (String dirname){
		Directory d = new Directory(dirname);
		this.dir.add(d);
	}
	
	public synchronized void deleteDir (String dirname){
		for (int i = 0; i < dir.size(); i++){
			if (dir.get(i).getDirectoryName() == dirname){
				dir.remove(i);
				break;
			}
		}
	}
	
	public synchronized String[] listDir (){
		String [] list = new String[dir.size()];
		for (int i = 0; i < dir.size(); i++){
			list[i] = dir.get(i).getDirectoryName();
		}
		
		return list;
	}
	
	public synchronized String getDirectoryName(){
		return this.dirName;
	}
	
	public synchronized void createFile(String filename){
		TinyFSFile tfsFile = new TinyFSFile();
		files.add(tfsFile);
	}
	
	public synchronized void deleteFile(String filename){
		for (int i = 0; i < files.size(); i++){
			if (files.get(i).getFilename() == filename){
				files.remove(i);
				break;
			}
		}
	}
	
	public synchronized String[] openFile(String filename){
		for (int i = 0; i < files.size(); i++){
			if (files.get(i).getFilename() == filename){
				return files.get(i).getChunksStr();
			}
		}
		
		return null;
	}
	
	public synchronized void setDirectoryName(String newName){
		this.dirName = newName;
	}
	
	// only for debug
	public void print(){
		String strDirName = this.dirName;
		if (this.dirName.equals("")){
			strDirName += "/";
		}
		System.out.println("-- " + strDirName + " --");
		
		System.out.println("-- Files --");
		for (int i = 0; i < files.size(); i++){
			System.out.println (files.get(i).getFilename());
		}
		
		System.out.println("-- Subdirectories --");
		for (int i = 0; i < dir.size(); i++){
			System.out.println(dir.get(i).getDirectoryName());
		}
		
		for (int i = 0; i < dir.size(); i++){
			dir.get(i).print();
		}
		
	}
	
}
