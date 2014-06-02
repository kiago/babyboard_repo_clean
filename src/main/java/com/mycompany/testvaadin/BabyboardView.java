/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testvaadin;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field.ValueChangeEvent;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author baptman
 */
public class BabyboardView extends Panel implements View {

    public static final String NAME = "Board";
    private User user;
    private int i = 0;
    private Baby baby;
    private Date date;
    private InlineDateField calendar;

    //Vue d'accueil du site (avant le log)
    public BabyboardView() {

        user = VaadinSession.getCurrent().getAttribute(User.class);
//
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
//        layout.addComponent(user.printUserInfo());

        if (user.babyList.isEmpty()) {
            Label test = new Label("Vous n'aver pas d'enfant associé à votre compte!");
            layout.addComponent(test);
        } else {

            try {
                i = Integer.parseInt(VaadinSession.getCurrent().getAttribute("babyNumber").toString());
                baby = user.babyList.get(i);
                baby.getBabyMainFacts();
                layout.addComponent(baby.printBabyInfo());

                layout.addComponent(mainFactComponent());
                calendar = new InlineDateField();
                calendar.setValue(new java.util.Date());
                calendar.addValueChangeListener(new ValueChangeListener() {
                    @Override
                    public void valueChange(Property.ValueChangeEvent event) {
                        Date dateCal = calendar.getValue();

                        formatDate(dateCal.getDay(), dateCal.getMonth(), dateCal.getYear());

                        layout.addComponent(new Label("" + date));
                        final String valueString = String.valueOf(event.getProperty()
                                .getValue());
                        Notification.show("Date sélectionnée: ", valueString,
                                Type.TRAY_NOTIFICATION);
                    }

                });
                layout.addComponent(calendar);

            } catch (Exception e) {
                layout.addComponent(new Button("test"));
                System.out.println("e");
            }
        }
        setContent(layout);
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    private void formatDate(int day, int month, int year) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            date = new Date();
            date = sdf.parse(year + "/" + month + "/" + day);
        } catch (ParseException ex) {
            Logger.getLogger(BabyboardView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private VerticalLayout mainFactComponent() {
        VerticalLayout VL = new VerticalLayout();
        try {

            for (MainFact fact : baby.FactList) {
                Label title = new Label(fact.getTitle() + " à " + fact.getHour());
                Label description = new Label(fact.getDescription());
                VL.addComponent(title);
                VL.addComponent(description);
            }
        } catch (Exception e) {
            System.out.println("e");
        }
        return VL;
    }

}
