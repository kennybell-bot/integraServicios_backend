package com.lazarus.resources.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
import java.util.Map;
import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "resource")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Resource {

    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    private String code;
    private String name;

    @Column(name = "attributes", columnDefinition = "jsonb")
    @Type(JsonType.class)
    private Map<String, Object> attributesJson;


    private String photoUrl;

    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private ResourceLocation location;

    @ManyToOne
    @JoinColumn(name = "resource_type_id", nullable = false)
    private ResourceType type;
}