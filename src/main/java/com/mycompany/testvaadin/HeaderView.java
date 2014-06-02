/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.testvaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ClassResource;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.io.File;


/**
 *
 * @author geoffroyrouaix
 */
public class HeaderView extends Panel implements View {
    
    
    public HeaderView() {
    
        final CssLayout layout = new CssLayout();
        layout.addStyleName("header");
        
        //logo
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath + "/WEB-INF/images/logo.png"));
        Image image = new Image(null, resource);
        image.addStyleName("logo");
        layout.addComponent(image);
        
        //menu
        CssLayout menu = new CssLayout();
        menu.addStyleName("menu");
        Link lnk = new Link("Accueil", new ExternalResource("#!"));
        Link lnk2 = new Link("Mon bébé", new ExternalResource("#!" + BabyView.NAME));
        Link lnk3 = new Link("Agenda", new ExternalResource("#!" + BabyView.NAME));
        lnk.addStyleName("menu-link");
        lnk2.addStyleName("menu-link");
        lnk3.addStyleName("menu-link");
        menu.addComponent(lnk);
        menu.addComponent(lnk2);
        menu.addComponent(lnk3);
        layout.addComponent(menu);
        setContent(layout);
        
    }
    

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
