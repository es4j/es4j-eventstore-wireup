package org.es4j.eventstore.wireup;

import org.es4j.eventstore.api.dispatcher.IDispatchCommits;
import org.es4j.eventstore.api.dispatcher.IScheduleDispatches;
import org.es4j.eventstore.api.persistence.IPersistStreams;
import org.es4j.eventstore.core.dispatcher.NullDispatcher;
import org.es4j.eventstore.core.dispatcher.SynchronousDispatchScheduler;
import org.es4j.util.logging.ILog;
import org.es4j.util.logging.LogFactory;


public class SynchronousDispatchSchedulerWireup extends Wireup {
    private static final ILog logger = LogFactory.buildLogger(SynchronousDispatchSchedulerWireup.class);

    public SynchronousDispatchSchedulerWireup(Wireup wireup, IDispatchCommits dispatcher) {
        super(wireup);
	logger.debug(Messages.syncDispatchSchedulerRegistered());
        
	this.dispatchTo((dispatcher!=null)? dispatcher : new NullDispatcher());
        
        IDispatchCommits    dispatchCommits    = this.getContainer().resolve(IDispatchCommits.class);
        IPersistStreams     persistStreams     = this.getContainer().resolve(IPersistStreams.class);
        IScheduleDispatches scheduleDispatches = new SynchronousDispatchScheduler(dispatchCommits, persistStreams);
        
	this.getContainer().register(scheduleDispatches);
    }
    
    public SynchronousDispatchSchedulerWireup dispatchTo(IDispatchCommits instance) {
        logger.debug(Messages.dispatcherRegistered(), instance.getClass().getName());
        this.getContainer().register(instance);
        return this;
    }    
}












/*
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


public class SynchronousDispatchSchedulerWireup extends Wireup_2 {
    private static final Logger logger = LoggerFactory.getLogger(SynchronousDispatchSchedulerWireup.class);

    public SynchronousDispatchSchedulerWireup(Wireup_2 wireup, IDispatchCommits dispatcher) {
        super(wireup);
	logger.debug(Messages_2.SyncDispatchSchedulerRegistered);
	this.dispatchTo((dispatcher!=null)? dispatcher : new NullDispatcher());
        IDispatchCommits    dispatchCommits    = this.container.resolve();
        IPersistStreams     persistStreams     = this.container.resolve();
        IScheduleDispatches scheduleDispatches = new SynchronousDispatchScheduler(dispatchCommits, persistStreams);
        
	this.container.register(scheduleDispatches);
    }

    public SynchronousDispatchSchedulerWireup dispatchTo(IDispatchCommits instance) {
        logger.debug(Messages_2.DispatcherRegistered, instance.getClass().getName());
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
*/