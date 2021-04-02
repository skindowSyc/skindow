package com.skindow.sql;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.MapUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ Author     ：syc.
 * @ Date       ：Created in 11:23 2021/2/22
 * @ Description：
 * @ Modified By：
 * @ Version:
 */
public class OracleToMySql {

    public static final String TABLE_NAME = "ods_03_tcombaccoinfo";

    /**
     * 表说明
     */
    public static final String TABLE_DERCTION = "恒生 - 投管人信息表";

    public static final String FILE = "/Users/shaoyongchang/临时文件/TSHAREDETAIL.sql";

    /**
     * 是否全量
     */
    public static final boolean IS_QL = true;

    public static void main(String[] args) throws Exception {
        Map<String, String> allTableColumn = getAllTableColumn();
        System.out.println(JSON.toJSONString(allTableColumn));
    }
    public static void main_1(String[] args) throws Exception {
        String readFile = readFile(FILE);
        if (StringUtils.isEmpty(readFile)) {
            System.out.println("读取文件内容为空");
        }
        List<String> list = Arrays.asList(readFile.split("\n"));
        if (CollectionUtils.isEmpty(list)) {
            System.out.println("内容有误");
        }
        Map<String, String> stringStringMap = getAllTableColumn();
        StringBuilder result = new StringBuilder();
        result.append("CREATE TABLE " + TABLE_NAME + " (\n");
        result.append("  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',\n");
        if (!IS_QL) {
            result.append("  `dt` bigint(8) DEFAULT NULL COMMENT '数据日期',\n");
        }
        StringBuilder temp;
        for (String s : list) {
            if (StringUtils.isEmpty(s)) {
                continue;
            }
            s = s.trim();
            temp = new StringBuilder();
            //转换小写
            temp.append("  `");
            String columnName = s.substring(s.indexOf("\"") + 1, s.lastIndexOf("\"")).toLowerCase();
            temp.append(columnName);
            temp.append("`");
            //字段类型
            String s2 = s.split(" ")[1];
            //解析CHAR(
            if (s2.contains("CHAR(")) {
                //截取括号内的内容
                String substring = s.substring(s.indexOf("(") + 1, s.indexOf(")"));
                String s1 = substring.replaceAll("\\D", "");
                temp.append(" char(" + s1 + ") ");
            }
            //解析VARCHAR2(
            if (s2.contains("VARCHAR2(")) {
                //截取括号内的内容
                String substring = s.substring(s.indexOf("(") + 1, s.indexOf(")"));
                String s1 = substring.replaceAll("\\D", "");
                temp.append(" varchar(" + s1 + ") ");
            }
            //解析NUMBER
            if (s2.contains("NUMBER(")) {
                //截取括号内的内容
                String substring = s.substring(s.indexOf("(") + 1, s.indexOf(")"));
                String[] split = substring.split(",");
                temp.append(" decimal(" + split[0] + "," + split[1] + ")");
            }
            //解析date
            if (s2.contains("DATE")) {
                //截取括号内的内容
                temp.append(" datetime");
            }
            //解析null
            if (s.indexOf("NOT NULL") > 0) {
                temp.append(" NOT NULL");
            } else {
                temp.append(" DEFAULT NULL");
            }
            //解析注释
            Map<String, String> conKey = parseMapForFilter(stringStringMap, columnName);
            if (MapUtils.isNotEmpty(stringStringMap) && MapUtils.isNotEmpty(conKey)) {
                temp.append(" COMMENT ' " + conKey.values().toArray()[0] + "'");
            }
            temp.append(",\n");
            result.append(temp);
        }
        result.append("  `record_create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',\n" +
                "  `record_update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',\n" +
                "  `record_status` tinyint DEFAULT '1' COMMENT '是否有效 0-无效，1-有效',\n" +
                "PRIMARY KEY (`id`)\n");
        result.append(") ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='" + TABLE_DERCTION + "';");
        System.out.println(result);
    }

    public static String readFile(String strFile) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            InputStream is = new FileInputStream(strFile);
            int iAvail = is.available();
            byte[] bytes = new byte[iAvail];
            is.read(bytes);
            stringBuilder.append(new String(bytes));
            is.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, String> getAllTableColumn() throws Exception {
        System.out.println("-----------读取字段注释开始-----------");
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection conn = java.sql.DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/skindow", "root", "1194169073");
        // 1、获取数据库所有表
        StringBuilder sbTables = new StringBuilder();
        List<String> tables = new ArrayList<>();
        sbTables.append("-------------- 数据库中有下列的表 ----------<br/>");
        try {
            DatabaseMetaData dbMetaData = conn.getMetaData();
            ResultSet rs = dbMetaData.getTables(null, null, null, new String[]{"TABLE"});
            while (rs.next()) {// ///TABLE_TYPE/REMARKS
                if ("sys_config".equals(rs.getString("TABLE_NAME"))) {
                    continue;
                }
                tables.add(rs.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 2、遍历数据库表，获取各表的字段等信息
        Map<String, String> map = new HashMap<>();
        for (String tableName : tables) {
            String sql = "show full columns from " + tableName;
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    if (!StringUtils.isEmpty(rs.getString("Comment"))) {
                        map.put(rs.getString("Field"), rs.getString("Comment"));
                        System.out.println(rs.getString("Type"));
                    }
                }
            } catch (SQLException e) {

            }
        }
        return map;
    }

    public static Map<String, String> parseMapForFilter(Map<String, String> map, String filters) {
        if (map == null) {
            return null;
        } else {
            map = map.entrySet().stream()
                    .filter((e) -> checkKey(e.getKey(), filters))
                    .collect(Collectors.toMap(
                            (e) -> (String) e.getKey(),
                            Map.Entry::getValue
                    ));
        }
        return map;
    }

    private static boolean checkKey(String key, String filters) {
        String s = key.toLowerCase();
        String s1 = filters.toLowerCase();
        return s.contains(s1);
    }

//    public static void main(String[] args) {
//        System.out.println(checkKey("cust_type","vc_custytype"));
//    }
}
