/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testvaadin;

import com.vaadin.data.Item;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Compare.Equal;
import com.vaadin.data.util.filter.Compare.Greater;
import com.vaadin.data.util.filter.Compare.Less;
import com.vaadin.data.util.filter.Like;
import com.vaadin.data.util.filter.Or;
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
public class User {

    //Variable de l'utilsateur
    private int idUser;
    private String name;
    private String email;
    private String password;
    private String adress;
    private int zip;
    private String city;
    private int tel;
    private String firstname;
    public List<Baby> babyList;
    private SQLContainer userTable;

    final Oracle oracle = new Oracle("jdbc:oracle:thin:@dbisep:1521:orcl", "gilles", "gilles");
//contruction des niveaux de droits de l'utilsateur

    public enum RightLevel {

        ADMIN,
        USER,
        NURSE,
        VISITOR;
    }
    //Variable contenant le droit de l'utilsateur
    private RightLevel rightLvl;

    public Component printUserInfo() {
        Label userInfo;
        if (isConnected()) {
            userInfo = new Label("nom: " + name + " prénom: " + firstname + " id: " + idUser + " email: "
                    + email + " password: " + password + " adresse: " + adress + " zip: " + zip + " city: " + city
                    + " tel: " + tel + " rightLvl: " + rightLvl.toString() + " baby: " + babyList.size());
            return userInfo;
        } else {
            userInfo = new Label("pas enregistré");
            return userInfo;
        }

    }

    //Fonction test
    public void setUserInfos() {
        userTable = oracle.queryTable("users");
//        userTable.addContainerFilter(
//                new Or(new And(new Equal("NAME", "Paul"),
//                                new Or(new Less("AGE", 18),
//                                        new Greater("AGE", 65))),
//                        new Like("NAME", "A%")));

        userTable.addContainerFilter(
                new Equal("name", "Cheg"));// WHERE name="cheg"
        //On récupère l'idUser
        idUser = Integer.parseInt(userTable.firstItemId().toString());
        Item infoUser = userTable.getItem(new RowId(new Object[]{idUser}));

        name = infoUser.getItemProperty("name").getValue().toString();
        firstname = infoUser.getItemProperty("firstName").getValue().toString();
        email = infoUser.getItemProperty("email").getValue().toString();
        password = infoUser.getItemProperty("password").getValue().toString();
        adress = infoUser.getItemProperty("adress").getValue().toString();
        zip = Integer.parseInt(infoUser.getItemProperty("zip").getValue().toString());
        city = infoUser.getItemProperty("city").getValue().toString();
        tel = Integer.parseInt(infoUser.getItemProperty("tel").getValue().toString());
        rightLvl = RightLevel.ADMIN;

    }

    //Constructeur User avec le couple (mail, password) d'un user de la bdd
    User(String emailToTest, String passwordToTest) {
        userTable = oracle.queryTable("users");
        //Ajout du container pour filtrer les résultats
        try {
            userTable.addContainerFilter(
                    new And(new Equal("email", emailToTest),
                            new Equal("password", passwordToTest)));// WHERE name=emailToTest AND password=passwordToTest
            //On récupère l'idUser
            idUser = Integer.parseInt(userTable.firstItemId().toString());
            Item infoUser = userTable.getItem(new RowId(new Object[]{idUser}));

            name = infoUser.getItemProperty("name").getValue().toString();
            firstname = infoUser.getItemProperty("firstName").getValue().toString();
            email = infoUser.getItemProperty("email").getValue().toString();
            password = infoUser.getItemProperty("password").getValue().toString();
            adress = infoUser.getItemProperty("adress").getValue().toString();
            zip = Integer.parseInt(infoUser.getItemProperty("zip").getValue().toString());
            city = infoUser.getItemProperty("city").getValue().toString();
            tel = Integer.parseInt(infoUser.getItemProperty("tel").getValue().toString());
            rightLvl = setRightLevel(infoUser.getItemProperty("rightLevel").getValue().toString());

            babyList = new ArrayList<Baby>();
            babyList = getBabyByUser();

        } catch (Exception e) {
            System.out.println("e");
        }
    }

    public Boolean checkEmailPassword(String emailToTest, String passwordToTest) {
        try {
            userTable = oracle.queryTable("users");
            //Ajout du container pour filtrer les résultats
            userTable.addContainerFilter(
                    new And(new Equal("email", emailToTest),
                            new Equal("password", passwordToTest)));// WHERE name=emailToTest AND password=passwordToTest

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    //Constructeur par défaut
    User() {
        this.idUser = idUser;
        this.name = name;
        this.email = email;
        this.password = password;
        this.adress = adress;
        this.zip = zip;
        this.city = city;
        this.tel = tel;
        this.firstname = firstname;
        this.rightLvl = RightLevel.VISITOR;
        babyList = new ArrayList<Baby>();
    }

    private RightLevel setRightLevel(String s) {
        if (s.equals("ADMIN")) {
            return RightLevel.ADMIN;
        } else if (s.equals("USER")) {
            return RightLevel.USER;
        } else if (s.equals("NURSE")) {
            return RightLevel.NURSE;
        } else {
            return RightLevel.VISITOR;
        }

    }

    public RightLevel getRightLevel() {
        return rightLvl;
    }

    //Fonction pour savoir si un utilisateur est connecté
    public Boolean isConnected() {
        if (rightLvl == RightLevel.ADMIN || rightLvl == RightLevel.USER || rightLvl == RightLevel.NURSE) {
            return true;
        }
        return false;
    }
    public Boolean reloadUser(){
        Boolean toReturn=false;
        try{
            isConnected();
            toReturn = true;
        }catch (Exception e) {
            toReturn = false;
            System.out.println("e");
        }
        return toReturn;
    }

    public List<Baby> getBabyByUser() {

        userTable = oracle.queryTable("jonction");
        Collection BabyIds = new ArrayList<Object>();
        List<Baby> listBaby = new ArrayList<Baby>();

        try {
            userTable.addContainerFilter(
                    new Equal("idUser", idUser));// WHERE name=emailToTest AND password=passwordToTest
            BabyIds = userTable.getItemIds();
            for (Object item : BabyIds) {

                int i = Integer.parseInt(item.toString());
                Item infoJonctionTable = userTable.getItem(new RowId(new Object[]{i}));
                Baby babyCurrent = new Baby(Integer.parseInt(infoJonctionTable.getItemProperty("idBaby").getValue().toString()));
                listBaby.add(babyCurrent);
            }
        } catch (Exception e) {
            System.out.println("e");
        }
        return listBaby;
    }
    
    public String getName(){
        return name;
    }

}
