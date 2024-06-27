package com.goestoque.goestoqueservice.outputs;

import com.goestoque.goestoqueservice.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity(name = "out_outputs")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Output {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "out_id")
    private String id;

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
