package at.htl.jeopardized.category;

import at.htl.jeopardized.clue.Clue;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Category.findAll",query = "Select c from Category c"),
        @NamedQuery(name = "Category.findById",query = "Select c from Category c where c.id = :Id"),
        @NamedQuery(name = "Category.averageValue",query = "Select avg(c.value) from Clue c where c.category.id = :category or :category = -1")

})
public class Category {

    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    public String getTitle() {
        return title;
    }

    String title;

    @OneToMany(mappedBy = "category",cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    List<Clue> clues;

    public Category(String title)  {
        this();
        this.title = title;
    }

    public Category() {
        this.clues = new ArrayList<>();
    }
}
