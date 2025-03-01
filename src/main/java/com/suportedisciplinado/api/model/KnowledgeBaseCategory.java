package com.suportedisciplinado.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "knowledge_base_category")
public class KnowledgeBaseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;
}
