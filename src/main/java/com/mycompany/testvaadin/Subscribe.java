/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testvaadin;

import static com.mycompany.testvaadin.MyVaadinUI.navigator;
import com.vaadin.annotations.Theme;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author geoffroyrouaix
 */
public class Subscribe extends Panel implements View {

    public static final String NAME = "Subscribe";
    private User user;
    private int i = 0;

    //Vue d'accueil du site (avant le log)
    public Subscribe() {

        user = VaadinSession.getCurrent().getAttribute(User.class);

        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);

        User bean = new User();

// Form for editing the bean
        final BeanFieldGroup<User> binder
                = new BeanFieldGroup<User>(User.class);
        binder.setItemDataSource(bean);
        FormLayout form = new FormLayout();
        form.addComponent(binder.buildAndBind("Name", "name"));
//        form.addComponent(binder.buildAndBind("Age", "age"));
        layout.addComponent(form);
//        layout.addComponent(binder.buildAndBind("Name", "name"));
//        layout.addComponent(binder.buildAndBind("Age", "age"));

// Buffer the form content
        binder.setBuffered(true);
        layout.addComponent(new Button("OK", new ClickListener() {
            public void buttonClick(ClickEvent event) {
                try {
                    binder.commit();
                } catch (CommitException e) {
                }
            }

        }));

        setContent(layout);

    }

    public void enter(ViewChangeEvent event) {

    }

}
