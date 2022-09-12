package com.fevertime.coinvillage.domain.account;

import com.fevertime.coinvillage.domain.BaseEntity;
import com.fevertime.coinvillage.domain.Country;
import com.fevertime.coinvillage.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Stock extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    private String content;

    private String description;

    private Long count;

    private Long price;

    private Long stockTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public void update(String content, String description, Long price) {
        this.content = content;
        this.description = description;
        this.price = price;
    }
}