package org.q10viking.five;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ContentGetter {

	public static void main(String[] args) {
		try {
			URL u = new URL("http://www.cafeaulait.org/course/week9/spacemusic.au");
			System.out.println(u.getProtocol()+" "+u.getAuthority());
			Object o = u.getContent();
			System.out.println("I got a "+o.getClass().getName());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
/*
I got a sun.applet.AppletAudioClip*/