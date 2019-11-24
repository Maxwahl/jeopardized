package at.htl.jeopardized.clue;

import at.htl.jeopardized.category.Category;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NamedQueries({
        @NamedQuery(name = "Clue.findAll",query = "Select c from Clue c"),
        @NamedQuery(name = "Clue.findById",query = "Select c from Clue c where c.id = :Id")})
public class Clue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String answer;

    private String question;

    int value;
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private Category category;
    LocalDate airdate;

    public Clue(String answer, String question, int value, Category category, LocalDate airdate) {
        this.answer = answer;
        this.question = question;
        this.value = value;
        this.category = category;
        this.airdate = airdate;
    }

    public Clue() {
    }


    public Long getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    public int getValue() {
        return value;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDate getAirdate() {
        return airdate;
    }
}
