package com.kneecapdev.jlogix.gui.editor;

import com.kneecapdev.jlogix.JLogixModule;
import com.kneecapdev.jlogix.api.events.EventHook;
import com.kneecapdev.jlogix.api.events.EventListener;
import com.kneecapdev.jlogix.api.events.project.LogixProjectSwitchEvent;
import com.kneecapdev.jlogix.api.project.LogixProject;
import com.kneecapdev.jlogix.gui.controller.AbstractViewController;

public class EditorViewController extends AbstractViewController<EditorView> {

    private LogixProject currentProject;

    public EditorViewController(EditorView view) {
        super(view);

        JLogixModule.instance.registerListener(new LogixProjectSwitchHandler());
    }

    class LogixProjectSwitchHandler implements EventListener {

        @EventHook
        public void projectSwitchEvent(LogixProjectSwitchEvent lpse) {
            currentProject = lpse.newProject;
        }

    }

}
