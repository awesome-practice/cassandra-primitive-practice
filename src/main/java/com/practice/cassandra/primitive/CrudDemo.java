package com.practice.cassandra.primitive;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;

/**
 * https://github.com/datastax/java-driver/tree/4.x/manual/core
 */
public class CrudDemo {

    private static CqlSession session;

    @BeforeAll
    public static void beforeAll(){
        session = new CqlSessionBuilder()
                .addContactPoint(new InetSocketAddress("localhost",9042))
                .withLocalDatacenter("datacenter1")
                .build();
    }
    @AfterAll
    public static void afterAll(){
        if (session != null) {
            session.close();
        }
    }

    @Test
    public void query(){
        ResultSet resultSet = session.execute("select username,birth_year,country  from test.users");

        for (Row row : resultSet) {
            String username = row.getString("username");
            int birth_year = row.getInt("birth_year");
            String country = row.getString("country");
            System.out.println("username = " + username);
            System.out.println("birth_year = " + birth_year);
            System.out.println("country = " + country);
        }

    }


}
