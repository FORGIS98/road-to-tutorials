package com.tutorial.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class ClientItemProcessor implements ItemProcessor<Client, Client> {

    private static final Logger logger = LoggerFactory.getLogger(ClientItemProcessor.class);

    @Override
    public Client process(Client client) throws Exception {
        logger.info("START Transformation");

        client.setCommercialBrand(client.getCommercialBrand().toUpperCase());
        client.setContactMediumType(client.getContactMediumType().toUpperCase());
        client.setContactMediumValue(client.getContactMediumValue().toUpperCase());
        client.setIdParty(client.getIdParty().toUpperCase());
        client.setVerified(client.getVerified().toUpperCase());

        logger.info("END Transformation");

        return client;
    }

}
