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
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

public class BabyView extends Panel implements View {

    public static final String NAME = "Baby";
    private User user;
    private int i = 0;

    //Vue d'accueil du site (avant le log)
    public BabyView() {

        user = VaadinSession.getCurrent().getAttribute(User.class);

        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
//        layout.addComponent(user.printUserInfo());

        if (user.babyList.isEmpty()) {
            Label test = new Label("Vous n'aver pas d'enfant associé à votre compte!");
            layout.addComponent(test);
        } else {
            try {

                i = 0;
                for (Baby baby : user.babyList) {
                    BabyLayout babyL = new BabyLayout(i, baby);
//                        VerticalLayout layoutV = new VerticalLayout();
//                        TextArea area1 = new TextArea(baby.getFisrtname() + " " + baby.getName());
//                        area1.setWordwrap(true); // The default
//                        area1.setValue(baby.getOld());
//                        layoutV.addComponent(area1);
//
//                        layoutV.addListener(new LayoutClickListener() {
//                            @Override
//                            public void layoutClick(LayoutClickEvent event) {
//                                int l=i;
//                                VaadinSession.getCurrent().setAttribute("babyNumber", l);
//                                navigator.navigateTo(BabyboardView.NAME);
//                            }
//                        });
//                    layout.addComponent(layoutV);
                    layout.addComponent(babyL.getLayout());
                    i++;
                }
            } catch (Exception e) {
                System.out.println("e");
            }
        }
        setContent(layout);
    }

    public void enter(ViewChangeEvent event) {

    }

//    public int get iBaby(){
//        
//    }
    public void fctClick(int i) {
        VaadinSession.getCurrent().setAttribute("babyNumber", i);
        navigator.navigateTo(BabyboardView.NAME);
    }

}
