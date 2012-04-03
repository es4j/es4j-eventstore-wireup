package com.lingona.es4j.wireup;

import com.lingona.eventstore.joliver.api.IStoreEvents;
import com.lingona.eventstore.joliver.api.Persistence.IPersistStreams;
import com.lingona.eventstore.joliver.api.Serialization.ISerialize;
//import com.lingona.eventstore.joliver.core.Persistence.InMemoryPersistence.InMemoryPersistenceEngine;
import com.lingona.eventstore.joliver.core.persistence.inmemory.InMemoryPersistenceEngine;
import com.lingona.eventstore.joliver.core.system.TransactionScopeOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//using System.Transactions;
//using Persistence;
//using Serialization;


public class PersistenceWireup extends Wireup {
    private static final Logger logger = LoggerFactory.getLogger(PersistenceWireup.class);
    private boolean initialize;

    public PersistenceWireup(Wireup inner) {
        super(inner);
        this.container.register(TransactionScopeOption.SUPPRESS);
    }

    public PersistenceWireup withPersistence(IPersistStreams instance) { //virtual
        logger.debug(Messages.RegisteringPersistenceEngine, instance.getClass().getName());
        this.with(instance);
        return this;
    }

    protected SerializationWireup withSerializer(ISerialize serializer) { //virual
        return new SerializationWireup(this, serializer);
    }

    public PersistenceWireup initializeStorageEngine() { // virutal
        logger.debug(Messages.ConfiguringEngineInitialization);
        this.initialize = true;
        return this;
    }

    public PersistenceWireup enlistInAmbientTransaction() { // virual
        logger.debug(Messages.ConfiguringEngineEnlistment);
        this.container.register(TransactionScopeOption.REQUIRED);
        return this;
    }

    @Override
    public IStoreEvents build() {
        logger.debug(Messages.BuildingEngine);
        IPersistStreams engine = this.container.resolve/*<IPersistStreams>*/();

        if (this.initialize) {
            logger.debug(Messages.InitializingEngine);
            engine.initialize();
        }

        return super.build();
    }

    @Override
    public PersistenceWireup usingInMemoryPersistence() {
        this.with/*<IPersistStreams>*/(new InMemoryPersistenceEngine());
        return new PersistenceWireup(this);
    }

    @Override
    public int records(int records) {
        return records;
    }
}