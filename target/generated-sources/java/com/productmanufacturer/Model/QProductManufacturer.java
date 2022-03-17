package com.productmanufacturer.Model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductManufacturer is a Querydsl query type for ProductManufacturer
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProductManufacturer extends EntityPathBase<ProductManufacturer> {

    private static final long serialVersionUID = -1802572800L;

    public static final QProductManufacturer productManufacturer = new QProductManufacturer("productManufacturer");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Integer> avaliableStock = createNumber("avaliableStock", Integer.class);

    //inherited
    public final DateTimePath<java.sql.Timestamp> createdDate = _super.createdDate;

    public final StringPath description = createString("description");

    public final DateTimePath<java.util.Date> expDate = createDateTime("expDate", java.util.Date.class);

    public final ComparablePath<Character> isActive = createComparable("isActive", Character.class);

    public final DateTimePath<java.util.Date> MfgDate = createDateTime("MfgDate", java.util.Date.class);

    public final StringPath modelNo = createString("modelNo");

    public final StringPath productbrandName = createString("productbrandName");

    public final StringPath productCode = createString("productCode");

    public final StringPath productName = createString("productName");

    public final NumberPath<Double> productprice = createNumber("productprice", Double.class);

    public final StringPath serialNo = createString("serialNo");

    public final NumberPath<Integer> totalStock = createNumber("totalStock", Integer.class);

    //inherited
    public final DateTimePath<java.sql.Timestamp> updatedDate = _super.updatedDate;

    public QProductManufacturer(String variable) {
        super(ProductManufacturer.class, forVariable(variable));
    }

    public QProductManufacturer(Path<? extends ProductManufacturer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductManufacturer(PathMetadata metadata) {
        super(ProductManufacturer.class, metadata);
    }

}

