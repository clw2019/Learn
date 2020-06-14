package com.clw;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.*;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/6/13 23:33
 */
public class ExcelUtil<T> {

        // 2007 版本以上 最大支持1048576行
        public  final static String  EXCEl_FILE_2007 = "2007";
        // 2003 版本 最大支持65536 行
        public  final static String  EXCEL_FILE_2003 = "2003";

        /**
         * 导出无头部标题行Excel
         * 时间格式默认：yyyy-MM-dd hh:mm:ss
         * @param title 表格标题
         * @param dataset 数据集合
         * @param out 输出流
         * @param version 2003 或者 2007，不传时默认生成2003版本
         */
        public void exportExcel(String title, Collection<T> dataset, OutputStream out, String version) {
            if(StringUtils.isEmpty(version) || EXCEL_FILE_2003.equals(version.trim())){
                exportExcel2003(title, null, dataset, out, "yyyy-MM-dd HH:mm:ss");
            }else{
                exportExcel2007(title, null, dataset, out, "yyyy-MM-dd HH:mm:ss");
            }
        }

        /**
         * 导出带有头部标题行的Excel
         * 时间格式默认：yyyy-MM-dd hh:mm:ss
         * @param title 表格标题
         * @param headers 头部标题集合
         * @param dataset 数据集合
         * @param out 输出流
         * @param version 2003 或者 2007，不传时默认生成2003版本
         */
        public void exportExcel(String title,String[] headers, Collection<T> dataset, OutputStream out,String version) {
            if(StringUtils.isBlank(version) || EXCEL_FILE_2003.equals(version.trim())){
                exportExcel2003(title, headers, dataset, out, "yyyy-MM-dd HH:mm:ss");
            }else{
                exportExcel2007(title, headers, dataset, out, "yyyy-MM-dd HH:mm:ss");
            }
        }

        /**
         * 通用Excel导出方法,利用反射机制遍历对象的所有字段，将数据写入Excel文件中
         * 此版本生成2007以上版本的文件 (文件后缀：xlsx)
         * @param title
         *            表格标题名
         * @param headers
         *            表格头部标题集合
         * @param dataset
         *            需要显示的数据集合,集合中一定要放置符合JavaBean风格的类的对象。此方法支持的
         *            JavaBean属性的数据类型有基本数据类型及String,Date
         * @param out
         *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
         * @param pattern
         *            如果有时间数据，设定输出格式。默认为"yyyy-MM-dd hh:mm:ss"
         */
        public void exportExcel2007(String title, String[] headers, Collection<T> dataset, OutputStream out, String pattern) {
            // 声明一个工作薄
            XSSFWorkbook workbook = new XSSFWorkbook();
            // 生成一个表格
            XSSFSheet sheet = workbook.createSheet(title);
            // 设置表格默认列宽度为15个字节
            sheet.setDefaultColumnWidth(20);
            // 生成一个样式
            XSSFCellStyle style = workbook.createCellStyle();
            // 设置这些样式
            //style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            //style.setFillPattern(FillPatternType.SQUARES);
            // 单元格边框
            //style.setBorderBottom(BorderStyle.THIN);
            //style.setBorderLeft(BorderStyle.THIN);
            //style.setBorderRight(BorderStyle.THIN);
            //style.setBorderTop(BorderStyle.THIN);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            // 生成一个字体
            XSSFFont font = workbook.createFont();
            font.setFontName("宋体");
            font.setFontHeightInPoints((short) 11);
            // 把字体应用到当前的样式
            style.setFont(font);
            // 生成并设置另一个样式
            XSSFCellStyle style2 = workbook.createCellStyle();
            style2.setAlignment(HorizontalAlignment.CENTER);
            style2.setVerticalAlignment(VerticalAlignment.CENTER);
            // 生成另一个字体
            XSSFFont font2 = workbook.createFont();
            font2.setFontName("宋体");
            font2.setFontHeightInPoints((short) 11);
            // 把字体应用到当前的样式
            style2.setFont(font2);

            // 产生表格标题行
            XSSFRow row = sheet.createRow(0);
            XSSFCell cellHeader;
            for (int i = 0; i < headers.length; i++) {
                cellHeader = row.createCell(i);
                cellHeader.setCellStyle(style);
                cellHeader.setCellValue(new XSSFRichTextString(headers[i]));
            }

            // 遍历集合数据，产生数据行
            Iterator<T> it = dataset.iterator();
            int index = 0;
            T t;
            Field[] fields;
            Field field;
            XSSFRichTextString richString;
            Pattern p = Pattern.compile("^//d+(//.//d+)?$");
            Matcher matcher;
            String fieldName;
            String getMethodName;
            XSSFCell cell;
            Class tCls;
            Method getMethod;
            Object value;
            String textValue;
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            while (it.hasNext()) {
                index++;
                row = sheet.createRow(index);
                t = (T) it.next();
                // 利用反射，根据JavaBean属性的先后顺序，动态调用getXxx()方法得到属性值
                fields = t.getClass().getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    cell = row.createCell(i);
                    cell.setCellStyle(style2);
                    field = fields[i];
                    fieldName = field.getName();
                    getMethodName = "get" + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);
                    try {
                        tCls = t.getClass();
                        getMethod = tCls.getMethod(getMethodName, new Class[] {});
                        value = getMethod.invoke(t, new Object[] {});
                        // 判断值的类型后进行强制类型转换
                        textValue = null;
                        if (value instanceof Integer) {
                            cell.setCellValue((Integer) value);
                        } else if (value instanceof Float) {
                            textValue = String.valueOf((Float) value);
                            cell.setCellValue(textValue);
                        } else if (value instanceof Double) {
                            textValue = String.valueOf((Double) value);
                            cell.setCellValue(textValue);
                        } else if (value instanceof Long) {
                            cell.setCellValue((Long) value);
                        }
                        if (value instanceof Boolean) {
                            textValue = "是";
                            if (!(Boolean) value) {
                                textValue = "否";
                            }
                        } else if (value instanceof Date) {
                            textValue = sdf.format((Date) value);
                        } else {
                            // 其它数据类型都当作字符串简单处理
                            if (value != null) {
                                textValue = value.toString();
                            }
                        }
                        if (textValue != null) {
                            matcher = p.matcher(textValue);
                            if (matcher.matches()) {
                                // 是数字当作double处理
                                cell.setCellValue(Double.parseDouble(textValue));
                            } else {
                                richString = new XSSFRichTextString(textValue);
                                cell.setCellValue(richString);
                            }
                        }
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } finally {
                        // 清理资源
                    }
                }
            }
            try {
                workbook.write(out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        /**
         * 通用Excel导出方法,利用反射机制遍历对象的所有字段，将数据写入Excel文件中
         * 此方法生成2003版本的excel,文件名后缀：xls
         * @param title
         *            表格标题名
         * @param headers
         *            表格头部标题集合
         * @param dataset
         *            需要显示的数据集合,集合中一定要放置符合JavaBean风格的类的对象。此方法支持的
         *            JavaBean属性的数据类型有基本数据类型及String,Date
         * @param out
         *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
         * @param pattern
         *            如果有时间数据，设定输出格式。默认为"yyyy-MM-dd hh:mm:ss"
         */
        public void exportExcel2003(String title, String[] headers, Collection<T> dataset, OutputStream out, String pattern) {
            // 声明一个工作薄
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 生成一个表格
            HSSFSheet sheet = workbook.createSheet(title);
            // 设置表格默认列宽度为15个字节
            sheet.setDefaultColumnWidth(20);
            // 生成一个样式
            HSSFCellStyle style = workbook.createCellStyle();
            // 设置这些样式
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            // 生成一个字体
            HSSFFont font = workbook.createFont();
            font.setFontName("宋体");
            font.setFontHeightInPoints((short) 11);
            // 把字体应用到当前的样式
            style.setFont(font);
            // 生成并设置另一个样式
            HSSFCellStyle style2 = workbook.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            // 生成另一个字体
            HSSFFont font2 = workbook.createFont();
            font2.setFontName("宋体");
            font2.setFontHeightInPoints((short) 11);
            // 把字体应用到当前的样式
            style2.setFont(font2);

            // 产生表格标题行
            HSSFRow row = sheet.createRow(0);
            HSSFCell cellHeader;
            for (int i = 0; i < headers.length; i++) {
                cellHeader = row.createCell(i);
                cellHeader.setCellStyle(style);
                cellHeader.setCellValue(new HSSFRichTextString(headers[i]));
            }

            // 遍历集合数据，产生数据行
            Iterator<T> it = dataset.iterator();
            int index = 0;
            T t;
            Field[] fields;
            Field field;
            HSSFRichTextString richString;
            Pattern p = Pattern.compile("^//d+(//.//d+)?$");
            Matcher matcher;
            String fieldName;
            String getMethodName;
            HSSFCell cell;
            Class tCls;
            Method getMethod;
            Object value;
            String textValue;
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            while (it.hasNext()) {
                index++;
                row = sheet.createRow(index);
                t = (T) it.next();
                // 利用反射，根据JavaBean属性的先后顺序，动态调用getXxx()方法得到属性值
                fields = t.getClass().getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    cell = row.createCell(i);
                    cell.setCellStyle(style2);
                    field = fields[i];
                    fieldName = field.getName();
                    getMethodName = "get" + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);
                    try {
                        tCls = t.getClass();
                        getMethod = tCls.getMethod(getMethodName, new Class[] {});
                        value = getMethod.invoke(t, new Object[] {});
                        // 判断值的类型后进行强制类型转换
                        textValue = null;
                        if (value instanceof Integer) {
                            cell.setCellValue((Integer) value);
                        } else if (value instanceof Float) {
                            textValue = String.valueOf((Float) value);
                            cell.setCellValue(textValue);
                        } else if (value instanceof Double) {
                            textValue = String.valueOf((Double) value);
                            cell.setCellValue(textValue);
                        } else if (value instanceof Long) {
                            cell.setCellValue((Long) value);
                        }
                        if (value instanceof Boolean) {
                            textValue = "是";
                            if (!(Boolean) value) {
                                textValue = "否";
                            }
                        } else if (value instanceof Date) {
                            textValue = sdf.format((Date) value);
                        } else {
                            // 其它数据类型都当作字符串简单处理
                            if (value != null) {
                                textValue = value.toString();
                            }
                        }
                        if (textValue != null) {
                            matcher = p.matcher(textValue);
                            if (matcher.matches()) {
                                // 是数字当作double处理
                                cell.setCellValue(Double.parseDouble(textValue));
                            } else {
                                richString = new HSSFRichTextString(textValue);
                                cell.setCellValue(richString);
                            }
                        }
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } finally {
                        // 清理资源
                    }
                }
            }
            try {
                workbook.write(out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
