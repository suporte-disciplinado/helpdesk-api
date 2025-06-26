package com.suportedisciplinado.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "knowledge_base_category")
@Getter
@Setter
@NoArgsConstructor
public class KnowledgeBaseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    public KnowledgeBaseCategory(Long id, String description) {
        this.id = id;
        this.description = description;
    }

}
