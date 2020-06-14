import com.clw.ExcelUtil;
import com.clw.Student;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: clw
 * @Description:
 * @Date: 2020/6/14 11:43
 */
public class ExcelTest {

    @Test
    public void excelExport() {
        List<Student> list = new ArrayList<Student>();
        for (int i = 0; i < 5; i++) {
            list.add(new Student(111,"张三asdf","男"));
            list.add(new Student(111,"李四asd","男"));
            list.add(new Student(111,"王五","女"));
        }
        String[] columnNames = {"ID", "姓名", "性别"};
        try {
            ExcelUtil<Student> excelUtil = new ExcelUtil<Student>();
            excelUtil.exportExcel("用户导出", columnNames, list, new FileOutputStream("E:/test2.xlsx"), ExcelUtil.EXCEl_FILE_2007);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
