package org.es4j.eventstore.wireup;

//using Serialization;

import org.es4j.logging.api.ILog;
import org.es4j.logging.api.LogFactory;
import org.es4j.serialization.GzipSerializer;
import org.es4j.serialization.RijndaelSerializer;
import org.es4j.serialization.api.ISerialize;


public class SerializationWireup extends Wireup {

    private static final ILog logger = LogFactory.buildLogger(SerializationWireup.class);

    public SerializationWireup(Wireup inner, ISerialize serializer) {
        super(inner);
        this.getContainer().register(serializer);
    }

    public SerializationWireup compress() {
        logger.debug(Messages.configuringCompression());
        ISerialize wrapped = this.getContainer().resolve(ISerialize.class);

        logger.debug(Messages.WrappingSerializerGZip, wrapped.getClass().getName());
        this.getContainer().register((ISerialize)new GzipSerializer(wrapped));
        return this;
    }

    public SerializationWireup encryptWith(byte[] encryptionKey) {
        logger.debug(Messages.ConfiguringEncryption);
        ISerialize wrapped = this.getContainer().resolve(ISerialize.class);

        logger.debug(Messages.WrappingSerializerEncryption, wrapped.getClass().getName());
        this.getContainer().register((ISerialize)new RijndaelSerializer(wrapped, encryptionKey));
        return this;
    }
}
