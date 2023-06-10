package com.snapped.web.persistance.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "photo_detail")
@Data
public class PhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "category")
    private String category;
    @Column(name = "link")
    private String link;
    @Column(name = "description")
    private String description;
    @Column(name = "orientation")
    private String orientation;

    @Column(name = "cloud_id")
    private String cloudId;



}
