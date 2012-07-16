package org.es4j.eventstore.wireup;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.es4j.generics.NanoContainer;
import org.es4j.generics.Resolver;
import org.es4j.dotnet.TransactionScopeOption;
import org.es4j.eventstore.api.IPipelineHook;
import org.es4j.eventstore.api.IStoreEvents;
import org.es4j.eventstore.api.dispatcher.IScheduleDispatches;
import org.es4j.eventstore.api.persistence.IPersistStreams;
import org.es4j.eventstore.core.DispatchSchedulerPipelineHook;
import org.es4j.eventstore.core.OptimisticEventStore;
import org.es4j.eventstore.core.OptimisticPipelineHook;
import org.es4j.eventstore.core.conversion.EventUpconverterPipelineHook;
import org.es4j.eventstore.core.persistence.inmemory.InMemoryPersistenceEngine;


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

        container.register(TransactionScopeOption.Suppress);
        container.register((IPersistStreams) new InMemoryPersistenceEngine());
        container.register(new Resolver() {

            @Override
            public IStoreEvents resolve(NanoContainer context) {
                TransactionScopeOption        scopeOption = context.resolve(TransactionScopeOption.class);
                OptimisticPipelineHook        concurrency = scopeOption == TransactionScopeOption.Suppress ? new OptimisticPipelineHook() : null;
                DispatchSchedulerPipelineHook scheduler;
                IScheduleDispatches scheduleDispatches = context.resolve(IScheduleDispatches.class);
                scheduler = new DispatchSchedulerPipelineHook(scheduleDispatches);
                EventUpconverterPipelineHook  upconverter = context.resolve(EventUpconverterPipelineHook.class);

                List<IPipelineHook> hooks = context.resolve(IPipelineHook.class);
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

    public <T> Wireup with(T instance) { // where T : class
        this.container.register(instance);
        return this;
    }

    public Wireup hookIntoPipelineUsing(IPipelineHook... hooks) {
        return this.hookIntoPipelineUsing(Arrays.asList(hooks));
    }
    public Wireup hookIntoPipelineUsing(Iterable<IPipelineHook> hooks) {
        List<IPipelineHook> collection = new LinkedList<IPipelineHook>();
        if (hooks != null) {
            for (IPipelineHook hook : hooks) {
                if (hook != null) {
                    collection.add(hook);
                }
            }
        }
        this.getContainer().register(collection);
        return this;
    }

    public IStoreEvents build() {
        if (this.inner != null)
            return this.inner.build();

        return this.getContainer().resolve/*<IStoreEvents>*/(IStoreEvents.class);
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