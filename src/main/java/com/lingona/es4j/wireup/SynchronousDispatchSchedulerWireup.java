package com.lingona.eventstore.joliver.wireup;

import com.lingona.eventstore.joliver.api.Dispatcher.IDispatchCommits;
import com.lingona.eventstore.joliver.api.Dispatcher.IScheduleDispatches;
import com.lingona.eventstore.joliver.api.Persistence.IPersistStreams;
import com.lingona.eventstore.joliver.core.Dispatcher.NullDispatcher;
import com.lingona.eventstore.joliver.core.Dispatcher.SynchronousDispatchScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//using Dispatcher;
//using Logging;
//using Persistence;


public class SynchronousDispatchSchedulerWireup extends Wireup {
    private static final Logger logger = LoggerFactory.getLogger(SynchronousDispatchSchedulerWireup.class);

    public SynchronousDispatchSchedulerWireup(Wireup wireup, IDispatchCommits dispatcher) {
        super(wireup);
	logger.debug(Messages.SyncDispatchSchedulerRegistered);
	this.dispatchTo((dispatcher!=null)? dispatcher : new NullDispatcher());
        IDispatchCommits    dispatchCommits    = this.container.resolve();
        IPersistStreams     persistStreams     = this.container.resolve();
        IScheduleDispatches scheduleDispatches = new SynchronousDispatchScheduler(dispatchCommits, persistStreams);
        
	this.container.register(scheduleDispatches);
    }

    public SynchronousDispatchSchedulerWireup dispatchTo(IDispatchCommits instance) {
        logger.debug(Messages.DispatcherRegistered, instance.getClass().getName());
        this.container.register(instance);
        return this;
    }


    public SynchronousDispatchSchedulerWireup usingSynchronousDispatchScheduler() {
        return this.usingSynchronousDispatchScheduler(null);
    }

    public SynchronousDispatchSchedulerWireup usingSynchronousDispatchScheduler(IDispatchCommits dispatcher) {
        return new SynchronousDispatchSchedulerWireup(this, dispatcher);
    }
}