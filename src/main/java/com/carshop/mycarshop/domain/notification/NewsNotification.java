package com.carshop.mycarshop.domain.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="NewsNotification")
@Setter
public class NewsNotification extends Notification{

    @Column(name="target", length = 30, nullable = false)
    private String newsTarget;

    public void changeTarget(String newsTarget){
        this.newsTarget = newsTarget;
    }
}