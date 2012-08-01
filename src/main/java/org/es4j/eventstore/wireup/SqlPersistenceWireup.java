package org.es4j.eventstore.wireup;

import org.es4j.container.NanoContainer;
import org.es4j.container.Resolver;
//import org.es4j.dotnet.IConnectionFactory;
import org.es4j.dotnet.TransactionScopeOption;
import org.es4j.logging.api.ILog;
import org.es4j.logging.api.LogFactory;
import org.es4j.persistence.sql.api.IConnectionFactory;
import org.es4j.persistence.sql.api.ISqlDialect;
import org.es4j.persistence.sql.api.SqlPersistenceFactory;
import org.es4j.serialization.api.ISerialize;

//using System.Transactions;
//using Logging;
//using Persistence.SqlPersistence;
//using Serialization;

public class SqlPersistenceWireup extends PersistenceWireup {
    private static final ILog logger = LogFactory.buildLogger(SqlPersistenceWireup.class);

    private static final int defaultPageSize = 512;
    private              int pageSize        = defaultPageSize;

    public SqlPersistenceWireup(final Wireup wireup, final IConnectionFactory connectionFactory) {
        super(wireup);
        logger.debug(Messages.ConnectionFactorySpecified, connectionFactory);

        logger.verbose(Messages.AutoDetectDialect);
        this.getContainer().register(new Resolver<ISqlDialect>() {

            @Override
            public ISqlDialect resolve(NanoContainer container) {
                return null; // auto-detect               
            }
        });
        
        final ISerialize             serializer = this.getContainer().resolve(ISerialize.class);
        final ISqlDialect            dialect    = this.getContainer().resolve(ISqlDialect.class);
        final TransactionScopeOption scope      = this.getContainer().resolve(TransactionScopeOption.class);
        final int                    pageSize   = this.pageSize;
        this.getContainer().register(new Resolver<SqlPersistenceFactory>() {

            @Override
            public SqlPersistenceFactory resolve(NanoContainer container) {
                SqlPersistenceFactory factory = new SqlPersistenceFactory(
				connectionFactory,
				serializer,
				dialect,
				scope,
				pageSize);
               //.build()) {};
                return factory;
            }
        });
    }

    public SqlPersistenceWireup withDialect(ISqlDialect instance) {
        logger.debug(Messages.DialectSpecified, instance.getClass().getName());
        this.getContainer().register(instance);
        return this;
    }

    public SqlPersistenceWireup PageEvery(int records) {
        logger.debug(Messages.PagingSpecified, records);
        this.pageSize = records;
        return this;
    }
}