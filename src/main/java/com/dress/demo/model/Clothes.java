package com.dress.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "clothes")
public class Clothes {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String gender;
    @Column
    private String type;
    @Column
    private String color_main;
    @Column
    private String color_one;
    @Column
    private String color_two;
    @Column
    private String size;
    @Column
    private String age_level;
    @Column
    private String event;
    @Column
    private String image_path;
    @Column
    private int layer;

    //--------Many To One connection to user table
    @JsonIgnore
    @ManyToOne
    private User user;

    public Clothes() {
    }

    public Clothes(Long id, String name, String gender, String type, String color_main, String color_one, String color_two, String size, String age_level, String event, String image_path, int layer) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.type = type;
        this.color_main = color_main;
        this.color_one = color_one;
        this.color_two = color_two;
        this.size = size;
        this.age_level = age_level;
        this.event = event;
        this.image_path = image_path;
        this.layer = layer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor_main() {
        return color_main;
    }

    public void setColor_main(String color_main) {
        this.color_main = color_main;
    }

    public String getColor_one() {
        return color_one;
    }

    public void setColor_one(String color_one) {
        this.color_one = color_one;
    }

    public String getColor_two() {
        return color_two;
    }

    public void setColor_two(String color_two) {
        this.color_two = color_two;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAge_level() {
        return age_level;
    }

    public void setAge_level(String age_level) {
        this.age_level = age_level;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Clothes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", type='" + type + '\'' +
                ", color_main='" + color_main + '\'' +
                ", color_one='" + color_one + '\'' +
                ", color_two='" + color_two + '\'' +
                ", size='" + size + '\'' +
                ", age_level='" + age_level + '\'' +
                ", event='" + event + '\'' +
                ", image_path='" + image_path + '\'' +
                ", layer=" + layer +
                '}';
    }
}
