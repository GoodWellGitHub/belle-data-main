package com.belle.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/*@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)*/
public class ShopEmpMain {
    File file = new File("D:\\work\\documents\\内容平台数据\\store_emp.txt");
    String storeUrl = "https://openapi.hillinsight.com/api/ehr_data_api/store/get_store_user?token=2039361cba287fee27a4210e6397e5b5&storeCode=";

    // @Test
/*    public  void main() {
        System.out.println("begin.... ");
        try {
            new ShopEmpMain().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("end.....");
    }*/

    public static void main(String[] args) {
        System.out.println("begin.... ");
        try {
            new ShopEmpMain().get();
        } catch (Exception e) {
            System.out.println("出现异常" + e.getMessage());
        }
        System.out.println("end.....");
    }

    public void get() throws Exception {
        List<String> shop = Arrays.asList("IA04BL", "IA06BL", "IA1JBL", "IA1KBL", "IA3PBL", "IA3YBL", "IAD001", "IAD002", "IAD010", "IAD018", "IA08BS", "IA0ABS", "IA33BS", "IA43BS", "IA58BS", "IA64BS", "IAC003", "IAC004", "IAC005", "IAC006", "IA12BT", "IA14BT", "IA40BT", "IA41BT", "IA47BT", "IA58BT", "IA68BT", "IA76BT", "IAG002", "IAG007", "IA03JP", "IA05JP", "IA07JP", "IA13JP", "IA14JP", "IA35JP", "IA36JP", "IA37JP", "IA43JP", "IA49JP", "IA51JP", "IA52JP", "IAB002", "IAB003", "IAB004", "IAB005", "IAB006", "IAB007", "IAB008", "IAI002", "IAI003", "IAI004", "IAI005", "IS06ML", "IS0AML", "IS18ML", "IS48ML", "IS56ML", "IS65ML", "IS69ML", "IS75ML", "IA04SD", "IA14SD", "IA21SD", "IA35SD", "IA55SD", "IA69SD", "IA71SD", "IAH001", "IAH002", "IAH010", "I010ST", "I014ST", "I01BST", "I01FST", "I01HST", "I01IST", "I01JST", "I01SST", "I022ST", "I023ST", "I028ST", "I029ST", "I032ST", "I033ST", "I035ST", "I038ST", "I044ST", "I045ST", "I046ST", "I051ST", "I063ST", "I064ST", "I066ST", "I067ST", "I070ST", "I072ST", "I078ST", "IAJ001", "IAJ002", "IAJ003", "IAJ004", "IAJ006", "IAJ007", "IAJ009", "IAJ011", "IAJ013", "IAJ014", "IAJ016", "IAJ017", "IA0BTM", "IA30TM", "IA3BTM", "IA3CTM", "IAF001", "IAF002", "IAF004", "IAF005", "IAF007", "IAF009", "IAF012", "IAF013", "IAF014", "IAF017", "IA05TT", "IA0BTT", "IA0WTT", "IA17TT", "IA1ITT", "IA1MTT", "IA1NTT", "IA34TT", "IA45TT", "IA77TT", "I710ST", "I718ST", "I720ST", "I726ST", "I729ST", "I732ST", "I744ST", "I754ST", "I757ST", "IKJ003", "IKJ004", "I746ST", "I913ST", "I920ST", "I922ST", "I924ST", "I925ST", "IRJ001", "I724ST", "I734ST", "I740ST", "I763ST", "I722ST", "I737ST", "I741ST", "I743ST", "I745ST", "I760ST", "I770ST", "I771ST", "IKJ001", "I311ST", "I316ST", "I318ST", "I319ST", "I321ST", "I322ST", "I323ST", "I327ST", "IDJ002", "I214ST", "I216ST", "I218ST", "I219ST", "I220ST", "I222ST", "I223ST", "I225ST", "I228ST", "I232ST", "I234ST", "IBJ001", "IBJ002", "IBJ004", "IBJ006", "IBJ007", "I510ST", "I511ST", "I524ST", "I525ST", "I526ST", "I536ST", "I614ST", "I616ST", "IHJ002", "I410ST", "I417ST", "I433ST", "I434ST", "I437ST", "I452ST", "I461ST", "I462ST", "IGJ001", "IGJ002", "IGJ003", "IGJ004", "IGJ005", "IGJ007", "IGJ008", "I430ST", "I436ST", "I440ST", "I441ST", "I455ST", "I459ST", "ILJ001", "DC11ST", "DH23ST", "DB56ST", "DBA5ST", "DT47ST", "DF15ST", "DY26ST", "DD07ST", "DN06ST", "DS21ST", "DQ01ST", "DA05ST", "I818ST", "I824ST", "I825ST", "I835ST", "IWJ001", "IWJ002", "IWJ004", "IWJ005", "IWJ008", "IWJ009", "IWJ010", "I836ST", "I837ST", "I841ST", "IWJ003", "I834ST", "I842ST", "I843ST", "IWJ006", "EJ01ST", "EJ03ST", "EJ05ST", "EJ06ST", "EJ08ST", "EJ11ST", "EJ12ST", "EJ13ST", "EJ14ST", "EJ30ST", "EJ32ST", "EJ34ST", "EJ36ST", "EJ37ST", "EJ39ST", "EJ42ST", "EN02ST", "EZ01ST", "EZ05ST", "EZ06ST", "EZ13ST", "EJ09ST", "EW01ST", "EW06ST", "EW11ST", "EW12ST", "EW13ST", "EY02ST", "EY03ST", "EY06ST", "EY07ST", "EY09ST", "EQ02ST", "EQ04ST", "EQ08ST", "EQ09ST", "EQ17ST", "EQ34ST", "EQ35ST", "EQ43ST", "EQ44ST", "EG01ST", "EM01ST", "EL06ST", "EL10ST", "EL25ST", "EP03ST", "EA01ST", "EI02ST", "EK01ST", "EL01ST", "EL17ST", "EL18ST", "EL26ST", "EL27ST", "EL29ST", "EL32ST", "EL33ST", "EL35ST", "EL36ST", "EL37ST", "EL38ST", "EU01ST");
        final Writer out = new FileWriter(file);

        for (String store : shop) {
            List<String> info = employeeInfo(store);
            if (info == null || info.size() == 0) {
                continue;
            }
            for (String emp : info) {
                try {
                    out.write(emp);
                } catch (IOException e) {
                    System.out.println("出现异常: " + e.getMessage());
                    ;
                }
                out.flush();
            }
        }

        Thread.sleep(600000);
    }

    public List<String> employeeInfo(String storeCode) {
        String url = storeUrl + storeCode;
        List<String> deptInfo = Lists.newLinkedList();
        try {
            String result = HillHttp.INSTANCE.get(null, url);
            System.out.println("result:  " + result);
            JSONObject jsonObject = JSON.parseObject(result);
            JSONArray dptArray = (JSONArray) jsonObject.get("result");

            if (Objects.isNull(dptArray) || dptArray.size() == 0) {
                return null;
            }

            for (int i = 0; i < dptArray.size(); i++) {
                JSONObject jsonObject1 = (JSONObject) dptArray.get(i);
                if (Objects.isNull(jsonObject1)) {
                    continue;
                }
                StringBuilder infoBuilder = new StringBuilder(storeCode);
                infoBuilder.append("\t")
                        .append(jsonObject1.getString("employeeCode")).append("\t")
                        .append(jsonObject1.getString("employeeName")).append("\t")
                        .append(jsonObject1.getString("positionName")).append("\t")
                        .append(jsonObject1.getString("employeeStatus")).append("\r\n");
                String info = infoBuilder.toString();
                deptInfo.add(info);
            }
        } catch (Exception e) {
            System.out.println("出现异常: " + e.getMessage());
            ;
        }
        return deptInfo;
    }
}
