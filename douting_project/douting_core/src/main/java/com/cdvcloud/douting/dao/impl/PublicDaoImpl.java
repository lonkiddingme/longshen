package com.cdvcloud.douting.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cdvcloud.douting.dao.PublicDao;
import com.cdvcloud.douting.domain.BasicObject;
import com.cdvcloud.rms.dao.BasicDao;
import com.cdvcloud.rms.dao.mongodb.QueryOperators;

/**
 * @author SongYuanKun
 * @date 2017/11/4
 */
@Repository
public class PublicDaoImpl implements PublicDao {

    @Autowired
    private BasicDao basicDao;

    /**
     * inc更新方法
     *
     * @param jsonMap 参数
     * @return 更新条数
     */
    @SuppressWarnings("unchecked")
	@Override
    public long incValue(Map<String, Object> jsonMap) {
        String document = String.valueOf(jsonMap.get("document"));
        String key = String.valueOf(jsonMap.get("key"));
        Integer value = (Integer) jsonMap.get("value");
        List<String> ids = (List<String>) jsonMap.get("ids");
        List<ObjectId> objectIds = new ArrayList<ObjectId>();
        for (String id : ids) {
            objectIds.add(new ObjectId(id));
        }
        Map<String, Object> filter = new HashMap<String, Object>(16);
        filter.put(BasicObject.ID, new Document(QueryOperators.IN, objectIds));
        Map<String, Object> update = new HashMap<String, Object>(16);
        update.put(QueryOperators.INC, new Document(key, value));

        return basicDao.updateMany(document, filter, update, true);
    }
}
