package com.suportedisciplinado.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "knowledge_base_tag")
@Getter
@Setter
@NoArgsConstructor
public class KnowledgeBaseTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    public KnowledgeBaseTag(Long id, String description) {
        this.id = id;
        this.description = description;
    }
}
