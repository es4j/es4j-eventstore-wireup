package com.lingona.es4j.wireup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ContainerRegistration {

    private static final Logger logger = LoggerFactory.getLogger(ContainerRegistration.class);
    
    private final IContainerResolver<NanoContainer, Object> resolver;
    
    private Object  instance;
    private boolean instancePerCall;

    public ContainerRegistration(IContainerResolver<NanoContainer, Object> resolver) {
        logger.info/*verbose*/(Messages.AddingWireupCallback);
        this.resolver = resolver;
    }

    public ContainerRegistration(Object instance) {
        logger.info/*verbose*/(Messages.AddingWireupRegistration, instance.getClass().getName());
        this.instance = instance;
        this.resolver = null;
    }

    public ContainerRegistration instancePerCall() { // virutal
        logger.info/*verbose*/(Messages.ConfiguringInstancePerCall);
        this.instancePerCall = true;
        return this;
    }

    public Object resolve(NanoContainer container) { //virtual
        logger.info/*verbose*/(Messages.ResolvingInstance);
        if (this.instancePerCall) {
            logger.info/*verbose*/(Messages.BuildingNewInstance);
            return this.resolver.resolve(container);
        }

        logger.info/*verbose*/(Messages.AttemptingToResolveInstance);

        if (this.instance != null) {
            return this.instance;
        }

        logger.info/*verbose*/(Messages.BuildingAndStoringNewInstance);
        this.instance = this.resolver.resolve(container);
        return this.instance;
    }
}