package org.es4j.eventstore.wireup;

import java.util.concurrent.ExecutorService;
import org.es4j.dotnet.TransactionScopeOption;
import org.es4j.eventstore.api.dispatcher.IDispatchCommits;
import org.es4j.eventstore.api.dispatcher.IScheduleDispatches;
import org.es4j.eventstore.api.persistence.IPersistStreams;
import org.es4j.eventstore.core.dispatcher.AsynchronousDispatchScheduler;
import org.es4j.eventstore.core.dispatcher.NullDispatcher;
import org.es4j.container.NanoContainer;
import org.es4j.container.Resolver;
import org.es4j.logging.api.ILog;
import org.es4j.logging.api.LogFactory;


public class AsynchronousDispatchSchedulerWireup extends Wireup {
    private static final ILog logger = LogFactory.buildLogger(AsynchronousDispatchSchedulerWireup.class);

    public AsynchronousDispatchSchedulerWireup(Wireup wireup, IDispatchCommits dispatcher) {
        super(wireup);
		
        TransactionScopeOption option = this.getContainer().resolve(TransactionScopeOption.class);
        if (option != TransactionScopeOption.SUPPRESS) {
            logger.warn(Messages.synchronousDispatcherTwoPhaseCommits());
        }

        logger.debug(Messages.asyncDispatchSchedulerRegistered());
        this.dispatchTo(dispatcher!=null? dispatcher : new NullDispatcher());     
        this.getContainer().register(new Resolver<IScheduleDispatches>() {

            @Override
            public IScheduleDispatches resolve(NanoContainer c) {
                ExecutorService  threadPool = null;
                return new AsynchronousDispatchScheduler(c.resolve(IDispatchCommits.class), 
                                                         c.resolve(IPersistStreams.class),
                                                         threadPool);
            }
        });
    }

    public AsynchronousDispatchSchedulerWireup dispatchTo(final IDispatchCommits instance) {
        logger.debug(Messages.dispatcherRegistered(), instance.getClass());
      //this.getContainer().register(instance);
        this.getContainer().register(new Resolver<IDispatchCommits>() {

            @Override
            public IDispatchCommits resolve(NanoContainer container) {
                return instance;
            }
        });
        return this;
    }
}

/*
namespace EventStore
{
	using System.Transactions;
	using Dispatcher;
	using Logging;
	using Persistence;

	public class AsynchronousDispatchSchedulerWireup : Wireup
	{
		private static readonly ILog Logger = LogFactory.BuildLogger(typeof(AsynchronousDispatchSchedulerWireup));

		public AsynchronousDispatchSchedulerWireup(Wireup wireup, IDispatchCommits dispatcher)
			: base(wireup)
		{
			var option = this.Container.Resolve<TransactionScopeOption>();
			if (option != TransactionScopeOption.Suppress)
				Logger.Warn(Messages.SynchronousDispatcherTwoPhaseCommits);

			Logger.Debug(Messages.AsyncDispatchSchedulerRegistered);
			this.DispatchTo(dispatcher ?? new NullDispatcher());
			this.Container.Register<IScheduleDispatches>(c => new AsynchronousDispatchScheduler(
				c.Resolve<IDispatchCommits>(), c.Resolve<IPersistStreams>()));
		}

		public AsynchronousDispatchSchedulerWireup DispatchTo(IDispatchCommits instance)
		{
			Logger.Debug(Messages.DispatcherRegistered, instance.GetType());
			this.Container.Register(instance);
			return this;
		}
	}
}
*/
