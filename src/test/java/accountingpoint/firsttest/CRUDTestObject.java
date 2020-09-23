package accountingpoint.firsttest;

import accountingpoint.firsttest.entity.MyJsonData;
import accountingpoint.firsttest.entity.PersistingObject;
import accountingpoint.firsttest.service.PersistingObjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class CRUDTestObject {

    @Autowired
    private PersistingObjectService persistingObjectService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createObjects() {
        PersistingObject persistingObject = new PersistingObject(
                "name", "some name");
        persistingObjectService.addObject(persistingObject);
        var list = persistingObjectService.getAll();
        System.out.println(list);
    }

    @Test
    public void GenerateRandomData() {

        for (int i = 0; i < 120; i++) {
            PersistingObject po = new PersistingObject();
            int max = 121; int min = 62;
            long parent = (long) (Math.random() * (max - min + 1) + min);
            po.setParentObjectId(parent);
            po.setUid(UUID.randomUUID());
            int percent = (int) (Math.random()*10); //get random int [0;9]
            int type = (int) (Math.random()*6);
            po.setObjectType(type);
            if (percent < 8) {
                Map<String, String> map = new HashMap<>();
                map.put("name", "some name");
                po.setMyJsonData(map);
            }
            persistingObjectService.addObject(po);
        }

    }

    @Test
    public void serializeObject() {
        MyJsonData data = new MyJsonData("sdfh dksjhfk");
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonString);
    }

    @Test
    public void findByParentId() {
        var list = persistingObjectService.findByParentObjectId(0);
        System.out.println(list);
    }


}
