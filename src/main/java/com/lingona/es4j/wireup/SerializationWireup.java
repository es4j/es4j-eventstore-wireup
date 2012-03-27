package com.lingona.eventstore.joliver.wireup;

import com.lingona.eventstore.joliver.api.Serialization.ISerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//using Serialization;

public class SerializationWireup extends Wireup {

    private static final Logger logger = LoggerFactory.getLogger(SerializationWireup.class);

    public SerializationWireup(Wireup inner, ISerialize serializer) {
        super(inner);
        this.container.register(serializer);
    }

    public SerializationWireup compress() {
        logger.debug(Messages.ConfiguringCompression);
        ISerialize wrapped = this.container.resolve();

        logger.debug(Messages.WrappingSerializerGZip, wrapped.getClass().getName());
        this.container.register < ISerialize > (new GzipSerializer(wrapped));
        return this;
    }

    public SerializationWireup encryptWith(byte[] encryptionKey) {
        Logger.Debug(Messages.ConfiguringEncryption);
        ISerialize wrapped = this.container.resolve();

        logger.debug(Messages.WrappingSerializerEncryption, wrapped.getClass().getName());
        this.container.register<ISerialize>(new RijndaelSerializer(wrapped, encryptionKey));
        return this;
    }
}