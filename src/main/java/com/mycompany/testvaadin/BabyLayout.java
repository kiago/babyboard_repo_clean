/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.testvaadin;

import static com.mycompany.testvaadin.MyVaadinUI.navigator;
import com.vaadin.event.LayoutEvents;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author baptman
 */
//Classe pour la vue BabyView, permettant de créer le textarea contenant un bébé et lors du clique
//ajoute un paramètre pour identifier le bébé sélectionné.
public class BabyLayout {
    private int id;
    private VerticalLayout layoutV;
    BabyLayout(int i, Baby baby){
        id =i;
        layoutV = new VerticalLayout();
                        TextArea area1 = new TextArea(baby.getFisrtname() + " " + baby.getName());
                        area1.setWordwrap(true); // The default
                        area1.setValue(baby.getOld());
                        layoutV.addComponent(area1);

                        layoutV.addListener(new LayoutEvents.LayoutClickListener() {
                            @Override
                            public void layoutClick(LayoutEvents.LayoutClickEvent event) {
                                
                                VaadinSession.getCurrent().setAttribute("babyNumber", id);
                                navigator.navigateTo(BabyboardView.NAME);
                            }
                        });
        
    }
    public Layout getLayout(){
        return layoutV;
    }
    
    
}
