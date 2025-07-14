package com.satish.journal.entity;


import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@Data
@Document(collection = "journal_entrise")
public class Journal {

    @Id
    private ObjectId id;
    @NonNull
    private String title ;
    private  String content;
    private LocalDateTime date;






}
