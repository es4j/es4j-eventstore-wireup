package org.es4j.eventstore.wireup;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.es4j.container.NanoContainer;
import org.es4j.container.Resolver;
//import org.es4j.dotnet.ConfigurationConnectionFactory;
//import org.es4j.dotnet.IConnectionFactory;
import org.es4j.dotnet.TransactionScopeOption;
import org.es4j.eventstore.api.IPipelineHook;
import org.es4j.eventstore.api.IStoreEvents;
import org.es4j.eventstore.api.dispatcher.IDispatchCommits;
import org.es4j.eventstore.api.dispatcher.IScheduleDispatches;
import org.es4j.eventstore.api.persistence.IPersistStreams;
import org.es4j.eventstore.core.DispatchSchedulerPipelineHook;
import org.es4j.eventstore.core.OptimisticEventStore;
import org.es4j.eventstore.core.OptimisticPipelineHook;
import org.es4j.eventstore.core.conversion.EventUpconverterPipelineHook;
import org.es4j.eventstore.core.logging.ConsoleWindowLogger;
import org.es4j.eventstore.core.logging.OutputWindowLogger;
import org.es4j.eventstore.core.persistence.inmemory.InMemoryPersistenceEngine;
import org.es4j.logging.api.ILog;
import org.es4j.logging.api.LogFactory;
import org.es4j.logging.api.LoggerDelegate;
import org.es4j.persistence.sql.api.ConfigurationConnectionFactory;
import org.es4j.persistence.sql.api.IConnectionFactory;


public class Wireup {
    
    private final Wireup        inner;
    private final NanoContainer container;

    protected Wireup(NanoContainer container) {
        this.container = container;
        this.inner     = null;
    }
    protected Wireup(Wireup inner) {
        this.inner     = inner;
        this.container = null;
    }

    public static Wireup init() {
        NanoContainer container = new NanoContainer();

      //container.register(TransactionScopeOption.Suppress);
        container.register(new Resolver<TransactionScopeOption>(){

            @Override
            public TransactionScopeOption resolve(NanoContainer container) {
                return TransactionScopeOption.SUPPRESS;
            }
        });
        
      //container.register((IPersistStreams) new InMemoryPersistenceEngine());
        container.register(new Resolver<IPersistStreams>(){

            @Override
            public IPersistStreams resolve(NanoContainer container) {
                return new InMemoryPersistenceEngine();
            }
        });
        
        
        container.register(new Resolver<IStoreEvents>() {

            @Override
            public IStoreEvents resolve(NanoContainer context) {
                TransactionScopeOption        scopeOption = context.resolve(TransactionScopeOption.class);
                OptimisticPipelineHook        concurrency = scopeOption == TransactionScopeOption.SUPPRESS ? new OptimisticPipelineHook() : null;
                DispatchSchedulerPipelineHook scheduler;
                IScheduleDispatches scheduleDispatches = context.resolve(IScheduleDispatches.class);
                scheduler = new DispatchSchedulerPipelineHook(scheduleDispatches);
                EventUpconverterPipelineHook  upconverter = context.resolve(EventUpconverterPipelineHook.class);

                List<IPipelineHook> hooks = context.resolveAll(IPipelineHook.class);
                hooks = (hooks != null)? hooks : new LinkedList<IPipelineHook>();
                
                if(concurrency != null) hooks.add(concurrency);
                if(scheduler   != null) hooks.add(scheduler);
                if(upconverter != null) hooks.add(upconverter);

                IPersistStreams persistStream = context.resolve(IPersistStreams.class);
                return new OptimisticEventStore(persistStream, hooks);
            }
        });

        return new Wireup(container);
    }

    protected NanoContainer getContainer() {
        return (this.container!=null)? this.container 
                                     : this.inner.getContainer();
    }

    public <T> Wireup with(final T instance) { // where T : class
      //this.container.register(instance);
        this.container.register(new Resolver<T>(){

            @Override
            public T resolve(NanoContainer container) {
                //throw new UnsupportedOperationException("Not supported yet.");
                return instance;
            }
        });
        return this;
    }

    public Wireup hookIntoPipelineUsing(IPipelineHook... hooks) {
        return this.hookIntoPipelineUsing(Arrays.asList(hooks));
    }
    
    public Wireup hookIntoPipelineUsing(Iterable<IPipelineHook> hooks) {
        final List<IPipelineHook> collection = new LinkedList<IPipelineHook>();
        if (hooks != null) {
            for (IPipelineHook hook : hooks) {
                if (hook != null) {
                    collection.add(hook);
                }
            }
        }
      //this.getContainer().register(collection);
        this.getContainer().register(new Resolver<List<IPipelineHook>>() {

            @Override
            public List<IPipelineHook> resolve(NanoContainer container) {
                return collection;
            }
        });

        return this;
    }

    public IStoreEvents build() {
        if (this.inner != null) {
            return this.inner.build();
        }
        return this.getContainer().resolve(IStoreEvents.class);
    }
    
    // Extensions for Logger
    
    public Wireup logToConsoleWindow() {
        //return this.logTo(type => new ConsoleWindowLogger(type));
        return this.logTo(new LoggerDelegate() {

            @Override
            public <T> ILog buildLogger(Class<T> typeToLog) {
                return new ConsoleWindowLogger(typeToLog);
            }
        });
    }
                
    public Wireup logToOutputWindow() {
        //return this.logTo(type => new OutputWindowLogger(type));
        return this.logTo(new LoggerDelegate() {

            @Override
            public <T> ILog buildLogger(Class<T> typeToLog) {
                return new OutputWindowLogger(typeToLog);
            }
        });
    }
                
    public Wireup logTo(LoggerDelegate logger) {
        LogFactory.setBuildLogger(logger);
        return this;
    }
    
    // Extensions for EventUpconverter
    
    public EventUpconverterWireup usingEventUpconversion() {
        return new EventUpconverterWireup(this);
    }
    
    // Extensions for SynchronousDispatcher
    
    public SynchronousDispatchSchedulerWireup usingSynchronousDispatchScheduler() {
        return this.usingSynchronousDispatchScheduler(null);
    }

    public SynchronousDispatchSchedulerWireup usingSynchronousDispatchScheduler(IDispatchCommits dispatcher) {
        return new SynchronousDispatchSchedulerWireup(this, dispatcher);
    }
    
    // Extensions for SqlPersistence

    public SqlPersistenceWireup usingSqlPersistence(String connectionName) {
        ConfigurationConnectionFactory factory = new ConfigurationConnectionFactory(connectionName);
        return this.usingSqlPersistence(factory);
    }
    
    public SqlPersistenceWireup usingSqlPersistence(String masterConnectionName, 
                                                    String replicaConnectionName) {
        ConfigurationConnectionFactory factory = new ConfigurationConnectionFactory(masterConnectionName, replicaConnectionName, 1);
        return this.usingSqlPersistence(factory);
    }
    
    public SqlPersistenceWireup usingSqlPersistence(IConnectionFactory factory) {
        return new SqlPersistenceWireup(this, factory);
    }

}


/*
public class Wireup {

    static Wireup init() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    Wireup logToOutputWindow() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    Wireup usingSqlPersistence(String eventStore) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    Wireup enlistInAmbientTransaction() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    Wireup initializeStorageEngine() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    Wireup trackPerformanceInstance(String example) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    Wireup usingJsonSerialization() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    Wireup compress() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    Wireup encryptWith(byte[] encryptionKey) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    Wireup usingSynchronousDispatchScheduler() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    IStoreEvents build() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
*/

/*
namespace EventStore
{
	using System.Collections.Generic;
	using System.Linq;
	using System.Transactions;
	using Conversion;
	using Dispatcher;
	using Persistence;
	using Persistence.InMemoryPersistence;

	public class Wireup
	{
		private readonly Wireup inner;
		private readonly NanoContainer container;

		protected Wireup(NanoContainer container)
		{
			this.container = container;
		}
		protected Wireup(Wireup inner)
		{
			this.inner = inner;
		}

		public static Wireup Init()
		{
			var container = new NanoContainer();

			container.Register(TransactionScopeOption.Suppress);
			container.Register<IPersistStreams>(new InMemoryPersistenceEngine());
			container.Register(BuildEventStore);

			return new Wireup(container);
		}

		protected NanoContainer Container
		{
			get { return this.container ?? this.inner.Container; }
		}

		public virtual Wireup With<T>(T instance) where T : class
		{
			this.Container.Register(instance);
			return this;
		}

		public virtual Wireup HookIntoPipelineUsing(IEnumerable<IPipelineHook> hooks)
		{
			return this.HookIntoPipelineUsing((hooks ?? new IPipelineHook[0]).ToArray());
		}
		public virtual Wireup HookIntoPipelineUsing(params IPipelineHook[] hooks)
		{
			ICollection<IPipelineHook> collection = (hooks ?? new IPipelineHook[] { }).Where(x => x != null).ToArray();
			this.Container.Register(collection);
			return this;
		}

		public virtual IStoreEvents Build()
		{
			if (this.inner != null)
				return this.inner.Build();

			return this.Container.Resolve<IStoreEvents>();
		}

		private static IStoreEvents BuildEventStore(NanoContainer context)
		{
			var scopeOption = context.Resolve<TransactionScopeOption>();
			var concurrency = scopeOption == TransactionScopeOption.Suppress ? new OptimisticPipelineHook() : null;
			var scheduler = new DispatchSchedulerPipelineHook(context.Resolve<IScheduleDispatches>());
			var upconverter = context.Resolve<EventUpconverterPipelineHook>();

			var hooks = context.Resolve<ICollection<IPipelineHook>>() ?? new IPipelineHook[0];
			hooks = new IPipelineHook[] { concurrency, scheduler, upconverter }
				.Concat(hooks)
				.Where(x => x != null)
				.ToArray();

			return new OptimisticEventStore(context.Resolve<IPersistStreams>(), hooks);
		}
	}
}
*/