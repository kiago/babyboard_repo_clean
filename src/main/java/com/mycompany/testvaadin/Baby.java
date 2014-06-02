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
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author baptman
 */
public class Baby {

    private int idBaby;
    private String name;
    private String old;
    private int sex;
    private String firstname;
    Oracle oracle = new Oracle("jdbc:oracle:thin:@dbisep:1521:orcl", "gilles", "gilles");
    private SQLContainer babyTable;
    private SQLContainer factTable;
    public List<MainFact> FactList;
    
    Baby(int idB){
         
        babyTable = oracle.queryTable("babies");
        babyTable.addContainerFilter(
                new Compare.Equal("idBaby", idB));// WHERE idBaby=idBaby
//        idBaby = Integer.parseInt(babyTable.firstItemId().toString());
        idBaby = idB;
        Item infoUser = babyTable.getItem(new RowId(new Object[]{idBaby}));

        name = infoUser.getItemProperty("name").getValue().toString();
        old = infoUser.getItemProperty("age").getValue().toString();
        sex = Integer.parseInt(infoUser.getItemProperty("sex").getValue().toString());
        firstname = infoUser.getItemProperty("firstName").getValue().toString();
    }
    public int getId(){
        return idBaby;
    }
    public String getName(){
        return name;
    }
    public String getOld(){
        return old;
    }
    public int getSex(){
        return sex;
    }
    public String getFisrtname(){
        return firstname;
    }
    
    public Component printBabyInfo(){
        Label infoB = new Label("name "+name+" firstname: "+firstname);
        return infoB;
    }
    
        public List<MainFact> getBabyMainFacts() {

        factTable = oracle.queryTable("mainfacts");
        Collection factsIds = new ArrayList<Object>();
        List<MainFact> listMFact = new ArrayList<MainFact>();

        try {
            factTable.addContainerFilter(
                    new Compare.Equal("idBaby", idBaby));// WHERE name=emailToTest AND password=passwordToTest
            factsIds = factTable.getItemIds();
            for (Object item : factsIds) {

                int i = Integer.parseInt(item.toString());
                Item infoJonctionTable = factTable.getItem(new RowId(new Object[]{i}));
//                 MainFact(int idF, int idB, String titleF, String descriptionF, String hourF, String factF, String dateF) {
                MainFact MF = new MainFact(Integer.parseInt(infoJonctionTable.getItemProperty("idFact").getValue().toString()),
                Integer.parseInt(infoJonctionTable.getItemProperty("idBaby").getValue().toString()), 
                        infoJonctionTable.getItemProperty("title").getValue().toString(),
                infoJonctionTable.getItemProperty("description").getValue().toString(),
                infoJonctionTable.getItemProperty("date").getValue().toString(),
                infoJonctionTable.getItemProperty("hours").getValue().toString());
                listMFact.add(MF);
                //test
            }
        } catch (Exception e) {
            System.out.println("e");
        }
        FactList = new ArrayList<MainFact>();
        FactList = listMFact;
        return listMFact;
    }
}
