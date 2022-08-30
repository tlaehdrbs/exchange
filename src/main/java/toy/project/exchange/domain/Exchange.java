package toy.project.exchange.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String basDate; // 기준일자

    private String curUnit; // 국가코드 USD(미국)

    private String dealBasR; // 매매 기준율

    private String ttb; // 전신환(송금) 받을때
}
