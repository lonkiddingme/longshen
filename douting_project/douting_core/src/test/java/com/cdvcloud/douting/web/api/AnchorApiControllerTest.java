/*
package com.cdvcloud.douting.web.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cdvcloud.rms.dao.BasicDao;


@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration(locations = {"classpath*:applicationContext.xml", "classpath*:springMVC.xml"}) //加载配置文件
public class AnchorApiControllerTest {

    @Autowired
    private BasicDao basicDao;
    @Test
    public void sumNum(){
    	 //Group操作
    	 Document groupFields = new Document("_id", "$proprietorId");
//    	 Document groupFields = new Document("_id", new Document("id", "$proprietorId").append("name", "$proprietorName"));
    	 groupFields.put("money", new Document("$sum", "$income"));
// 		 $last：返回每组最后一个文档，如果有排序，按照排序，如果没有按照默认的存储的顺序的最后个文档。
    	 groupFields.put("name", new Document("$last", "$proprietorName"));
//    	 groupFields.put("name", new Document("$addToSet", "$proprietorName"));
    	 List<Document> list = new ArrayList<>();
//    	 list.add(new Document("$project", new Document("proprietorName",1).append("income", 1).append("proprietorId", 1).append("gainerId", 1).append("expenditureType", 1)));
    	 list.add(new Document("$match", new Document("gainerId", "5a9f425aee37872f64dbb524").append("expenditureType", "reward")));
//    	 list.add(new Document("$match", new Document("expenditureType", "reward")));
    	 list.add(new Document("$group", groupFields));
    	 list.add(new Document("$limit", 10)); 
    	 list.add(new Document("$sort", new Document("money",-1)));
    	 List<Map<String, Object>> map =  basicDao.aggregate("moneyDetail", list);
    	 for (Map<String, Object> map2 : map) {
//    		 Map<String, Object> map3 = (Map<String, Object>) map2.get("_id");
//    		 System.out.println(map3.get("name")+"    "+map2.get("money"));
    		 System.out.println(map2.toString());
		 }
    	 
    }
}
*/