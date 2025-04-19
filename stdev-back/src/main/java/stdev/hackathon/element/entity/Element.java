package stdev.hackathon.element.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long elementId;

    private String elementName;

    @Column(columnDefinition = "TEXT")
    private String elementDescription;

    @Column(columnDefinition = "TEXT")
    private String elementCharacteristics;

    private String elementUrl1;

    private String elementUrl2;


}
