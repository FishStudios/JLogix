package com.kneecapdev.jlogix.api.project;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.JSONParser;

import com.kneecapdev.jlogix.api.config.ConfigManager;
import com.kneecapdev.jlogix.api.events.EventManager;
import com.kneecapdev.jlogix.api.events.project.LogixProjectCreateEvent;
import com.kneecapdev.jlogix.api.events.project.LogixProjectDeleteEvent;
import com.kneecapdev.jlogix.api.events.project.LogixProjectLoadEvent;
import com.kneecapdev.jlogix.api.events.project.LogixProjectSwitchEvent;
import com.kneecapdev.jlogix.api.events.project.LogixProjectUnloadEvent;
import com.kneecapdev.jlogix.api.log.LogixLogger;
import com.kneecapdev.jlogix.api.module.loader.ModuleManager;
import com.kneecapdev.jlogix.utils.FileUtils;

public class ProjectManager {
	
	public static JSONParser parser = new JSONParser();
	
	private static ProjectManager instance;
	
	public ArrayList<LogixProject> projects;
	
	private LogixProject currentProject;
	
	public File projectDir;
	
	private ProjectManager() {
		projects = new ArrayList<>();
		
		projectDir = new File(System.getenv("APPDATA") + "\\Logix\\projects");
		if(!projectDir.exists()) projectDir.mkdirs();
	}
	
	/**
	 * Returns current opened project
	 * @return current opened project
	 */
	public LogixProject getCurrentProject() {
		return this.currentProject;
	}
	
	/**
	 * Creates new project with the given name
	 * @param projectName name of the new project
	 * @return newly created LogixProject
	 */
	public LogixProject createNewProject(String projectName) {
		if(projects.contains(projectName)) {
			//TODO: Replace with Alert/Something similar 
			System.out.println("Unable to create project " + projectName + "! Name already in use!");
			return null;
		}
		
		LogixProject project = new LogixProject(projectName);
		
		LogixProjectCreateEvent e = new LogixProjectCreateEvent(project);
		EventManager.getInstance().fire(e);
		if(e.isCanceled()) return null;
		
		project.config = ConfigManager.getInstance().loadConfig(projectName, projectDir + "\\" + projectName);
		
		project.config.addProperty("projectName", projectName);
		project.config.addProperty("description", "");
		project.config.addProperty("creationDate", System.currentTimeMillis());
		project.config.addProperty("modules", new ArrayList<String>());
		
		this.projects.add(project);
		File projectFile = new File(projectDir, "\\" + projectName);
		if(!projectFile.exists()) projectFile.mkdir();
		
		return project;
	}
	
	/**
	 * Deletes the given project
	 * @param project LogixProject to be deleted
	 */
	public void deleteProject(LogixProject project) {
		if(!projects.contains(project)) return;
		
		LogixProjectDeleteEvent e = new LogixProjectDeleteEvent(project);
		EventManager.getInstance().fire(e);
		if(e.isCanceled()) return;

		if(currentProject == project) switchProject(null, false);
		
		this.projects.remove(project);
		File projectFile = new File(projectDir, "\\" + project.getName());
		FileUtils.deleteDirectory(projectFile);
	}
	
	/**
	 * Unloads the current project and loads the given new project
	 * @param project new project to be loaded
	 * @param saveCurrent boolean determining if the current project should be saved
	 */
	public void switchProject(LogixProject project, boolean saveCurrent) {
		if(!projects.contains(project)) {
			//TODO: Replace with Alert/Something similar 
			System.out.println("Unable to load project " + project.getName() + "! Project not found!");
			return;
		}
		
		LogixProjectSwitchEvent e = new LogixProjectSwitchEvent(currentProject, project);
		EventManager.getInstance().fire(e);
		if(e.isCanceled()) return;
		
		if(currentProject != null) unload(saveCurrent);
		if(!load(project)) {
			//TODO: Replace with Alert/Something similar 
			System.out.println("Unable to load project " + project.getName() + "! Project depends on a module that isn't loaded!");
		}
	}
	
	/**
	 * Returns true or false if there's a project loaded
	 * @return true or false if there's a project loaded
	 */
	public boolean hasOpenProject() {
		return (currentProject != null);
	}
	
	
	/**
	 * Gets the LogixProject instance with the given name.
	 * Returns null if the projects don't exist
	 * @param name name of the project
	 * @return LogixProject with the given name, null if not found
	 */
	public LogixProject getProject(String name) {
		for(LogixProject p: projects) {
			if(p.getName().equalsIgnoreCase(name)) return p;
		}
		return null;
	}
	
	private boolean load(LogixProject project) {
		//Check if all modules used in this project are currently loaded.
		if(project.config.containsKey("modules")) {
			List<Object> modules = project.config.getList("modules");
			for(Object m: modules) {
				String moduleID = (String) m;
				
				if(ModuleManager.getInstance().getModule(moduleID) == null) return false;
			}
		}
		
		LogixProjectLoadEvent e = new LogixProjectLoadEvent(project);
		EventManager.getInstance().fire(e);
		
		LogixLogger.info(this, "Loading project " + project.getName());
		project.load();
			
		currentProject = project;
		
		return true;
	}
	
	private void unload(boolean save) {
		LogixProjectUnloadEvent e = new LogixProjectUnloadEvent(currentProject, save);
		EventManager.getInstance().fire(e);
		
		LogixLogger.info(this, "Unloading project " + currentProject.getName());
		if(save) currentProject.save();
		currentProject.unload();
	}
	
	public void resolveProjects() {
		for(File f: projectDir.listFiles()) {
			if(f.isDirectory()) {
				for(File projectFile: f.listFiles()) {
					if(projectFile.getName().equalsIgnoreCase(f.getName() + ".cfg")) {
						LogixProject project = new LogixProject(f.getName());
						project.config = ConfigManager.getInstance().loadConfig(project.getName(), projectDir + "\\" + project.getName());
						projects.add(project);
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
