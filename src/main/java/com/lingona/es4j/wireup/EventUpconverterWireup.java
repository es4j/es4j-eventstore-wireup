package com.lingona.eventstore.joliver.wireup;

import com.lingona.eventstore.joliver.core.Conversion.IConverter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//using System.Collections.Generic;
//using System.Linq;
//using System.Reflection;
//using Conversion;
//using Logging;
public class EventUpconverterWireup extends Wireup {

    private static final Logger logger = LoggerFactory.getLogger(EventUpconverterWireup.class);
    private final Map<Class, IConverter<Object, Object>> registered = new HashMap<Class, IConverter<Object, Object>>();
    private final List<Assembly> assembliesToScan = new LinkedList<Assembly>();

    public EventUpconverterWireup(Wireup wireup) {

        super(wireup);
        logger.debug(Messages.EventUpconverterRegistered);
/*
        this.container.register(c => {
            if (this.registered.Count > 0) {
                return new EventUpconverterPipelineHook(this.registered);
            }

            if (!this.assembliesToScan.Any()) {
                this.assembliesToScan.AddRange(GetAllAssemblies());
            }

            var converters = GetConverters(this.assembliesToScan);
            return new EventUpconverterPipelineHook(converters);
        }
    );
		}
		private static IEnumerable<Assembly> GetAllAssemblies() {
        return Assembly.GetCallingAssembly().GetReferencedAssemblies().Select(Assembly.Load).Concat(new []{Assembly.GetCallingAssembly()});
    */
    }

    private static Map<Class, IConverter<Object, Object>> getConverters(Iterable<Assembly> toScan) {
        /*
        var c = from a in toScan from t in a.GetTypes()
					let i = t.GetInterface(typeof(IUpconvertEvents <,  >).FullName)
					where i != null
					let sourceType = i.GetGenericArguments().First()
					let convertMethod = i.GetMethods(BindingFlags.Public | BindingFlags.Instance).First()
					let instance = Activator.CreateInstance(t)
					select new KeyValuePair<Type, Func<object, object>>
        (
						sourceType
        , e =  > convertMethod.Invoke(instance, new []{e})
        );
			try {
            return c.ToDictionary(x =  > x.Key, x =  > x.Value);
        } catch (ArgumentException e) {
            throw new MultipleConvertersFoundException(e.Message, e);
        }
        */
        return null;
    }


    public EventUpconverterWireup withConvertersFrom(Assembly... assemblies) {
        /*
        logger.debug(Messages.EventUpconvertersLoadedFrom, String.concat(", ", assemblies));
        this.assembliesToScan.addRange(assemblies);
        */
        return this;
    }

    public EventUpconverterWireup WithConvertersFromAssemblyContaining(Class... converters) { // virtual
        /*
        var assemblies = converters.Select(c =  > c.Assembly).Distinct();
        Logger.Debug(Messages.EventUpconvertersLoadedFrom, string.Concat(", ", assemblies));
        this.assembliesToScan.addRange(assemblies);
        */
        return this;
    }
    /*
    public EventUpconverterWireup addConverter<TSource, TTarget> (IUpconvertEvents<TSource, TTarget> converter)
        //where TSource : class
        //where TTarget : class
    { // virtual
        if (converter == null)
            throw new ArgumentNullException("converter");

			this.registered [typeof(TSource)
            ] = @event
              => converter.Convert(
            @event
            as TSource
            );

return this;
    }
    */



    public EventUpconverterWireup usingEventUpconversion() {
        return new EventUpconverterWireup(this);
    }
}