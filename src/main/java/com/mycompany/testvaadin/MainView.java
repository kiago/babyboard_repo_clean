/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testvaadin;

/**
 *
 * @author baptman
 */
import static com.mycompany.testvaadin.MyVaadinUI.navigator;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
public class MainView extends Panel implements View {

    public static final String NAME = "";
    private User user;
    private int i=0;

    //Vue d'accueil du site (avant le log)
    public MainView() {
        
       user = VaadinSession.getCurrent().getAttribute(User.class);
       
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        

//        layout.addComponent(VaadinSession.getCurrent().getAttribute(User.class).printUserInfo());
        Link lnk = new Link("Page de connexion", new ExternalResource("#!"
                + Connection.NAME));
        if(!user.isConnected()){
        
        layout.addComponent(lnk);
        Link lnkS = new Link("Page d'inscription", new ExternalResource("#!"
                + Subscribe.NAME));
        layout.addComponent(lnkS);
//        layout.addComponent(MyVaadinUI.user.printUserInfo());
        }else{
            layout.addComponent(user.printUserInfo());
            layout.addComponent(lnk);
            Link lnkTU = new Link("TableUser", new ExternalResource("#!"
                + tableUser.NAME));
            layout.addComponent(lnkTU);
            
            Link lnkB = new Link("BabyView", new ExternalResource("#!"
                + BabyView.NAME));
            layout.addComponent(lnkB);
            
            Button deconnection = new Button("Déconnexion");
       
        
        deconnection.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                user = new User();
                navigator.navigateTo(MainView.NAME);
                
            }
        });
        layout.addComponent(deconnection);
            
        }
        setContent(layout);


    }
    
    

     public void enter(ViewChangeEvent event) {
        
    }

}