package com.solar.utils;

import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @author hushaoge
 * @date 2019/6/3 17:25
 */
public class RegionCodeUtils {
    public static void main(String[] args) throws Exception {
        String filePath = "D:\\行政区划代码.xlsx";
        FileInputStream excelFile = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(excelFile);

        // 读入Excel文件的第一个表
        Sheet sheet = workbook.getSheetAt(0);
        // 从文件第二行开始读取，第一行为标识行
        int rowNum = sheet.getLastRowNum();
        Map<String, Node> map = Maps.newLinkedHashMap();
        for (int i = 0; i < rowNum; i++){
            Row row = sheet.getRow(i);
            String value = row.getCell(0).getStringCellValue();
            String name = row.getCell(1).getStringCellValue();
            String parentValue = getParentValue(value);

            Node node = new Node();
            node.setValue(value);
            node.setName(name);
            node.setParentValue(parentValue);
            map.put(value, node);
        }

//        for(Node root : roots){
//            fillChildData(root, map);
//        }
        // 写入文件

        Workbook workbook2 = new XSSFWorkbook();

        // 读入Excel文件的第一个表
        Sheet sheet2 = workbook2.createSheet();
        int j = 0;
        for (Map.Entry<String, Node> entry : map.entrySet()){
            String value = entry.getKey();
            Node node = entry.getValue();
            Row row = sheet2.createRow(j);

            String parentValue = node.getParentValue();
            Node parentNode = map.get(parentValue);
            row.createCell(0).setCellValue(value);
            row.createCell(1).setCellValue(node.getName());
            if(parentNode != null && !StringUtils.equals(value, parentValue)){
                row.createCell(2).setCellValue(parentNode.getValue());
                row.createCell(3).setCellValue(parentNode.getName());
            } else {
                // 没有上级或者省直辖县级市
                parentValue = StringUtils.substring(parentValue, 0, 2);
                parentValue = StringUtils.rightPad(parentValue, 6, "0");
                parentNode = map.get(parentValue);
                if(parentNode != null && !StringUtils.equals(value, parentValue)){
                    row.createCell(2).setCellValue(parentNode.getValue());
                    row.createCell(3).setCellValue(parentNode.getName());
                }
            }
            j++;
        }


        filePath = "D:\\行政区划代码(处理).xlsx";
        File file = new File(filePath);
        //目录不存在则创建
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 第六步，将文件模板件存到指定位置
        BufferedOutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
            workbook2.write(outputStream);
        } catch (IOException e) {
            throw e;
        } finally {
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void fillChildData(Node node, Map<String, List<Node>> map ){

        // 采用remove,获取过的数据则不在处理，防止互相嵌套导致死循环
        List<Node> valRangeDetails = map.remove(node.getValue());
        if(CollectionUtils.isEmpty(valRangeDetails)){
            return;
        }
        for (Node childNode : valRangeDetails) {
            fillChildData(childNode, map);
        }
        node.setChildNode(valRangeDetails);
    }

    private static String getParentValue(String value){
        // 110000 110101
        if(StringUtils.startsWithAny(value, "11", "12", "31", "50")){
            value = StringUtils.substring(value, 0, 2);
            value = StringUtils.rightPad(value, 6, "00");
            return value;
        }
        // 130200
        String parentValue = value;
        if(StringUtils.endsWith(parentValue, "00")){
            parentValue = StringUtils.substringBeforeLast(value,"00");
            // 1302
            if(StringUtils.endsWith(parentValue, "00")){
                parentValue = StringUtils.substringBeforeLast(value,"00");
            } else {
                // 13
                parentValue = StringUtils.substring(parentValue, 0, 2);
            }
        }

        parentValue = StringUtils.substring(parentValue, 0, 4);
        parentValue = StringUtils.rightPad(parentValue, 6, "00");
        return parentValue;
    }
    static class Node {
        String value;
        String name;
        String parentValue;
        List<Node> childNode;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParentValue() {
            return parentValue;
        }

        public void setParentValue(String parentValue) {
            this.parentValue = parentValue;
        }

        public List<Node> getChildNode() {
            return childNode;
        }

        public void setChildNode(List<Node> childNode) {
            this.childNode = childNode;
        }
    }
}
