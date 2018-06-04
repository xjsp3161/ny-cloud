package com.nycloud.admin;

import com.nycloud.security.annotation.ResourcesMapping;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 资源初始化所有资源数据并添加进数据库，注意先清空掉数据库资源再添加，以免重复性添加
 * @author: super.wu
 * @date: Created in 2018/5/18 0018
 * @modified By:
 * @version: 1.0
 **/
public class ResourceGeneration {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/nycloud-admin?useUnicode=true&characterEncoding=utf-8";
    static final String USER = "root";
    static final String PASS = "123456";

    static final String packageName = "com.nycloud.admin.controller";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        List<String> classNames = getClassName(packageName);
        try {
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();
            for (String className : classNames) {
                Class<?> cls = Class.forName(className);
                RequestMapping requestMapping = cls.getDeclaredAnnotation(RequestMapping.class);
                String classUrl = null;
                if (requestMapping != null && requestMapping.value().length > 0) {
                    classUrl = requestMapping.value()[0];
                }
                Method[] methods = cls.getMethods();
                for (Method method: methods) {
                    ApiOperation apiOperation = method.getDeclaredAnnotation(ApiOperation.class);
                    RequestMapping methodRequestMapping = method.getDeclaredAnnotation(RequestMapping.class);
                    GetMapping getMapping = method.getDeclaredAnnotation(GetMapping.class);
                    PostMapping postMapping = method.getDeclaredAnnotation(PostMapping.class);
                    PutMapping putMapping = method.getDeclaredAnnotation(PutMapping.class);
                    DeleteMapping deleteMapping = method.getDeclaredAnnotation(DeleteMapping.class);
                    ResourcesMapping resourcesMapping = method.getDeclaredAnnotation(ResourcesMapping.class);
                    if (apiOperation != null) {
                        StringBuffer sb = new StringBuffer("url = ");
                        sb.append(classUrl);
                        String methodType;
                        String methodUrl = "";
                        if (getMapping != null) {
                            methodType = "GET";
                            if (getMapping.value().length > 0) {
                                methodUrl = getMapping.value()[0];
                            }
                        } else if (postMapping != null) {
                            methodType = "POST";
                            if (postMapping.value().length > 0) {
                                methodUrl = postMapping.value()[0];
                            }
                        } else if (putMapping != null) {
                            methodType = "PUT";
                            if (putMapping.value().length > 0) {
                                methodUrl = putMapping.value()[0];
                            }
                        } else if (deleteMapping != null) {
                            methodType = "DELETE";
                            if (deleteMapping.value().length > 0) {
                                methodUrl = deleteMapping.value()[0];
                            }
                        } else if (methodRequestMapping != null) {
                            methodUrl = methodRequestMapping.value()[0];
                            methodType = methodRequestMapping.value()[1];
                        } else {
                            break;
                        }
                        sb.append(methodUrl);
                        sb.append(" method = ");
                        sb.append(methodType);
                        sb.append(" 功能 = ");
                        sb.append(apiOperation.value());
                        System.out.println(sb.toString());
                        String name = apiOperation.value();
                        String url = classUrl  != null ? classUrl + methodUrl : methodUrl;
                        String urlRequestType = methodType;
                        String description = apiOperation.notes();
                        if (resourcesMapping != null) {
                            String code = resourcesMapping.code();
                            String pageElements = resourcesMapping.elements();
                            StringBuffer sqlBuffer = new StringBuffer("insert into sys_resource (name, url, url_request_type, description, code, page_elements) values('");
                            sqlBuffer.append(name).append("','");
                            sqlBuffer.append(url).append("','");
                            sqlBuffer.append(urlRequestType).append("','");
                            sqlBuffer.append(description).append("','");
                            sqlBuffer.append(code).append("','");
                            sqlBuffer.append(pageElements);
                            sqlBuffer.append("')");
                            stmt.executeUpdate(sqlBuffer.toString());
                        } else {
                            StringBuffer sqlBuffer = new StringBuffer("insert into sys_resource (name, code, page_elements, url, url_request_type, description) values('");
                            sqlBuffer.append(name).append("','','','");
                            sqlBuffer.append(url).append("','");
                            sqlBuffer.append(urlRequestType).append("','");
                            sqlBuffer.append(description).append("')");
                            try {
                                stmt.executeUpdate(sqlBuffer.toString());
                            } catch (Exception e) {
                                System.out.println(sqlBuffer.toString());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {

            }
        }
    }

    public static List<String> getClassName(String packageName) {
        String filePath = ClassLoader.getSystemResource("").getPath() + packageName.replace(".", "\\");
        List<String> fileNames = getClassName(filePath, null);
        return fileNames;
    }

    private static List<String> getClassName(String filePath, List<String> className) {
        List<String> myClassName = new ArrayList<String>();
        File file = new File(filePath);
        File[] childFiles = file.listFiles();
        for (File childFile : childFiles) {
            if (childFile.isDirectory()) {
                myClassName.addAll(getClassName(childFile.getPath(), myClassName));
            } else {
                String childFilePath = childFile.getPath();
                childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9, childFilePath.lastIndexOf("."));
                childFilePath = childFilePath.replace("\\", ".");
                myClassName.add(childFilePath);
            }
        }
        return myClassName;
    }

}
