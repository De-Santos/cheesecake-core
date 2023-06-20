package com.product.service.entity.additional.tag;

import com.product.service.entity.ArchiveProduct;
import com.product.service.entity.DraftProduct;
import com.product.service.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@Entity
@Table(name = "tag_collection")
@NoArgsConstructor
@AllArgsConstructor
public class TagCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Tag> tags;

    @OneToOne
    @JoinColumn(name = "archive_product_id", referencedColumnName = "version_id", unique = true)
    private ArchiveProduct archiveProduct;

    @OneToOne
    @JoinColumn(name = "draft_product_hash", referencedColumnName = "hash", unique = true)
    private DraftProduct draftProduct;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "version_id", unique = true)
    private Product product;
}
