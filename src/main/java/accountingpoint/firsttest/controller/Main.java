package accountingpoint.firsttest.controller;

import accountingpoint.firsttest.entity.PersistingObject;
import accountingpoint.firsttest.service.PersistingObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class Main {

    @Autowired
    PersistingObjectService objectService;
    String nullRecordsFound = "<h1>Ни одного корневого элемента не найдено!</h1>";

    @ResponseBody
    @RequestMapping(value = "/test", produces = "text/html")
    public String generateHTMLPage() {
        var sb = new StringBuilder();
        int level = 0;
        var list = objectService.findByParentObjectId(level);
        Map<Integer, Integer> counter = new HashMap<>();
        if (list.size() > 0) {
            list.stream()
                    .forEach(persistingObject -> {
                        sb.append("<ul><b>" + getObjectAsText(persistingObject) + "</b>\n");
                        traverseTree(persistingObject, sb, counter);
                        sb.append("</ul>\n");
                    });
        } else {
            sb.append(nullRecordsFound);
        }
        var sbStat = new StringBuilder(
                "<table><tr><td>Тип элемента</td><td>Количество</td></tr>\n");
        counter.forEach((k, v) -> sbStat.append("<tr><td>" +
                k + "</td><td>" + v + "</td></tr>\n"));
        sbStat.append("</table>\n");
        return sbStat.toString() + sb.toString();

    }

    public void traverseTree(PersistingObject persistingObject,
                             StringBuilder sb,
                             Map<Integer, Integer> counter) {

        int type = persistingObject.getObjectType();
        counter.merge(type, 1, Integer::sum);

        // traverse children
        var list = objectService.findByParentObjectId(persistingObject.getId());
        int childCount = list.size();
        if (childCount == 0) {
            // no child
        } else {
            sb.append("<ul>\n");
//            <b>").append(getObjectAsText(persistingObject)).append("</b>
            for (int i = 0; i < childCount; i++) {
                var child = list.get(i);
                sb.append("<li>" + getObjectAsText(child) + "</li>\n");
                traverseTree(child, sb, counter);
            }
            sb.append("</ul>\n");
        }
    }

    private String getObjectAsText(PersistingObject persistingObject) {
        if (persistingObject.getMyJsonData().size() == 0) {
            return persistingObject.getUid().toString();
        } else {
            return persistingObject.getMyJsonData().get("name");
        }
    }

}
