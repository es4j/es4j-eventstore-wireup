package com.lingona.es4j.wireup;

import com.lingona.eventstore.joliver.api.IPipelineHook;
import com.lingona.eventstore.joliver.api.IStoreEvents;
import com.lingona.eventstore.joliver.api.Serialization.ISerialize;
import com.lingona.eventstore.joliver.core.Persistence.InMemoryPersistence.InMemoryPersistenceEngine;
import com.lingona.eventstore.joliver.core.system.TransactionScopeOption;
import com.lingona.eventstore.joliver.persistence.sqlpersistence.ConfigurationConnectionFactory;
import java.util.LinkedList;
import java.util.List;
/*
using System.Collections.Generic;
using System.Linq;
using System.Transactions;
using Conversion;
using Dispatcher;
using Persistence;
using Persistence.InMemoryPersistence;
*/


public class Wireup {

    protected final Wireup        inner;
    protected final NanoContainer container;

    protected Wireup(NanoContainer container) {
        this.container = container;
        this.inner     = null;
    }

    protected Wireup(Wireup inner) {
        this.container = null;
        this.inner     = inner;
    }

    public static Wireup init() {
        NanoContainer container = new NanoContainer();

        container.register(TransactionScopeOption.SUPPRESS);
        container.register/*<IPersistStreams>*/( new InMemoryPersistenceEngine() );
	//container.register(buildEventStore);

        return new Wireup(container);
    }

    protected NanoContainer getContainer() {
      //return this.container ?? this.inner.Container;
        return (this.container != null)? this.container : this.inner.container;
    }

    public <T> Wireup with/*<T>*/(T instance) /*where T : class*/ { // virtual
        this.container.register(instance);
        return this;
    }

    public Wireup hookIntoPipelineUsing(Iterable<IPipelineHook> hooks) { // virtual
        return this.hookIntoPipelineUsing(((hooks != null)? hooks : new LinkedList<IPipelineHook>())/*.ToArray()*/);
    }

    public Wireup hookIntoPipelineUsing(IPipelineHook... hooksArg) { //virtual
        List<IPipelineHook> hooks = new LinkedList<IPipelineHook>();
        for(int index = 0; index < hooksArg.length; index++) {
            hooks.add(index, hooksArg[index]);
        }

        //Set<IPipelineHook> collection = ((hooks == null)?hooks : new IPipelineHook[]{}).Where(x =  > x != null).ToArray();
        List<IPipelineHook> collection = (hooks != null)? hooks :  new LinkedList<IPipelineHook>();
        for(IPipelineHook x : hooks) {
            if(x != null) {
                collection.add(x);
            }
        }

        this.container.register(collection);
        return this;
    }

    public IStoreEvents build() { // virtual
        if (this.inner != null) {
            return this.inner.build();
        }
        return this.container.resolve/*<IStoreEvents>*/ ();
    }
/*
    private static IStoreEvents buildEventStore(NanoContainer context) {
        var scopeOption = context.resolve<TransactionScopeOption>();
        var concurrency = scopeOption == TransactionScopeOption.SUPPRESS ? new OptimisticPipelineHook() : null;
			var scheduler = new DispatchSchedulerPipelineHook(context.Resolve<IScheduleDispatches>());
			var upconverter = context.Resolve<EventUpconverterPipelineHook>();

			var hooks = context.resolve<ICollection<IPipelineHook>>() ?? new IPipelineHook[0];
			hooks = new IPipelineHook[] { concurrency, scheduler, upconverter }
				.Concat(hooks)
				.Where(x => x != null)
				.ToArray();

        return new OptimisticEventStore(context.resolve<IPersistStreams>(), hooks);
    }
*/

    public Wireup logToConsoleWindow() {
        return this; //wireup.logTo(type => new ConsoleWindowLogger(type));
    }

    public Wireup logToOutputWindow() {
        return this; //wireup.logTo(type => new OutputWindowLogger(type));
    }

    public Wireup logTo(/*Func<Type, ILog> logger*/) {
        //LogFactory.setBuildLogger() = logger;
        //return wireup;
        return this;
    }

    public PersistenceWireup usingInMemoryPersistence() {
        this.with/*<IPersistStreams>*/(new InMemoryPersistenceEngine());
	return new PersistenceWireup(this);
    }

    public int records(int records) {
        return records;
    }


    public SqlPersistenceWireup usingSqlPersistence(String connectionName) {
        ConfigurationConnectionFactory factory = new ConfigurationConnectionFactory(connectionName);
        return this.usingSqlPersistence(factory);
    }

    public SqlPersistenceWireup usingSqlPersistence(String masterConnectionName, String replicaConnectionName) {
        ConfigurationConnectionFactory factory = new ConfigurationConnectionFactory(masterConnectionName, replicaConnectionName, 1);
        return this.usingSqlPersistence(factory);
    }

    public SqlPersistenceWireup usingSqlPersistence(IConnectionFactory factory) {
        return new SqlPersistenceWireup(this, factory);
    }

    public SerializationWireup usingBinarySerialization() {
        assert(false); return null; // this.UsingCustomSerialization(new BinarySerializer());
    }

    public SerializationWireup UsingCustomSerialization(ISerialize serializer) {
        return new SerializationWireup(this, serializer);
    }
}
