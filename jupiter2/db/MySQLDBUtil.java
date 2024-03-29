package com.laioffer.jupiter2.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//这个class目的是生成要连接的DB的地址（DB在AWS上）
public class MySQLDBUtil {
    private static final String INSTANCE = "laiproject.csjtkuxuo9bs.us-east-2.rds.amazonaws.com";
    private static final String PORT_NUM = "3306";
    private static final String DB_NAME = "jupiter2";

    //是public static的方法，如果要连接数据库，可以直接调用它，返回的就是要连接的数据库的地址
    public static String getMySQLAddress() throws IOException {
        Properties prop = new Properties();
        String propFileName = "config.properties";


        InputStream inputStream = MySQLDBUtil.class.getClassLoader().getResourceAsStream(propFileName);
        prop.load(inputStream);


        String username = prop.getProperty("user");
        String password = prop.getProperty("password");
        return String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s&autoReconnect=true&serverTimezone=UTC&createDatabaseIfNotExist=true",
                //jdbc意识是 java DB connector，这么写是一个格式
                INSTANCE, PORT_NUM, DB_NAME, username, password);
    }

}
