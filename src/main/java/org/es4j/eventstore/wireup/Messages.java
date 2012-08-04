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
    
    public static String eventUpconvertersLoadedFrom() {
        return null;
    }
    
    public static String syncDispatchSchedulerRegistered() {
        return null;
    }

    public static String configuringCompression() {
        return null;
    }
    
    public static String configuringEnginePerformanceTracking() {
        return null;
    }
        
    static final String AsyncDispatchSchedulerRegistered = "";
    static final String DispatcherRegistered = "";
    static final String EventUpconverterRegistered = "";
    static final String registeringWireupCallback = "";
    static final String ConfiguringInstancePerCall = "";
    static final String ResolvingInstance = "";
    static final String BuildingNewInstance = "";
    static final String AttemptingToResolveInstance = "";
    static final String BuildingAndStoringNewInstance = "";
    static final String AddingWireupRegistration = "";
    static final String AddingWireupCallback = "";
    static final String RegisteringWireupCallback = "";
    static final String InstanceCannotBeNull = "";
    static final String TypeMustBeInterface = "";
    static final String RegisteringServiceInstance = "";
    static final String ResolvingService = "";
    static final String UnableToResolve = "";
   
    static final String SynchronousDispatcherTwoPhaseCommits = "";
    static final String RegisteringPersistenceEngine = "";
    static final String ConfiguringEngineInitialization = "";
    static final String ConfiguringEngineEnlistment = "";
    static final String BuildingEngine = "";
    static final String InitializingEngine = "";
    static final String ConfiguringCompression = "";
    static final String WrappingSerializerGZip = "";
    static final String WrappingSerializerEncryption = "";
    static final String ConfiguringEncryption = "";
    static final String ConnectionFactorySpecified = "";
    static final String AutoDetectDialect = "";
    static final String DialectSpecified = "";
    static final String PagingSpecified = "";
    static final String SyncDispatchSchedulerRegistered = "";    
}

/*
package org.es4j.eventstore.wireup;

//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.237
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------
/*
namespace EventStore {
    using System;
    
    
    /// <summary>
    ///   A strongly-typed resource class, for looking up localized strings, etc.
    /// </summary>
    // This class was auto-generated by the StronglyTypedResourceBuilder
    // class via a tool like ResGen or Visual Studio.
    // To add or remove a member, edit your .ResX file then rerun ResGen
    // with the /str option, or rebuild your VS project.
    [global::System.CodeDom.Compiler.GeneratedCodeAttribute("System.Resources.Tools.StronglyTypedResourceBuilder", "4.0.0.0")]
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
    [global::System.Runtime.CompilerServices.CompilerGeneratedAttribute()]
    internal class Messages {
        
        private static global::System.Resources.ResourceManager resourceMan;
        
        private static global::System.Globalization.CultureInfo resourceCulture;
        
        [global::System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1811:AvoidUncalledPrivateCode")]
        internal Messages() {
        }
        
        /// <summary>
        ///   Returns the cached ResourceManager instance used by this class.
        /// </summary>
        [global::System.ComponentModel.EditorBrowsableAttribute(global::System.ComponentModel.EditorBrowsableState.Advanced)]
        internal static global::System.Resources.ResourceManager ResourceManager {
            get {
                if (object.ReferenceEquals(resourceMan, null)) {
                    global::System.Resources.ResourceManager temp = new global::System.Resources.ResourceManager("EventStore.Messages", typeof(Messages).Assembly);
                    resourceMan = temp;
                }
                return resourceMan;
            }
        }
        
        /// <summary>
        ///   Overrides the current thread's CurrentUICulture property for all
        ///   resource lookups using this strongly typed resource class.
        /// </summary>
        [global::System.ComponentModel.EditorBrowsableAttribute(global::System.ComponentModel.EditorBrowsableState.Advanced)]
        internal static global::System.Globalization.CultureInfo Culture {
            get {
                return resourceCulture;
            }
            set {
                resourceCulture = value;
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Adding wireup registration callback..
        /// </summary>
        internal static string AddingWireupCallback {
            get {
                return ResourceManager.GetString("AddingWireupCallback", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Adding wireup registration for an object instance of type &apos;{0}&apos;..
        /// </summary>
        internal static string AddingWireupRegistration {
            get {
                return ResourceManager.GetString("AddingWireupRegistration", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Configuring the store to dispatch messages asynchronously..
        /// </summary>
        internal static string AsyncDispatchSchedulerRegistered {
            get {
                return ResourceManager.GetString("AsyncDispatchSchedulerRegistered", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Attempting to resolve existing instance..
        /// </summary>
        internal static string AttemptingToResolveInstance {
            get {
                return ResourceManager.GetString("AttemptingToResolveInstance", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Configuring SQL engine to auto-detect dialect..
        /// </summary>
        internal static string AutoDetectDialect {
            get {
                return ResourceManager.GetString("AutoDetectDialect", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Building (and storing) new instance for later calls..
        /// </summary>
        internal static string BuildingAndStoringNewInstance {
            get {
                return ResourceManager.GetString("BuildingAndStoringNewInstance", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Building the persistence engine..
        /// </summary>
        internal static string BuildingEngine {
            get {
                return ResourceManager.GetString("BuildingEngine", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Building new instance..
        /// </summary>
        internal static string BuildingNewInstance {
            get {
                return ResourceManager.GetString("BuildingNewInstance", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Configuring serializer to compress the serialized payload..
        /// </summary>
        internal static string ConfiguringCompression {
            get {
                return ResourceManager.GetString("ConfiguringCompression", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Configuring serializer to encrypt the serialized payload..
        /// </summary>
        internal static string ConfiguringEncryption {
            get {
                return ResourceManager.GetString("ConfiguringEncryption", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Configuring persistence engine to enlist in ambient transactions using TransactionScope..
        /// </summary>
        internal static string ConfiguringEngineEnlistment {
            get {
                return ResourceManager.GetString("ConfiguringEngineEnlistment", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Configuring persistence engine to initialize..
        /// </summary>
        internal static string ConfiguringEngineInitialization {
            get {
                return ResourceManager.GetString("ConfiguringEngineInitialization", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Registration configured to resolve a new instance per call..
        /// </summary>
        internal static string ConfiguringInstancePerCall {
            get {
                return ResourceManager.GetString("ConfiguringInstancePerCall", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Using SQL connection factory of type &apos;{0}&apos;..
        /// </summary>
        internal static string ConnectionFactorySpecified {
            get {
                return ResourceManager.GetString("ConnectionFactorySpecified", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Registering SQL dialect of type &apos;{0}&apos;..
        /// </summary>
        internal static string DialectSpecified {
            get {
                return ResourceManager.GetString("DialectSpecified", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Registering dispatcher of type &apos;{0}&apos;..
        /// </summary>
        internal static string DispatcherRegistered {
            get {
                return ResourceManager.GetString("DispatcherRegistered", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Configuring the store to upconvert events when fetched..
        /// </summary>
        internal static string EventUpconverterRegistered {
            get {
                return ResourceManager.GetString("EventUpconverterRegistered", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Will scan for event upconverters from the following assemblies: &apos;{0}&apos;.
        /// </summary>
        internal static string EventUpconvertersLoadedFrom {
            get {
                return ResourceManager.GetString("EventUpconvertersLoadedFrom", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Initializing the configured persistence engine..
        /// </summary>
        internal static string InitializingEngine {
            get {
                return ResourceManager.GetString("InitializingEngine", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to The instance provided cannot be null..
        /// </summary>
        internal static string InstanceCannotBeNull {
            get {
                return ResourceManager.GetString("InstanceCannotBeNull", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Persistence engine configured to page every &apos;{0}&apos; records..
        /// </summary>
        internal static string PagingSpecified {
            get {
                return ResourceManager.GetString("PagingSpecified", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Registering persistence engine of type &apos;{0}&apos;..
        /// </summary>
        internal static string RegisteringPersistenceEngine {
            get {
                return ResourceManager.GetString("RegisteringPersistenceEngine", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Registering wireup instance for service of type &apos;{0}&apos;..
        /// </summary>
        internal static string RegisteringServiceInstance {
            get {
                return ResourceManager.GetString("RegisteringServiceInstance", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Registering wireup resolver for service of type &apos;{0}&apos;..
        /// </summary>
        internal static string RegisteringWireupCallback {
            get {
                return ResourceManager.GetString("RegisteringWireupCallback", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Resolving instance..
        /// </summary>
        internal static string ResolvingInstance {
            get {
                return ResourceManager.GetString("ResolvingInstance", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Attempting to resolve instance for service of type &apos;{0}&apos;..
        /// </summary>
        internal static string ResolvingService {
            get {
                return ResourceManager.GetString("ResolvingService", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Configuring the store to dispatch messages synchronously..
        /// </summary>
        internal static string SyncDispatchSchedulerRegistered {
            get {
                return ResourceManager.GetString("SyncDispatchSchedulerRegistered", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Only the synchronous dispatcher can enlist in two-phase commits..
        /// </summary>
        internal static string SynchronousDispatcherTwoPhaseCommits {
            get {
                return ResourceManager.GetString("SynchronousDispatcherTwoPhaseCommits", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to The type provided must be registered as an interface rather than as a concrete type, e.g. &quot;container.Register&lt;IDispatchCommits&gt;(instance);&quot;..
        /// </summary>
        internal static string TypeMustBeInterface {
            get {
                return ResourceManager.GetString("TypeMustBeInterface", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Unable to resolve requested instance of type &apos;{0}&apos;..
        /// </summary>
        internal static string UnableToResolve {
            get {
                return ResourceManager.GetString("UnableToResolve", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Wrapping serializer of type &apos;{0}&apos; in RijndaelSerializer..
        /// </summary>
        internal static string WrappingSerializerEncryption {
            get {
                return ResourceManager.GetString("WrappingSerializerEncryption", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Wrapping serializer of type &apos;{0}&apos; in GZipSerializer..
        /// </summary>
        internal static string WrappingSerializerGZip {
            get {
                return ResourceManager.GetString("WrappingSerializerGZip", resourceCulture);
            }
        }
    }
}
*/



/*
<root>
  <!--
    Microsoft ResX Schema

    Version 2.0

    The primary goals of this format is to allow a simple XML format
    that is mostly human readable. The generation and parsing of the
    various data types are done through the TypeConverter classes
    associated with the data types.

    Example:

    ... ado.net/XML headers & schema ...
    <resheader name="resmimetype">text/microsoft-resx</resheader>
    <resheader name="version">2.0</resheader>
    <resheader name="reader">System.Resources.ResXResourceReader, System.Windows.Forms, ...</resheader>
    <resheader name="writer">System.Resources.ResXResourceWriter, System.Windows.Forms, ...</resheader>
    <data name="Name1"><value>this is my long string</value><comment>this is a comment</comment></data>
    <data name="Color1" type="System.Drawing.Color, System.Drawing">Blue</data>
    <data name="Bitmap1" mimetype="application/x-microsoft.net.object.binary.base64">
        <value>[base64 mime encoded serialized .NET Framework object]</value>
    </data>
    <data name="Icon1" type="System.Drawing.Icon, System.Drawing" mimetype="application/x-microsoft.net.object.bytearray.base64">
        <value>[base64 mime encoded string representing a byte array form of the .NET Framework object]</value>
        <comment>This is a comment</comment>
    </data>

    There are any number of "resheader" rows that contain simple
    name/value pairs.

    Each data row contains a name, and value. The row also contains a
    type or mimetype. Type corresponds to a .NET class that support
    text/value conversion through the TypeConverter architecture.
    Classes that don't support this are serialized and stored with the
    mimetype set.

    The mimetype is used for serialized objects, and tells the
    ResXResourceReader how to depersist the object. This is currently not
    extensible. For a given mimetype the value must be set accordingly:

    Note - application/x-microsoft.net.object.binary.base64 is the format
    that the ResXResourceWriter will generate, however the reader can
    read any of the formats listed below.

    mimetype: application/x-microsoft.net.object.binary.base64
    value   : The object must be serialized with
            : System.Runtime.Serialization.Formatters.Binary.BinaryFormatter
            : and then encoded with base64 encoding.

    mimetype: application/x-microsoft.net.object.soap.base64
    value   : The object must be serialized with
            : System.Runtime.Serialization.Formatters.Soap.SoapFormatter
            : and then encoded with base64 encoding.

    mimetype: application/x-microsoft.net.object.bytearray.base64
    value   : The object must be serialized into a byte array
            : using a System.ComponentModel.TypeConverter
            : and then encoded with base64 encoding.
    -->
  <xsd:schema id="root" xmlns="" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:msdata="urn:schemas-microsoft-com:xml-msdata">
    <xsd:import namespace="http://www.w3.org/XML/1998/namespace" />
    <xsd:element name="root" msdata:IsDataSet="true">
      <xsd:complexType>
        <xsd:choice maxOccurs="unbounded">
          <xsd:element name="metadata">
            <xsd:complexType>
              <xsd:sequence>
                <xsd:element name="value" type="xsd:string" minOccurs="0" />
              </xsd:sequence>
              <xsd:attribute name="name" use="required" type="xsd:string" />
              <xsd:attribute name="type" type="xsd:string" />
              <xsd:attribute name="mimetype" type="xsd:string" />
              <xsd:attribute ref="xml:space" />
            </xsd:complexType>
          </xsd:element>
          <xsd:element name="assembly">
            <xsd:complexType>
              <xsd:attribute name="alias" type="xsd:string" />
              <xsd:attribute name="name" type="xsd:string" />
            </xsd:complexType>
          </xsd:element>
          <xsd:element name="data">
            <xsd:complexType>
              <xsd:sequence>
                <xsd:element name="value" type="xsd:string" minOccurs="0" msdata:Ordinal="1" />
                <xsd:element name="comment" type="xsd:string" minOccurs="0" msdata:Ordinal="2" />
              </xsd:sequence>
              <xsd:attribute name="name" type="xsd:string" use="required" msdata:Ordinal="1" />
              <xsd:attribute name="type" type="xsd:string" msdata:Ordinal="3" />
              <xsd:attribute name="mimetype" type="xsd:string" msdata:Ordinal="4" />
              <xsd:attribute ref="xml:space" />
            </xsd:complexType>
          </xsd:element>
          <xsd:element name="resheader">
            <xsd:complexType>
              <xsd:sequence>
                <xsd:element name="value" type="xsd:string" minOccurs="0" msdata:Ordinal="1" />
              </xsd:sequence>
              <xsd:attribute name="name" type="xsd:string" use="required" />
            </xsd:complexType>
          </xsd:element>
        </xsd:choice>
      </xsd:complexType>
    </xsd:element>
  </xsd:schema>
  <resheader name="resmimetype">
    <value>text/microsoft-resx</value>
  </resheader>
  <resheader name="version">
    <value>2.0</value>
  </resheader>
  <resheader name="reader">
    <value>System.Resources.ResXResourceReader, System.Windows.Forms, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089</value>
  </resheader>
  <resheader name="writer">
    <value>System.Resources.ResXResourceWriter, System.Windows.Forms, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089</value>
  </resheader>
  <data name="InstanceCannotBeNull" xml:space="preserve">
    <value>The instance provided cannot be null.</value>
  </data>
  <data name="TypeMustBeInterface" xml:space="preserve">
    <value>The type provided must be registered as an interface rather than as a concrete type, e.g. "container.Register&lt;IDispatchCommits&gt;(instance);".</value>
  </data>
  <data name="AsyncDispatchSchedulerRegistered" xml:space="preserve">
    <value>Configuring the store to dispatch messages asynchronously.</value>
  </data>
  <data name="DispatcherRegistered" xml:space="preserve">
    <value>Registering dispatcher of type '{0}'.</value>
  </data>
  <data name="AddingWireupRegistration" xml:space="preserve">
    <value>Adding wireup registration for an object instance of type '{0}'.</value>
  </data>
  <data name="AddingWireupCallback" xml:space="preserve">
    <value>Adding wireup registration callback.</value>
  </data>
  <data name="ConfiguringInstancePerCall" xml:space="preserve">
    <value>Registration configured to resolve a new instance per call.</value>
  </data>
  <data name="ResolvingInstance" xml:space="preserve">
    <value>Resolving instance.</value>
  </data>
  <data name="BuildingNewInstance" xml:space="preserve">
    <value>Building new instance.</value>
  </data>
  <data name="AttemptingToResolveInstance" xml:space="preserve">
    <value>Attempting to resolve existing instance.</value>
  </data>
  <data name="BuildingAndStoringNewInstance" xml:space="preserve">
    <value>Building (and storing) new instance for later calls.</value>
  </data>
  <data name="RegisteringWireupCallback" xml:space="preserve">
    <value>Registering wireup resolver for service of type '{0}'.</value>
  </data>
  <data name="RegisteringServiceInstance" xml:space="preserve">
    <value>Registering wireup instance for service of type '{0}'.</value>
  </data>
  <data name="ResolvingService" xml:space="preserve">
    <value>Attempting to resolve instance for service of type '{0}'.</value>
  </data>
  <data name="UnableToResolve" xml:space="preserve">
    <value>Unable to resolve requested instance of type '{0}'.</value>
  </data>
  <data name="RegisteringPersistenceEngine" xml:space="preserve">
    <value>Registering persistence engine of type '{0}'.</value>
  </data>
  <data name="ConfiguringEngineInitialization" xml:space="preserve">
    <value>Configuring persistence engine to initialize.</value>
  </data>
  <data name="ConfiguringEngineEnlistment" xml:space="preserve">
    <value>Configuring persistence engine to enlist in ambient transactions using TransactionScope.</value>
  </data>
  <data name="BuildingEngine" xml:space="preserve">
    <value>Building the persistence engine.</value>
  </data>
  <data name="InitializingEngine" xml:space="preserve">
    <value>Initializing the configured persistence engine.</value>
  </data>
  <data name="ConfiguringCompression" xml:space="preserve">
    <value>Configuring serializer to compress the serialized payload.</value>
  </data>
  <data name="WrappingSerializerGZip" xml:space="preserve">
    <value>Wrapping serializer of type '{0}' in GZipSerializer.</value>
  </data>
  <data name="ConfiguringEncryption" xml:space="preserve">
    <value>Configuring serializer to encrypt the serialized payload.</value>
  </data>
  <data name="WrappingSerializerEncryption" xml:space="preserve">
    <value>Wrapping serializer of type '{0}' in RijndaelSerializer.</value>
  </data>
  <data name="AutoDetectDialect" xml:space="preserve">
    <value>Configuring SQL engine to auto-detect dialect.</value>
  </data>
  <data name="PagingSpecified" xml:space="preserve">
    <value>Persistence engine configured to page every '{0}' records.</value>
  </data>
  <data name="DialectSpecified" xml:space="preserve">
    <value>Registering SQL dialect of type '{0}'.</value>
  </data>
  <data name="ConnectionFactorySpecified" xml:space="preserve">
    <value>Using SQL connection factory of type '{0}'.</value>
  </data>
  <data name="SyncDispatchSchedulerRegistered" xml:space="preserve">
    <value>Configuring the store to dispatch messages synchronously.</value>
  </data>
  <data name="SynchronousDispatcherTwoPhaseCommits" xml:space="preserve">
    <value>Only the synchronous dispatcher can enlist in two-phase commits.</value>
  </data>
  <data name="EventUpconverterRegistered" xml:space="preserve">
    <value>Configuring the store to upconvert events when fetched.</value>
  </data>
  <data name="EventUpconvertersLoadedFrom" xml:space="preserve">
    <value>Will scan for event upconverters from the following assemblies: '{0}'</value>
  </data>
</root>
*/

