package org.es4j.eventstore.wireup;

import org.es4j.dotnet.data.TransactionScopeOption;
import org.es4j.eventstore.api.IStoreEvents;
import org.es4j.eventstore.api.persistence.IPersistStreams;
import org.es4j.eventstore.core.diagnostics.PerformanceCounterPersistenceEngine;
import org.es4j.exceptions.ArgumentNullException;
import org.es4j.serialization.BinarySerializer;
import org.es4j.serialization.BsonSerializer;
import org.es4j.serialization.JsonSerializer;
import org.es4j.serialization.api.ISerialize;
import org.es4j.util.logging.ILog;
import org.es4j.util.logging.LogFactory;

//using System.Transactions;
//using Persistence;
//using Serialization;

public class PersistenceWireup extends Wireup {
    private static final ILog logger = LogFactory.buildLogger(PersistenceWireup.class);
    private boolean initialize;
    private boolean tracking;
    private String trackingInstanceName;

    public PersistenceWireup(Wireup inner) {
        super(inner);
        this.getContainer().register(TransactionScopeOption.Suppress);
    }

    public PersistenceWireup withPersistence(IPersistStreams instance) {
        logger.debug(Messages.RegisteringPersistenceEngine, instance.getClass().getName());
        this.with(instance);
        return this;
    }
    
    protected SerializationWireup withSerializer(ISerialize serializer) {
        return new SerializationWireup(this, serializer);
    }
    
    public PersistenceWireup initializeStorageEngine() {
        logger.debug(Messages.ConfiguringEngineInitialization);
        this.initialize = true;
        return this;
    }
    
    public PersistenceWireup trackPerformanceInstance(String instanceName) {
        if (instanceName == null) {
            throw new ArgumentNullException("instanceName", Messages.InstanceCannotBeNull);
        }            
        logger.debug(Messages.configuringEnginePerformanceTracking());
        this.tracking = true;
        this.trackingInstanceName = instanceName;
        return this;
    }
    
    public PersistenceWireup enlistInAmbientTransaction() {
        logger.debug(Messages.ConfiguringEngineEnlistment);
        this.getContainer().register(TransactionScopeOption.Required);
        return this;
    }

    @Override
    public IStoreEvents build() {
        logger.debug(Messages.BuildingEngine);
        IPersistStreams engine = this.getContainer().resolve(IPersistStreams.class);

        if (this.initialize) {
            logger.debug(Messages.InitializingEngine);
            engine.initialize();
        }
        if (this.tracking) {
            this.getContainer().register((IPersistStreams)new PerformanceCounterPersistenceEngine(engine, this.trackingInstanceName));
        }
        return super.build();
    }
    
    
    // Serialization Extensions
    
    public SerializationWireup usingJsonSerialization() {
        return this.usingCustomSerialization(new JsonSerializer());
    }

    public SerializationWireup usingBsonSerialization() {
        return this.usingCustomSerialization(new BsonSerializer());
    }
    
    // SerializationExtensionWirewup
    
    public SerializationWireup usingBinarySerialization() {
        return this.usingCustomSerialization(new BinarySerializer());
    }

    public SerializationWireup usingCustomSerialization(ISerialize serializer) {
        return new SerializationWireup(this, serializer);
    }    
}


/*
public class PersistenceWireup extends Wireup {
    private static final ILog logger = LogFactory.buildLogger(PersistenceWireup.class);
    private boolean initialize;

    public PersistenceWireup(Wireup inner) {
        super(inner);
        this.getContainer().register(TransactionScopeOption.SUPPRESS);
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
        this.getContainer().register(TransactionScopeOption.REQUIRED);
        return this;
    }

    @Override
    public IStoreEvents build() {
        logger.debug(Messages.BuildingEngine);
        IPersistStreams engine = this.getContainer().resolve(IPersistStreams.class);

        if (this.initialize) {
            logger.debug(Messages_2.InitializingEngine);
            engine.initialize();
        }

        return super.build();
    }

    // Extensions for PersistenceWireup
    
    public PersistenceWireup usingInMemoryPersistence() {
        this.with((IPersistStreams)new InMemoryPersistenceEngine());
        return new PersistenceWireup(this);
    }

    public int records(int records) {
        return records;
    }
}
*/