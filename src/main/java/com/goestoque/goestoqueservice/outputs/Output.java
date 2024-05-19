package com.goestoque.goestoqueservice.outputs;

import com.goestoque.goestoqueservice.users.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Set;

@Entity(name = "out_outputs")
@Data
public class Output {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "out_id")
    private long id;

    @CreationTimestamp
    @Column(name = "out_date",
            nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "use_id",
                nullable = false)
    private User user;

    @OneToMany(mappedBy = "output")
    private Set<OutputItem> outputItems;
}
