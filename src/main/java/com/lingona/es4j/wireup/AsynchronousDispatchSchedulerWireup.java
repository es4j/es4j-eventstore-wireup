package com.lingona.eventstore.joliver.wireup;

import com.lingona.eventstore.joliver.api.Dispatcher.IDispatchCommits;
import com.lingona.eventstore.joliver.api.Persistence.IPersistStreams;
import com.lingona.eventstore.joliver.core.Dispatcher.AsynchronousDispatchScheduler;
import com.lingona.eventstore.joliver.core.Dispatcher.NullDispatcher;
import com.lingona.eventstore.joliver.core.system.TransactionScopeOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//using Dispatcher;
//using Logging;
//using Persistence;

public final class AsynchronousDispatchSchedulerWireup extends Wireup {

    private static final Logger logger = LoggerFactory.getLogger(AsynchronousDispatchSchedulerWireup.class);

    public AsynchronousDispatchSchedulerWireup(Wireup wireup, IDispatchCommits dispatcherNew) {
        super(wireup);
        TransactionScopeOption option = this.container.resolve/*<TransactionScopeOption>*/();
        if (option != TransactionScopeOption.SUPPRESS) {
            logger.warn(Messages.SynchronousDispatcherTwoPhaseCommits);
        }

        logger.debug(Messages.AsyncDispatchSchedulerRegistered);
        this.dispatchTo((dispatcherNew != null)? dispatcherNew : new NullDispatcher());

        IPersistStreams  persistStreams = container.resolve/*<IPersistStreams>*/();
        IDispatchCommits dispatcher     = container.resolve();
        AsynchronousDispatchScheduler scheduler = new AsynchronousDispatchScheduler(dispatcher, persistStreams);
        //this.container.register/*<IScheduleDispatches>*/(c => new AsynchronousDispatchScheduler(c.resolve/*<IDispatchCommits>*/(),
        //                                                                                        c.resolve/*<IPersistStreams>*/()));
        this.container.register(scheduler);
    }

    public AsynchronousDispatchSchedulerWireup dispatchTo(IDispatchCommits instance) {
        logger.debug(Messages.DispatcherRegistered, instance.getClass().getName());
        this.container.register(instance);
        return this;
    }

     public AsynchronousDispatchSchedulerWireup usingAsynchronousDispatchScheduler() {
        return this.usingAsynchronousDispatchScheduler(null);
    }

    public AsynchronousDispatchSchedulerWireup usingAsynchronousDispatchScheduler(IDispatchCommits dispatcher) {
        return new AsynchronousDispatchSchedulerWireup(this, dispatcher);
    }
}