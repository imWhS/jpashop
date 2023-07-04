package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"), //중간 테이블에 생성되어질 Category의 외래 키 컬럼 이름
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList();

    /*
    양방향 연관 관계 설정 편의 메서드
     */

    public void addChildCategory(Category category) {
        this.child.add(category);
        category.setParent(this);
    }

    public void addItem(Item item) {
        this.getItems().add(item);
        item.getCategories().add(this);
    }

}
