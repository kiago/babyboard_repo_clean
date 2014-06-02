/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testvaadin;

import static com.mycompany.testvaadin.MyVaadinUI.*;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;

/**
 *
 * @author geoffroyrouaix
 */
public class Connection extends Panel implements View {

    public static final String NAME = "Connect";



    private TextField tf = new TextField("Email:");
    private TextField tf2 = new TextField("Password:");
//    private User user; //représente l'utilisateur actuelle
    private String emailUser;
    private String passwordUser;
    public FormLayout fl;
    private User user;

    //Formulaire
    public Connection() {
        user = VaadinSession.getCurrent().getAttribute(User.class);
        
//        user = new User();
        // A FormLayout used outside the context of a Form
        fl = new FormLayout();
// Make the FormLayout shrink to its contents
        fl.setSizeUndefined();
//        TextField tf = new TextField("Email:");
        fl.addComponent(tf);
// Mark the first field as required
        tf.setRequired(true);
        tf.setRequiredError("requis!");

        fl.addComponent(tf2);
// Set the second field straing to error state with a message.
        tf2.setRequired(true);
        tf2.setRequiredError("requis!");
        
        //bouton de connexion
        Button connection = new Button("Connexion");
        
        Link lnk = new Link("Accueil", new ExternalResource("#!"));
        fl.addComponent(lnk);
        
        connection.addClickListener(new ClickListener() {
            public void buttonClick(ClickEvent event) {
//                user = testEmailPassword();
                if (user.checkEmailPassword(tf.getValue(),tf2.getValue())) {
                    user = new User(tf.getValue(), tf2.getValue());
                    fl.addComponent(user.printUserInfo());
                    VaadinSession.getCurrent().setAttribute(User.class, user);
                    
                    navigator.navigateTo(MainView.NAME);
//                    //On enlève le formulaire de connexion dans l'affichage du layout
                    //On navigue vers une autre view
//                    fl.removeComponent(getLayout());
                    //affichage des variables de l'utilisateur
                    
                }
            }
        });
        fl.addComponent(connection);
        
        setContent(fl);
    }

//    public User getUserFromLogin() {
//
//        user = new User(emailUser, passwordUser);
//        return user;
//    }
//
//    public User testEmailPassword() {
//        try {
//
//            if (user.checkEmailPassword(tf.getValue(), tf2.getValue())) {
//                emailUser = tf.getValue();
//                passwordUser = tf2.getValue();
//                user = new User(emailUser, passwordUser);
//                return user;
//
//            }
//        } catch (UnsupportedOperationException ex) {
//            Logger.getLogger(MyVaadinUI.class.getName()).log(Level.SEVERE, null, ex);
//
//        }
//        return new User();
//    }

    @Override
    public void enter(ViewChangeEvent event) {
        
    }
}
