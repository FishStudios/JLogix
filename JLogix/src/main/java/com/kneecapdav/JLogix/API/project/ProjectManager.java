package com.kneecapdav.JLogix.API.project;

import java.io.File;
import java.util.ArrayList;

import org.json.simple.parser.JSONParser;

public class ProjectManager {
	
	public static JSONParser parser = new JSONParser();
	
	private static ProjectManager instance;
	
	public ArrayList<String> projects;
	
	private LogixProject currentProject;
	
	private File projectDir;
	
	private ProjectManager() {
		projects = new ArrayList<>();
		
		projectDir = new File(System.getenv("APPDATA") + "\\Logix\\projects");
		if(!projectDir.exists()) projectDir.mkdirs();
	}
	
	public LogixProject getCurrentProject() {
		return this.currentProject;
	}
	
	public void createNewProject(String projectName) {
		if(projects.contains(projectName)) {
			//TODO: Replace with logger
			System.out.println("Unable to create project " + projectName + "! Name already in use!");
			return;
		}
		this.projects.add(projectName);
	}
	
	public LogixProject switchProject(String projectName, boolean saveCurrent) {
		if(!projects.contains(projectName)) {
			//TODO: Replace with logger
			System.out.println("Unable to load project " + projectName + "! Project not found!");
			return null;
		}
		if(currentProject != null) unload(saveCurrent);
		load(projectName);
		return currentProject;
	}
	
	private void load(String project) {
		currentProject = new LogixProject(project);
		currentProject.load(new File(projectDir, "\\" + currentProject.getName()));
	}
	
	private void unload(boolean save) {
		if(save) currentProject.save(new File(projectDir, "\\" + currentProject.getName()));
	}
	
	public void resolveProjects() {
		for(File f: projectDir.listFiles()) {
			if(f.isDirectory()) {
				for(File projectFile: f.listFiles()) {
					System.out.println(projectFile.getName());
					if(projectFile.getName().equalsIgnoreCase("project.properties")) {
						//TODO Validate project.properties
						projects.add(f.getName());
						break;
					}
				}
			}
		}
	}
	
	public static ProjectManager getInstance() {
		if(instance == null) instance = new ProjectManager();
		
		return instance;
	}
	
}
