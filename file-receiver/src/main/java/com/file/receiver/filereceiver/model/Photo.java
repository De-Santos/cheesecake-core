package com.file.receiver.filereceiver.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "photos")
public class Photo {
    @Id
    private String id;
    private String title;
    private Binary image;

    public Photo(String title, Binary image) {
        this.title = title;
        this.image = image;
    }
}
