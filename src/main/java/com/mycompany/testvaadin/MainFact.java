/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testvaadin;

import com.vaadin.data.Item;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.sqlcontainer.RowId;
import com.vaadin.data.util.sqlcontainer.SQLContainer;

/**
 *
 * @author baptman
 */
public class MainFact {

    private String date;
    private int idFact;
    private int idBaby;
    private String title;
    private String description;
    private String hour;
    private SQLContainer factTable;
     final Oracle oracle = new Oracle("jdbc:oracle:thin:@dbisep:1521:orcl", "gilles", "gilles");

    MainFact(int idF, int idB, String titleF, String descriptionF, String hourF, String dateF) {
        idFact = idF;
        idBaby = idB;
        title = titleF;
        description = descriptionF;
        hour = hourF;
        date = dateF;
        

    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public String getHour(){
        return hour;
    }
}
