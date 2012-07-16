package org.es4j.eventstore.wireup;


public class Messages {

    public static String registeringWireupCallback() {
        return "Registering wireup resolver for service of type '{0}'.";
    }
    
    public static String addingWireupCallback() {
        return "Adding wireup registration callback.";
    }
    
    public static String addingWireupRegistration() {
        return "Adding wireup registration for an object instance of type '{0}'.";
    }

    public static String configuringInstancePerCall() {
        return "Registration configured to resolve a new instance per call.";
    }

    public static String resolvingInstance() {
        return "Resolving instance.";
    }
    
    public static String buildingNewInstance() {
        return "Building new instance.";
    }
    
    public static String attemptingToResolveInstance() {
        return "Attempting to resolve existing instance.";
    }
    
    public static String buildingAndStoringNewInstance() {
        return "Building (and storing) new instance for later calls.";
    }

    public static String instanceCannotBeNull() {
        return "The instance provided cannot be null.";
    }
    
    public static String typeMustBeInterface() {
        return "The type provided must be registered as an interface rather than as a concrete type, " +
               "e.g. \"container.Register&lt;IDispatchCommits&gt;(instance);";
    }
    
    public static String registeringServiceInstance() {
        return "Registering wireup instance for service of type '{0}'.";
    }
    
    public static String unableToResolve() {
        return "Unable to resolve requested instance of type '{0}'.";
    }
    
    public static String synchronousDispatcherTwoPhaseCommits() {
        return null;
    }
     
    public static String asyncDispatchSchedulerRegistered() {
        return null;
    }
    
    public static String dispatcherRegistered() {
        return null;
    }
    
    public static String eventUpconverterRegistered() {
        return null;
    }
    
}

