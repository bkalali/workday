package com.appnovation.salesforce.transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.streaming.ConsumerIterator;
import org.mule.transformer.AbstractMessageTransformer;

public class SalesForceToPojoTransformer extends AbstractMessageTransformer {

	@Override
	public Object transformMessage(MuleMessage message, String outputEncoding)
			throws TransformerException {
		ConsumerIterator consumerIterator = (ConsumerIterator) message
				.getPayload();
		List<Contact> contacts = new ArrayList<Contact>();
		while (consumerIterator.hasNext()) {
			Contact contact = new Contact();
			HashMap contactMap = (HashMap) consumerIterator.next();
			contact.setEmail((String)contactMap.get("Email"));
			contact.setFirstName((String)contactMap.get("FirstName"));
			contact.setLastName((String)contactMap.get("LastName"));
			contacts.add(contact);
		}
		return contacts;
	}

}
