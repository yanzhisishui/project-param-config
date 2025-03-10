package com.syc.projectparamconfig;

import com.syc.projectparamconfig.bo.demo.RepayAccount;
import com.syc.projectparamconfig.client.ProjectConfigClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class ProjectParamConfigApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ProjectParamConfigApplication.class, args);
        ProjectConfigClient client = run.getBean(ProjectConfigClient.class);
        String value1 = client.getValue("biz-ins-credit-srv", "credit_allow_time_span", "09:00~23:00");
        String value2 = client.getValue("biz-ins-credit-srv", "credit_allow_time_span");

        List<RepayAccount> testData1 = new ArrayList<>();
        RepayAccount repayAccount1 = new RepayAccount();
        repayAccount1.setAccountName("test1");
        repayAccount1.setAccountId("id1");
        testData1.add(repayAccount1);

        List<RepayAccount> list1 = client.getList("biz-ins-mas-srv","repay_account_config",testData1);
        List<RepayAccount> list2 = client.getList("biz-ins-mas-srv","repay_account_config");

        Map<String,List<RepayAccount>> testData2 = new HashMap<>();
        testData2.put("repay_account_config_map1",testData1);
        Map<String, List<RepayAccount>> map1 = client.getMap("biz-ins-mas-srv", "repay_account_config_map1", testData2);
        Map<String, List<RepayAccount>> map2 = client.getMap("biz-ins-mas-srv", "repay_account_config_map1");

        Boolean flag1 = client.getSwitch("biz-ins-credit-srv", "credit_switch", true);
        Boolean flag2 = client.getSwitch("biz-ins-credit-srv", "credit_switch");

        System.out.println("end");
    }

}
