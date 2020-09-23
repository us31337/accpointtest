package accountingpoint.firsttest.entity;

import java.io.Serializable;

public class MyJsonData implements Serializable {

    private String name;

    public MyJsonData() {
    }

    public MyJsonData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
