package com.lingona.es4j.wireup;

import com.lingona.eventstore.joliver.api.Serialization.ISerialize;
import com.lingona.eventstore.joliver.core.system.TransactionScopeOption;
import com.lingona.eventstore.joliver.persistence.sqlpersistence.IConnectionFactory;
import com.lingona.eventstore.joliver.persistence.sqlpersistence.ISqlDialect;
import com.lingona.eventstore.joliver.persistence.sqlpersistence.SqlPersistenceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//using System.Transactions;
//using Persistence.SqlPersistence;
//using Serialization;


public class SqlPersistenceWireup extends PersistenceWireup {

    private static final Logger logger = LoggerFactory.getLogger(SqlPersistenceWireup.class);

    private final static int DefaultPageSize = 512;
    private int pageSize = DefaultPageSize;

    public SqlPersistenceWireup(Wireup wireup, IConnectionFactory connectionFactory) {
        super(wireup);
        logger.debug(Messages.ConnectionFactorySpecified, connectionFactory);

        logger.info(Messages.AutoDetectDialect); //Verbose
        
        ISqlDialect sqlDialect = null;
        this.container.register((ISqlDialect)null); // auto-detect
        
        
        ISerialize  serializer = this.container.resolve();
        ISqlDialect dialect    = this.container.resolve();
        TransactionScopeOption scopeOption = this.container.resolve();
        
        SqlPersistenceFactory sqlPersistFactory = new SqlPersistenceFactory(connectionFactory,
				                                            serializer,
				                                            dialect,
				                                            scopeOption,
				                                            this.pageSize);
                //.Build());

			this.container.register(c => new SqlPersistenceFactory(
				connectionFactory,
				c.resolve<ISerialize>(),
				c.resolve<ISqlDialect>(),
				c.resolve<TransactionScopeOption>(),
				this.pageSize).build());
        }

    public SqlPersistenceWireup withDialect(ISqlDialect instance) { //virtual
        logger.debug(Messages.DialectSpecified, instance.getClass().getName());
        this.container.register(instance);
        return this;
    }

    public SqlPersistenceWireup pageEvery(int records) { // virtual
        logger.debug(Messages.PagingSpecified, records);
        this.pageSize = records;
        return this;
    }

}