package com.advertise;

import java.util.Random;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class InfoDetails implements InfoContributor {

	@Override
	public void contribute(Builder builder) {
		Random random = new Random();
		int totaluser = random.nextInt(1000);
		builder.withDetail("total advertise count", totaluser);
		
		
				
		
	}

}
