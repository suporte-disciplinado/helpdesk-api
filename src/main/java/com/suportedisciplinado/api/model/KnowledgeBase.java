package com.suportedisciplinado.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "knowledge_base")
@Getter
@Setter
@NoArgsConstructor
public class KnowledgeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private KnowledgeBaseCategory category;

    @Column
    private String description;

    @Column(columnDefinition = "TEXT")
    private String annotation;

    @ManyToMany
    @JoinTable(
            name = "knowledge_base_tag_relation",
            joinColumns = @JoinColumn(name = "knowledge_base_id"),
            inverseJoinColumns = @JoinColumn(name = "knowledge_base_tag_id")
    )
    private Set<KnowledgeBaseTag> tags;

    @Column
    private String author;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
