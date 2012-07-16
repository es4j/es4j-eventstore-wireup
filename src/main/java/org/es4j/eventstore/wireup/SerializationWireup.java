package org.es4j.eventstore.wireup;

import com.lingona.eventstore.joliver.api.Serialization.ISerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//using Serialization;

public class SerializationWireup extends Wireup_2 {

    private static final Logger logger = LoggerFactory.getLogger(SerializationWireup.class);

    public SerializationWireup(Wireup_2 inner, ISerialize serializer) {
        super(inner);
        this.container.register(serializer);
    }

    public SerializationWireup compress() {
        logger.debug(Messages_2.ConfiguringCompression);
        ISerialize wrapped = this.container.resolve();

        logger.debug(Messages_2.WrappingSerializerGZip, wrapped.getClass().getName());
        this.container.register < ISerialize > (new GzipSerializer(wrapped));
        return this;
    }

    public SerializationWireup encryptWith(byte[] encryptionKey) {
        Logger.Debug(Messages_2.ConfiguringEncryption);
        ISerialize wrapped = this.container.resolve();

        logger.debug(Messages_2.WrappingSerializerEncryption, wrapped.getClass().getName());
        this.container.register<ISerialize>(new RijndaelSerializer(wrapped, encryptionKey));
        return this;
    }
}