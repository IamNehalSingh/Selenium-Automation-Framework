package utils;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class LoginDataProvider {

    @DataProvider(name = "Logindata")
    public static Object[][] getLoginData() throws IOException {

        String filePath = System.getProperty("user.dir") + "/testdata/test.xlsx";
        ExcelUtils.loadExcel(filePath, "Sheet1");

        int rowCount = ExcelUtils.getRowCount();
        Object[][] data = new Object[rowCount - 1][2];

        for (int i = 1; i < rowCount; i++) {
            data[i - 1][0] = ExcelUtils.getCellData(i, 0);
            data[i - 1][1] = ExcelUtils.getCellData(i, 1);
        }

        ExcelUtils.closeExcel();
        return data;
    }
}
