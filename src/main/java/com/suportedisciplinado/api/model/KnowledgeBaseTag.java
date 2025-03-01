package com.suportedisciplinado.api.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "knowledge_base_tag")
public class KnowledgeBaseTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @ManyToMany(mappedBy = "tags")
    private Set<KnowledgeBase> knowledgeBase;
}
