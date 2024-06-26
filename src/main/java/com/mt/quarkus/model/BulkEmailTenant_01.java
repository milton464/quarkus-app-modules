/*
 * This file is generated by jOOQ.
 */
package com.mt.quarkus.model;


import java.util.Arrays;
import java.util.List;

import com.mt.quarkus.model.tables.Campaigns;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BulkEmailTenant_01 extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>bulk_email_tenant_01</code>
     */
    public static final BulkEmailTenant_01 BULK_EMAIL_TENANT_01 = new BulkEmailTenant_01();

    

    /**
     * The table <code>bulk_email_tenant_01.campaigns</code>.
     */
    public final Campaigns CAMPAIGNS = Campaigns.CAMPAIGNS;

   

    /**
     * No further instances allowed
     */
    private BulkEmailTenant_01() {
        super("bulk_email_tenant_01", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            
            Campaigns.CAMPAIGNS
            
        );
    }
}
