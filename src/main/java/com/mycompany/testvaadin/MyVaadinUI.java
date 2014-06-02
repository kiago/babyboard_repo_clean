package com.mycompany.testvaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import javax.servlet.annotation.WebServlet;

@Title("Babyboard")
@Theme("ourtheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {

//    public User user; //représente l'utilisateur actuelle
    public static Navigator navigator;

//    private Connection connexionForm;
    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "com.mycompany.testvaadin.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        //on met dans la session de l'utilisateur une objet de classe User
        Boolean reloadUser = true;

        if (reloadUser) {
            VaadinSession.getCurrent().setAttribute(User.class, new User());
            reloadUser = false;
        }
        // Create Navigator, use the UI content layout to display the views
        navigator = new Navigator(this, this);

        // Add some Views
//        MainView mainVue = new MainView();
        //raffraichit la page a chaque appel
        navigator.addView(MainView.NAME, MainView.class);
        //garde la même page pour chaque appel

        Connection connectionView = new Connection();
        navigator.addView(Connection.NAME, connectionView); // no fragment

        tableUser tableUserView = new tableUser();
        navigator.addView(tableUserView.NAME, new tableUser());

        BabyView babyView = new BabyView();
        navigator.addView(BabyView.NAME, BabyView.class);

        BabyboardView bordView = new BabyboardView();
        navigator.addView(BabyboardView.NAME, BabyboardView.class);
        
        navigator.addView(Subscribe.NAME, Subscribe.class);
        

    }


}
