package at.htl.jeopardized.business;

import at.htl.jeopardized.category.Category;
import at.htl.jeopardized.category.CategoryDao;
import at.htl.jeopardized.clue.Clue;
import at.htl.jeopardized.clue.ClueDao;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ApplicationScoped
public class InitBean {

    @Inject
    @RestClient
    JeopardyService jeopardyService;

    @Inject
    CategoryDao categoryDao;

    @Inject
    ClueDao clueDao;
    @Transactional
    public void init(/*@Observes StartupEvent event*/){
        System.err.println("hello from InitBean.init");

        for(int i =0;i<4;i++){
            JsonArray categoriesJson = jeopardyService.getCategories(5,i*5);
            for (JsonValue categoryJson: categoriesJson) {
                Category category = new Category(categoryJson.asJsonObject().getString("title"));
                categoryDao.persist(category);
            }
        }
        List<Category> categories = categoryDao.getAll();
        for (Category category :categories) {
            for(int i = 0;i<3;i++){
                JsonArray cluesJson = jeopardyService.getClues(category.getId().intValue(),i*100);
                for (JsonValue clueJson: cluesJson) {
                    JsonObject clueJsonObject = clueJson.asJsonObject();
                    String answer = clueJsonObject.getString("answer");
                    String question = clueJsonObject.getString("question");
                    int value = (clueJsonObject.isNull("value")) ? 0:clueJsonObject.getInt("value");
                    LocalDate airdate = LocalDateTime.parse(clueJsonObject.getString("airdate"),DateTimeFormatter.ISO_DATE_TIME).toLocalDate();
                    Clue clue = new Clue(answer,question,value,category,airdate);
                    clueDao.persist(clue);
                }
            }
        }
    }
}
