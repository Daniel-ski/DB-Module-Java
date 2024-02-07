package org.example;

import entities.Account;
import orm.EntityManager;
import orm.config.Connector;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connector.createConnection("root","dev010","custom_orm_workshop");

        EntityManager<Account> accountEntityManager = new EntityManager<>(Connector.getConnection());

        accountEntityManager.doAlter(Account.class);
    }
}