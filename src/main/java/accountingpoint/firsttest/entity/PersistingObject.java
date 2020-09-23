package accountingpoint.firsttest.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "objects")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
//@SequenceGenerator(name = "objects_id_seq", sequenceName = "objects_id_seq")
public class PersistingObject {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id") private Long id;
    @Column(name = "uid") @GeneratedValue private UUID uid;
    @Column(name = "object_type") private int objectType;
//    @Column(name = "data") @Type(type = "jsonb") private MyJsonData myJsonData;
    @Column(name = "data") @Type(type = "jsonb")
    private Map<String, String> myJsonData = new HashMap<>();
    @Column(name = "parent_object_id") private Long parentObjectId;

    public PersistingObject(String property, String value) {
        this.myJsonData.put(property, value);
        this.uid = UUID.randomUUID();
        this.parentObjectId = (long) (Math.random()*101);
    }

    public PersistingObject() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public int getObjectType() {
        return objectType;
    }

    public void setObjectType(int objectType) {
        this.objectType = objectType;
    }

    public Map<String, String> getMyJsonData() {
        return myJsonData;
    }

    public void setMyJsonData(Map<String, String> myJsonData) {
        this.myJsonData = myJsonData;
    }

    public Long getParentObjectId() {
        return parentObjectId;
    }

    public void setParentObjectId(Long parentObjectId) {
        this.parentObjectId = parentObjectId;
    }
}
