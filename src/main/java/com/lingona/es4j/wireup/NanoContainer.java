package com.lingona.eventstore.joliver.wireup;

//import com.lingona.eventstore.joliver.api.system.ArgumentException;
//import com.lingona.eventstore.joliver.api.system.ArgumentNullException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//using System.Collections.Generic;
//using Logging;

public class NanoContainer {

    private final static Logger logger = LoggerFactory.getLogger(NanoContainer.class);

    private final Map<Class, ContainerRegistration> registrations = new HashMap<Class, ContainerRegistration>();

    public <TService> ContainerRegistration register(IContainerResolver<NanoContainer, TService> resolver) { //virtual
        logger.debug(Messages.RegisteringWireupCallback, resolver.getClass().getName()/*TService*/);
        ContainerRegistration registration = new ContainerRegistration(resolver/*c => (Object) resolver.resolve(c)*/);
        //this.registrations[typeof(TService)] = registration;
        this.registrations.put(resolver.getClass(), registration);
        return registration;
    }

    public <TService> ContainerRegistration register/*<TService>*/(TService instance) { // virtual
        if (instance == null)
	    throw new IllegalArgumentException(/*"instance",*/ Messages.InstanceCannotBeNull);
        if (true/*!typeof(TService).isValueType*/ && !instance.getClass().isInterface())
	    throw new IllegalArgumentException(Messages.TypeMustBeInterface/*, "instance"*/);

        logger.debug(Messages.RegisteringServiceInstance, instance.getClass().getName());
        ContainerRegistration registration = new ContainerRegistration(instance);
        //this.registrations[typeof(TService)] = registration;
        this.registrations.put(instance.getClass(), registration);
        return registration;
    }

    public <TService> TService resolve() { // virtual
        TService service = null;
        
        logger.debug(Messages.ResolvingService, service.getClass().getName());

        ContainerRegistration registration;
        //if (this.registrations.TryGetValue(typeof(TService), out registration))
        if (this.registrations.containsKey(service.getClass()))  {
            registration = this.registrations.get(service.getClass());
            return (TService) registration.resolve(this);
        }

        logger.debug(Messages.UnableToResolve, service.getClass().getName());
        return  service; //default(TService);
    }
}
