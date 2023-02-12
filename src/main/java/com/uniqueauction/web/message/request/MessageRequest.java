package com.uniqueauction.web.message.request;

import lombok.Data;

@Data
public class MessageRequest {
	private String address;
	private String title;
	private String content;
}
