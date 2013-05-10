package edu.ycp.cs320.magicprogram.client;

import com.google.gwt.core.client.GWT;

public class RPC {
	public static final UserServiceAsync userServ =
			GWT.create(UserService.class);
}
