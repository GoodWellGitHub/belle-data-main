package com.belle.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        String result = "{\"error_code\":0,\"message\":\"Success\",\"result\":[{\"employeeCode\":\"150635356\",\"employeeName\":\"吴瑞\",\"employeeStatus\":2,\"jobTypeName\":\"导购\",\"phoneNo\":\"13655511686\",\"positionName\":\"导购\",\"workMail\":\"\"},{\"employeeCode\":\"170587672\",\"employeeName\":\"孟梅\",\"employeeStatus\":4,\"jobTypeName\":\"导购\",\"phoneNo\":\"18919687660\",\"positionName\":\"导购\",\"workMail\":\"\"},{\"employeeCode\":\"180399365\",\"employeeName\":\"王霞\",\"employeeStatus\":4,\"jobTypeName\":\"导购\",\"phoneNo\":\"15005608250\",\"positionName\":\"导购\",\"workMail\":\"\"},{\"employeeCode\":\"180998369\",\"employeeName\":\"杜花红\",\"employeeStatus\":2,\"jobTypeName\":\"店长\",\"phoneNo\":\"13966730865\",\"positionName\":\"店长\",\"workMail\":\"\"},{\"employeeCode\":\"191197162\",\"employeeName\":\"任双\",\"employeeStatus\":2,\"jobTypeName\":\"导购\",\"phoneNo\":\"18105696070\",\"positionName\":\"导购\",\"workMail\":\"\"}]}";
        JSONObject jsonObject = JSON.parseObject(result);
        JSONArray dptArray = (JSONArray) jsonObject.get("result");

        if (Objects.isNull(dptArray) || dptArray.size() == 0) {
            System.out.println("空");
        }

        for (int i = 0; i < dptArray.size(); i++) {
            JSONObject jsonObject1 = (JSONObject) dptArray.get(i);
            if (Objects.isNull(jsonObject1)) {
                continue;
            }
            StringBuilder infoBuilder = new StringBuilder("");
            infoBuilder.append("\t")
                    .append(jsonObject1.getString("employeeCode")).append("\t")
                    .append(jsonObject1.getString("employeeName")).append("\t")
                    .append(jsonObject1.getString("positionName")).append("\t")
                    .append(jsonObject1.getString("employeeStatus")).append("\n\n");
            System.out.println("信息" +  infoBuilder.toString());
        }
    }
}
