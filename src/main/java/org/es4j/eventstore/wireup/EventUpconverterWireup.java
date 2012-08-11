package org.es4j.eventstore.wireup;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.es4j.container.NanoContainer;
import org.es4j.container.Resolver;
import org.es4j.dotnet.Assembly;
import org.es4j.eventstore.api.conversion.UpconvertEvents;
import org.es4j.eventstore.core.conversion.Converter;
import org.es4j.eventstore.core.conversion.EventUpconverterPipelineHook;
import org.es4j.exceptions.ArgumentNullException;
import org.es4j.util.logging.ILog;
import org.es4j.util.logging.LogFactory;


public class EventUpconverterWireup extends Wireup {
    private static final ILog logger = LogFactory.buildLogger(EventUpconverterWireup.class);
    
    private final Map<Class<?>, Converter<Object,Object>> registered = new HashMap<Class<?>, Converter<Object,Object>>();
    private final List<Assembly>                       assembliesToScan = new LinkedList<Assembly>();

    public EventUpconverterWireup(Wireup wireup) {
        super(wireup);
        logger.debug(Messages.eventUpconverterRegistered());
        
        this.getContainer().register(new Resolver<EventUpconverterPipelineHook>(){

            @Override
            public EventUpconverterPipelineHook resolve(NanoContainer container) {
                if (!registered.isEmpty()) {
                    return new EventUpconverterPipelineHook(registered);
                }
                //if (!assembliesToScan.Any()) {
                //    assembliesToScan.AddRange(getAllAssemblies());
                //}
                Map<Class<?>, Converter<Object,Object>> converters = getConverters(assembliesToScan);
                return new EventUpconverterPipelineHook(converters);
            }
        });
    }
    
    private static Iterable<Assembly> getAllAssemblies() {
        List<Assembly> assemblies = new LinkedList<Assembly>();
      //return Assembly.getCallingAssembly()
      //               .getReferencedAssemblies()
      //               .select(Assembly.Load)
      //               .concat(new[] { Assembly.getCallingAssembly() });
        return assemblies;
    }
    
    private static Map<Class<?>, Converter<Object,Object>> getConverters(Iterable<Assembly> toScan) {
        Map<Class<?>, Converter<Object,Object>> converters = new HashMap<Class<?>, Converter<Object,Object>>();
        /*
        var c = from a in toScan
            from t in a.GetTypes()
                let i = t.GetInterface(typeof(IUpconvertEvents<,>).FullName)
                          where i != null
                let sourceType = i.GetGenericArguments().First()
                let convertMethod = i.GetMethods(BindingFlags.Public | BindingFlags.Instance).First()
                let instance = Activator.CreateInstance(t)
                select new KeyValuePair<Type,
                                        Func<object, object>>(sourceType, e => convertMethod.Invoke(instance, new[] { e }));
                try {
                    return c.ToDictionary(x => x.Key, x => x.Value);
                }
                catch (ArgumentException e) {
                throw new MultipleConvertersFoundException(e.Message, e);
                }
        */
        for(Assembly a : toScan) {
            for(Class t : a.getTypes()) {
                
            }
        }
        return converters;
    }

    public EventUpconverterWireup withConvertersFrom(Assembly... assemblies) {
        //logger.debug(Messages.eventUpconvertersLoadedFrom(), string.Concat(", ", assemblies));
        //this.assembliesToScan.AddRange(assemblies);
        return this;
    }
    
    public EventUpconverterWireup withConvertersFromAssemblyContaining(Class... converters) {
      //List<Assembly> assemblies = converters.Select(c => c.Assembly).Distinct();
        List<Package> assemblies = new LinkedList<Package>();
        StringBuilder concat = new StringBuilder();
        for(Class c : converters) {
            Package a = c.getPackage();
            if(!assemblies.contains(a)) {
                assemblies.add(a);
                concat.append(", ");
                concat.append(a.getName());
            }
        }
        
	logger.debug(Messages.eventUpconvertersLoadedFrom(), concat.toString());
        
        //this.assembliesToScan.AddRange(assemblies);
        for(Package a : assemblies) {
           //this.assembliesToScan.add(a); 
        }
        return this;
    }

    public <TSource, TTarget> EventUpconverterWireup addConverter(UpconvertEvents<TSource, TTarget> converter) {
        if (converter == null) {
            throw new ArgumentNullException("converter");
        }

        //this.registered[typeof(TSource)] = @event => converter.Convert(@event as TSource);
        //this.registered.put(TSource, TSource);

        return this;
    }
}





/*
abstract class Upconverter {
    public abstract Object upconvert(Object object);
}


public class EventUpconverterWireup extends Wireup {
    private static final ILog logger = LogFactory.buildLogger(EventUpconverterWireup.class);
    
    private final Map<Type, Upconverter> registered       = new HashMap<Type, Upconverter>();
    private static List<Assembly>        assembliesToScan = new LinkedList<Assembly>();

    public EventUpconverterWireup(Wireup wireup) {
        super(wireup);
        logger.debug(Messages.eventUpconverterRegistered());
        
        this.getContainer().register(new Resolver<EventUpconverterPipelineHook>() {
            @Override
            public EventUpconverterPipelineHook resolve(NanoContainer container) {
                
                if (registered.size() > 0) {
                    return new EventUpconverterPipelineHook(registered)();
                }

                if (!this.assembliesToScan.Any())
                    this.assembliesToScan.AddRange(GetAllAssemblies());

                Map<Type, Upconverter> converters = getConverters(this.assembliesToScan);
                return new EventUpconverterPipelineHook(converters);                 
            }
        });
    }
    
    private static Iterable<Assembly> getAllAssemblies() {
        return Assembly.getCallingAssembly()
                       .getReferencedAssemblies()
                       .select(Assembly.Load)
                       .cncat(new[] { Assembly.getCallingAssembly() });
    }
    
    private static Map<Type, Upconverter> getConverters(IEnumerable<Assembly> toScan) {
        
			var c = from a in toScan
					from t in a.GetTypes()
					let i = t.GetInterface(typeof(IUpconvertEvents<,>).FullName)
					where i != null
					let sourceType = i.GetGenericArguments().First()
					let convertMethod = i.GetMethods(BindingFlags.Public | BindingFlags.Instance).First()
					let instance = Activator.CreateInstance(t)
					select new KeyValuePair<Type, Func<object, object>>(
						sourceType, e => convertMethod.Invoke(instance, new[] { e }));
			try
			{
				return c.ToDictionary(x => x.Key, x => x.Value);
			}
			catch (ArgumentException e)
			{
				throw new MultipleConvertersFoundException(e.Message, e);
			}
		}

		public virtual EventUpconverterWireup WithConvertersFrom(params Assembly[] assemblies)
		{
			Logger.Debug(Messages.EventUpconvertersLoadedFrom, string.Concat(", ", assemblies));
			this.assembliesToScan.AddRange(assemblies);
			return this;
		}
		public virtual EventUpconverterWireup WithConvertersFromAssemblyContaining(params Type[] converters)
		{
			var assemblies = converters.Select(c => c.Assembly).Distinct();
			Logger.Debug(Messages.EventUpconvertersLoadedFrom, string.Concat(", ", assemblies));
			this.assembliesToScan.AddRange(assemblies);
			return this;
		}

		public virtual EventUpconverterWireup AddConverter<TSource, TTarget>(
			IUpconvertEvents<TSource, TTarget> converter)
			where TSource : class
			where TTarget : class
		{
			if (converter == null)
				throw new ArgumentNullException("converter");

			this.registered[typeof(TSource)] = @event => converter.Convert(@event as TSource);

			return this;
		}
	}
}
*/