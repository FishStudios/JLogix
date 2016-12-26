package com.kneecapdav.JLogix.gui.view;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.kneecapdav.JLogix.API.events.EventManager;
import com.kneecapdav.JLogix.gui.LogixGUI;

public class GUIManager {

	private static GUIManager instance;
	
	public ArrayList<GUIView> views;
	
	public GUIView currentView;
	
	private GUIManager() {
		views = new ArrayList<>();
	}
	
	public void register(GUIView view) {
		if(contains(view.getID())) return;
		
		GUIViewRegistrationEvent e = new GUIViewRegistrationEvent(view);
		EventManager.getInstance().fire(e);
		if(e.isCanceled()) return;
		
		view.onInit();
		
		views.add(view);
	}
	
	public void unregister(GUIView view) {
		if(!contains(view.getID())) return;
		
		GUIViewUnregistrationEvent e = new GUIViewUnregistrationEvent(view);
		EventManager.getInstance().fire(e);
		if(e.isCanceled()) return;
		
		views.remove(view);
	}
	
	public boolean contains(GUIView view) {
		return views.contains(view);
	}

	public boolean contains(String ID) {
		return views.stream().filter((v) -> v.getID().equals(ID)).collect(Collectors.toCollection(ArrayList::new)).size() > 0;
	}
	
	public GUIView getView(String ID) {
		ArrayList<GUIView> view = views.stream().filter((v) -> v.getID().equals(ID)).collect(Collectors.toCollection(ArrayList::new));
		if(view.isEmpty()) return null;
		else return view.get(0);
	}
	
	public void changeView(GUIView view) {
		if(!contains(view)) return;
		
		GUIViewChangeEvent change = new GUIViewChangeEvent(currentView, view);
		EventManager.getInstance().fire(change);
		if(change.isCanceled()) return;
		
		if(currentView != null) {
			GUIViewHideEvent hide = new GUIViewHideEvent(currentView);
			EventManager.getInstance().fire(hide);
			currentView.onHide();
		}
		
		GUIViewShowEvent show = new GUIViewShowEvent(view);
		EventManager.getInstance().fire(show);
		view.onShow();
		
		LogixGUI.instance.stage.setScene(view.getScene());
		
		this.currentView = view;
	}
	
	public void changeView(String ID) {
		GUIView view = getView(ID);
		if(view != null) changeView(view);
	}
	
	public static GUIManager getInstance() {
		if(instance == null) instance = new GUIManager();
		
		return instance;
	}
	
}
